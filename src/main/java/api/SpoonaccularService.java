package api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;


public class SpoonaccularService {
    private SpoonaccularApi api;
    private static final String API_KEY = "be91867c87be4cce82c4445e0240dc5b";


    public SpoonaccularService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/") // у Retrofit Url должны оканчиваться на слэш
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(SpoonaccularApi.class);
        // реализацию для этого интерфейса создаст retrofit
    }


    public ApiSearchResult findRecipes(String query, Integer number) {
        Call<ApiSearchResult> call = api.findRecipes(API_KEY, query, number);
        return RetrofitUtils.execute(call);
    }

    public ApiUserConnectResult connect(ApiUserConnectRequest request) {
        Call<ApiUserConnectResult> call = api.connect(request, API_KEY);
        return RetrofitUtils.execute(call);
    }

}
