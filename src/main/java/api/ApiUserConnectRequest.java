package api;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ApiUserConnectRequest {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
}
