
public class Joueur {
	private int id;
	private String prenom;
	private int role; // final pour constante
	private boolean enVie;
	
	public Joueur(int i) {
		this.id = i;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public boolean isEnVie() {
		return enVie;
	}
	public void setEnVie(boolean enVie) {
		this.enVie = enVie;
	}
	public int isLoupGarou() {
		return this.role;
	}
	
}
