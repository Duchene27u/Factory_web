package sopra.formation.model;

public enum Dispositif {
	CPRO("Contrat Pro"), POEI("POEI"), POEC("POEC");
	
	private final String label;
	
	private Dispositif(String label) {
		this.label = label;
	}



	public String getLabel() {
		return label;
	}
	
	
}



