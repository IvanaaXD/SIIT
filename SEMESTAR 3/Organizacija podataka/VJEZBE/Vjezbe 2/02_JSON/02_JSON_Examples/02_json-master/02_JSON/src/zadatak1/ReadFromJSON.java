package zadatak1;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadFromJSON {
	
	public void read() throws JsonParseException, JsonMappingException, IOException{
		
		try {
			ObjectMapper om = new ObjectMapper();
			
			File file = new File("resources/Bookstore.json");
			
			om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			
			Library library = om.readValue(file, Library.class);
			
			for (Book b : library.getBooks()) {
                System.out.println(b);
            }
			
			for (Magazine m : library.getMagazines()) {
				System.out.println(m);
			}
		} catch (Exception e) {
            e.printStackTrace();
        }

	}
	
}
