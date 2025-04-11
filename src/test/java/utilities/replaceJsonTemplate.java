package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.Map;

public class replaceJsonTemplate {

    @Test
    public static void replaceJson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String eventString = "{\n" +
                "   \"event\":\"conversation_started\",\n" +
                "   \"context\":\"context information\",\n" +
                "   \"user\":{\n" +
                "      \"id\":\"01234567890A=\",\n" +
                "      \"name\":\"John McClane\",\n" +
                "      \"avatar\":\"http://avatar.example.com\",\n" +
                "      \"country\":\"UK\",\n" +
                "      \"language\":\"en\",\n" +
                "      \"api_version\":1\n" +
                "   },\n" +
                "   \"subscribed\":false\n" +
                "}";

        JsonNode jsonNode = objectMapper.readTree(eventString);
        String eventType = jsonNode.get("event").asText();
        Map<String, Object> map = objectMapper.readValue(eventString, new TypeReference<Map<String,Object>>(){});

        Map<String, Object> user = (Map<String, Object>) map.get("user");

        System.out.println( "conversation started - avatar is " + user.get("avatar"));
    }
}
