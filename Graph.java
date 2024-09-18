import com.sun.jdi.ArrayReference;

import java.util.*;

public class Graph {
    Set<Sommet> sommetSet;
    Set<Arete> arreteSet;
    Map<Arete, Set<Sommet>> AreteTosommet;

    public Graph(){
        sommetSet = new HashSet<>();
        arreteSet = new HashSet<>();
        AreteTosommet = new HashMap<>();
    }
    public String toString(){
        //Utilisation de CHATGPT pour afficher en ligne chaque iteration de la Map
        StringBuilder sb = new StringBuilder();

        // Utilisation de la méthode forEach pour itérer sur la map
        AreteTosommet.forEach((arete, sommets) -> {
            sb.append(arete)  // Affiche l'arête
                    .append(" = ")  // Séparateur entre l'arête et les sommets
                    .append(sommets) // Affiche l'ensemble de sommets
                    .append("\n");   // Ajoute un saut de ligne après chaque entrée
        });

        return sb.toString();
    }
    public void addSommet(Sommet s){
        sommetSet.add(s);
    }
    public void addArete(Arete a){
        arreteSet.add(a);

        AreteTosommet.put(a, a.getPairSommet());
    }
    /*  GETTERS & SETTERS */
    public Set<Arete> getArreteSet() {
        return arreteSet;
    }
    public Set<Sommet> getSommetSet() {
        return sommetSet;
    }
    public Set<Sommet> getSommetByArete(Arete a){
        return AreteTosommet.get(a);
    }
    public Map<Arete, Set<Sommet>> getAreteTosommet() {
        return AreteTosommet;
    }
}
