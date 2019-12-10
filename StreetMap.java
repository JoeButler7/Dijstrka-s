/*Class that contains main 
*method for this program.
*Takes in command line 
*arguments and calls proper
*methods.
*
*@author Joe Butler (Jbutl18@u.rochester.edu)
*/


import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
public class StreetMap {
    public static void main(String[]args){
        String input=args[0];
        String to="";
        String from="";
        FileParse parse=new FileParse(input);
        parse.count();
        parse.build();
        Graph g=new Graph(parse.getVertices(),parse.getEdges());
        LinkedList<Node> shortest=new LinkedList<>();
        if(args.length==5){
            to=args[3];
            from=args[4];
            shortest=g.shortestPath(to,from);
        }
        else if(args.length==4){
            to=args[2];
            from=args[3];
            shortest=g.shortestPath(to,from);
        }
        if(args[1].equals("--show")||args[2].equals("--show")){
        JFrame frame=new JFrame();
        DrawMap map=new DrawMap(g);
        if(args.length>2)
            if(args[1].equals("--directions")||args[2].equals("--directions"))
                 map.setShortestPath(shortest);
        if(args[0].equals("ur.txt"))
            map.setImg(1);
        else if(args[0].equals("monroe.txt"))
            map.setImg(0);
        else if(args[0].equals("nys.txt"))
            map.setImg(2);
        else
            map.setImg(5);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.add(map);
        frame.setVisible(true);}
        if(args.length>2)
             if(args[1].equals("--directions")||args[2].equals("--directions")){
                if(shortest.size()>1){
                    System.out.println("The shortest path between "+to+" and "+from+" is as follows:");
        for(Node n:shortest)
                System.out.println(n.getId());
            System.out.println("Total Distance: "+shortest.getLast().getDistance()+" miles");}
            else
                System.out.println("There exists no path between "+to+" and "+from);
            }
    }
}
