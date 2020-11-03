package lavavageexplicit;

public class Assiette {
	private static final String verrou = "verrou";

	private static Integer nombreAssiette = 0;
	private final int numeroAssiette;

	Assiette() {
		synchronized(verrou) {
			numeroAssiette = ++nombreAssiette;
		}
	}

	@Override
	public String toString() {
		return "Assiette n° " + numeroAssiette;
	}

}
