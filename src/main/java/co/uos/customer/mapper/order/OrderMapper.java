package co.uos.customer.mapper.order;

import co.uos.customer.dto.order.OrderDTO;
import co.uos.customer.entity.order.Order;
import co.uos.customer.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toEntity(OrderDTO orderDTO);

    OrderDTO toDto(Order order);

    List<OrderDTO> toListDto(List<Order> orders);
}