import java.util.*;

public class WelshPowell {

    public static void main(String[] args) {
        // Noms des sommets de A à F
        char[] sommets = {'A', 'B', 'C', 'D', 'E', 'F'};

        // Matrice d'adjacence du graphe non orienté
        //Exemple vu en TD

       /*   int[][] graphe = {
            {0, 1, 0, 0, 0, 1}, // A
            {1, 0, 1, 1, 0, 1}, // B
            {0, 1, 0, 0, 1, 0}, // C
            {0, 1, 0, 0, 0, 0}, // D
            {0, 0, 1, 0, 0, 0}, // E
            {1, 1, 0, 0, 0, 0}  // F
        };
        */

       //Exemple de notre choix

        int[][] graphe = {
            {0, 1, 1, 0, 0, 0}, // A
            {1, 0, 1, 1, 0, 0}, // B
            {1, 1, 0, 0, 0, 0}, // C
            {0, 1, 0, 0, 1, 1}, // D
            {0, 0, 0, 1, 0, 1}, // E
            {0, 0, 0, 1, 1, 0}  // F
        }; 

   
        // Affichage de la matrice d’adjacence
        afficherMatrice(graphe);

        int[] couleurs = welshPowell(graphe);

        // Affichage classique (sommet : couleur)
        System.out.println("Coloration du graphe (sommet : couleur) :");
        for (int i = 0; i < couleurs.length; i++) {
            System.out.println(sommets[i] + " : Couleur " + couleurs[i]);
        }

        // Affichage regroupé par noms de couleurs
        afficherColorationParNom(couleurs, sommets);
    }

    //  coloration du graphe
    public static int[] welshPowell(int[][] graphe) {
        int n = graphe.length;
        int[] couleurs = new int[n];
        Arrays.fill(couleurs, -1); // -1 = pas encore colorié

        // Tri des sommets par degré décroissant
        Integer[] sommets = new Integer[n];
        for (int i = 0; i < n; i++) sommets[i] = i;
        Arrays.sort(sommets, (a, b) -> Integer.compare(degre(graphe, b), degre(graphe, a)));

        int couleur = 1;

        for (int i = 0; i < n; i++) {
            int s = sommets[i];
            if (couleurs[s] == -1) {
                couleurs[s] = couleur;
                for (int j = i + 1; j < n; j++) {
                    int t = sommets[j];
                    if (couleurs[t] == -1 && !sontAdjacents(graphe, s, t, couleurs)) {
                        couleurs[t] = couleur;
                    }
                }
                couleur++;
            }
        }

        return couleurs;
    }

    // Calcul du degré d'un sommet
    public static int degre(int[][] graphe, int sommet) {
        int degre = 0;
        for (int val : graphe[sommet]) {
            if (val != 0) degre++;
        }
        return degre;
    }

    // Vérifie si deux sommets sont adjacents et ont la même couleur
    public static boolean sontAdjacents(int[][] graphe, int s, int t, int[] couleurs) {
        for (int i = 0; i < graphe.length; i++) {
            if (graphe[t][i] == 1 && couleurs[i] == couleurs[s]) {
                return true;
            }
        }
        return false;
    }

    // Affichage de la matrice d’adjacence
    public static void afficherMatrice(int[][] mat) {
        System.out.println("Matrice d'adjacence :");
        for (int[] row : mat) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println(); 
    }

    // Affichage des sommets regroupés par couleur 
    public static void afficherColorationParNom(int[] couleurs, char[] nomsSommets) {
        // les noms associés aux couleurs
        String[] nomsCouleurs = {"Rouge", "Vert", "Bleu", "Jaune", "Cyan", "Magenta", "Orange", "Gris"};

        // Regroupage des sommets par couleur
        Map<String, List<Character>> regroupement = new LinkedHashMap<>();

        for (int i = 0; i < couleurs.length; i++) {
            int couleurIndex = couleurs[i] - 1;
            String nomCouleur = (couleurIndex < nomsCouleurs.length) ? nomsCouleurs[couleurIndex] : "Autre";

            regroupement.putIfAbsent(nomCouleur, new ArrayList<>());
            regroupement.get(nomCouleur).add(nomsSommets[i]);
        }

        
        for (String nom : regroupement.keySet()) {
            System.out.print(nom + "\t\t\t");
        }
        System.out.println();

        // Affichage par ligne
        int maxLigne = regroupement.values().stream().mapToInt(List::size).max().orElse(0);
        for (int i = 0; i < maxLigne; i++) {
            for (List<Character> liste : regroupement.values()) {
                if (i < liste.size()) {
                    System.out.print(liste.get(i) + "\t\t\t");
                } else {
                    System.out.print("\t\t\t");
                }
            }
            System.out.println();
        }
    }
}
 