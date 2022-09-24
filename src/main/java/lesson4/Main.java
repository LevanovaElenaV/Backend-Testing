package lesson4;
import api.ApiSearchResult;
import api.ApiUserConnectRequest;
import api.ApiUserConnectResult;
import api.SpoonaccularService;

public class Main {
    public static void main(String[] args) {

//        private static ApiUserConnectResult apiUserConnectResult;
//        private static Integer idForRemove;

        SpoonaccularService spoonaccularService = new SpoonaccularService();
        ApiSearchResult recipes = spoonaccularService.findRecipes("Bread", 3);

        ApiUserConnectRequest apiUserConnectRequest = ApiUserConnectRequest.builder()
//                .userName("LenaL")
//                .firstName("Lena")
//                .lastName("L")
//                .email("enalevster@gmail.com")
                .build();
        ApiUserConnectResult apiUserConnectResult = spoonaccularService.connect(apiUserConnectRequest); // здесь валится
//        System.out.println(apiUserConnectResult);

        }
}
