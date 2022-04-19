package com.example.coco.service;

import com.example.coco.dao.UserDAO;
import com.example.coco.dto.EditRequest;
import com.example.coco.dto.SkillRequest;
import com.example.coco.models.*;
import com.example.coco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return PrincipalUser.build(user);
    }

    public Optional<User> getUserFirstName(User user) {
        return userDAO.findUserById(user.getId());
    }

    public User getCurrentUser() {

        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userDAO.findUserById(principalUser.getId());
        if (user.isEmpty()) {
            return null;
        }
        return user.get();

    }


// Interest methods

    public Interest addInterest(Interest interest) {
        return userDAO.addInterest(interest);
    }

    public List<Interest> getAllInterests() {
        return userDAO.getAllInterests();
    }

    /*
        public Interest addInterestToUser(long userId, long id) {
            return userDAO.addInterestToUser(userId, id);
        }
    */
    public String getInterestByUser(User user) {
        return user.getInterests();
    }

    // Location methods:

    public List<Location> getAllLocations() {
        return userDAO.getAllLocations();
    }

    public Location addLocation(Location location) {
        return userDAO.addLocation(location);
    }

    // Search methods

    public List<SearchType> openFor(User user) {
        return user.getOpenForSearchType();
    }

    public Search addSearch(Search search) {
        return userDAO.addSearch(search);
    }
/*
    /**
     * returns list of searches in the db that user is a match to.
     *
     * @param user User
     * @return List of searches
     */
    /*
    public List<Search> getMatchingSearches(User user) {
        return userDAO.getAllSearches().stream()
                .filter(s -> matchUserToSearch(user, s))
                .collect(Collectors.toList());
    }

    public List<User> getMatchingUsers(Search search) {
        return userDAO.getAllUsers().stream()
                .filter(u -> matchUserToSearch(u, search))
                .collect(Collectors.toList());
    }
/*
    public User connectViaSearch(User user, long id) {
        Optional<Search> maybeSearch = userDAO.getSearchById(id);
        if(maybeSearch.isEmpty()) return null;
        Optional<User> maybeContact = userDAO.findUserById(maybeSearch.get().getUserId());
        if(maybeContact.isEmpty()) return null;

        List<User> userContacts = user.getContacts();
        userContacts.add(maybeContact.get());
        user.setContacts(userContacts);
        userDAO.saveUser(user);

        List<User> contactContacts = maybeContact.get().getContacts();
        contactContacts.add(user);
        maybeContact.get().setContacts(contactContacts);
        userDAO.saveUser(maybeContact.get());
        return maybeContact.get();
    }
*/
    // Skill methods

    public List<Skill> getAllSkills() {
        return (List<Skill>) userDAO.getAllSkills();
    }

    public Skill addSkill(Skill skill) {
        return userDAO.addSkill(skill);
    }

    public List<Skill> getMySkills(User user) {
        return user.getSkills();
    }




    public String addSkillsToUSer(SkillRequest skillRequest) {
        Skill skill = userDAO.getSkillById(skillRequest.getSkillId());
        if (skill == null) {
            return "Skill not found";
        }

        User user = userDAO.findCurrentUserById(skillRequest.getUserId());
        if (user == null) {
            return "User not found";
        }

        List<Skill> userSkills = user.getSkills();

        for (Skill skill1 : userSkills) {
            if (skill1 == skill) {
                return "Skill already exist in ur list";
            }

        }
        userSkills.add(skill);
        user.setSkills(userSkills);
        userDAO.saveUser(user);

        return "Skill added";

    }

    public String removeUserSkill(SkillRequest skillRequest) {
        Skill skill = userDAO.getSkillById(skillRequest.getSkillId());
        if (skill == null) {
            return "Skill does not exist";
        }
        User user = userDAO.findCurrentUserById(skillRequest.getUserId());
        if (user == null) {
            return "User does not exist";
        }

        List<Skill> userSkills = user.getSkills();

        for (int i = 0; i < userSkills.size(); i++){
            if  (!userSkills.contains(skill)){
                return "Skill does not exist in list";
            }
        }

        userSkills.remove(skill);
        user.setSkills(userSkills);
        userDAO.saveUser(user);

        return "Skill removed";
    }

    public List<User> findAllUserBySkills(Integer skillId) {
        List<User> userList = userDAO.getAllUsers();
        List<User> userList1 = new ArrayList<>();


        for (User user : userList) {
            for (Skill skill : user.getSkills()) {
                if (Objects.equals(skillId, skill.getId())) {
                    userList1.add(user);
                    break;
                }
            }
        }

        return userList1;
    }

    public String editUser(EditRequest editRequest) {
        String message = "Edit success";
        User user = userDAO.findCurrentUserById(editRequest.getUserId());

        if (user == null) {
            return "User not found";
        }
        switch (editRequest.getEditCase()) {

            case 1: user.setFullName(editRequest.getFullName());
                userDAO.saveUser(user);
                break;


            case 2: user.setInterests(editRequest.getInterest());
                userDAO.saveUser(user);
                break;


            case 3: user.setCountry(editRequest.getCountry());
                userDAO.saveUser(user);
                break;


            case 4: user.setCity(editRequest.getCity());
                userDAO.saveUser(user);
                break;


            case 5: user.setContacts(editRequest.getContacts());
                userDAO.saveUser(user);
                break;


            case 6: user.setOccupation(editRequest.getOccupation());
                userDAO.saveUser(user);
                break;

            case 7: user.setPresentation(editRequest.getPresentation());
                userDAO.saveUser(user);
                break;

                default: return "Error";


        }
        return message;

    }


    // Help Methods:

/*
    /**
     * returns true if user is a match to search
     *
     * @param user User
     * @param search Search
     * @return boolean match
     */

    /*
    private boolean matchUserToSearch(User user, Search search) {
        // check if user is open to contact of SearchType
        List<SearchType> isOpenTo = user.getOpenForSearchType().stream()
                .filter(o -> o.getId() == search.getSearchTypeId())
                .toList();

        if (isOpenTo.size() == 0) {
            System.out.println("User is not open to searches of this type.");
            return false;
        }

        // check location
        if (!(search.getLocationId() == null || search.getLocationId().equals(user.getLocation().getId()))) {
            System.out.println("User location is not right for this search.");
            return false;
        }

        // check if user have all the skills in the search
        List<Skill> searchedSkills = search.getSearchSkills();
        List<Skill> userSkills = user.getSkills();
        for (Skill searchedSkill : searchedSkills) {
            boolean foundSkill = false;
            for (Skill userSkill : userSkills) {
                if (searchedSkill.getId().equals(userSkill.getId())) {
                    foundSkill = true;
                    break;
                }
            }
            if (!foundSkill) {
                System.out.println("User does not have the required skills for this search.");
                return false; //if we find one unmatched skill - no match!}
            }

        }

        // check if user have all interests in the search
        List<Interest> searchedInterests = search.getSearchInterests();
        List<Interest> userInterests = user.getInterests();
        for (Interest searchedInterest : searchedInterests) {
            boolean foundInterest = false;
            for (Interest userInterest : userInterests) {
                if (searchedInterest.getId().equals(userInterest.getId())) {
                    foundInterest = true;
                    break;
                }
            }

            if (!foundInterest) {
                System.out.println("User does not have the required interests for this search.");
                return false; //if we find one unmatched interest - no match!
            }
        }
        //if we got this far without returning false, consider it a match!
        return true;
    }

*/
    /*
    public Location setUsersLocation(long userId, long id) {
        return userDAO.setUsersLocation(userId, id);
    }
*/


}

