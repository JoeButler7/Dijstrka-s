NAME: Joe Butler
NETID: Jbutl18
EMAIL: Jbutl18@u.rochester.edu
ASSIGNMENT: Project 3
I did not collaborate with anyone on this assignment

	Project 3 saw us creating a comprehensive mapping program in java that utilized 
the graph data structure. 
	I started this project by creating my own Node, Edge, and Graph classes. These can be 
found in the files Node.java, Edge.java, and Graph.java respectively. While I did build these
classes from the ground up I did structure them based off of both the graph implementation in
our textbook, Data Structures & Algorithm Analysis in Java by Clifford A. Shaffer, as well as
a couple of online implementations I found, notably one found at https://gist.github.com/smddzcy/bf8fc17dedf4d40b0a873fc44f855a58.
I did however build everything my self and did not copy any code and merely used these sources as 
inspiration for my code. I decided to construct my node with the visited boolean contained in 
each node itself instead of an external array because I felt that it would be easier to organize 
in this way as the ids for the vertices are Strings not numbers so keeping track of which array 
index corresponded to which node may have been confusing and hard for me to implement. Each node also
has a double to represent its distance from the starting node that is initialized to the maximum value 
for a double and also a pointer to the previous node in the shortest path to the starting node that is intialized
to null. Both of these were not originally in my node class but I decided to add them once I started to
implement the shortest path algorithm as they helped alot in implementing the algorithm. Each Node also has a hash
map where the key is Node and the data is my edge class and this hashmap represents each nodes adjacency list. I decided to
use a hashmap so I could include both the nodes it is connected to as well as edges along which it was connected as 
this made it easier to keep track of the edge weights and the distance to each neighbor vertex. Besides a constructor and
getters and setters my Node class only contains two methods. addEdge() which takes in a node that the node is adjacent to 
as well as the edge that the node is adjacent to on and it just adds the node and edge to the adjacency list hashmap. There
is also a compareTo method which overrides the method from the Comparable interface and comapres the nodes based off of thier
distance from the starting node. 
	My edge class is fairly simple, with each edge only containing two strings, which are the IDs of the vertices that 
it connects, and a double which represnets the distance or the weight of the edge. There are no methods besides a constructor 
and a getter and setter in my edge class. I decided to have an my edge class contain the IDs of the Nodes it connects represented
by a string isntead of pointers to the actual nodes as it made it easier to create the array of edges when I parse the file. Originally
I had used Node pointers but this became problematic to create the edges when parsing the file with my implementation of parsing the files
and storing the data in arrays. I also knew that accessing the nodes via their string in the hashmap is a constant time operation so it 
would not be very high cost to use another implementation so I could include Node pointers in my edge class.
	My Graph class is also not extremely complicated as the only class variables are a hashmap where String is the key and Nodes are the
data and  4 doubles to represent the minimum and maximum latitude and longitude as those are very useful when deciding the scale of 
the actual drawing of my map. The Hashmap is just a list of all of the Nodes in the graph indexed by their strings, I decided to do a hashmap
instead of a lsit or another collection as it allows easy access to the nodes with lookup by ID. My graph class contains a constructor which
takes in an array of Edges and an array of nodes and constructs the graph by adding all of the nodes to the hashmap in the graph and adding all
of the edges to the adjacency lists of the nodes that they connect. This constructor also checks each node's coordinates to find the minimum
and maximum latitude and longitude. There are two other methods within my graph class. One is Haversine, which takes in 4 doubles, lat1, long1,
lat2 and long2, which represent the latitude and longitude of two points and it uses the haversine formula to calculate the distance
between the two points and it returns teh absolute value of this distance. I decided to return the absolute value just to make sure it would
not return a negative number as this would not make sense for our project. There is also a method called Dijsktra which takes in two
Strings, start and end, which are the IDs of the two vertices that we want to calculate the shortest path between. This method uses
dijsktra's algorithm to find the shortest path between two points and returns a linked list of the nodes along the shortest path between these
nodes. I will disucss this in more depth later. 
	My DrawMap class is in the file DrawMap.java and it simply acts as the class that hadnles all of the graphics for this project. This class contains
three methods other than the constructor, CalcX and CalcY which just calculate the coordinates of the points fro java graphics based off of their latitude
and longitude, and paintcomponent which does the actual drawing. This class takes in a graph as a argument for its constructor and to draw the map it simply
iterates through each vertex and their adjacency lists and draws lines to connect all neighbor vertices. It also contains witch statements and if/else blocks
to change the color based off of which map it is, as well as to draw the seal of the respected area. For ur.txt it will draw the map in Yellow and Blue for 
the UR colors and print the UR seal in the top left corner. For NYS.txt it will print the map in Navy and white as the New York flag is navy, and it will
also print the NYS seal in the corner. For Monroe.txt it will print the Monroe county seal in the top right corner and draws the map in black and yellow
(Black and yellow have no relation to monroe county I just thought it looked cool). If given an input file that is not one of those three it will simply
draw a black and white map. The files containing the seals arw "Meliora,jpeg","MonroeSeal.jpeg", and "NewYork.jpeg".
	I also have a class called FileParse in the file FileParse.java. This class contains two methods, count which counts the number of intersections
