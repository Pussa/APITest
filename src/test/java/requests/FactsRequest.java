package requests;

import com.google.gson.Gson;
import models.FactsInformation;
import org.apache.http.HttpStatus;

public class FactsRequest {
    private static String path = "facts";

    public static FactsInformation[] getFacts() {
        String response = PrepareRequest.prepareRequest().get(path).then().statusCode(HttpStatus.SC_OK).assertThat()
                .extract().asString();
        return new Gson().fromJson(response, FactsInformation[].class);

    }
}
