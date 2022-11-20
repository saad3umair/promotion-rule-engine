package com.promo.rule.engine;

import com.promo.AbstractTest;
import com.promo.util.BuildCartItems;
import com.promotion.dao.PromotionDao;
import com.promotion.model.PromotionItem;
import com.promotion.request.CartItem;
import com.promotion.rule.engine.MultipleItemPromotionRule;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MultipleItemPromotionRuleTest extends AbstractTest {
    @Autowired
    MultipleItemPromotionRule multipleItemPromotionRule;
    @Mock
    PromotionDao promotionDao;

    @Test
    public void testMultipleItemPromotionRuleForMultipleItems() {
        List<PromotionItem> modifiedCartItems = new ArrayList<>();
        List<CartItem> cartItems = BuildCartItems.buildCartItemsWithMultiItemPromotion();

        Mockito.doNothing().when(promotionDao).loadPromotions();
        multipleItemPromotionRule.applyRule(cartItems, modifiedCartItems);

        Assert.assertEquals(1, modifiedCartItems.size());
        Assert.assertEquals("C&D", modifiedCartItems.get(0).getItem().getId());
        Assert.assertEquals(modifiedCartItems.get(0).getPromotionPrice().getUnitPrice(), Integer.valueOf(30));
        Assert.assertTrue(modifiedCartItems.get(0).getIsPromotionApplied());
    }

    @Test
    public void testMultipleItemPromotionRuleForSingleItem() {
        List<PromotionItem> modifiedCartItems = new ArrayList<>();
        List<CartItem> cartItems = BuildCartItems.buildCartItemsWithoutSingleItemPromotion();

        Mockito.doNothing().when(promotionDao).loadPromotions();
        multipleItemPromotionRule.applyRule(cartItems, modifiedCartItems);

        Assert.assertTrue(modifiedCartItems.isEmpty());
        Assert.assertFalse(cartItems.get(0).getPromotionItem().getIsPromotionApplied());
    }

    @Test
    public void testMultipleItemPromotionRuleForMultipleItemsInQuantity() {
        List<PromotionItem> modifiedCartItems = new ArrayList<>();
        List<CartItem> cartItems = BuildCartItems.buildCartItemsWithMultiItemPromotionForMultipleQuantity();

        Mockito.doNothing().when(promotionDao).loadPromotions();
        multipleItemPromotionRule.applyRule(cartItems, modifiedCartItems);

        Assert.assertEquals(2, modifiedCartItems.size());
        Assert.assertEquals("C&D", modifiedCartItems.get(0).getItem().getId());
        Assert.assertEquals(modifiedCartItems.get(0).getPromotionPrice().getUnitPrice(), Integer.valueOf(60));
        Assert.assertTrue(modifiedCartItems.get(0).getIsPromotionApplied());

        Assert.assertEquals("C", modifiedCartItems.get(1).getItem().getId());
        Assert.assertEquals(modifiedCartItems.get(1).getPromotionPrice().getUnitPrice(), Integer.valueOf(20));
        Assert.assertFalse(modifiedCartItems.get(1).getIsPromotionApplied());
    }
}
