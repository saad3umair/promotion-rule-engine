package com.promotion.rule.engine;

import com.promotion.model.PromotionItem;
import com.promotion.request.CartItem;

import java.util.List;

public interface RuleEngine {
    void applyRule(List<CartItem> skuItems, List<PromotionItem> modifiedCartItems);
}
