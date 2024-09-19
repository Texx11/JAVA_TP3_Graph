import java.util.HashSet;
import java.util.Set;

public class Arete {
    private int weight;
    private String id; //For CSS pusposes
    private Sommet sommet1;
    private Sommet sommet2;
    private final Set<Sommet> pairSommet;
    private int angle; //Angle de la ligne

    public Arete(Sommet sommet1, Sommet sommet2, int angle) {
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
        this.angle = angle;
        this.pairSommet = new HashSet<Sommet>();
        pairSommet.add(sommet1);
        pairSommet.add(sommet2);
        this.id = sommet1.getNom() + "-" + sommet2.getNom();
        calculWeight();
    }

    public void calculWeight(){
        int x1 = sommet1.getX();
        int y1 = sommet1.getY();
        int x2 = sommet2.getX();
        int y2 = sommet2.getY();

        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        weight = (int) Math.round(distance);
    }
    public int getWeight() {
        return weight;
    }

    public String toString() {
        return "Il y a un poid de : " + weight + " pour l'arrete";
    }

    /*  GETTERS & SETTERS */
    public Sommet getSommet1() {
        return sommet1;
    }

    public void setSommet1(Sommet sommet1) {
        this.sommet1 = sommet1;
    }

    public Sommet getSommet2() {
        return sommet2;
    }

    public void setSommet2(Sommet sommet2) {
        this.sommet2 = sommet2;
    }
    public Set<Sommet> getPairSommet() {
        return pairSommet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
