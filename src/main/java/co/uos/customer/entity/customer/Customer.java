package co.uos.customer.entity.customer;

import co.uos.customer.entity.EntityModel;
import co.uos.customer.entity.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@Entity(name = "customer")
public class Customer implements EntityModel {

    @Id
    @Column(name = "customer_id")
    private Integer customerId;

    private String firstname;

    private String surname;

    private String email;

    private String address;

    @Column(name = "zip_code")
    private String zipCode;

    private String region;

    private Boolean status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();
}