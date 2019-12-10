/*Class that provides functionality 
*for the program to read in the roads
*and intersections from a text file
*and create the node and edge objects 
* from this 
*
*
*@author Joe Butler (Jbutl18@u.rochester.edu)
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParse {
    private int roads;
    private int intersections;
    private Node [] vertices;
    private Edge [] edges;
    private String input;

    public FileParse(String input){
        this.input=input;
    }

    /*Helper function that initializes the length
     * of the vertices and edges arrays.
     */
    public void count(){
        try{
            String [] line;
            Scanner scan=new Scanner(new File(input));
            while (scan.hasNextLine()){
                line=scan.nextLine().split("\\s");
                if(line[0].equals("i"))
                    intersections++;
                else
                    roads++;
            }
        }catch (FileNotFoundException s){s.printStackTrace();}
        vertices=new Node[intersections];
        edges=new Edge[roads];
    }

    /*Method that builds the
     *arrays of intersections and roads that we
     *will then use to build the graph
     */
    public void build(){
        int intersectioncount=0;
        int roadcount=0;
        try{
            String line [];
            Scanner scan=new Scanner(new File(input));
            while (scan.hasNextLine()){
                line=scan.nextLine().split("\\s");
               if(line[0].equals("i")){
                   vertices[intersectioncount]=new Node(line[1],Double.parseDouble(line[2]),Double.parseDouble(line[3]));
                    intersectioncount++;
               }
               else {
                   edges[roadcount]=new Edge(line[1],line[2],line[3]);
                   roadcount++;
               }
            }
        }catch (FileNotFoundException s){s.printStackTrace();}
    }


    /*Getters and Setters*/
    public int getRoads() {
        return roads;
    }
    public void setRoads(int roads) {
        this.roads = roads;
    }
    public int getIntersections() {
        return intersections;
    }
    public void setIntersections(int intersections) {
        this.intersections = intersections;
    }
    public Node[] getVertices() {
        return vertices;
    }
    public void setVertices(Node[] vertices) {
        this.vertices = vertices;
    }
    public Edge[] getEdges() {
        return edges;
    }
    public void setEdges(Edge[] edges) {
        this.edges = edges;
    }
}
