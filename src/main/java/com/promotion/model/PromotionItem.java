package com.promotion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionItem {
    Item item;
    Quantity totalQuantity;
    Price promotionPrice;
    Boolean isPromotionApplied;
}
