package com.example.coco.repository;

import com.example.coco.models.Search;
import com.example.coco.models.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SearchRepository extends CrudRepository<Search, Long> {
}