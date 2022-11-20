package com.promo.controller;

import com.promo.AbstractTest;
import com.promo.util.BuildCartItems;
import com.promotion.request.CartItem;
import com.promotion.response.PromotionCart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

public class PromotionEngineControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testApplyCartWithItemsWithoutPromotion() throws Exception {
        String uri = "/api/promotion/applyPromotion";
        List<CartItem> cartItems = BuildCartItems.buildCartItemsWithoutPromotion();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(cartItems))).andReturn();
        PromotionCart response = mapFromJson(mvcResult.getResponse().getContentAsString(), PromotionCart.class);
        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals(200, status);
        Assert.assertEquals(100, response.getTotalValue().getUnitPrice().intValue());
    }

    @Test
    public void testApplyCartWithSingleItemPromotion() throws Exception {
        String uri = "/api/promotion/applyPromotion";
        List<CartItem> cartItems = BuildCartItems.buildCartItemsWithSingleItemPromotion();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(cartItems))).andReturn();
        PromotionCart response = mapFromJson(mvcResult.getResponse().getContentAsString(), PromotionCart.class);
        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals(200, status);
        Assert.assertEquals(370, response.getTotalValue().getUnitPrice().intValue());
    }

    @Test
    public void testApplyCartWithSingleAndMultipleItemPromotion() throws Exception {
        String uri = "/api/promotion/applyPromotion";
        List<CartItem> cartItems = BuildCartItems.buildCartItemsWithSingleAndMultiItemPromotion();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(cartItems))).andReturn();
        PromotionCart response = mapFromJson(mvcResult.getResponse().getContentAsString(), PromotionCart.class);
        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals(200, status);
        Assert.assertEquals(280, response.getTotalValue().getUnitPrice().intValue());
    }
}
