package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru/api/auth";

    // Регистрация пользователя
    public Response register(UserData user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(BASE_URL + "/register");
    }

    // Удаление пользователя (требуется токен авторизации)
    public Response delete(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .delete(BASE_URL + "/user");
    }

    // Вход в систему (получаем токен)
    public Response login(UserData user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(BASE_URL + "/login");
    }
}