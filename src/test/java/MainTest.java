import org.testng.annotations.Test;
import java.util.Collections;
import java.util.List;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class MainTest {

    String response;
    List<String> facts;
    List<String> users;
    int number_of_ks;

    @Test
    public void test() {
        given().when().get("https://cat-fact.herokuapp.com/facts").then().statusCode(200);
        response = get("https://cat-fact.herokuapp.com/facts").asString();
        facts = from(response).getList("findAll{it.deleted == false}.text");
        users = from(response).getList("findAll{it.deleted == false}.user");
        number_of_ks = Collections.frequency(users, "58e007480aac31001185ecef");
        if (number_of_ks > users.size() / 2)
            System.out.println("Kasimir is mvp \n" + facts + "\n" + users);
        else
            System.out.println("Kasimir isn't mvp  \n" + facts + "\n" + users);
    }
}
