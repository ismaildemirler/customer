package co.uos.customer.infra;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse {

    private boolean success;
    private String message;
    private Object error;
    private Object data;
}