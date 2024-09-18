public class Main {
    public static void main(String[] args) {

        /*  Creation des variables  */
        //Creation du Graph
        Graph graph1 = new Graph();
        //Creation de sommets
        Sommet A = new Sommet("A", 0 ,0);
        Sommet B = new Sommet("B", 0, 5);
        Sommet C = new Sommet("C", 3, 9);
        Sommet D = new Sommet("D",-6, 2);
        //Creation des Aretes
        Arete a = new Arete(A, B);
        Arete b = new Arete(A, B);
        Arete c = new Arete(A, C);
        Arete d = new Arete(C, B);
        Arete e = new Arete(C, B);
        Arete f = new Arete(C, B);
        Arete g = new Arete(B, D);

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

        System.out.println("\n  END-OF-CODE \n");

    }
}
