/*Class that represents our graph 
*data structure and contains the  
*functionality to build a graph given
*a collection of vertices and edges
*
*@author Joe Butler (Jbutl18@u.rochester.edu)
*/
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph {
    HashMap<String, Node> vertices;//Node List
    double minlat, minlon,maxlat,maxlon;//these will be used to scale drawing

    public Graph(Node [] verts, Edge [] edges){
        vertices=new HashMap<>();
        minlat=verts[0].getLatitiude();
        maxlat=verts[0].getLatitiude();
        minlon=verts[0].getLongitude();
        maxlon=verts[0].getLongitude();
        for(Node vert: verts){//adds all Nodes to graph
             vertices.put(vert.getId(),vert);
             //Checks max/mins and updates when necessary
             if(vert.getLatitiude()>maxlat)
                 maxlat=vert.getLatitiude();
             if(vert.getLatitiude()<minlat)
                minlat=vert.getLatitiude();
             if(vert.getLongitude()>maxlon)
                maxlon=vert.getLongitude();
             if(vert.getLongitude()<minlon)
                minlon=vert.getLongitude();

        }
        for(Edge e:edges){//adds all edges to graph
            Node to=vertices.get(e.getTo());
            Node from=vertices.get(e.getFrom());
            e.setDist(findDist(to.getLatitiude(),from.getLatitiude(),to.getLongitude(),from.getLongitude()));
            to.addEdge(from,e);
            from.addEdge(to,e);
            //System.out.println(e.getDist()+" miles");
        }
    }

    /*prints adjacency list for each node in graph
        Used for debugging*/
    public void print(){
        for(String s:vertices.keySet()){
            System.out.print(s+" is adj to ");
            vertices.get(s).printadj();
            System.out.println("\n");
        }
    }


    /*Method that uses Dijsktra's Algorithm to
     *compute the shortest path between two given points
     *on the graph
     *@param String start, ID of intersection we are starting from
     *@param String end, ID of intersection we want to reach
     *@return LinkedList<Node> shortestPath, linked list of the vertices along the shortest path
     */
    public LinkedList<Node> shortestPath(String start, String end){
        Node beginning =vertices.get(start);
        Node last=vertices.get(end);
        PriorityQueue<Node> pq=new PriorityQueue<>();
        LinkedList<Node> ret=new LinkedList<>();
        beginning.setVisited(true);//sets starting node to visited
        beginning.setDistance(0);//sets distance to starting node to 0
        pq.add(beginning);//adds starting node to priority queue
        while(!pq.isEmpty()){
            Node curr=pq.poll();//removes top of queue
            curr.setVisited(true);//sets current to visited
            for(Node n:curr.getAdj().keySet()){//iterates thru adjacency list for current node
                Edge edge=curr.getAdj().get(n);

                if(curr.getDistance()+edge.getDist()<n.getDistance()){
                    //checks if path from current to neighbor is shorter than shortest path to neighbor
                    n.setDistance(curr.getDistance()+edge.getDist());//if it is updates shortest path
                    n.setPrevious(curr);//also updates node its coming from
                    if(!n.isVisited()){//adds neighbor to q if it hasn't been visited yet
                        pq.add(n);
                    }
                    else if(pq.remove(n))//if it has been visited removes and adds it back to queue to update queue
                        pq.add(n);
                }

            }
        }

        Node fin=last;
        while(fin!=null){//starts at last node and iterates through previous node along shortest path
            ret.addFirst(fin);//keeps adding next node (previous) to return list
            fin=fin.getPrevious();
        }
        return ret;
    }

    /*Helper method that uses Haversine
     * formula to calculate the distance
     * between two points using latitude and
     * longitude
     * @param double lat1, latitude of first point
     * @param double lat2, latitude of second point
     * @param double long1, longitude of first point
     * @param long2, longitude of second point
     * @return double ret, distance between the two points*/
    private double findDist(double lat1,double lat2, double long1, double long2){
        double ret;
        double lat1rad=Math.toRadians(lat1);
        double lat2rad=Math.toRadians(lat2);
        double long1rad=Math.toRadians(long1);
        double long2rad=Math.toRadians(long2);

        double a=Math.pow(Math.sin((lat2rad-lat1rad)/2),2.0);
        double b=Math.pow(Math.sin((long2rad-long1rad)/2),2.0);
        //3958.8 is earth's radius
        ret=2*3958.8*Math.asin(Math.sqrt(a+Math.cos(long1rad)*Math.cos(long2rad)*b));//haversine
        return Math.abs(ret);//returns absolute value to avoid negative values
    }
}
