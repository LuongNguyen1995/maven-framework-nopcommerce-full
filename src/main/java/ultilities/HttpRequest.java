package ultilities;

import commons.EndPoint;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.Properties;

public class HttpRequest {
    private static final String RESOURCE_PATH = "src/main/java/API/requestBody/";
    private static ConfigReader configReader = new ConfigReader();
    private static Properties prop = configReader.init_prop();
    private static String baseURL = prop.getProperty("apiURL");
    public static String getToken(){
        RestAssured.baseURI = baseURL;
        File file = new File(RESOURCE_PATH + "login.json");
        Response response = RestAssured.given()
                .header("Content-Type","application/json")
                .body(file)
                .post(EndPoint.SIGNIN_ENDPOINT);
        String token = response.getBody().jsonPath().getString("access_token");
        return token;
    }

    public static Response get(String endPoint){
        String token = getToken();
        RestAssured.baseURI = baseURL;
        Response response = RestAssured.given()
                .header("Content-Type","application/json")
                .when()
                .get(endPoint);
        return response;
    }

    public static Response post(String endPoint, String bodyFileName){
        String token = getToken();
        RestAssured.baseURI = baseURL;
        File file = new File(RESOURCE_PATH + bodyFileName);
        Response response = RestAssured.given()
                .header("Content-Type","application/json")
                .header("Bearer", token)
                .body(file)
                .when()
                .post(endPoint);
        return response;
    }

    public static Response put(String endPoint, String bodyFileName){
        String token = getToken();
        RestAssured.baseURI = baseURL;
        File file = new File(RESOURCE_PATH + bodyFileName);
        Response response = RestAssured.given()
                .header("Content-Type","application/json")
                .header("Bearer", token)
                .body(file)
                .when()
                .put(endPoint);
        return response;
    }

    public static Response delete(String endPoint, JSONObject requestParams){
        String token = getToken();
        RestAssured.baseURI = baseURL;
        Response response = RestAssured.given()
                .header("Content-Type","application/json")
                .header("Bearer", token)
                .body(requestParams.toJSONString())
                .when()
                .put(endPoint);
        return response;
    }

}
