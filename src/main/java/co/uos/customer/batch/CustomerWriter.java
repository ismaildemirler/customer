package co.uos.customer.batch;

import co.uos.customer.dto.customer.CustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RequiredArgsConstructor
public class CustomerWriter implements ItemWriter<CustomerDTO> {

    @Override
    public void write(Chunk<? extends CustomerDTO> chunk) throws Exception {
        log.info("Writing: {}", chunk.getItems().size());

    }
}
