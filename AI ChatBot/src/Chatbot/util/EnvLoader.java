package Chatbot.util;

import java.io.*;
import Chatbot.CustomADT.*;

public class EnvLoader {
	
	/**
	 * Loads environment variables from a .env file.
	 * @param filePath
	 * @return
	 */
    public static HashMap<String, String> loadEnv(String filePath) {
    	HashMap<String, String> envMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    envMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load .env file: " + e.getMessage());
        }
        return envMap;
    }
    
    
    // Example usage
//	public static void main(String[] args) {
//		HashMap<String, String> env = loadEnv(".env");
//		System.out.println("Loaded environment variables:");
//		for (Object key : env.keys()) {
//			System.out.println((String)key + " = " + env.get((String)key));
//		}
//	}
}
