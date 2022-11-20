package com.promotion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SKUItem implements Serializable {
    @Id
    @Column(name = "ID")
    public String id;
    @Column(name = "PRICE")
    public Integer price;
}
