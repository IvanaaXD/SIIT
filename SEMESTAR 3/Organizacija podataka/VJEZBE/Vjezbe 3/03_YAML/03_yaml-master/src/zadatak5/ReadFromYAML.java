package zadatak5;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class ReadFromYAML {

	public All read(String filePath) {
		
		All all = new All();
		
		Yaml yaml = new Yaml(new Constructor(All.class));
		File file = new File(filePath);
		
		try (InputStream input = new FileInputStream(file)){
			
			all =(All) yaml.load(input);
			System.out.println(all);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return all;
	}
}
