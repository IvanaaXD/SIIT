package vjezbe_2;

public class primjer4 {

	public static void main(String[] args) {

		int i = 1, n = 10, suma = 0;
		
		System.out.println("Ispis parnih brojeva na intervalu [1,10], ciji je zbir manji od 15");
		
		while (i <= 10) {
			
			if (i%2==1) {
				i += 1;
				continue;
			}
			
			if (suma + i > 15) {
				break;
			}
			
			System.out.println(i + " ");
			suma += i;
			i +=1;
		}
		
	}

}
