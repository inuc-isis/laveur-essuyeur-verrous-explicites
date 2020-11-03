package lavavageexplicit;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Pile {

	private final Lock verrou = new ReentrantLock();
	private final Condition pasVide = verrou.newCondition();
	private final Condition pasPleine = verrou.newCondition();
	private final static int MAX = 10;
	private final List<Assiette> myList = new LinkedList<>();

	private boolean isEmpty() {
		return myList.isEmpty();
	}

	private boolean isFull() {
		return (myList.size() >= MAX);
	}

	public void push(Assiette assiette) throws InterruptedException {
		verrou.lock();
		try {
			while (isFull()) {
				pasPleine.await();  // J'attends qu'on me signale que la pile n'est pas pleine
			}
			// Ajouter à la fin de la liste
			myList.add(assiette);
			System.out.println("La pile contient " + myList.size() + " assiettes");
			pasVide.signalAll(); // Je signale que la pile n'est pas vide
		} finally {
			verrou.unlock();
		}
	}

	public Assiette pop() throws InterruptedException {
		Assiette result;
		verrou.lock();
		try {
			while (isEmpty()) {
				pasVide.await();
			} // J'attends qu'on me signale que la pile n'est pas vide

			result = myList.remove(myList.size() - 1);
			System.out.println("La pile contient " + myList.size() + " assiettes");
			pasPleine.signalAll();
		} finally {
			verrou.unlock();
		}
		return result;
	}
}
