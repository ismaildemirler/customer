package co.uos.customer.batch;

import co.uos.customer.dto.customer.CustomerDTO;
import co.uos.customer.infra.ServiceResponse;
import co.uos.customer.mapper.customer.CustomerMapper;
import co.uos.customer.payload.CustomerPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomerTasklet implements Tasklet, StepExecutionListener {

    int counter = 0;
    private ObjectMapper objectMapper;

    private final CustomerMapper customerMapper;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        StringBuilder sourceUrl = new StringBuilder("http://localhost:8080/api/v1/customers/list/active");
        StringBuilder targetUrl = new StringBuilder("https://35a74e98-a90d-40a5-9680-9adcdc4f7016.mock.pstmn.io/customers");

        ServiceResponse serviceResponse;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try(FileWriter fileWriter = new FileWriter("output.json")) {

            HttpGet getRequest = new HttpGet(sourceUrl.toString());
            getRequest.addHeader("Accept", "application/json");

            CloseableHttpResponse getResponse = httpClient.execute(getRequest);
            HttpEntity entity = getResponse.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                serviceResponse = objectMapper.readValue(result, ServiceResponse.class);

                List<CustomerDTO> data = (List<CustomerDTO>)serviceResponse.getData();

                final String json = objectMapper.writeValueAsString(data);
                final StringEntity stringEntity = new StringEntity(json);

                HttpPost postRequest = new HttpPost(targetUrl.toString());
                postRequest.setEntity(stringEntity);
                postRequest.setHeader("Accept", "application/json");
                postRequest.setHeader("Content-type", "application/json");
                CloseableHttpResponse postRsponse = httpClient.execute(postRequest);
                HttpEntity postEntity = postRsponse.getEntity();
                String postResult = EntityUtils.toString(postEntity);
                System.out.println(postResult);
                fileWriter.write(postResult);
            }

        } catch (IOException exception) {
            System.out.printf("io exception happened %s%n", exception);
        } catch (Exception exception) {
            System.out.printf("general exception happened %s%n", exception);
        }

        if (counter == 5) {
            counter = 0;
            return RepeatStatus.FINISHED;
        } else {
            counter++;
            return RepeatStatus.CONTINUABLE;
        }
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        //logger.info("Before step execution");
        objectMapper = new ObjectMapper();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        try {

        } catch(Exception exception) {
            //logger.error("exception ");
        }
        return null;
    }
}
