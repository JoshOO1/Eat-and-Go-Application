package com.example.eatandgo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends UserBaseEntity{

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastLogin;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany
    private List<OrderDetails> orderDetails;

    @OneToMany
    private List<Transaction> transactions;

    @OneToMany
    private List<Shipment> shipments;

}
