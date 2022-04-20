package com.example.coco.service;

import com.example.coco.dao.UserDAO;
import com.example.coco.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest extends MockitoExtension {

    static UserService unitUnderTest;
    static UserDAO userDAO;
    static Interest testInterest;
    static List<Interest> testInterests;
    static Skill testSkill1, testSkill2;
    static List<Skill> testSkills;
    static User testUser1, testUser2;
    static List<User> testUsers;
    static Location testLocation;
    static List<Location> testLocations;
    static SearchType testSearchType;
    static List<SearchType> testSearchTypes;
    static Search testSearch;
    static List<Search> testSearches;

    @BeforeAll
    public static void init() {
        userDAO = Mockito.mock(UserDAO.class);

        unitUnderTest = new UserService(userDAO);

        testInterest = new Interest();
        testInterest.setId((long) 1);
        testInterest.setName("Testing");
        testInterest.setDescription("I like testing.");
        testInterests = new ArrayList<>();
        testInterests.add(testInterest);

        testSkill1 = new Skill();
        testSkill1.setId((long) 1);
        testSkill1.setName("Testing");
        testSkill1.setDescription("I am good at testing.");
        testSkill2 = new Skill();
        testSkill2.setId((long) 2);
        testSkill2.setName("Debugging");
        testSkill2.setDescription("I am good at debugging.");
        testSkills = new ArrayList<>();
        testSkills.add(testSkill1);

        testLocation = new Location();
        testLocation.setId(1);
        testLocation.setName("Testeristan");
        testLocations = new ArrayList<>();
        testLocations.add(testLocation);

        testSearchType = new SearchType();
        testSearchType.setId(1);
        testSearchType.setName("Test");
        testSearchType.setDescription("We might want to test hanging out.");
        testSearchTypes = new ArrayList<>();
        testSearchTypes.add(testSearchType);

        testUser1 = new User("Tester1", "test1@test.test", "password");
        testUser1.setId((long) 1);
        testUser1.setSkills(testSkills);
        testUser1.setInterests(testInterests);
        testUser1.setLocation(testLocation);
        testUser1.setOpenForSearchType(testSearchTypes);
        testUsers = new ArrayList<>();
        testUsers.add(testUser1);

        testUser2 = new User("Tester2", "test2@test.test", "password");
        testUser2.setId((long) 1);
        testUser2.setSkills(testSkills);
        testUser2.setInterests(testInterests);
        testUser2.setLocation(testLocation);
        testUser2.setOpenForSearchType(testSearchTypes);
        testUsers.add(testUser2);

        testSearch = new Search();
        testSearch.setSearchId((long) 1);
        testSearch.setUserId((long) 1);
        testSearch.setSearchTypeId((long) 1);
        testSearch.setSearchSkills(testSkills);
        testSearch.setSearchInterests(testInterests);
        testSearch.setSearchPresentation("I am looking for someone to run tests with!");
        testSearch.setLocationId((long) 1);
        testSearches = new ArrayList<>();
        testSearches.add(testSearch);

    }

    @Test
    void addInterest() {
        //setup
        Mockito.when(userDAO.addInterest(testInterest)).thenReturn(testInterest);

        //test
        Interest actualInterest = unitUnderTest.addInterest(testInterest);

        //verify
        assertEquals(1, actualInterest.getId());
        assertEquals("Testing", actualInterest.getName());
        assertEquals("I like testing.", actualInterest.getDescription());
    }

    @Test
    void getAllInterests() {
        //setup
        Mockito.when(userDAO.getAllInterests()).thenReturn(testInterests);

        //test
        List<Interest> actualInterests = unitUnderTest.getAllInterests();

        //verify
        assertEquals(1, actualInterests.get(0).getId());
        assertEquals("Test", actualInterests.get(0).getName());
        assertEquals("I like testing.", actualInterests.get(0).getDescription());
    }

    @Test
    void addInterestToUser() {
        //setup
        long testUserId = 1;

        Mockito.when(userDAO.addInterestToUser(testUserId, testInterest.getId())).thenReturn(testInterest);

        //test

        Interest actualInterest = unitUnderTest.addInterestToUser(testUserId, testInterest.getId());

        //verify
        assertEquals(1, actualInterest.getId());
        assertEquals("Test", actualInterest.getName());
        assertEquals("I like testing.", actualInterest.getDescription());
    }

    @Test
    void getInterestByUser() {
        //setup

        //test

        List<Interest> actualInterest = unitUnderTest.getInterestByUser(testUser1);

        //verify
        assertEquals(1, actualInterest.get(0).getId());
        assertEquals("Testing", actualInterest.get(0).getName());
        assertEquals("I like testing.", actualInterest.get(0).getDescription());
    }

    @Test
    void getAllLocations() {
        //setup
        Mockito.when(userDAO.getAllLocations()).thenReturn(testLocations);

        //test
        List<Location> actualLocations = unitUnderTest.getAllLocations();

        //verify
        assertEquals(1, actualLocations.get(0).getId());
        assertEquals("Testeristan", actualLocations.get(0).getName());
    }

    @Test
    void addLocation() {
        //setup
        Mockito.when(userDAO.addLocation(testLocation)).thenReturn(testLocation);

        //test
        Location actualLocation = unitUnderTest.addLocation(testLocation);

        //verify
        assertEquals(1, actualLocation.getId());
        assertEquals("Testeristan", actualLocation.getName());
    }

    @Test
    void openFor() {
        //setup

        //test

        List<SearchType> actualSearchTypes = unitUnderTest.openFor(testUser1);

        //verify
        assertEquals(1, actualSearchTypes.get(0).getId());
        assertEquals("Test", actualSearchTypes.get(0).getName());
        assertEquals("We might want to test hanging out.", actualSearchTypes.get(0).getDescription());
    }

    @Test
    void addSearch() {
        //setup
        Mockito.when(userDAO.addSearch(testSearch)).thenReturn(testSearch);

        //test
        Search actualSearch = unitUnderTest.addSearch(testSearch);

        //verify
        assertEquals(1, actualSearch.getSearchId());
        assertEquals(1, actualSearch.getUserId());
        assertEquals(1, actualSearch.getSearchTypeId());

    }

    @Test
    void getMatchingSearches() {
        //setup
        Mockito.when(userDAO.getAllSearches()).thenReturn(testSearches);

        //test
        List<Search> actualSearches = unitUnderTest.getMatchingSearches(testUser1);
        System.out.println(actualSearches);
        //verify
        assertEquals(1, actualSearches.get(0).getSearchId());
        assertEquals(1, actualSearches.get(0).getUserId());
    }

    @Test
    void getMatchingUsers() {
        //setup
        Mockito.when(userDAO.getAllUsers()).thenReturn(testUsers);

        //test
        List<User> actualUsers = unitUnderTest.getMatchingUsers(testSearch);
        System.out.println(actualUsers);

        //verify
        assertEquals(1, actualUsers.get(0).getId());
    }

    @Test
    void connectViaSearch() {

        //setup
        Mockito.when(userDAO.getSearchById(1)).thenReturn(Optional.ofNullable(testSearch));
        Mockito.when(userDAO.findUserById(any())).thenReturn(Optional.ofNullable(testUser1));

        //test
        User actualUser = unitUnderTest.connectViaSearch(testUser2, 1);
        System.out.println(actualUser);
        //verify
        assertEquals(1, actualUser.getId());
        Mockito.verify(userDAO, Mockito.times(2)).saveUser(any());

    }

    @Test
    void getAllSkills() {
        //setup
        Mockito.when(userDAO.getAllSkills()).thenReturn(testSkills);

        //test
        List<Skill> actualSkills = unitUnderTest.getAllSkills();
        System.out.println(actualSkills);

        //verify
        assertEquals(1, actualSkills.get(0).getId());
    }

    @Test
    void addSkill() {
        //setup
        Mockito.when(userDAO.addSkill(testSkill1)).thenReturn(testSkill1);

        //test
        Skill actualSkill = unitUnderTest.addSkill(testSkill1);

        //verify
        assertEquals(1, actualSkill.getId());
    }

    @Test
    void getMySkills() {
        //setup

        //test
        List<Skill> actualSkills = unitUnderTest.getMySkills(testUser1);

        //verify
        assertEquals(1, actualSkills.get(0).getId());
    }


    @Test
    void addSkillToUser() {
        //setup
        Mockito.when(userDAO.getSkillById(2)).thenReturn(Optional.ofNullable(testSkill2));
        //kolla med Lena att mocka saveuser.

        //test
        Skill actualSkill = unitUnderTest.addSkill(testSkill1);

        //verify
        assertEquals(1, actualSkill.getId());

    }

    @Test
    void setUsersLocation() {
        //setup
        Mockito.when(userDAO.setUsersLocation(1, 1)).thenReturn(testLocation);
        //test
        Location actualLocation = unitUnderTest.setUsersLocation(1, 1);

        //verify
        assertEquals(1, actualLocation.getId());
    }
}