package de.ostfalia.ftse.dvd_rental_customer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "count", query = "SELECT COUNT(c) FROM Customer c")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_customer_id_seq")
    @NotNull
    @Column(name = "customer_id")
    private int customerId;

    @NotNull
    @Column(name = "store_id")
    private int storeId;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Column
    private String email;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @NotNull
    @Column(name = "activebool")
    private boolean activeBool;

    @NotNull
    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Column
    private int active;

}
