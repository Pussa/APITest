import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class MainTest {

    String response;
    List<String> facts;
    List<String> users;
    String mvp;

    @Test
    public void test() {
        given().when().get("https://cat-fact.herokuapp.com/facts").then().statusCode(200);
        response = get("https://cat-fact.herokuapp.com/facts").asString();
        facts = from(response).getList("findAll{it.deleted == false}.text");
        users = from(response).getList("findAll{it.deleted == false}.user");
        mvp = users.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
        System.out.println(mvp + "\n" + facts + "\n" + users);
    }
}
