package zadatak1;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WriteToJSON {

    public void write(List<Autobus> autobusi, String filePath) throws JsonGenerationException, JsonMappingException, IOException {
    	
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        File file = new File(filePath);

        mapper.writeValue(file, autobusi);
    }
}

