package Chatbot.LLM;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import Chatbot.CustomADT.HashMap;
import Chatbot.util.EnvLoader;

import org.json.JSONObject;



public class LLMApiClient implements LLMApiClientIF {

	
	/*
	 * This method sends a request to the LLM API and returns the response.
	 * It uses the Gemini API for generating content.
	 * The method constructs a JSON request body with the prompt,
	 * 
	 * @param prompt The prompt to send to the LLM API.
	 * @return The response from the LLM API.
	 */
    @Override
    public String callLLM(String prompt) {
    	
    	HashMap<String, String> env = EnvLoader.loadEnv(".env");
        String API_KEY = env.get("GEMINI_API_KEY");
    	
        // API endpoint
        String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY;

        // Prepare the request body
        String requestBody = String.format("{\"contents\": [{\"parts\": [{\"text\": \"%s\"}]}]}", prompt);

     // Initialize the response variable
        String jsonResponse = ""; 
        
        try {
        	// Create URL object
            URL url = new URL(apiUrl);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Send the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read the response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                jsonResponse =  response.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to connect to the API.";
        }
        
        
        // parse the JSON response
        JSONObject responseJson = new JSONObject(jsonResponse);
        
        // extract the bot response
        String botResponse = responseJson
                .getJSONArray("candidates")
                .getJSONObject(0)        
                .getJSONObject("content") 
                .getJSONArray("parts")    
                .getJSONObject(0)        
                .getString("text");     
        // print the bot response
//        System.out.println(botResponse);
        // return the bot response
        return botResponse;
    }
    
//    // Main method for testing
//    public static void main(String[] args) {
//        // Create an instance of LLMApiClient
//        LLMApiClient client = new LLMApiClient();
//
//        // Sample prompt for testing
//        String prompt = "Explain how AI works in a few words";
//
//        // Call the LLM API and print the response
//        String response = client.callLLM(prompt);
//        System.out.println("Response from LLM API: " + response);
//    }
}
