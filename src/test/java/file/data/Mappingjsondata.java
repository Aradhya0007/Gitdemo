package file.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mappingjsondata {

    public List<HashMap<String, String>> mapping() throws IOException {
        // Convert the JSON file to a string
        String json = FileUtils.readFileToString(
            new File("C:\\Users\\hp\\eclipse-workspace\\mavenwork\\src\\test\\java\\file\\data\\productinfo.json"), 
            StandardCharsets.UTF_8
        );

        // Map the JSON string into HashMaps and store them in a List
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(
            json, 
            new TypeReference<List<HashMap<String, String>>>() {}
        );

        return data;
    }
}
