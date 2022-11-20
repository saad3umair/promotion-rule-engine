package com.promotion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "PROMOTION")
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {
    @Id
    @Column(name = "ID")
    private String itemId;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "PROMOTIONAL_ITEM_PRICE")
    private Integer promotionalItemPrice;
    @Column(name = "MULTI_ITEM_PROMOTION")
    private Boolean multiItemPromotion;
}
