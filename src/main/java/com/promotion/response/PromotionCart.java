package com.promotion.response;

import com.promotion.model.Price;
import com.promotion.model.PromotionItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionCart {
    List<PromotionItem> promotionItems;
    Price totalValue;
}
