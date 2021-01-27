import models.AllFacts;
import models.Fact;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import requests.FactsRequest;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static requests.FactRequest.getFact;

public class MainTest {

    @Test
    public void test() throws NullPointerException {
        given().when().get("https://cat-fact.herokuapp.com/facts").then().statusCode(200);

        AllFacts allFacts = new AllFacts();
        allFacts.setAllFacts(FactsRequest.getFacts());

        List<Fact> users = allFacts.getAllFacts().stream().map(fact -> getFact(fact.getFactId()))
                .collect(Collectors.toList());

        for (int i = 0; i < allFacts.allFacts.size(); i++)
            System.out.println(allFacts.allFacts.get(i).fact);

        Fact mvp = users.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();

        Fact fact = new Fact();
        fact.user.name.first = "Kasimir";
        fact.user.name.last = "Schulz";
        AssertJUnit.assertEquals(fact, mvp);
    }
}