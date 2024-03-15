package vjezbe_2;

public class primjer8 {

	public static void main(String[] args) {

		int array[][] = {{1,2,3},{4,88,5}};
		boolean fountIt = false;
		int searchfor = 88;
		
		search:
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[i].length; j++) {
					if (array[i][j] == searchfor) {
						fountIt = true;
						break search;
					}
				}
			}
		
		if (fountIt) {
			System.out.println("Found " + searchfor);
		}
	}

}
