package zadatak5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class WriteToJSON {

	public void write(List<RentACar> lista, String filePath) throws FileNotFoundException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		
		om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		om.enable(SerializationFeature.INDENT_OUTPUT);
		
		File file = new File(filePath);
			
		om.writeValue(file, lista);
	}
	
}
