package co.uos.customer.payload;

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
public class CustomerPayload {

    private Integer customerId;
    private String name;
    private String firstname;
    private String surname;
    private String email;
    private String address;
    private String zipCode;
    private String region;
    private String status;
    private List<OrderDTO> orders;
}
