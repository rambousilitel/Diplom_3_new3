package praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String USER_PATH = "/api/auth/user";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    @Step("Создать тестового пользователя через API: {user.email}")
    public ValidatableResponse createUser(UserApi user) {
        return given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then();
    }

    @Step("Удалить тестового пользователя через API")
    public void deleteUser(String accessToken) {
        if (accessToken == null) {
            return;
        }

        given()
                .filter(new AllureRestAssured())
                .header("Authorization", accessToken)
                .when()
                .delete(USER_PATH)
                .then()
                .statusCode(202); // можно ослабить до any
    }
}
