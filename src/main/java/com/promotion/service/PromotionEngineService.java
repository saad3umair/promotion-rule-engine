package com.promotion.service;

import com.promotion.model.PromotionItem;
import com.promotion.request.CartItem;
import com.promotion.rule.engine.RuleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionEngineService {
    @Autowired
    List<RuleEngine> ruleEngines;
    public List<PromotionItem> applyPromotion(List<CartItem> cartItems) {
        List<PromotionItem> modifiedCartItems = new ArrayList<>();
        ruleEngines.forEach(ruleEngine -> ruleEngine.applyRule(cartItems, modifiedCartItems));
        return modifiedCartItems;
    }
}
