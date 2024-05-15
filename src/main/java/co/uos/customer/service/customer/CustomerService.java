package co.uos.customer.service.customer;

import co.uos.customer.dto.customer.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO getCustomer(Integer customerId);

    List<CustomerDTO> getCustomerList(String status);
}
