package api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import retrofit2.http.Body;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiUserConnectResult {
    private String status;
    private String username;
    private String spoonacularPassword;
    private String hash;
}
