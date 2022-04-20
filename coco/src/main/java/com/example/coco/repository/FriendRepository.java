package com.example.coco.repository;

import com.example.coco.models.Friend;
import com.example.coco.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Long> {



    Friend  removeFriendByFriend(User friend);
}
