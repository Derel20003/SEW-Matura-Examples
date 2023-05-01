package at.htl.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.*;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestHTTPEndpoint(CustomerResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerResourceTest {

    JsonObject customer;

    @BeforeEach
    void setUp() {
        customer = new JsonObject()
                .put("id", 0)
                .put("password", "string")
                .put("first_name", "string")
                .put("last_name", "string")
                .put("birth_date", "2022-03-10")
                .put("street", "string")
                .put("house_number", 0)
                .put("city", "string")
                .put("zip_code", "string")
                .put("email", "string")
                .put("tel_number", "string");
    }

    @Test
    @Order(1)
    void getCustomer() {
        // NOTE: when trying to run test with customer´s own id instead of fixed id, JsonException occurs while parsing
        // customer exists
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", -1)
            .when().get("/{id}")
            .then()
                .statusCode(200)
                .body("last_name", is("Muster"))
                .body("size()", is(11));
    }

    @Test
    @Order(2)
    void postCustomer() {
        // customer is added correctly
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer.toString())
            .when().post()
            .then()
                .statusCode(201);
    }

    @Test
    @Order(3)
    void putCustomer() {
        // NOTE: when trying to run test with customer´s own id instead of fixed id, JsonException occurs while parsing
        // update existing customer, change name
        customer.remove("id");
        customer.remove("last_name");
        customer.put("last_name", "Meister");
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer.toString())
                .pathParam("id", -1)
            .when().put("/{id}")
            .then()
                .statusCode(200)
                .body("last_name", is("Meister"))
                .body("size()", is(11));
    }

    @Test
    @Order(4)
    void deleteCustomer() {
        // NOTE: when trying to run test with customer´s own id instead of fixed id, JsonException occurs while parsing
        // delete existing customer
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", -1)
            .when().delete("/{id}")
            .then()
                .statusCode(200)
                .body(is("Customer successfully deleted"));
    }
}