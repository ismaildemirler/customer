package co.uos.customer.dto.customer;

import co.uos.customer.dto.DTOModel;
import co.uos.customer.dto.order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String status;
    private List<OrderDTO> orders;
}
