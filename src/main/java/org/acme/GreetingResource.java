package org.acme;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.preview.ChatSession;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import com.google.cloud.vertexai.generativeai.preview.ResponseHandler;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    String projectId = "seto-goagent0";
    String location = "us-central1";
    String modelName = "gemini-pro-vision";

    @Path("/world")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() throws Exception {
        // Initialize client that will be used to send requests. This client only needs
        // to be created once, and can be reused for multiple requests.
        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerateContentResponse response;

            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            // Create a chat session to be used for interactive conversation.
            ChatSession chatSession = new ChatSession(model);

            response = chatSession.sendMessage("Hello.");
            System.out.println(ResponseHandler.getText(response));

            response = chatSession.sendMessage("What are all the colors in a rainbow?");
            System.out.println(ResponseHandler.getText(response));

            response = chatSession.sendMessage("Why does it appear when it rains?");
            System.out.println(ResponseHandler.getText(response));
            System.out.println("Chat Ended.");
        }

//        Storage storage = StorageOptions.getDefaultInstance().getService();
//        String bucketName = "gemini-data-" + location;
//        Bucket bucket = storage.get(bucketName);
//        System.out.println(bucket);
        return "ok";
    }
}
