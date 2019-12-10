/*Class that does the actions related
*to drawing the map we have created
*as well as the shortest path using 
*java graphics
*
*
*@author Joe Butler (Jbutl18@u.rochester.edu)
*/
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class DrawMap extends JPanel{
    private double scalelat;
    private double scalelon;
    private double aspect;
    private double xpad;
    private double ypad;
    private Graph graph;
    private LinkedList<Node> shortestPath;
    private LinkedList<Node> drawPath;
    private int img;



    public DrawMap(Graph graph){
        this.graph=graph;
        aspect=(graph.maxlat-graph.minlat)/(graph.maxlon-graph.minlon);
        shortestPath=new LinkedList<>();
    }

    /*Generates X coordinate
     *for the next point using proper scale
     *@param Node n, node to calculate coordinate of
     *@return Double, X coordinate of node
     */
    public double CalcX(Node n){
        xpad=0.0;
        if(getWidth()>getHeight())//Will maximize use of smaller side and scale other side accordingly
            xpad=(getWidth()-getHeight())/2;
        scalelon=Math.abs((getWidth()-2*xpad)/(graph.maxlon-graph.minlon));
        return (n.getLongitude()- graph.minlon)*scalelon+xpad;
    }

    /*Generates Y coordinate
     *for the next point using proper scale
     *@param Node n, node to calculate coordinate of
     *@return Double, Y coordinate of node
     */
    public double CalcY(Node n){
        ypad=0.0;
        if(getHeight()>getWidth())
            ypad=(getHeight()-getWidth())/2;//Will maximize use of smaller side and scale other side accordingly
        scalelat=Math.abs(getHeight()-2*ypad)/(graph.maxlat-graph.minlat);
        return (n.getLatitiude()-graph.minlat)*scalelat+ypad;
    }

    /*Paint component method that will
     *Draw the graph to a JPanel that
     *we will add to a JFrame
     */
    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        super.paintComponent(g);
        int x,y,x1,y1;
        Node to;

        setBackground(Color.white);
        g2d.setColor(Color.BLACK);
        /*If/else statements that will
         *set the background color and color
         * of the roads based off the map to give meaningful
         * color (i.e. Blue and yellow of UR)
         */
        if(img==0) {
            try {
                BufferedImage Pic = ImageIO.read(new File("MonroeSeal.jpeg"));
                int scale;
                if (getHeight() >= getWidth())
                    scale = getHeight() / 6;
                else
                    scale = getWidth() / 6;
                g.drawImage(Pic, getWidth()-scale, 0, scale, scale, this);
                setBackground(Color.black);
                g2d.setColor(Color.yellow);
            }catch(IOException e){e.printStackTrace();}
        }

       else if(img==1) {
            try {
                BufferedImage Pic = ImageIO.read(new File("Meliora.jpeg"));
                int scale;
                if (getHeight() >= getWidth())
                    scale = getHeight() / 6;
                else
                    scale = getWidth() / 6;
                g.drawImage(Pic, 0, 0, scale, scale, this);
                setBackground(new Color(12,16,170));
                g2d.setColor(new Color(247,247,2));
                g2d.setStroke(new BasicStroke(3));
            }catch(IOException e){e.printStackTrace();}
        }

        else if(img==2) {
            try {
                BufferedImage Pic = ImageIO.read(new File("NewYork.jpeg"));
                int scale;
                if (getHeight() >= getWidth())
                    scale = getHeight() / 6;
                else
                    scale = getWidth() / 6;
                g.drawImage(Pic, 0, 0, scale, scale, this);
                setBackground(new Color(0,16,84));
                g2d.setColor(Color.white);
            }catch(IOException e){e.printStackTrace();}
        }

        for(String s:graph.vertices.keySet()){
            //iterates through each vertex
            to=graph.vertices.get(s);
            x=(int)CalcX(graph.vertices.get(s));
            y=(int)(getHeight()-(CalcY(graph.vertices.get(s))));
            for(Node n:to.getAdj().keySet()){
                //iterates thru all neighbors, drawing a line between vertex and its neighbors
                x1=(int)CalcX(n);
                y1=(int)(getHeight()- CalcY(n));
                g2d.drawLine(x,y,x1,y1);
            }
        }

        //switch statment to set color/stroke based off of map
            switch (img){
                case 0:
                    g2d.setColor(Color.white);
                    g2d.setStroke(new BasicStroke(3));
                    break;
                case 1:
                    g2d.setColor(Color.BLACK);
                    g2d.setStroke(new BasicStroke(7));
                    break;
                case 2:
                    g2d.setColor(Color.yellow);
                    g2d.setStroke(new BasicStroke(3));
                    break;
                default:
                    g2d.setColor(Color.GREEN);
                    g2d.setStroke(new BasicStroke(5));
            }

            for(Node n: shortestPath){//iterates through vertices in shortest path
                if(n.getPrevious()!=null){
                //draws line between current vertex and previous one.
                x=(int)CalcX(n);
                y=(int)(getHeight()- CalcY(n));
                x1=(int)CalcX(n.getPrevious());
                y1=(int)(getHeight()- CalcY(n.getPrevious()));
                g2d.drawLine(x,y,x1,y1);}
            }

    }

    /*Getters and Setters*/
    public double getScalelat() {
        return scalelat;
    }

    public void setScalelat(double scalelat) {
        this.scalelat = scalelat;
    }

    public double getScalelon() {
        return scalelon;
    }

    public void setScalelon(double scalelon) {
        this.scalelon = scalelon;
    }

    public double getAspect() {
        return aspect;
    }

    public void setAspect(double aspect) {
        this.aspect = aspect;
    }

    public double getXpad() {
        return xpad;
    }

    public void setXpad(double xpad) {
        this.xpad = xpad;
    }

    public double getYpad() {
        return ypad;
    }

    public void setYpad(double ypad) {
        this.ypad = ypad;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public LinkedList<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void setImg(int img){
        this.img=img;
    }
}
