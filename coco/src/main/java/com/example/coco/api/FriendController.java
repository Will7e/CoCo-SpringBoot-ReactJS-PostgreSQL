package com.example.coco.api;

import com.example.coco.dto.FriendRequest;
import com.example.coco.models.Friend;
import com.example.coco.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/add")
    public String saveFriend(@Valid @RequestBody FriendRequest friendRequest){
        return friendService.saveFriend(friendRequest);
    }

    @PostMapping("/all")
    public List<Friend> getFriends(@Valid @RequestBody FriendRequest friendRequest){
        return friendService.getFriends(friendRequest);

    }
    @DeleteMapping("/unfriend")
    public String unFriend(@Valid @RequestBody FriendRequest friendRequest){
        return friendService.unFriend(friendRequest);
    }
}
