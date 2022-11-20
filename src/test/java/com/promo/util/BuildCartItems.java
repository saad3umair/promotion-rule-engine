package com.promo.util;

import com.promotion.model.Item;
import com.promotion.model.Price;
import com.promotion.model.PromotionItem;
import com.promotion.model.Quantity;
import com.promotion.request.CartItem;

import java.util.ArrayList;
import java.util.List;

public class BuildCartItems {
    public static List<CartItem> buildCartItemsWithoutPromotion() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem(new PromotionItem(new Item("A"), new Quantity(1), new Price(50), false)));
        cartItems.add(new CartItem(new PromotionItem(new Item("B"), new Quantity(1), new Price(30), false)));
        cartItems.add(new CartItem(new PromotionItem(new Item("C"), new Quantity(1), new Price(20), false)));
        return cartItems;
    }

    public static List<CartItem> buildCartItemsWithSingleItemPromotion() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem(new PromotionItem(new Item("A"), new Quantity(5), new Price(50), false)));
        cartItems.add(new CartItem(new PromotionItem(new Item("B"), new Quantity(5), new Price(30), false)));
        cartItems.add(new CartItem(new PromotionItem(new Item("C"), new Quantity(1), new Price(20), false)));
        return cartItems;
    }

    public static List<CartItem> buildCartItemsWithSingleAndMultiItemPromotion() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem(new PromotionItem(new Item("A"), new Quantity(3), new Price(50), false)));
        cartItems.add(new CartItem(new PromotionItem(new Item("B"), new Quantity(5), new Price(30), false)));
        cartItems.add(new CartItem(new PromotionItem(new Item("C"), new Quantity(1), new Price(20), false)));
        cartItems.add(new CartItem(new PromotionItem(new Item("D"), new Quantity(1), new Price(20), false)));
        return cartItems;
    }

    public static List<CartItem> buildCartItemsWithoutSingleItemPromotion() {
        List<CartItem> cartItems = new ArrayList<>();
       cartItems.add(new CartItem(new PromotionItem(new Item("C"), new Quantity(1), new Price(20), false)));
        return cartItems;
    }

    public static List<CartItem> buildCartItemsWithMultiItemPromotion() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem(new PromotionItem(new Item("C"), new Quantity(1), new Price(20), false)));
        cartItems.add(new CartItem(new PromotionItem(new Item("D"), new Quantity(1), new Price(20), false)));
        return cartItems;
    }

    public static List<CartItem> buildCartItemsWithMultiItemPromotionForMultipleQuantity() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem(new PromotionItem(new Item("C"), new Quantity(3), new Price(20), false)));
        cartItems.add(new CartItem(new PromotionItem(new Item("D"), new Quantity(2), new Price(20), false)));
        return cartItems;
    }
}
