package api;

import retrofit2.Call;
import retrofit2.http.*;

public interface SpoonaccularApi {

    @GET("/recipes/complexSearch")
    Call<ApiSearchResult> findRecipes(
            @Query("apiKey") String apiKey,  // в левой части - названия из интерфейса, в правой - чтобы нам было понятно
            @Query("query") String query,
            @Query("number") Integer number
            );

    @POST("/users/connect")
    Call<ApiUserConnectResult> connect(@Body ApiUserConnectRequest request, @Query("apiKey") String apiKey);

//    @POST("/mealplanner/:username/shopping-list/items")
//      Call<ApiAddToShoppingListResult> connect(@Body ApiAddToShoppingListRequest request, @Query("apiKey") String apiKey);
//    // hash, apiKey, username
//
//    @GET("/mealplanner/:username/shopping-list")
//    // hash, apiKey, username
//
//    @DELETE("/mealplanner/:username/shopping-list/items/:id")
//    // hash, apiKey, username, id

}
