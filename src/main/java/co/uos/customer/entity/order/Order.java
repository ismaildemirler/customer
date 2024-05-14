package co.uos.customer.entity.order;

import co.uos.customer.entity.EntityModel;
import co.uos.customer.entity.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
@Entity(name = "order")
public class Order implements EntityModel {

    @Id
    @Column(name = "order_id")
    private Integer orderId;

    private Date date;

    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;
}
