package critical.path;

import api.engine.EndPoints;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class CriticalSuite {

    private static Response response;
    private static JsonObject customJson;

    /**
     * GET Methods
     */

    //This test aims to verify the correct amount of posts
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void verify100Posts() {
        response = EndPoints.getAllPosts();
        response.then().
                statusCode(200).
                body("findall.size()", equalTo(100)). //Expecting a result of 100 posts
                log().all();
    }

    //This test aims to verify the correct post has been retrieves by using the value of its title
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void getSinglePostById() {
        int postId = 1;
        response = EndPoints.getPost(postId);
        response.then().
                statusCode(200).
                body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")).
                log().all();
    }

    //This test aims to verify the correct amount of comments in the specified post
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void getSinglePostComments() {
        int postId = 50;
        response = EndPoints.getPostComments(postId);
        response.then().
                statusCode(200).
                body("findall.size()", equalTo(5)).
                log().all();
    }

    //This test aims to verify the correct amount of comments in the specified post
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void getCommentsByPostId() {
        int postId = 100;
        response = EndPoints.getCommentsByPost(postId);
        response.then().
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
        int userId = 10;
        int id = 101;
        String title = "The Sorrows of Young Werther";
        String body = "The Sorrows of Young Werther or simply Werther, is a 1774 epistolary novel by Johann Wolfgang Goethe.";
        customJson = EndPoints.setJsonObject(userId, id, title, body);
        response = EndPoints.postPost(customJson);
        response.then().
                statusCode(201).
                log().all();
    }

    /**
     * PUT Methods
     */

    //This test aims to verify the correct PUT of the given post
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void putSinglePost() {
        int userId = 8;
        int id = 102;
        String title = "The Myth of Sisyphus";
        String body = "The Myth of Sisyphus is a 1942 philosophical work by Albert Camus.";
        int putId = 1;
        customJson = EndPoints.setJsonObject(userId, id, title, body);
        response = EndPoints.putPost(customJson, putId);
        response.then().
                statusCode(200).
                log().all();
    }

    /**
     * PATCH Methods
     */

    //This test aims to verify the correct PATCH of the given post
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void patchSinglePost() {
        JsonObject request = new JsonObject();
        int userId = 8;
        int id = 102;
        String title = "Nineteen Eighty-Four";
        String body = "Nineteen Eighty-Four is a dystopian novel and cautionary tale by English writer George Orwell.";
        int patchId = 1;
        customJson = EndPoints.setJsonObject(userId, id, title, body);
        response = EndPoints.patchPost(request, patchId);
        response.then().
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
        int deleteId = 5;
        response = EndPoints.deletePost(deleteId);
        response.then().
                statusCode(200);
    }

}
