import java.util.*;

public class AlgoDijkstra {

    public static void main(String[] args) {
        // Noms des sommets (A à G)
        char[] sommets = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };

        // Matrice du graphe non orienté, chaque case représente le poids de l’arête entre deux sommets
        //Exemplevu en TD

        int[][] graphe = {
            {0, 4, 0, 0, 0, 3, 7}, // A
            {4, 0, 2, 0, 0, 0, 0}, // B
            {0, 2, 0, 2, 0, 1, 0}, // C
            {0, 0, 2, 0, 1, 0, 0}, // D
            {0, 0, 0, 1, 0, 0, 3}, // E
            {3, 0, 1, 0, 0, 0, 5}, // F
            {7, 0, 0, 0, 3, 5, 0}  // G
        };
        
        // le sommet de A et E  Pour le graphe vu en TD
        int sommetDepart = 0;   
        int sommetArrivee = 4;  
       
        //Exemple de notre choix
        
       /*int[][] graphe = {
            {0, 4, 2, 0, 0, 0},  // A
            {4, 0, 5, 10, 0, 0}, // B
            {2, 5, 0, 0, 3, 0},  // C
            {0, 10, 0, 0, 4, 11},// D
            {0, 0, 3, 4, 0, 2},  // E
            {0, 0, 0, 11, 2, 0}  // F
        };
        // le sommet de A et F  Pour le graphe de notre choix
        int sommetDepart = 0;  
        int sommetArrivee = 5; 
        */
        

      

        // Affichage de la matrice du graphe
        print_matrix(graphe);

        // l'appel à l'algorithme de Dijkstra
        List<Integer> plusCourtChemin = dijkstra(graphe, sommetDepart, sommetArrivee);

        // Affichage du plus court chemin trouvé avec les lettres
        System.out.print("Plus court chemin de " + sommets[sommetDepart] + " à " + sommets[sommetArrivee] + " : ");
        for (int i = 0; i < plusCourtChemin.size(); i++) {
            int indice = plusCourtChemin.get(i);
            System.out.print(sommets[indice]);
            if (i < plusCourtChemin.size() - 1) {
                System.out.print(" --> ");
            }
        }

        // Calcul de la distance totale du chemin
        int distance = 0;
        for (int i = 0; i < plusCourtChemin.size() - 1; i++) {
            int from = plusCourtChemin.get(i);
            int to = plusCourtChemin.get(i + 1);
            distance += graphe[from][to];
        }
        System.out.println("\nDistance totale : " + distance);
    }

    // Algorithme de Dijkstra
    public static List<Integer> dijkstra(int[][] graphe, int sommetDepart, int sommetArrivee) {
        int nbSommets = graphe.length;

        // On initialise toutes les distances à l’infini
        int[] distances = new int[nbSommets];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[sommetDepart] = 0; // La distance du point de départ est 0

        // Ce tableau sert à reconstruire le chemin à la fin
        int[] predecesseurs = new int[nbSommets];
        Arrays.fill(predecesseurs, -1);

        // Ensemble des sommets qu’on n’a pas encore visités
        Set<Integer> nonVisites = new HashSet<>();
        for (int i = 0; i < nbSommets; i++) {
            nonVisites.add(i);
        }

        // Tant qu’il reste des sommets à visiter
        while (!nonVisites.isEmpty()) {
            // On choisit le sommet le plus proche (non visité)
            int sommetActuel = trouveSommetMinimum(nonVisites, distances);
            nonVisites.remove(sommetActuel);

            // On regarde tous les voisins de ce sommet
            for (int voisin = 0; voisin < nbSommets; voisin++) {
                if (graphe[sommetActuel][voisin] != 0) {
                    // Si aller chez ce voisin est plus court, on met à jour
                    int nouvelleDistance = distances[sommetActuel] + graphe[sommetActuel][voisin];
                    if (nouvelleDistance < distances[voisin]) {
                        distances[voisin] = nouvelleDistance;
                        predecesseurs[voisin] = sommetActuel;
                    }
                }
            }
        }

        // On reconstruit le chemin depuis l’arrivée jusqu’au départ
        List<Integer> chemin = new ArrayList<>();
        int sommet = sommetArrivee;
        while (sommet != sommetDepart) {
            chemin.add(0, sommet);
            sommet = predecesseurs[sommet];
        }
        chemin.add(0, sommetDepart);

        return chemin;
    }

    // recherche du sommet non visité le plus proche
    public static int trouveSommetMinimum(Set<Integer> ensemble, int[] distances) {
        int distanceMinimum = Integer.MAX_VALUE;
        int sommetMinimum = -1;
        for (int sommet : ensemble) {
            if (distances[sommet] < distanceMinimum) {
                distanceMinimum = distances[sommet];
                sommetMinimum = sommet;
            }
        }
        return sommetMinimum;
    }

    // Affichage de la matrice du graphe
    public static void print_matrix(int[][] mat) {
        for (int[] row : mat) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
