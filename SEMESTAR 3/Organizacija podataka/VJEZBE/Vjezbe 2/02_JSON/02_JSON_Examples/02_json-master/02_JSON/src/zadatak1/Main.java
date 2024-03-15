package zadatak1;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Main {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

		ReadFromJSON rfjson = new ReadFromJSON();

		try {
			rfjson.read();

		} catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}
