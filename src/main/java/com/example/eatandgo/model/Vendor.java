package com.example.eatandgo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vendor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendor extends UserBaseEntity{
    @OneToMany
    private List<OrderDetails> orderDetailsList;
    @OneToMany
    private List<Customer> customers;
    @OneToMany
    private List<Food> foods;
    @OneToMany
    private List<Shipment> shipments;
}
