package com.promotion.rule.engine;

import com.promotion.model.PromotionItem;
import com.promotion.request.CartItem;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class NoPromotionRule implements RuleEngine {
    @Override
    public void applyRule(List<CartItem> cartItems, List<PromotionItem> modifiedCartItems) {
        modifiedCartItems.addAll(cartItems.stream().filter(cartItem -> !cartItem.getPromotionItem().getIsPromotionApplied())
                .map(CartItem::getPromotionItem).collect(Collectors.toList()));
    }
}
