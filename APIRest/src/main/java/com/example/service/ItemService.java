package com.example.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Item;

public interface ItemService extends JpaRepository<Item, Long> {

}