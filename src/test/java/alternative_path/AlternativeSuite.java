package alternative_path;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class AlternativeSuite {

    /**
     * GET Methods
     */

    //This test aims to verify the correct status code when searching for non-existing posts
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void getInvalidPostId() {
        baseURI = "https://jsonplaceholder.typicode.com";
        int invalidId = 500;
        given().
            get("/posts/" + invalidId).
        then().
            statusCode(404).
            log().all();
    }

    /**
     * POST Methods
     */

    //The API is capable of handling null properties
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void postEmptyUserId() {
        baseURI = "https://jsonplaceholder.typicode.com";
        JsonObject request = new JsonObject();
        String userId = null;
        int id = 101;
        request.addProperty("userId", userId);
        request.addProperty("id", id);
        request.addProperty("title", "Wrong Title");
        request.addProperty("body", "Wrong Body");
        given().
            header("Content-Type", "application/json").
            contentType(ContentType.JSON).
            accept(request.toString()).
            body(request).
        when().
            post("/posts").
        then().
            statusCode(201).
            log().all();
    }

    //For this particular test, the API is capable of handling null ids by creating an auto-incremental one
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void postEmptyId() {
        baseURI = "https://jsonplaceholder.typicode.com";
        JsonObject request = new JsonObject();
        int userId = 10;
        String id = null;
        request.addProperty("userId", userId);
        request.addProperty("id", id);
        request.addProperty("title", "Wrong Title");
        request.addProperty("body", "Wrong Body");
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(request.toString()).
                body(request).
                when().
                post("/posts").
                then().
                statusCode(201).
                log().all();
    }

    //The API is capable of handling null properties
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void postEmptyTitle() {
        baseURI = "https://jsonplaceholder.typicode.com";
        JsonObject request = new JsonObject();
        int userId = 10;
        int id = 101;
        String title = null;
        request.addProperty("userId", userId);
        request.addProperty("id", id);
        request.addProperty("title", title);
        request.addProperty("body", "Wrong Body");
        given().
            header("Content-Type", "application/json").
            contentType(ContentType.JSON).
            accept(request.toString()).
            body(request).
        when().
            post("/posts").
        then().
            statusCode(201).
            log().all();
    }

    //The API is capable of handling null properties
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void postEmptyBody() {
        baseURI = "https://jsonplaceholder.typicode.com";
        JsonObject request = new JsonObject();
        int userId = 10;
        int id = 101;
        String body = null;
        request.addProperty("userId", userId);
        request.addProperty("id", id);
        request.addProperty("title", "Wrong Title");
        request.addProperty("body", body);
        given().
            header("Content-Type", "application/json").
            contentType(ContentType.JSON).
            accept(request.toString()).
            body(request).
        when().
            post("/posts").
        then().
            statusCode(201).
            log().all();
    }
    /**
     * PUT Methods
     */

    //This test aims to verify the correct status code when using PUT with a non-existing postsId
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void putInvalidPostId() {
        baseURI = "https://jsonplaceholder.typicode.com";
        JsonObject request = new JsonObject();
        int userId = 8;
        int id = 102;
        int putId = 101;
        request.addProperty("userId",userId);
        request.addProperty("id", id);
        request.addProperty("title", "The Myth of Sisyphus");
        request.addProperty("body", "The Myth of Sisyphus is a 1942 philosophical work by Albert Camus.");
        given().
            header("Content-Type", "application/json").
            contentType(ContentType.JSON).
            accept(request.toString()).
            body(request).
        when().
            put("/posts/" + putId).
        then().
            statusCode(500).
            log().all();
    }

    /**
     * DELETE Methods
     */

    //The API is capable of handling strings in "id"
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void deleteInvalidPostId() {
        baseURI = "https://jsonplaceholder.typicode.com";
        String id = "101";
        when().
            delete("/posts/" + id).
        then().
            statusCode(200);
    }

}
