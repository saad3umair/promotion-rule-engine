package com.promotion.dao;

import com.promotion.model.Item;
import com.promotion.model.Price;
import com.promotion.model.SKUItem;
import com.promotion.repository.SKUItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SKUItemDao {
    private static final Map<Item, SKUItem> skuItemMap = new HashMap<>();
    @Autowired
    SKUItemRepository skuItemRepository;

    public void loadSKUItems() {
        skuItemRepository.findAll().forEach(skuItem -> {
            SKUItem skuItem1 = new SKUItem();
            skuItem1.setItem(new Item(skuItem.getId()));
            skuItem1.setUnitPrice(new Price(skuItem.getPrice()));
            skuItemMap.put(skuItem1.getItem(), skuItem1);
        });
    }

    public Map<Item, SKUItem> getSkuItemMap() {
        return skuItemMap;
    }
}
