package com.promo.rule.engine;

import com.promo.AbstractTest;
import com.promo.util.BuildCartItems;
import com.promotion.dao.PromotionDao;
import com.promotion.model.PromotionItem;
import com.promotion.request.CartItem;
import com.promotion.rule.engine.SingleItemPromotionRule;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SingleItemPromotionRuleTest extends AbstractTest {
    @Autowired
    SingleItemPromotionRule singleItemPromotionRule;
    @Mock
    PromotionDao promotionDao;

    @Test
    public void testSingleItemPromotionRuleForValidItems() {
        List<PromotionItem> modifiedCartItems = new ArrayList<>();
        List<CartItem> cartItems = BuildCartItems.buildCartItemsWithSingleItemPromotion();

        Mockito.doNothing().when(promotionDao).loadPromotions();
        singleItemPromotionRule.applyRule(cartItems, modifiedCartItems);

        List<String> itemIds = modifiedCartItems.stream().map(promotionItem -> promotionItem.getItem().getId())
                .collect(Collectors.toList());
        Assert.assertEquals(2, modifiedCartItems.size());
        Assert.assertTrue(itemIds.containsAll(Arrays.asList("A", "B")));
    }

    @Test
    public void testSingleItemPromotionRuleForOtherItem() {
        List<PromotionItem> modifiedCartItems = new ArrayList<>();
        List<CartItem> cartItems = BuildCartItems.buildCartItemsWithoutSingleItemPromotion();

        Mockito.doNothing().when(promotionDao).loadPromotions();

        singleItemPromotionRule.applyRule(cartItems, modifiedCartItems);
        Assert.assertTrue(modifiedCartItems.isEmpty());
    }
}
