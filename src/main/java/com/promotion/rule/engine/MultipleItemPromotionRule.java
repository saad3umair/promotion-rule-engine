package com.promotion.rule.engine;

import com.promotion.dao.PromotionDao;
import com.promotion.model.*;
import com.promotion.request.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Order(2)
public class MultipleItemPromotionRule implements RuleEngine {
    @Autowired
    PromotionDao promotionDao;
    @Override
    public void applyRule(List<CartItem> cartItems, List<PromotionItem> modifiedCartItems) {
        Map<String, CartItem> cartItemMap = new HashMap<>();
        Map<Item, Promotion> promotionItemMap = promotionDao.getPromotionMap();
        Map<Item, Promotion> multiItemPromotionMap = promotionItemMap.entrySet().stream()
                .filter(promotionItemEntry -> promotionItemEntry.getValue().getMultiItemPromotion())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        cartItems.forEach(cartItem -> cartItemMap.put(cartItem.getPromotionItem().getItem().getId(), cartItem));

        multiItemPromotionMap.entrySet().forEach(item -> {
            List<String> items = Arrays.stream(item.getValue().getPromotionItem().getItem().getId().split("&"))
                    .collect(Collectors.toList());
            boolean exists = items.stream().allMatch(cartItemMap::containsKey);
            cartItemMap.keySet().retainAll(items);
            if (exists) {
                CartItem cartItem = Collections.min(cartItemMap.entrySet(),
                        Comparator.comparingInt(value -> value.getValue().getPromotionItem().getTotalQuantity().getTotalQuantity()))
                        .getValue();
                Integer minimumQuantity = cartItem.getPromotionItem().getTotalQuantity().getTotalQuantity();
                Integer totalQuantityForPromotion = item.getValue().getPromotionItem().getTotalQuantity().getTotalQuantity();
                Integer price = item.getValue().getPromotionItem().getPromotionPrice().getUnitPrice();
                Integer priceWithPromotion = price * (minimumQuantity / totalQuantityForPromotion);

                if (minimumQuantity >= totalQuantityForPromotion) {
                    modifiedCartItems.add(new PromotionItem(item.getValue().getPromotionItem().getItem(), new Quantity(minimumQuantity),
                            new Price(priceWithPromotion), true));

                    cartItemMap.entrySet().forEach(itemCartItemEntry -> {
                        Integer quantity = itemCartItemEntry.getValue().getPromotionItem().getTotalQuantity().getTotalQuantity() - minimumQuantity;
                        if (quantity > 0) {
                            modifiedCartItems.add(new PromotionItem(itemCartItemEntry.getValue().getPromotionItem().getItem(), new Quantity(quantity),
                                    new Price(itemCartItemEntry.getValue().getPromotionItem().getPromotionPrice().getUnitPrice() * quantity),
                                    false));
                        }
                        itemCartItemEntry.getValue().getPromotionItem().setIsPromotionApplied(true);
                    });
                }
            }
        });
    }
}
