/*Class that represents the
*edge object that will be
*used in our graph data structure
*implementation 
*
*
*@author Joe Butler (Jbutl18@u.rochester.edu)
*/
public class Edge {
    private String id;
    private double dist;//weight of edge
    private String to, from;//ID of nodes the edge connects

    public Edge(String id, String from, String to){
        this.id=id;
        this.to=to;
        this.from=from;
    }


    /*Getters and Setters*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }
}
