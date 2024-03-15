package zadatak3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import rs.ac.uns.ftn.siit.op.yaml.example03.Product;

public class WriteToYAML {

	public void write(Product product, String filePath) throws IOException {
		
		Yaml yaml = new Yaml(new Constructor(Product.class));
		File file = new File(filePath);
		
		try (FileWriter writer = new FileWriter(file)){
			yaml.dump(product, writer);
		} 
	}
}
