package api.engine;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class EndPoints {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com"; //Setting the base url for all API calls
    private static JsonObject request = new JsonObject(); //Creating a JsonObject for use when using POST, PUT & PATCH HTTP methods

    public static JsonObject setJsonObject(int userId, int id, String title, String body) { //Using the JsonObject, properties are set
        request.addProperty("userId", userId);
        request.addProperty("id", id);
        request.addProperty("title", title);
        request.addProperty("body", body);
        return request;
    }

    public static RequestSpecification setRequestHeader() { //Setting the header, content type and accept characteristics
        baseURI = BASE_URL;
        RequestSpecification request = given();
        request.header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(request.toString());
        return request;
    }

    /**
     * From this point onward, the actual functionality of the methods is abstracted to this base class
     */

    public static Response getAllPosts() {
        baseURI = BASE_URL;
        RequestSpecification request = given();
        Response response = request.get("/posts");
        return response;
    }

    public static Response getPost(int targetPostId) {
        baseURI = BASE_URL;
        RequestSpecification request = given();
        Response response = request.get("/posts/" + targetPostId);
        return response;
    }

    public static Response getPostComments(int targetPostId) {
        baseURI = BASE_URL;
        RequestSpecification request = given();
        Response response = request.get("/posts/" + targetPostId + "/comments");
        return response;
    }

    public static Response getCommentsByPost(int targetPostId) {
        baseURI = BASE_URL;
        RequestSpecification request = given();
        Response response = request.queryParam("postId", targetPostId).
                get("/comments");
        return response;
    }

    public static Response postPost(JsonObject json) {
        RequestSpecification request = setRequestHeader().
                given();
        Response response = request.body(json).
                post("/posts");
        return response;
    }

    public static Response putPost(JsonObject json, int targetPostId) {
        RequestSpecification request = setRequestHeader().
                given();
        Response response = request.body(json).
                put("/posts/" + targetPostId);
        return response;
    }

    public static Response patchPost(JsonObject json, int targetPostId) {
        RequestSpecification request = setRequestHeader().
                given();
        Response response = request.body(json).
                patch("/posts/" + targetPostId);
        return response;
    }

    public static Response deletePost(int targetPostId) {
        baseURI = BASE_URL;
        RequestSpecification request = given();
        Response response = request.delete("/posts/" + targetPostId);
        return response;
    }

}
