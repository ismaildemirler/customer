package co.uos.customer.dto.customer;

import co.uos.customer.dto.DTOModel;
import co.uos.customer.dto.order.OrderDTO;
import co.uos.customer.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements DTOModel {

    private Integer customerId;
    private String firstname;
    private String surname;
    private String email;
    private String address;
    private String zipCode;
    private String region;
    private Boolean status;
    private Set<OrderDTO> orders;

    public CustomerStatus getCustomerStatus(Boolean status) {
        return status ? CustomerStatus.ACTIVE : CustomerStatus.ARCHIVE;
    }
}
