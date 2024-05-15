package co.uos.customer.repository.order;

import co.uos.customer.entity.customer.Customer;
import co.uos.customer.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {

}
