package com.promotion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SKUItem {
    Item item;
    @Positive(message = "Unit price should be positive!")
    Price unitPrice;
}
