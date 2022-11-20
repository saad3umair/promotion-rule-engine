package com.promotion.request;

import com.promotion.model.PromotionItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    PromotionItem promotionItem;
}
