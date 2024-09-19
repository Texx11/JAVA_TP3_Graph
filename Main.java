import java.util.*;

public class Main {
    public static void main(String[] args) {

        /*  Creation des variables  */
        //Creation du Graph
        Graph graph1 = new Graph("Graph de Leo TEXIER");
        //Creation de sommets
        Sommet A = new Sommet("A", 100,200);
        Sommet B = new Sommet("B", 500, 200);
        Sommet C = new Sommet("C", 280, 500);
        Sommet D = new Sommet("D",750, 150);
        //Creation des Aretes
        Arete a = new Arete(A, B, -5);
        Arete b = new Arete(A, B, 0);
        Arete c = new Arete(A, C, 5);
        Arete d = new Arete(C, B, 15);
        Arete e = new Arete(C, B, 0);
        Arete f = new Arete(C, B, 10);
        Arete g = new Arete(B, D, 0);

        /*  Initialisation du graph */
        //Ajout des arretes dans le set Arete de graph
        graph1.addArete(a);
        graph1.addArete(b);
        graph1.addArete(c);
        graph1.addArete(d);
        graph1.addArete(e);
        graph1.addArete(f);
        graph1.addArete(g);
        //Ajout des sommets dans le set sommets de graph
        graph1.addSommet(A);
        graph1.addSommet(B);
        graph1.addSommet(C);
        graph1.addSommet(D);


        System.out.println("Affichage de la Map complète :");
        System.out.println("--------------------------------");
        System.out.println(graph1.toString());
        System.out.println("--------------------------------");

        /*      CHOIX DU PATH A COLORER     */
        List<Object> path = new ArrayList<>();
        path.add(A);
        path.add(c);
        path.add(C);
        path.add(d);
        path.add(B);
        path.add(g);
        path.add(D);
        graph1.colorPath(path, "Red");

        /*      CREATION DU HTML & CSS     */
        graph1.saveToHTMLFile("graph.html");
        graph1.saveToCSSFile();
        System.out.println("Fichier sauvegardé");
        System.out.println("\n  END-OF-CODE \n");
    }
}
