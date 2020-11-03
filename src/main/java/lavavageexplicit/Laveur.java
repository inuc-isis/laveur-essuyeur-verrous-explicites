package lavavageexplicit;

class Laveur extends Thread {
	private final Pile myStack;

	Laveur(Pile s) {
		myStack = s;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// Laver une assiette
				Assiette assiette = new Assiette();
				sleep(1600);
				// La mettre sur la pile
				System.out.println("J'empile : " + assiette);
				myStack.push(assiette);
			} catch (InterruptedException ex) {
				break; // Sort du while, termine le Thread;
			}
		}
		System.out.println("Laveur terminé");
	}
}