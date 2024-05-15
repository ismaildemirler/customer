package co.uos.customer.config;

import co.uos.customer.batch.CustomerProcessor;
import co.uos.customer.batch.CustomerTasklet;
import co.uos.customer.batch.CustomerWriter;
import co.uos.customer.dto.customer.CustomerDTO;
import co.uos.customer.mapper.customer.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final CustomerMapper customerMapper;

    @Bean
    public Job customerReaderJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("customerReadJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(taskletStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step taskletStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("taskletStep", jobRepository)
                .tasklet(new CustomerTasklet(customerMapper), platformTransactionManager)
                .build();
    }

    @Bean
    @StepScope
    public ItemWriter<CustomerDTO> writer() {
        return new CustomerWriter();
    }

    @Bean
    @StepScope
    public ItemProcessor<CustomerDTO, CustomerDTO> processor() {
        CompositeItemProcessor<CustomerDTO, CustomerDTO> processor = new CompositeItemProcessor<>();
        processor.setDelegates(List.of(new CustomerProcessor()));
        return processor;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}