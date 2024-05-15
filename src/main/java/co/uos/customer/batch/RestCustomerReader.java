package co.uos.customer.batch;

import co.uos.customer.dto.customer.CustomerDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RestCustomerReader implements ItemReader<CustomerDTO> {

    private final String url;
    private final RestTemplate restTemplate;

    public RestCustomerReader(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    private int nextCustomer;
    private List<CustomerDTO> customerList;

    @Override
    public CustomerDTO read() throws Exception {
        if (this.customerList == null) {
            customerList = fetchCustomers();
        }
        CustomerDTO customer = null;

        if (nextCustomer < customerList.size()) {
            customer = customerList.get(nextCustomer);
            nextCustomer++;
        } else {
            nextCustomer = 0;
            customerList = null;
        }
        return customer;
    }

    private List<CustomerDTO> fetchCustomers() {
        ResponseEntity<CustomerDTO[]> response = restTemplate.getForEntity(this.url, CustomerDTO[].class);
        CustomerDTO[] customers = response.getBody();
        if (customers != null) {
            return Arrays.asList(customers);
        }
        return null;
    }
}
