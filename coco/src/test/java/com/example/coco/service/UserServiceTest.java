package com.example.coco.service;

import com.example.coco.dao.UserDAO;
import com.example.coco.dto.EditRequest;
import com.example.coco.dto.FriendRequest;
import com.example.coco.dto.SkillRequest;
import com.example.coco.models.Skill;
import com.example.coco.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class UserServiceTest extends MockitoExtension {


    static UserService unitUnderTest;
    static UserDAO userDAO;

    static User testUser;
    static User testUser2;
    static User testUser3;


    static PrincipalUser principalUserTest;
    static Skill skill;
    static SkillRequest skillRequest;
    static EditRequest editRequest;
    static FriendRequest friendRequest;
    static List<User> userList;




    @BeforeAll
    static void init(){


        userDAO = Mockito.mock(UserDAO.class);
        unitUnderTest = new UserService(userDAO);

        testUser = new User("William","UserWill","userWill@gmail.com","12345");
        testUser.setId(1L);

        principalUserTest = new PrincipalUser("William","UserWill","userWill@gmail.com","12345");

        skill = new Skill();
        skill.setId(1);
        skill.setName("Java");

        skillRequest = new SkillRequest();
        skillRequest.setSkillId(1);
        skillRequest.setUserId(testUser.getId());

        testUser2 = new User("Lisa","UserLisa","userLisa@gmail.com","12345");
        testUser2.setId(2L);
        testUser2.getSkills().add(skill);


        testUser3 = new User("Rossie","UserRossie","userRossie@gmail.com","12345");
        testUser3.setId(3L);

        testUser2.getFriendList().add(testUser3);


        userList = new ArrayList<>();
        userList.add(testUser);
        userList.add(testUser2);
        userList.add(testUser3);

        editRequest = new EditRequest();
        editRequest.setUserId(1);
        editRequest.setEditCase(1);

        friendRequest = new FriendRequest();



    }




    @Test
    void loadUserByUsername() {
        //Setup
        Mockito.when(userDAO.findByUsername("UserWill")).thenReturn(Optional.ofNullable(testUser));

        //Test
        PrincipalUser actualUser = (PrincipalUser) unitUnderTest.loadUserByUsername("UserWill");


        //Verify
        assertEquals("UserWill",actualUser.getUsername());

    }

    @Test
    void getUserFirstName() {
        //Setup
        Mockito.when(userDAO.findUserById(1L)).thenReturn(Optional.ofNullable(testUser));

        //Test
        Optional<User> actualUser = unitUnderTest.getUserFirstName(testUser);
        if (actualUser.isPresent()){
            User user = actualUser.get();


            //Verify
            assertEquals("UserWill",user.getUsername());
        }

    }

    @Test
    void getCurrentUser() {
       // Get current login user by using SecurityContextHolder.getContext.getAuthentication.getPrincipal();
    }


    @Test
    void addSkillsToUSer() {
        //Setup
        Mockito.when(userDAO.getSkillById(skillRequest.getSkillId())).thenReturn(skill);
        Mockito.when(userDAO.findCurrentUserById(skillRequest.getUserId())).thenReturn(testUser);

        //Test
        String message =  unitUnderTest.addSkillsToUSer(skillRequest);

        //Verify
        assertEquals("Skill added",message);


    }

    @Test
    void removeUserSkill() {

        //Setup
        Mockito.when(userDAO.getSkillById(skillRequest.getSkillId())).thenReturn(skill);
        Mockito.when(userDAO.findCurrentUserById(skillRequest.getUserId())).thenReturn(testUser);

        //Test

        String message = unitUnderTest.removeUserSkill(skillRequest);

        //Verify
        assertEquals("Skill removed",message);
    }

    @Test
    void findAllUserBySkills() {
        List<User> userList2 = new ArrayList<>();

        //Setup
        Mockito.when(userDAO.getAllUsers()).thenReturn(userList);
        Mockito.when(userDAO.getSkillById(skill.getId())).thenReturn(skill);

        userList2.add(testUser2);

        //Test
        List<User> testUserList = unitUnderTest.findAllUserBySkills(skill.getId());

        //Verify
        assertEquals(testUserList,userList2);

    }

    @Test
    void editUser() {

        //Setup
        Mockito.when(userDAO.findCurrentUserById(editRequest.getUserId())).thenReturn(testUser2);


        //Test
        String message = unitUnderTest.editUser(editRequest);

        //Verify
        assertEquals("Edit success",message);




    }

    @Test
    void getAllUser() {
        //Setup
        Mockito.when(userDAO.getAllUsers()).thenReturn(userList);

        //Test
        List <User> userListTest = unitUnderTest.getAllUser();

        //Verify
        assertEquals(userListTest,userList);



    }

    @Test
    void addFriend() {
        //Setup
        Mockito.when(userDAO.findCurrentUserById(friendRequest.getUserId())).thenReturn(testUser2);
        Mockito.when(userDAO.findCurrentUserById(friendRequest.getFriendId())).thenReturn(testUser3);


        //Test
        String message = unitUnderTest.addFriend(friendRequest);

        //Verify
        assertEquals("User added",message);

    }

    @Test
    void getFriendList() {


        //Setup
        Mockito.when(userDAO.findCurrentUserById(1)).thenReturn(testUser);

        //Test
        List<User> userListTest = unitUnderTest.getFriendList(testUser.getId());

        //Verify
        assertEquals(userListTest,testUser.getFriendList());


    }

    @Test
    void unfriend() {

        //Setup

        Mockito.when(userDAO.findCurrentUserById(friendRequest.getUserId())).thenReturn(testUser);
        Mockito.when(userDAO.findCurrentUserById(friendRequest.getFriendId())).thenReturn(testUser2);

        //Test
        String message = unitUnderTest.unfriend(friendRequest);

        //Verify
        assertEquals("Not removed",message);

    }
}