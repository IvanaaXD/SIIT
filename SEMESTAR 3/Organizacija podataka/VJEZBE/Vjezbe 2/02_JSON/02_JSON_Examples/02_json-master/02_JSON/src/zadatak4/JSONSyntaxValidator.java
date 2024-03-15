package zadatak4;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONSyntaxValidator {
    public static boolean isJSONSyntaxValid(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        try {
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        String filePath = "resources/Example1_Book.json";

        if (isJSONSyntaxValid(filePath)) {
            System.out.println("The JSON syntax is valid.");
        } else {
            System.out.println("The JSON syntax is invalid.");
        }
    }
}

