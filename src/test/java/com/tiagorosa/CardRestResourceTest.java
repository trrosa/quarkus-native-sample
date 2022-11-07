package com.tiagorosa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiagorosa.model.Card;
import com.tiagorosa.repository.CardRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CardRestResourceTest {

    private static Card card;

    @Inject
    private CardRepository repository;

    @Inject
    private ObjectMapper mapper;

    @BeforeAll
    static void beforeAll() {
        CardRestResourceTest.card = new Card();
        card.setIssuer("Sicredi");
        card.setCardFlag("Mastercard");
    }

    @Test
    @Order(1)
    public void testCreate() {
        given().contentType(ContentType.JSON).body(card)
                .when().post("/cards")
                .then()
                .statusCode(204);
        List<Card> all = repository.list();
        Assertions.assertEquals(1, all.size());
        Card c = all.get(0);
        Assertions.assertNotNull(c.getId());
        card.setId(c.getId());
    }

    @Test
    @Order(2)
    public void testUpdate() throws JsonProcessingException {
        card.setIssuer("XP");
        given().contentType(ContentType.JSON).body(card)
                .when().put("/cards/{id}", card.getId())
                .then()
                .statusCode(200)
                .body(Matchers.is(mapper.writeValueAsString(card)));
    }

}