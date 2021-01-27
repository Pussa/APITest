package requests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.FactsInformation;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.List;

public class FactsRequest {
    private static String path = "facts";

    public static List getFacts() {
        String response = PrepareRequest.prepareRequest().get(path).then().statusCode(HttpStatus.SC_OK).assertThat()
                .extract().asString();
        Type listType = new TypeToken<List<FactsInformation>>(){}.getType();
        return new Gson().fromJson(response, listType);
    }
}
