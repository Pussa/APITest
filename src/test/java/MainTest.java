import models.FactsInformation;
import models.RetrofitInterface;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.List;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class MainTest {

    String response;
    List<String> ids;

    @Test
    public void test() throws NullPointerException {
        given().when().get("https://cat-fact.herokuapp.com/facts").then().statusCode(200);
        response = get("https://cat-fact.herokuapp.com/facts").asString();
        ids = from(response).getList("findAll{it.deleted == false}._id");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cat-fact.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FactsInformation fact = null;
        RetrofitInterface service = retrofit.create(RetrofitInterface.class);
        showFactsAndAuthors(service, fact);
    }

    public void showFactsAndAuthors(RetrofitInterface service, FactsInformation fact) {
        for (int i = 0; i < ids.size(); i++) {
            Call<FactsInformation> call = service.listRepos(ids.get(i));
            try {
                retrofit2.Response<FactsInformation> response = call.execute();
                fact = response.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fact.showInfo();
        }
    }
}





