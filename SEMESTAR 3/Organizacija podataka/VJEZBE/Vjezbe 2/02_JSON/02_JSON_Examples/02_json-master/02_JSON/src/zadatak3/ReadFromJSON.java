package zadatak3;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadFromJSON {

	public void readPrva(String filePath) throws JsonParseException, JsonMappingException, IOException {
		
		try {
			
			ObjectMapper om = new ObjectMapper();
			
			File file = new File(filePath);
			
			om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		
            List<Prva> prvaList = om.readValue(file, new TypeReference<List<Prva>>() {});			
			for (Prva p: prvaList) {
				System.out.println(p);
			}
			
			
		} catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	public void readDruga(String filePath) throws JsonParseException, JsonMappingException, IOException {
		
		try {
			
			ObjectMapper om = new ObjectMapper();
			
			File file = new File(filePath);
			
			om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		
            List<Prva> prvaList = om.readValue(file, new TypeReference<List<Prva>>() {});	
            
			for (Prva p: prvaList) {
				System.out.println(p);
			}
			
			
		} catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}
