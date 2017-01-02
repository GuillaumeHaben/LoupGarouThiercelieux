import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

public class Run {
	
	private static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private static HashMap<Joueur, Integer> votes = new HashMap<Joueur, Integer>();
	private static Joueur victime;
	
	public static void tour() {
		nuit();
		jour();
	}

	public static void jour() {
		System.out.println("Le jour se lève sur le village. Tous les villageois se réveillent sauf...");
		System.out.println(victime.getPrenom() + "!");
		System.out.println("Les villageois sont donc amenés à voter pour tuer l'un d'entre eux.");
		menu("jour");
		System.out.println("Les villageois ont tués " + victime.getPrenom() + "!");
	}
	
	public static void nuit() {
		System.out.println("La nuit tombe sur le village. Tous les villageois s'endorment.");
		System.out.println("Les loups garous vont être appelés pour désigner une victime...");
		menu("nuit");		
	}
	
	public static void choisirMort(ArrayList joueursATuer) {
		int nombreJoueursATuer = joueursATuer.size();
		if (nombreJoueursATuer == 1) {
			victime = (Joueur) joueursATuer.get(0);
		}
		else {
		    Random rand = new Random();
		    int hasard = rand.nextInt(nombreJoueursATuer + 1);
		    victime = (Joueur) joueursATuer.get(hasard-1);
		}
		joueurs.remove(victime);
		
	}
	
	public static void menu(String etat) {
		votes.clear();
		for (Joueur joueur : joueurs) {
			
		
			System.out.println("A toi de jouer " + joueur.getPrenom() + ". Appuie sur une touche pour commencer.");
			Scanner sc = new Scanner(System.in);
			if (etat.equals("jour")) {
				System.out.println("Choisi une victime parmi les joueurs encors en vie :");
				for (Joueur j : joueurs) {
					System.out.println("[" + j.getId() + "] Joueur n°" + j.getId() + " : " + j.getPrenom());// int j ++
				}
				int numJoueur = sc.nextInt();
				for (Joueur j : joueurs) {
					if (numJoueur == j.getId()) {
						System.out.println("Tu souhaites tuer : " + j.getPrenom() + ".");
						if (votes.get(j) == null) {
							votes.put(j, 1);
						}
						else {
							votes.put(j, votes.get(j) + 1);
						}
						break;
					}
				}
				//Mettre à jour les votes
			}
			if (etat.equals("nuit")) {
				if (joueur.isLoupGarou() == 1) {
					System.out.println("Choisi une victime parmi les joueurs encors en vie :");
					for (Joueur j : joueurs) {
						System.out.println("[" + j.getId() + "] Joueur n°" + j.getId() + " : " + j.getPrenom());
					}
					int numJoueur = sc.nextInt();
					for (Joueur j : joueurs) {
						if (numJoueur == j.getId()) {
							System.out.println("Tu souhaites tuer : " + j.getPrenom() + ".");
							if (votes.get(j) == null) {
								votes.put(j, 1);
							}
							else {
								votes.put(j, votes.get(j) + 1);
							}
						}
						break;
					}
	
				}
				if (joueur.isLoupGarou() == 0) {
					System.out.println("Reste endormi ;)");
				}
			}
			//effacer écran
		} 
		int maxValueInMap=(Collections.max(votes.values()));  // This will return max value in the Hashmap
		ArrayList joueursATuer = new ArrayList();
        for (Entry<Joueur, Integer> entry : votes.entrySet()) {  // Itrate through hashmap
            if (entry.getValue() == maxValueInMap) {
                joueursATuer.add(entry.getKey());   // Print the key with max value
            }
        }
        choisirMort(joueursATuer);
	}

	private static void combienJoueur() {
		System.out.println("Combien de joueur pour cette partie ?");
		Scanner sc = new Scanner(System.in);
		int nbJoueur = sc.nextInt();
		System.out.println("Très bien : " + nbJoueur + " joueurs !");
		for (int i = 0; i < nbJoueur; i++) {
			joueurs.add(new Joueur(i));
		} 
	}
	
	public static void choixPrenom() {
		for (Joueur joueur : joueurs) {
			System.out.println("Joueur n°" + joueur.getId() + " : Comment t'appelles tu ?"); // int i
			Scanner sc = new Scanner(System.in);
			String prenom = sc.next();
			System.out.println("Merci pour l'info " + prenom + " !");
			joueur.setPrenom(prenom);
			joueur.setEnVie(true);
		}
	}
	
	public static void assignerRole() {
		for (Joueur joueur : joueurs) {
		    Random rand = new Random();
			joueur.setRole(rand.nextInt(2));
		}
	}

	private static void devoilerRole() {
		for (Joueur joueur : joueurs) {
			System.out.print(joueur.getPrenom() + " ! Tu es un ");
			if (joueur.isLoupGarou() == 1) {
				System.out.print("Loup-Garou");
			}
			if (joueur.isLoupGarou() == 0) {
				System.out.print("Villageois");
			}
			System.out.println();
		}
	}
	
	public static void main (String[] args){
		combienJoueur();
		choixPrenom();
		assignerRole();
		devoilerRole();
		while(nombreLoupGarou() != 0 && nombreVillageois() != 0) {
			tour();
		}
		
	}

	private static int nombreVillageois() {
		int villageois = 0;
		for (Joueur joueur : joueurs) {
			if (joueur.getRole() == 0) {
				villageois++;
			}
		}
		return villageois;
	}

	private static int nombreLoupGarou() {
		int loupgarous = 0;
		for (Joueur joueur : joueurs) {
			if (joueur.getRole() == 0) {
				loupgarous++;
			}
		}
		return loupgarous;
	}
	
}
