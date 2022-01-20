package sopra.formation.model;

public enum Niveau {
	DEBUTANT("Débutant"), CONFIRME("Confirmé"), EXPERT("Expert");

	private final String label;

	private Niveau(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
