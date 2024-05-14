package co.uos.customer.repository.customer;

import co.uos.customer.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCustomerId(Integer customerId);
    List<Customer> findAllByStatus(Boolean status);
}
