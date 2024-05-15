package co.uos.customer.mapper.customer;

import co.uos.customer.dto.customer.CustomerDTO;
import co.uos.customer.entity.customer.Customer;
import co.uos.customer.mapper.BaseMapper;
import co.uos.customer.payload.CustomerPayload;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends BaseMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toEntity(CustomerDTO customerDTO);

    CustomerDTO toDto(Customer customer);

    List<CustomerDTO> toListDto(List<Customer> Customers);

    CustomerPayload toPayload(CustomerDTO customerDTO);

    List<CustomerPayload> toListPayload(List<CustomerDTO> CustomerDTO);

    @AfterMapping
    default void setName(@MappingTarget CustomerPayload customerPayload, CustomerDTO customerDTO) {
        customerPayload.setName(customerDTO.getFirstname() + " " + customerDTO.getSurname());
    }
}
