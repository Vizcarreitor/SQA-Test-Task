package critical_path;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class CriticalSuite {

    /**
     * GET Methods
     */

    //This test aims to verify the correct amount of posts
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void get100Posts() {
        baseURI = "https://jsonplaceholder.typicode.com";
        given().
            get("/posts").
        then().
            statusCode(200).
            body("findall.size()", equalTo(100)). //Expecting a result of 100 posts
            log().all();
    }

    //This test aims to verify the correct post has been retrieves by using the value of its title
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void getSinglePostById() {
        baseURI = "https://jsonplaceholder.typicode.com";
        int postId = 1;
        given().
            get("/posts/"+postId).
        then().
            statusCode(200).
            body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")).
            log().all();
    }

    //This test aims to verify the correct amount of comments in the specified post
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void getSinglePostComments() {
        baseURI = "https://jsonplaceholder.typicode.com";
        int postId = 50;
        given().
            get("/posts/"+postId+"/comments").
        then().
            statusCode(200).
            body("findall.size()", equalTo(5)).
            log().all();
    }

    //This test aims to verify the correct amount of comments in the specified post
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void getCommentsByPostId() {
        baseURI = "https://jsonplaceholder.typicode.com";
        int postId = 100;
        given().
            queryParam("postId", postId).
            get("/comments").
        then().
            statusCode(200).
            body("findall.size()", equalTo(5)).
            log().all();
    }

    /**
     * POST Methods
     */

    //This test aims to verify the correct POST of the given post
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void postSinglePost() {
        baseURI = "https://jsonplaceholder.typicode.com";
        JsonObject request = new JsonObject();
        int userId = 10;
        int id = 101;
        request.addProperty("userId",userId);
        request.addProperty("id", id);
        request.addProperty("title", "The Sorrows of Young Werther");
        request.addProperty("body", "The Sorrows of Young Werther or simply Werther, is a 1774 epistolary novel by Johann Wolfgang Goethe.");
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

    //This test aims to verify the correct PUT of the given post
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void putSinglePost() {
        baseURI = "https://jsonplaceholder.typicode.com";
        JsonObject request = new JsonObject();
        int userId = 8;
        int id = 102;
        int putId = 1;
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
            statusCode(200).
            log().all();
    }

    /**
     * PATCH Methods
     */

    //This test aims to verify the correct PATCH of the given post
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void patchSinglePost() {
        baseURI = "https://jsonplaceholder.typicode.com";
        JsonObject request = new JsonObject();
        int userId = 8;
        int id = 102;
        int patchId = 1;
        request.addProperty("userId",userId);
        request.addProperty("id", id);
        request.addProperty("title", "The Myth of Sisyphus");
        request.addProperty("body", "The Myth of Sisyphus (French: Le mythe de Sisyphe) is a 1942 philosophical work by Albert Camus.");
        given().
            header("Content-Type", "application/json").
            contentType(ContentType.JSON).
            accept(request.toString()).
            body(request).
        when().
            patch("/posts/" + patchId).
        then().
            statusCode(200).
            log().all();
    }

    /**
     * DELETE Methods
     */

    //This test aims to verify the correct deletion of the specified post
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void deleteSinglePost() {
        baseURI = "https://jsonplaceholder.typicode.com";
        int id = 5;
        when().
            delete("/posts/" + id).
        then().
            statusCode(200);
    }

}
