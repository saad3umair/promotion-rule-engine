package com.promotion.repository;

import com.promotion.entity.SKUItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SKUItemRepository extends JpaRepository<SKUItem, String> {
}
