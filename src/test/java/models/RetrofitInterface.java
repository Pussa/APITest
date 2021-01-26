package models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @GET("facts/{factId}")
    Call<FactsInformation> listRepos(@Path("factId") String factId);
}
