package co.uos.customer.config;

import co.uos.customer.batch.CustomerProcessor;
import co.uos.customer.batch.CustomerTasklet;
import co.uos.customer.batch.CustomerWriter;
import co.uos.customer.batch.RestCustomerReader;
import co.uos.customer.dto.customer.CustomerDTO;
import org.hibernate.cfg.Environment;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class BatchConfig {

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
                .tasklet(new CustomerTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<CustomerDTO> restCustomerReader() {
        return new RestCustomerReader("http://localhost:8080/api/v1/customers/list", new RestTemplate());
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