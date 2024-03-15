package funkcije;

public class primjer7 {
	
	static void selection_sort (int niz[]) {
		
		for (int i = 0; i < niz.length - 1; i++) {
			for (int j = i; j < niz.length; j++) {
				
				if (niz[i] > niz[j]) {
					int t = niz[i];
					niz[i] = niz[j];
					niz[j] = t;
				}
			}
		}
		
	}

	public static void main(String[] args) {

		int niz[] = {23,-3,3,12,56,-8};
		
		selection_sort(niz);
		
		for (int i = 0; i < niz.length; i++) {
			
			System.out.print(niz[i] + "   ");
		}
		
	}

}
