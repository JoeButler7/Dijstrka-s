/*Class that represents our node 
*object that will be used as the
*vertices in our graph class for
*the graph data structure. 
*
*@author Joe Butler (Jbutl18@u.rochester.edu)
*/
import java.util.HashMap;

public class Node implements Comparable<Node>{

    private String id;
    private double latitiude;
    private double longitude;
    private boolean visited;
    private double distance;
    private Node previous;//pointer to previous node. Will be useful when we calculate the shortest path
    private HashMap<Node,Edge> adj;//adjacency list

    public Node(String id, double latitiude,double longitude){
        this.id=id;
        this.latitiude=latitiude;
        this.longitude=longitude;
        visited=false;
        adj=new HashMap<>();
        distance=Double.MAX_VALUE;
    }


    /*Method that prints adjacency
     *list for the node. Used for debugging
     */
    public void printadj(){
        for(Node n: adj.keySet()){
            System.out.print(n.getId()+" along "+adj.get(n).getId()+", ");
        }
    }

    /*Adds edge to adjacency list
     *@param Node Neighbor, neighbor vertex to add to adj list
     *@param Edge e, edge along which neighbor is connected
     * */
    public void addEdge(Node neighbor, Edge e){
    adj.put(neighbor,e);
    }

    /*CompareTo method used to construct the PriorityQueue
     *That will be used buy Dijsktra's algorithm
     *Compares based off of distance from starting node
     *@param Node n, node that it will be compared to
     */
    public int compareTo(Node n){
        if(n.getDistance()>this.distance)
            return -1;
        if(n.getDistance()<this.distance)
            return 1;
        else
            return 0;
    }


    /*Getters and Setters*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitiude() {
        return latitiude;
    }

    public void setLatitiude(double latitiude) {
        this.latitiude = latitiude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public HashMap<Node, Edge> getAdj() {
        return adj;
    }

    public void setAdj(HashMap<Node, Edge> adj) {
        this.adj = adj;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
