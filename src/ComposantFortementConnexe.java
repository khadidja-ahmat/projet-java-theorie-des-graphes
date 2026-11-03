import java.util.ArrayList;
import java.util.List;

public class ComposantFortementConnexe {

    public static void main(String[] args) {
        // exemple vu en TD : graphe à 5 sommets (A, B, C, D, E)

       int[][] matrix = {
            {0,1,0,1,0},
            {0,1,1,0,0},
            {0,0,0,0,1},
            {0,0,1,0,1},
            {0,0,0,1,1}
        };

        //Exemple de notre choix : graphe à 6 sommets A, B, C, D, E, F
        // A → B, B → C, C → A → boucle A-B-C
        // D → E, E → D → boucle D-E
        // F est isolé seul

      /*  int[][] matrix = {
            // A  B  C  D  E  F
            {0, 1, 0, 0, 0, 0}, // A → B
            {0, 0, 1, 0, 0, 0}, // B → C
            {1, 0, 0, 0, 0, 0}, // C → A
            {0, 0, 0, 0, 1, 0}, // D → E
            {0, 0, 0, 1, 0, 0}, // E → D
            {0, 0, 0, 0, 0, 0}  // F (isolé seul)
        };*/

        System.out.println("-----------------------------------Matrice Initiale-----------------------------------");
        print_matrix(matrix);
        System.out.println("-----------------------------------Fermeture Transitive-------------------------------");
        int[][] m_k = matrix_k(matrix);
        print_matrix(m_k);
        System.out.println("-----------------------------------Composantes Fortement Connexes---------------------");
        List<List<Integer>> result = composantes_fortement_connexe(m_k);
        afficher_resultat(result);
    }

    // Calcul de la fermeture transitive avec sécurité 
    public static int[][] matrix_k(int[][] mat1) {
        int[][] mat2 = mat1;
        int[][] result = new int[mat1.length][mat1[0].length];
        boolean is_same = false;
        int maxIterations = 20; 
        int count = 0;

        while (!is_same && count < maxIterations) {
            result = matrix_multiply(mat2, mat1);
            result = add_matrices(result, mat2); 
            is_same = same_matrix(mat2, result);
            mat2 = result;
            count++;
        }

        // Ajout de la matrice identité
        int[][] identity = create_identity_matrix(mat1.length);
        return add_matrices(result, identity);
    }

    // Multiplication logique de matrices 
    public static int[][] matrix_multiply(int[][] mat1, int[][] mat2) {
        int rows = mat1.length;
        int cols = mat1[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                for (int k = 0; k < cols; ++k)
                    result[i][j] |= (mat1[i][k] * mat2[k][j]);

        return result;
    }

    // Vérifie si deux matrices sont identiques
    public static boolean same_matrix(int[][] mat1, int[][] mat2) {
        int rows = mat1.length, cols = mat1[0].length;
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                if (mat1[i][j] != mat2[i][j])
                    return false;
        return true;
    }

    // Addition logique de deux matrices
    public static int[][] add_matrices(int[][] mat1, int[][] mat2) {
        int rows = mat1.length;
        int cols = mat1[0].length;
        int[][] sum = new int[rows][cols];
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                sum[i][j] = mat1[i][j] | mat2[i][j];
        return sum;
    }

    // Création de la matrice identité
    public static int[][] create_identity_matrix(int size) {
        int[][] identity = new int[size][size];
        for (int i = 0; i < size; i++)
            identity[i][i] = 1;
        return identity;
    }

    // Trouve les composantes fortement connexes à partir de la fermeture
    public static List<List<Integer>> composantes_fortement_connexe(int[][] closure) {
        int n = closure.length;
        boolean[] visited = new boolean[n];
        List<List<Integer>> sccs = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                List<Integer> scc = new ArrayList<>();
                for (int j = 0; j < n; ++j) {
                    if (closure[i][j] == 1 && closure[j][i] == 1) {
                        scc.add(j);
                        visited[j] = true;
                    }
                }
                if (!scc.isEmpty())
                    sccs.add(scc);
            }
        }
        return sccs;
    }

    // Affiche une matrice
    public static void print_matrix(int[][] mat) {
        for (int[] row : mat) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }

    // Affiche les composantes avec des lettres A, B, C...
    public static void afficher_resultat(List<List<Integer>> result) {
    System.out.println("Les composantes fortement connexes sont :");
    for (List<Integer> composante : result) {
        System.out.print("[ ");
        for (int index : composante)
            System.out.print((char) ('A' + index) + " ");
        System.out.println("]");
    }
}

}
