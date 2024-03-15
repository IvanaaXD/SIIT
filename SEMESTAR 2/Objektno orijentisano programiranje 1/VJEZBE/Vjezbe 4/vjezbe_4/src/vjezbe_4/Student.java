package vjezbe_4;

import java.util.ArrayList;

class Student {
	
	public String ime;
	public String indeks;
	public String grad;

	Student(String ime, String indeks, String grad){
		
		this.ime = ime;
		this.indeks = indeks;
		this.grad = grad;
		
	}
	
	public void ispis() {
		
		System.out.println("Student " + ime + " sa brojem indeksa " + indeks + " iz grada " + grad);
		System.out.println();
	}


public static void main(String[] args) {
	
	Student[] studenti;
	studenti  = new Student[2];
	
	studenti[0] = new Student("Ivana", "sv23", "Trebinje");
	studenti[1] = new Student("Milica", "sv18", "Kraljevo");

	studenti[0].ispis();
	studenti[1].ispis();
	
	ArrayList <Student> lista = new ArrayList<>();
	lista.add(new Student("Ivana", "sv23", "Trebinje"));
	lista.add(new Student("Milica", "sv18", "Kraljevo"));
	
	for (Student student: lista) {
		
		System.out.println("Student " + student.ime + " sa brojem indeksa " + student.indeks + " iz grada " + student.grad);
	}
	
	}
}
