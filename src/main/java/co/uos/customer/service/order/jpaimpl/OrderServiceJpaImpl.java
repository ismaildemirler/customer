package co.uos.customer.service.order.jpaimpl;

import co.uos.customer.repository.order.OrderJpaRepository;
import co.uos.customer.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceJpaImpl implements OrderService {

    private final OrderJpaRepository orderRepository;
}
