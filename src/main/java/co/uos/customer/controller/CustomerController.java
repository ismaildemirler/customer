package co.uos.customer.controller;

import co.uos.customer.dto.customer.CustomerDTO;
import co.uos.customer.infra.ServiceResponse;
import co.uos.customer.service.customer.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
@Tag(name = "Customer", description = "Customer Service Documentation")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    @Operation(summary = "Get Customer", description = "Finding Customer By CustomerId Method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Has Been Successfully Retrieved!")
    })
    public ResponseEntity<ServiceResponse> getCustomer(@PathVariable Integer customerId) {
        return ResponseEntity.ok(ServiceResponse.builder()
                .success(true)
                .message("Customer Has Been Successfully Retrieved!")
                .data(customerService.getCustomer(customerId))
                .build());
    }

    @GetMapping
    @Operation(summary = "Get All Customers", description = "Listing All Customers Optional Status Method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer List Has Been Successfully Fetched!")
    })
    public ResponseEntity<ServiceResponse> getCustomers() {
        List<CustomerDTO> customerList = customerService.getCustomerList();
        return ResponseEntity.ok(ServiceResponse.builder()
                .success(true)
                .message("Customer List Has Been Successfully Fetched!")
                .data(customerList)
                .build());
    }
}
