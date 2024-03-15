package zadatak5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadFromJSON {

	public List<RentACar> read(String filePath) throws JsonParseException, JsonMappingException, IOException{
		
		List<RentACar> lista = new ArrayList<RentACar>();

		try {
			
			ObjectMapper om = new ObjectMapper();
			om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			
			File file = new File(filePath);
			
			lista = om.readValue(file, new TypeReference<List<RentACar>>() {});

		}
		catch (Exception e) {
            e.printStackTrace();
        }
		
		return lista;
		
	}
}
