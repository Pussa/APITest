package requests;

import com.google.gson.Gson;
import models.AllFacts;
import models.Fact;
import models.FactsInformation;
import models.User;
import org.apache.http.HttpStatus;

public class FactRequest {
    public static Fact getFact(String id) {
        String response = PrepareRequest.prepareRequest().get("facts/" + id).then()
                .statusCode(HttpStatus.SC_OK).assertThat()
                .extract().asString();
        return new Gson().fromJson(response, Fact.class);

    }
}
