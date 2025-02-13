package alternative.path;

import api.engine.EndPoints;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class AlternativeSuite {

    private static Response response;
    private static JsonObject customJson;

    /**
     * GET Methods
     */

    //This test aims to verify the correct status code when searching for non-existing posts
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void getInvalidPostId() {
        int invalidId = 500;
        response = EndPoints.getPost(invalidId);
        response.then().
                statusCode(404).
                log().all();
    }

    /**
     * POST Methods
     */

    //The API is capable of handling null properties
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void postEmptyUserId() {
        JsonObject request = new JsonObject();
        String userId = null;
        int id = 101;
        request.addProperty("userId", userId);
        request.addProperty("id", id);
        request.addProperty("title", "Wrong Title");
        request.addProperty("body", "Wrong Body");
        response = EndPoints.postPost(request);
        response.then().
                statusCode(201).
                log().all();
    }

    //For this particular test, the API is capable of handling null ids by creating an auto-incremental one
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void postEmptyId() {
        JsonObject request = new JsonObject();
        int userId = 10;
        String id = null;
        request.addProperty("userId", userId);
        request.addProperty("id", id);
        request.addProperty("title", "Wrong Title");
        request.addProperty("body", "Wrong Body");
        response = EndPoints.postPost(request);
        response.then().
                statusCode(201).
                log().all();
    }

    //The API is capable of handling null properties
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void postEmptyTitle() {
        JsonObject request = new JsonObject();
        int userId = 10;
        int id = 101;
        String title = null;
        request.addProperty("userId", userId);
        request.addProperty("id", id);
        request.addProperty("title", title);
        request.addProperty("body", "Wrong Body");
        response = EndPoints.postPost(request);
        response.then().
                statusCode(201).
                log().all();
    }

    //The API is capable of handling null properties
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void postEmptyBody() {
        JsonObject request = new JsonObject();
        int userId = 10;
        int id = 101;
        String body = null;
        request.addProperty("userId", userId);
        request.addProperty("id", id);
        request.addProperty("title", "Wrong Title");
        request.addProperty("body", body);
        response = EndPoints.postPost(request);
        response.then().
                statusCode(201).
                log().all();
    }

    /**
     * PUT Methods
     */

    //This test aims to verify the correct status code when using PUT with a non-existing postsId
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void putInvalidPostId() {
        JsonObject request = new JsonObject();
        int userId = 8;
        int id = 102;
        String title = "The War of the Worlds";
        String body = "The War of the Worlds is a science fiction novel by English author H. G. Wells.";
        int putId = 101;
        customJson = EndPoints.setJsonObject(userId, id, title, body);
        response = EndPoints.putPost(request, putId);
        response.then().
                statusCode(500).
                log().all();
    }

    /**
     * DELETE Methods
     */

    //The API is capable of handling non-existing ids
    @Test(threadPoolSize = 2, invocationCount = 1, timeOut = 10000)
    public void deleteInvalidPostId() {
        int deleteId = 150;
        response = EndPoints.deletePost(deleteId);
        response.then().
                statusCode(200);
    }

}
