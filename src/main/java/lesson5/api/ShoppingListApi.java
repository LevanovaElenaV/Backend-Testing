package lesson5.api;

import api.ApiSearchResult;
import api.ApiUserConnectRequest;
import api.ApiUserConnectResult;
import retrofit2.Call;
import retrofit2.http.*;

public interface ShoppingListApi {

    @GET("/recipes/complexSearch")
    Call<ApiSearchResult> findRecipes(
            @Query("apiKey") String apiKey,  // в левой части - названия из интерфейса, в правой - чтобы нам было понятно
            @Query("query") String query,
            @Query("number") Integer number
    );

    @POST("/users/connect")
    Call<ApiUserConnectResult> connect(@Body ApiUserConnectRequest request, @Query("apiKey") String apiKey);

    @POST("/mealplanner/:username/shopping-list/items")
      Call<ApiAddToShoppingListResult> connect(@Body ApiAddToShoppingListRequest request, @Query("apiKey") String apiKey);
    // hash, apiKey, username

//    @GET("/mealplanner/:username/shopping-list")
//    // hash, apiKey, username
//
//    @DELETE("/mealplanner/:username/shopping-list/items/:id")
//    // hash, apiKey, username, id


//    @POST("products")
//    Call<Product> createProduct(@Body Product createProductRequest);
//
//    @DELETE("products/{id}")
//    Call<ResponseBody> deleteProduct(@Path("id") int id);
//
//    @PUT("products")
//    Call<Product> modifyProduct(@Body Product modifyProductRequest);
//
//    @GET("products/{id}")
//    Call<Product> getProductById(@Path("id") int id);
//
//    @GET("products")
//    Call<ResponseBody> getProducts();

}
