package com.example.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.OS_Item;

public interface OS_ItemService extends JpaRepository<OS_Item, Long> {

}