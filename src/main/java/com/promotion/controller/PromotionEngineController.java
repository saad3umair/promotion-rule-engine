package com.promotion.controller;

import com.promotion.model.Price;
import com.promotion.model.PromotionItem;
import com.promotion.request.CartItem;
import com.promotion.response.PromotionCart;
import com.promotion.service.PromotionEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotion")
public class PromotionEngineController {
    @Autowired
    PromotionEngineService promotionEngineService;

    @PostMapping("/applyPromotion")
    @ResponseBody
    public ResponseEntity<PromotionCart> applyPromotion(@RequestBody List<CartItem> cartItems) {
        List<PromotionItem> modifiedCartItems = promotionEngineService.applyPromotion(cartItems);
        PromotionCart promotionCart = new PromotionCart();
        promotionCart.setPromotionItems(modifiedCartItems);
        promotionCart.setTotalValue(new Price(modifiedCartItems.stream()
                .map(PromotionItem::getPromotionPrice).mapToInt(Price::getUnitPrice).sum()));
        return new ResponseEntity<>(promotionCart, HttpStatus.OK);
    }
}
