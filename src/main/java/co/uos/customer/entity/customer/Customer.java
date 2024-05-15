package co.uos.customer.entity.customer;

import co.uos.customer.entity.EntityModel;
import co.uos.customer.entity.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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

    private String status;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders;
}