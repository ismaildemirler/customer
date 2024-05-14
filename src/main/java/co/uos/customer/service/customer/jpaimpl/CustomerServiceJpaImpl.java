package co.uos.customer.service.customer.jpaimpl;

import co.uos.customer.dto.customer.CustomerDTO;
import co.uos.customer.entity.customer.Customer;
import co.uos.customer.mapper.customer.CustomerMapper;
import co.uos.customer.repository.customer.CustomerJpaRepository;
import co.uos.customer.service.customer.CustomerService;
import co.uos.customer.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceJpaImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerJpaRepository customerRepository;

    @Override
    public CustomerDTO getCustomer(Integer customerId) {
        return customerMapper.toDto(customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no customer registered by this customer id!")));
    }

    @Override
    public List<CustomerDTO> getCustomerList() {
        List<Customer> customerList = customerRepository.findAll();
        if(customerList.isEmpty()) {
            throw new ResourceNotFoundException("There is no customers!");
        }
        return customerMapper.toListDto(customerList);
    }

    @Override
    public List<CustomerDTO> getCustomerList(Boolean status) {
        List<Customer> customerList = customerRepository.findAllByStatus(status);
        if(customerList.isEmpty()) {
            throw new ResourceNotFoundException(String.format("There is no %s customers!", status ? "active" : "archive"));
        }
        return customerMapper.toListDto(customerList);
    }
}
