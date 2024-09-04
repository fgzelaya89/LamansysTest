package tests;

import ApiObject.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class PetstoreTest {
    private String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void findPetsByStatus() {
        Response response = RestAssured.get(BASE_URL + "/pet/findByStatus?status=available");
        assertEquals(response.getStatusCode(), 200, "Expected 200 OK response");
    }

    @Test
    public void createUser() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        User userSend = new User();
        userSend.setId(1989);
        userSend.setEmail("fg@gmail.com");
        userSend.setUsername("Fernando");
        userSend.setFirstName("Fernando");
        userSend.setLastName("Zelaya");
        userSend.setPassword("123qwer");
        userSend.setPhone("351123123");
        userSend.setUserStatus(0);

        request.body(userSend);
        Response response = request.post(BASE_URL + "/user");
        // You can print or log the response if needed
        System.out.println("response:\n\t" + response.asString());
        response.then().statusCode(200);
        // Validar que el campo "code" en el JSON de respuesta sea 200
        int code = response.jsonPath().getInt("code");
        Assert.assertEquals(code, 200, "El código de respuesta no es 200");

    }
}