and roads in the text file and then stores these values as integers, and build, which will read the same file that count did but will fill arrays with
the intersections and roads from the file. The count method exists so we can itialize the size of the road and intersection arrays. I decided to do this 
instead of storing the intersections and roads in linked lists as access to a linkedlist is n^2 time as you may have to iterate through each element to 
get to the desired element whereas access to an array is constant time and building the array as well as initializing the array only takes 2n time and order 
n is less than n^2 time. 
	The main method is in my class StreetMap.java. The main method just contains calls to all of the other functions, as well as checks to see if the 
command line arguments contain things like "--show" or "--directions" so the program knows to draw the map or compute the shortest path.
	There were a couple issues I ran into while creating this program that I had to think through and find a solution to. One of these issues was that
for my shortest path algorithm I did not know how to add nodes to the return path. I solved this by adding a pointer to the previous or "parent" node 
and then continuously ediitng this pointer while finding the shortest path. An implmentation of a graph using a shortest path algorithm I found at https://www.baeldung.com/java-dijkstra did this but instead each node had a LinkedLisdt of nodes that represented the shortest path to the starting node from it 
and I realized that including something such as a pointer to the previous or "parent" node would help solve my issue, 
but I figured it would be more space efficient to just point to the previous node instead of having an entire linked list of 
pointers to nodes. I also felt that this way it would be easier to edit the shortest path as the algorithm runs.  
	Another issue I ran into was that it was hard to keep the aspect ratio of my graph correct while scaling the size of the window. To solve this problem
I did some thinking, as well as reading online about the best way to scale graphics and colcuded that I should compute some sort of "padding", to add 
to my coordinates that I graphed. These paddings are represented by the variables xpad and ypad in the DrawMap class. They are computed in the CalcX and
CalcY classes and they are computed by first checking if the height of the window is larger then the width whewn calculating the Y coordinate, and vice 
verse when calculating that X coordinate. The way this works is that if the height is greater than the width, then the ypadding is half the difference
between the height and width of the window, this padding is added to both sides, and the Y scale is computed after subtracting this padding from the window.
The same happens for the X coordinate but with the roles of width and height reversed. This keeps the aspect ratio by maxmimizing the use of the smaller side
and then only using that much space on the bigger side so for example if it is a 1000x500 window the map will be drawn in a 500x500 window in the center. 
	Surprisingly I never encoutnered an issue with exceptionally long runtimes for the larger graphs. One smaller issue I did encounter however that that 
it would not indicate when no path was present and would insstead just print out the starting node and the value of a max Integer. To solve this issue I simply
added a check that will only print the path and distance if the pointer from the last node to a parent is not equal to null(ie if it hasn't been changed it will
not print). 
	We were also tasked with analyzing the runtime of our code. To build the in memory graph it is just order of O(|E|+|V|) as all we do is parse through the 
file and create node/edge objects and add them to a hashmap. As stated above since I stored my vertices and edges data from the file in arrays instead of lists
we have order n isntead of order n^2, or in this case |E|^2+|V|^2 as access to an array is a constant time operation, and insertion to a hashmap is also a constant time operation. Mapping of my graph is also O(|E|+|V|) as the plotting function simply preforms a breadth first search on the graph and does a couple
constant time operations to get the latitude and longitude and compute the coordinates and then draw a line. The reason a BFS is O(|E|+|V|) time is because
it simply iterates through each node in the graph and proccess each adjacency list at most once. The eleemnts in an adjaceny list represnets the edges or |E|
so at most we will do |E|+|V| operations, and all operations we do within the traversal are constant time operation. Our shortest path algorithm has a runtime 
of O((|E|+|V|)log|V|). This is because it must iterate through each edge in the graph as it preforms a BFS 
and must iterate through each eleemnt of each adjacency list to find the shortest path, so this is where the order 
|E|+V| comes from. The log|V| comes from having to build and update a min binary heap (priority queue) 
iteration and this head contains all of the available neighbors, worst case V, and updating a 
binary heap takes logn time. Therefore for each edge we process we pick the minimum element fron the heap O(1) time and 
then we have to update the heap which is logV time.
	I believe that my changed of color based off the map as well as printing the seal out is worthy of some extra credit
if you would consider it :). 

to build and run this program navigate to the folder with the source code and enter teh following commands via the command line

javac *.java
java StreetMap <InputFile> --show 		in order to just show the map where <InputFile> is the input text file
java StreetMap <InputFile> --directions <intersection1> <intersection2> 	where <InputFile> is the same as above and <intersection1> and <intersection2>
are two intersections you want directions between to only get the directions and distance between two points without drawing the map and

java StreetMap <InputFile> --directions --show <intersection1> <intersection2> 		to both directions and the map drawn with directions drawn on the map

NOTE: --show and --directions can be entered in any order but the intersection IDs must be the last two arguements. 

This project was built in inetlliJ idea and Sublimetext3