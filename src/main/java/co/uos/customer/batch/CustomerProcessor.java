package co.uos.customer.batch;

import co.uos.customer.dto.customer.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class CustomerProcessor implements ItemProcessor<CustomerDTO, CustomerDTO> {
    @Override
    public CustomerDTO process(CustomerDTO item) throws Exception {
        log.info("Process author: {}", item);
        item.setCustomerId(item.getCustomerId());
        return item;
    }
}