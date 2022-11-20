package com.promotion.dao;

import com.promotion.model.*;
import com.promotion.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class PromotionDao {
    private static final Map<Item, Promotion> promotionMap = new HashMap<>();
    @Autowired
    PromotionRepository promotionRepository;

    @PostConstruct
    public void loadPromotions() {
        promotionRepository.findAll().forEach(promotionEntity -> {
            Promotion promotion = new Promotion();
            PromotionItem promotionItem = new PromotionItem();
            promotionItem.setItem(new Item(promotionEntity.getItemId()));
            promotionItem.setTotalQuantity(new Quantity(promotionEntity.getQuantity()));
            promotionItem.setPromotionPrice(new Price(promotionEntity.getPromotionalItemPrice()));
            promotionItem.setIsPromotionApplied(false);
            promotion.setPromotionItem(promotionItem);
            promotion.setMultiItemPromotion(promotionEntity.getMultiItemPromotion());
            promotionMap.put(promotionItem.getItem(), promotion);
        });
    }

    public Map<Item, Promotion> getPromotionMap() {
        return promotionMap;
    }
}
