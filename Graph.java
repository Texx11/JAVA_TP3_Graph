import com.sun.jdi.ArrayReference;
import com.sun.jdi.ObjectReference;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Graph {
    private String graphName;
    private Set<Sommet> sommetSet;
    private Set<Arete> arreteSet;
    private Map<Arete, Set<Sommet>> AreteTosommet;
    private StringBuilder cssContent;

    public Graph(String graphName) {
        this.graphName = graphName;
        sommetSet = new HashSet<>();
        arreteSet = new HashSet<>();
        AreteTosommet = new HashMap<>();
        cssContent = new StringBuilder("body { background-color: #d9f0ed;}\n"); //Passage de cssContent en variable globale de classe
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        // Utilisation de la méthode forEach pour itérer sur la map
        AreteTosommet.forEach((arete, sommets) -> {
            sb.append(arete)
                    .append(" = ")
                    .append(sommets)
                    .append("\n");
        });

        return sb.toString();
    }

    public String javaToHtml() {
        StringBuilder htmlText;
        /*      HTML BODY     */
        htmlText = new StringBuilder("<body>\n");
        htmlText.append("<h1>").append(graphName).append("</h1>\n");
        htmlText.append("<svg width=\"1000\" height=\"900\">\n");
        /*      ITERATION THROUGH THE MAP        */
        for (Map.Entry<Arete, Set<Sommet>> entry : AreteTosommet.entrySet()) {
            Arete arete = entry.getKey();
            //<!-- Line connecting the circles -->
            //<line x1="50" y1="50" x2="100" y2="50" stroke="black" stroke-width="2" />
            if (arete.getAngle() == 0){
                htmlText.append("<line x1=\"").append(arete.getSommet1().getX()).append("\" y1=\"").append(arete.getSommet1().getY())
                        .append("\" x2=\"").append(arete.getSommet2().getX()).append("\" y2=\"").append(arete.getSommet2().getY())
                        .append("\" stroke=\"black\" stroke-width=\"2\" ")
                        .append( "class=\"").append(arete.getSommet1().getNom()).append("-").append(arete.getSommet2().getNom()).append("\" ")
                        //Name :
                        .append("/>\n");
            }
            //<path d="M 50 50 A 30 30 0 1 1 150 150" stroke="black" fill="transparent"/>
            else if (arete.getAngle() > 0) {
                htmlText.append("<path d=\"M ").append(arete.getSommet1().getX()).append(" ").append(arete.getSommet1().getY())
                        .append(" A ").append(arete.getAngle()).append(" ").append(arete.getAngle())
                        .append(" 0 0 0 ").append(arete.getSommet2().getX()).append(" ").append(arete.getSommet2().getY())
                        .append("\" class=\"").append(arete.getSommet1().getNom()).append("-").append(arete.getSommet2().getNom()).append("\" ")
                        .append("stroke=\"black\" fill=\"transparent\"/>\n");
            }
            else{
                htmlText.append("<path d=\"M ").append(arete.getSommet1().getX()).append(" ").append(arete.getSommet1().getY())
                        .append(" A ").append(arete.getAngle()).append(" ").append(arete.getAngle())
                        .append(" 0 0 1 ").append(arete.getSommet2().getX()).append(" ").append(arete.getSommet2().getY())
                        .append("\" class=\"").append(arete.getSommet1().getNom()).append("-").append(arete.getSommet2().getNom()).append("\" ")
                        .append("stroke=\"black\" fill=\"transparent\"/>\n");
            }
        }
        for (Sommet sommet : sommetSet) {
            // <circle cx="50" cy="50" r="10" fill="white" stroke="black" stroke-width="2"/>
            //dessin d'un cercle pour representer un sommet
            htmlText.append("<circle cx=\"").append(sommet.getX()).append("\" cy=\"").append(sommet.getY())
                    .append("\" r=\"10\" fill=\"white\" stroke=\"black\" stroke-width=\"2\"")
                    .append("\" class=\"").append(sommet.getNom())
                    .append("\" />\n");
            //Ecriture dans le rond du nom du sommet
            // <text x="47" y="50" font-size="16px" alignment-baseline="middle" alignment-baseline="middle">1</text>
            htmlText.append("<text x=\"").append(sommet.getX() - 5).append("\" y=\"").append(sommet.getY() + 1)
                    .append("\" alignment-baseline=\"middle\">")
                    .append(sommet.getNom())
                    .append("</text>").append("\n")
                    .append("\n");
        }
        htmlText.append("</svg>\n");
        htmlText.append("</body>\n");
        return htmlText.toString();
    }

    public void colorArete(Arete arete, String color) {
        this.cssContent.append(".").append(arete.getId()).append(" {\n")
                    .append("stroke:").append(color).append(";\n}\n");
    }

    public void colorSommet(Sommet sommet, String color) {
        this.cssContent.append(".").append(sommet.getNom()).append(" {\n")
                .append("stroke:").append(color).append(";\n")
                .append("fill:").append(color).append(";\n")
                .append("}\n");
    }

    public void colorPath (List<Object> path, String color){
        for (Object element : path) {
            if (element instanceof Sommet sommet){
                colorSommet(sommet, color);
            }
            else if (element instanceof Arete arete){
                colorArete(arete, color);
            }
            else {
                System.out.println("Veuillez verifier votre path");
            }
        }
    }

    public void saveToCSSFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("style.css")))
        {
            bw.write(this.cssContent.toString());
        }
        catch (IOException e)
        {
            System.out.println("Error while writing CSS file ");
        }
    }

    public void saveToHTMLFile(String fileName) {
        String htmlContent;
        htmlContent = "<!DOCTYPE html>\n";
        /*      HTML HEADER     */
        htmlContent += "<head>\n";
        // Include the CSS file :<link rel="stylesheet" href="mystyle.css">
        htmlContent += "<link rel=\"stylesheet\" href=\"style.css\">";
        htmlContent += "<title>" + graphName + "</title>\n";
        htmlContent += "</head>\n";
        //Inclusion du fichier css
        htmlContent += javaToHtml();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            bw.write(htmlContent);
        }
        catch (IOException e)
        {
            System.out.println("Error while writing file " + fileName);
        }
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
