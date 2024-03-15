package vjezbe_6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

public class primjeeeer {

	public static void main(String[] args) {

		TreeSet<String> imena = new TreeSet<>();
		imena.add("Pera");
		imena.add("Aca");
		imena.add("Aca");
		imena.add("Zika");
		System.out.println(imena);
		

		HashSet<String> imenaa = new HashSet<>();
		imenaa.add("Pera");
		imenaa.add("Aca");
		imenaa.add("Aca");
		imenaa.add("Zika");
		System.out.println(imenaa);
		
		HashMap<String, String> studenti = new HashMap<>();
		studenti.put("Pera", "Peric");
		studenti.put("Marko", "Markovic");
		studenti.put("Nikola", "Nikolic");
	
		//studenti.remove("Marko");
		//System.out.println(studenti);

		for (Map.Entry<String, String> student: studenti.entrySet()) {
			System.out.println(student.getKey() + " " + student.getValue());
		}
		
		ArrayList <Student> studentii = new ArrayList<>(); 
			
		studentii.add(new Student("Pera", "Peric"));
		studentii.add(new Student("Aca", "Arsic"));

		Collections.sort(studentii, new StudentImeSorter());
		System.out.println(studentii);

		Collections.sort(studentii, new StudentPrezimeSorter());
		System.out.println(studentii);
		
	}

}

class Student implements Comparable<Student>{
	
	String ime, prezime;
	Student(String ime, String prezime){
		this.ime = ime;
		this.prezime = prezime;
	}
	
	@Override
	public String toString() {
		
		return this.ime + " " + this.prezime;
	}

	@Override
	public int compareTo(Student o) {
		
		return this.ime.compareTo(o.ime);
	}
	
}

class StudentImeSorter implements Comparator<Student>{
	
	@Override
	public int compare(Student o1, Student o2) {
		
		return o1.ime.compareTo(o2.ime);
		
	}
}


class StudentPrezimeSorter implements Comparator<Student>{
	
	@Override
	public int compare(Student o1, Student o2) {
		
		return o1.prezime.compareTo(o2.prezime);
		
	}
}