package co.uos.customer.mapper.customer;

import co.uos.customer.dto.customer.CustomerDTO;
import co.uos.customer.dto.order.OrderDTO;
import co.uos.customer.entity.customer.Customer;
import co.uos.customer.entity.order.Order;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-15T09:02:31+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.customerId( customerDTO.getCustomerId() );
        customer.firstname( customerDTO.getFirstname() );
        customer.surname( customerDTO.getSurname() );
        customer.email( customerDTO.getEmail() );
        customer.address( customerDTO.getAddress() );
        customer.zipCode( customerDTO.getZipCode() );
        customer.region( customerDTO.getRegion() );
        customer.status( customerDTO.getStatus() );
        customer.orders( orderDTOListToOrderList( customerDTO.getOrders() ) );

        return customer.build();
    }

    @Override
    public CustomerDTO toDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO.CustomerDTOBuilder customerDTO = CustomerDTO.builder();

        customerDTO.customerId( customer.getCustomerId() );
        customerDTO.firstname( customer.getFirstname() );
        customerDTO.surname( customer.getSurname() );
        customerDTO.email( customer.getEmail() );
        customerDTO.address( customer.getAddress() );
        customerDTO.zipCode( customer.getZipCode() );
        customerDTO.region( customer.getRegion() );
        customerDTO.status( customer.getStatus() );
        customerDTO.orders( orderListToOrderDTOList( customer.getOrders() ) );

        return customerDTO.build();
    }

    @Override
    public List<CustomerDTO> toListDto(List<Customer> Customers) {
        if ( Customers == null ) {
            return null;
        }

        List<CustomerDTO> list = new ArrayList<CustomerDTO>( Customers.size() );
        for ( Customer customer : Customers ) {
            list.add( toDto( customer ) );
        }

        return list;
    }

    protected Order orderDTOToOrder(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.orderId( orderDTO.getOrderId() );
        order.date( orderDTO.getDate() );
        order.amount( orderDTO.getAmount() );

        return order.build();
    }

    protected List<Order> orderDTOListToOrderList(List<OrderDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Order> list1 = new ArrayList<Order>( list.size() );
        for ( OrderDTO orderDTO : list ) {
            list1.add( orderDTOToOrder( orderDTO ) );
        }

        return list1;
    }

    protected OrderDTO orderToOrderDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO.OrderDTOBuilder orderDTO = OrderDTO.builder();

        orderDTO.orderId( order.getOrderId() );
        orderDTO.date( order.getDate() );
        orderDTO.amount( order.getAmount() );

        return orderDTO.build();
    }

    protected List<OrderDTO> orderListToOrderDTOList(List<Order> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDTO> list1 = new ArrayList<OrderDTO>( list.size() );
        for ( Order order : list ) {
            list1.add( orderToOrderDTO( order ) );
        }

        return list1;
    }
}
