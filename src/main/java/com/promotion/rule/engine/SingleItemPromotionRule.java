package com.promotion.rule.engine;

import com.promotion.dao.PromotionDao;
import com.promotion.model.Item;
import com.promotion.model.Price;
import com.promotion.model.Promotion;
import com.promotion.model.PromotionItem;
import com.promotion.request.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Order(1)
public class SingleItemPromotionRule implements RuleEngine {
    @Autowired
    PromotionDao promotionDao;

    @Override
    public void applyRule(List<CartItem> cartItems, List<PromotionItem> modifiedCartItems) {
        Map<Item, CartItem> cartItemMap = new HashMap<>();
        Map<Item, Promotion> promotionItemMap = promotionDao.getPromotionMap();
        Map<Item, Promotion> singleItemPromotionMap = promotionItemMap.entrySet().stream()
                .filter(promotionItemEntry -> !promotionItemEntry.getValue().getMultiItemPromotion())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        cartItems.forEach(cartItem -> cartItemMap.put(cartItem.getPromotionItem().getItem(), cartItem));
        cartItemMap.keySet().retainAll(singleItemPromotionMap.keySet());
        cartItemMap.values().forEach(cartItem -> {
            PromotionItem promotionItem = singleItemPromotionMap.get(cartItem.getPromotionItem().getItem()).getPromotionItem();
            Integer promotionPrice = promotionItem.getPromotionPrice().getUnitPrice();
            Integer unitPrice = cartItem.getPromotionItem().getPromotionPrice().getUnitPrice();
            Integer totalItemForPromotion = promotionItem.getTotalQuantity().getTotalQuantity();
            Integer totalItemInCart = cartItem.getPromotionItem().getTotalQuantity().getTotalQuantity();
            if (singleItemPromotionMap.containsKey(cartItem.getPromotionItem().getItem())
                    && totalItemInCart.compareTo(totalItemForPromotion) >= 0) {
                int priceWithPromotion = promotionPrice * (totalItemInCart / totalItemForPromotion);
                int priceWithoutPromotion = unitPrice * (totalItemInCart % totalItemForPromotion);
                modifiedCartItems.add(new PromotionItem(cartItem.getPromotionItem().getItem(),
                        cartItem.getPromotionItem().getTotalQuantity(), new Price(priceWithPromotion + priceWithoutPromotion), true));
                cartItem.getPromotionItem().setIsPromotionApplied(true);
            }
        });
    }
}
