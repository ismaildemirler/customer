package co.uos.customer.mapper.order;

import co.uos.customer.dto.order.OrderDTO;
import co.uos.customer.entity.order.Order;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-15T11:09:22+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.orderId( orderDTO.getOrderId() );
        order.date( orderDTO.getDate() );
        order.amount( orderDTO.getAmount() );

        return order.build();
    }

    @Override
    public OrderDTO toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO.OrderDTOBuilder orderDTO = OrderDTO.builder();

        orderDTO.orderId( order.getOrderId() );
        orderDTO.date( order.getDate() );
        orderDTO.amount( order.getAmount() );

        return orderDTO.build();
    }

    @Override
    public List<OrderDTO> toListDto(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( orders.size() );
        for ( Order order : orders ) {
            list.add( toDto( order ) );
        }

        return list;
    }
}
