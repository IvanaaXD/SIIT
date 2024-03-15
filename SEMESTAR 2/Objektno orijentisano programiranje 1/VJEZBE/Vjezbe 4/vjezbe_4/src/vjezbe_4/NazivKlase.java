package vjezbe_4;

	
	public class NazivKlase{
		
		boolean NazivAtributa;
		
		void nazivMetode() {
			
			NazivAtributa = true;
			
		}
		
	}

	class TestKlasa{
		
		public static void main(String[] args) {

			NazivKlase k;
			
			k = new NazivKlase();
			k.nazivMetode();
			
		}
		
	}
