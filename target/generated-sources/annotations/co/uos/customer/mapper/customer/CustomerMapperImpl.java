package co.uos.customer.mapper.customer;

import co.uos.customer.dto.customer.CustomerDTO;
import co.uos.customer.dto.order.OrderDTO;
import co.uos.customer.entity.customer.Customer;
import co.uos.customer.entity.order.Order;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-14T21:14:06+0100",
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
        customer.orders( orderDTOSetToOrderSet( customerDTO.getOrders() ) );

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
        customerDTO.orders( orderSetToOrderDTOSet( customer.getOrders() ) );

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

    protected Set<Order> orderDTOSetToOrderSet(Set<OrderDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Order> set1 = new LinkedHashSet<Order>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OrderDTO orderDTO : set ) {
            set1.add( orderDTOToOrder( orderDTO ) );
        }

        return set1;
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

    protected Set<OrderDTO> orderSetToOrderDTOSet(Set<Order> set) {
        if ( set == null ) {
            return null;
        }

        Set<OrderDTO> set1 = new LinkedHashSet<OrderDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Order order : set ) {
            set1.add( orderToOrderDTO( order ) );
        }

        return set1;
    }
}
