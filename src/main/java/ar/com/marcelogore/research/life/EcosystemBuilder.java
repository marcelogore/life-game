package ar.com.marcelogore.research.life;

public class EcosystemBuilder {

	public static void main(String[] args) {
		
		Ecosystem ecosystem = new Ecosystem(100, 100);
		
		while (true) {
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ecosystem.evolve();
		}
	}
}
