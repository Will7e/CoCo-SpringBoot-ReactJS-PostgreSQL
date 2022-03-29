package com.example.coco.repository;

import com.example.coco.models.User;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User,Long> {

}

