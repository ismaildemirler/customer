package co.uos.customer.dto.order;

import co.uos.customer.dto.DTOModel;
import co.uos.customer.dto.customer.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements DTOModel {

    private Integer orderId;
    private Date date;
    private CustomerDTO customerId;
    private Double amount;
}
