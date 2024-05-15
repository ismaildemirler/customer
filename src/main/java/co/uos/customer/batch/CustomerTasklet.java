package co.uos.customer.batch;

import co.uos.customer.infra.ServiceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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

public class CustomerTasklet implements Tasklet, StepExecutionListener {

    int counter = 0;
    private ObjectMapper objectMapper;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        StringBuilder courseraUrl = new StringBuilder("http://localhost:8080/api/v1/customers/list");

        ServiceResponse serviceResponse = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try(FileWriter fileWriter = new FileWriter("output.json")) {

            HttpGet request = new HttpGet(courseraUrl.toString());
            request.addHeader("Accept", "application/json");

            CloseableHttpResponse response = httpClient.execute(request);
            System.out.println("response body " + response.getAllHeaders());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                serviceResponse = objectMapper.readValue(result, ServiceResponse.class);
                fileWriter.write(objectMapper.writeValueAsString(serviceResponse.getData()));

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
