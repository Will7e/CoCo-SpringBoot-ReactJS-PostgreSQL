package com.example.coco.service;

import com.example.coco.dto.FriendRequest;
import com.example.coco.models.Friend;
import com.example.coco.models.User;
import com.example.coco.repository.FriendRepository;
import com.example.coco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    @Autowired
    FriendRepository friendRepository;

    @Autowired
    UserRepository userRepository;


    public String saveFriend(FriendRequest friendRequest) {
        Friend friend = new Friend();
        User currentUser = userRepository.findUserById(friendRequest.getUserId());
        if (currentUser == null){
            return "current user not found";
        }
        User userToAdd = userRepository.findUserById(friendRequest.getFriendId());
        if (userToAdd == null){
            return "person to add not found";
        }

        if (currentUser.getId() != userToAdd.getId()){
            friend.setFriend(userToAdd);
            friendRepository.save(friend);
            currentUser.getFriendList().add(friend);
            userRepository.save(currentUser);
        return "Friend added";
        }
        return "Not added";
    }


    public List<Friend> getFriends(FriendRequest friendRequest) {
        User user = userRepository.findUserById(friendRequest.userId);
        return user.getFriendList();

    }

    public String unFriend(FriendRequest friendRequest) {


        User currentUser = userRepository.findUserById(friendRequest.getUserId());
        if (currentUser == null){
            return "current user not found";
        }

        Friend friend = friendRepository.findFriendByFriendId(friendRequest.getFriendId());


      for (int i = 0; i< currentUser.getFriendList().size(); i++){
          if (currentUser.getFriendList().contains(friend)){
              currentUser.getFriendList().remove(friend);
              userRepository.save(currentUser);
              return "friend removed";

          }

      }
            return "not removed";

    }
}
