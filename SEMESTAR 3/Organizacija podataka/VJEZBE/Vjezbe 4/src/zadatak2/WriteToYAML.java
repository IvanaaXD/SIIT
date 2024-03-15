package zadatak2;

import java.io.File;
import java.io.FileWriter;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import zadatak1.Autobus;

public class WriteToYAML {
	
	public void writeFirst(HashMap<Autobus, Integer> mapa, String filePath) {
		
		Yaml yaml = new Yaml(new Constructor(First.class));
		File file = new File(filePath);
		First first = new First();
		
		try (FileWriter writer = new FileWriter(file)){
			
			for (Map.Entry<Autobus, Integer> entry : mapa.entrySet()) {
				first.setAutobus(entry.getKey());
				first.setNum(entry.getValue());
				yaml.dump(first, writer);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void writeSecond(HashMap<Autobus, Duration> mapa, String filePath) {
		
		Yaml yaml = new Yaml(new Constructor(Second.class));
		File file = new File(filePath);
		Second second = new Second();
		
		try (FileWriter writer = new FileWriter(file)){
			
			for (Map.Entry<Autobus, Duration> entry : mapa.entrySet()) {
				second.setAutobus(entry.getKey());
				second.setDuration(entry.getValue());
				yaml.dump(second, writer);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void writeThird(HashMap<Autobus, Double> mapa, String filePath) {
		
		Yaml yaml = new Yaml(new Constructor(Second.class));
		File file = new File(filePath);
		Third third = new Third();
		
		try (FileWriter writer = new FileWriter(file)){
			
			for (Map.Entry<Autobus, Double> entry : mapa.entrySet()) {
				third.setAutobus(entry.getKey());
				third.setNum(entry.getValue());
				yaml.dump(third, writer);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void write(List<Autobus> autobusi, String filePath) {
		
		Yaml yaml = new Yaml(new Constructor(Autobus.class));
		File file = new File(filePath);
		
		try (FileWriter writer = new FileWriter(file)){
			
			yaml.dumpAll(autobusi.iterator(), writer);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
