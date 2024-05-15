package co.uos.customer.repository.customer;

import co.uos.customer.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Integer> {

    //@Query('select c from customer c inner join public.order o on c.customer_id = o.customer_id')
    Optional<Customer> findByCustomerId(Integer customerId);
    List<Customer> findAllByStatus(String status);
}
