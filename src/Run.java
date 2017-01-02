import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

public class Run {
	
	private static Joueur[] joueurs;
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
		menu();
		System.out.println("Les villageois ont tués " + victime.getPrenom() + "!");
	}
	
	public static void nuit() {
		System.out.println("La nuit tombe sur le village. Tous les villageois s'endorment.");
		System.out.println("Les loups garous vont être appelés pour désigner une victime...");
		menu();		
	}
	
	public static void choisirMort(ArrayList joueursATuer) {
		int nombreJoueursATuer = joueursATuer.size();
		if (nombreJoueursATuer == 1) {
			victime = (Joueur) joueursATuer.get(0);
		}
		else {
		    Random rand = new Random();
		    int hasard = rand.nextInt(nombreJoueursATuer + 1);
		    victime = (Joueur) joueursATuer.get(hasard);
		}
		
	}
	
	public static void menu() {
		votes.clear();
		for (int i = 0; i < joueurs.length; i++) {
			System.out.println("A toi de jouer " + joueurs[i].getPrenom() + ". Appuie sur une touche pour commencer.");
			Scanner sc = new Scanner(System.in);
			String wait = sc.next();
			if (joueurs[i].isLoupGarou() == 1) {
				System.out.println("Choisi une victime parmi les joueurs encors en vie :");
				for (int j = 0; j < joueurs.length; j++) {
					System.out.println("[" + j + "] Joueur n°" + j + " : " + joueurs[j].getPrenom());
				}
				int numJoueur = sc.nextInt();
				System.out.println("Tu souhaites tuer : " + joueurs[numJoueur].getPrenom() + ".");
				//Mettre à jour les votes
				votes.put(joueurs[numJoueur], votes.get(joueurs[numJoueur]) + 1); // si existe pas

			}
			if (joueurs[i].isLoupGarou() == 0) {
				System.out.println("Reste endormi ;)");
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
		joueurs = new Joueur[nbJoueur];
		for (int i = 0; i < nbJoueur; i++) {
			joueurs[i] = new Joueur();
		} 
	}
	
	public static void choixPrenom() {
		for (int i = 0; i < joueurs.length; i++) {
			System.out.println("Joueur n°" + i + " : Comment t'appelles tu ?");
			Scanner sc = new Scanner(System.in);
			String prenom = sc.next();
			System.out.println("Merci pour l'info " + prenom + " !");
			joueurs[i].setPrenom(prenom);
			joueurs[i].setEnVie(true);
		}
	}
	
	public static void assignerRole() {
		for (int i = 0; i < joueurs.length; i++) {
		    Random rand = new Random();
			joueurs[i].setRole(rand.nextInt(2));
		}
	}

	private static void devoilerRole() {
		for (int i = 0; i < joueurs.length; i++) {
			System.out.print(joueurs[i].getPrenom() + " ! Tu es un ");
			if (joueurs[i].isLoupGarou() == 1) {
				System.out.print("Loup-Garou");
			}
			if (joueurs[i].isLoupGarou() == 0) {
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
			//si Loup Garou = 0 break;
			//si Villageois = 0 break;
		}
		
	}

	private static int nombreVillageois() {
		int villageois = 0;
		for (int i = 0; i < joueurs.length; i++) {
			if (joueurs[i].getRole() == 0) {
				villageois++;
			}
		}
		return villageois;
	}

	private static int nombreLoupGarou() {
		int loupgarous = 0;
		for (int i = 0; i < joueurs.length; i++) {
			if (joueurs[i].getRole() == 0) {
				loupgarous++;
			}
		}
		return loupgarous;
	}
	
}
