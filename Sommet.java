public class Sommet {
    private String nom;
    private int x;
    private int y;

    public Sommet(String nom, int x, int y) {
        this.nom = nom;
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return getNom() + "(" + getX() + " ; " + getY() + ")";
    }


    /*  GETTERS & SETTERS */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
