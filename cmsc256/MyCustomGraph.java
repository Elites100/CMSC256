package cmsc256;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

/***********************************************************************************************************************************************************************************
 * MyCustomGraph
 ************************************************************************************************************************************************************************************
 * project six -customGraph
 ************************************************************************************************************************************************************************************
 * Project description
 * Stack implementations
 * Kevin Phung
 * 4/29/2022
 * CMSC-256
 *********************************************************************************************************************************/



public class MyCustomGraph<V> extends UnweightedGraph<V>{

    //private ArrayList<MyCustomGraph> nodes;

    public MyCustomGraph(){
        super();
    }

    public MyCustomGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public MyCustomGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    public MyCustomGraph(List<Edge> edges, int numberOfVertices) {

        super(edges, numberOfVertices);
    }

    public MyCustomGraph(int[][] edges, int numberOfVertices) {

        super(edges, numberOfVertices);
    }



    public MyCustomGraph<V> readFile(String fileName) throws FileNotFoundException {

        File inputFile;
        Scanner in;
        List<Edge> edges = new ArrayList<>();
         int vertices = 0;

        //accept a file name with a string parameter
        try {

             inputFile =  new File(fileName);
             in = new Scanner(inputFile);
        }
        catch (FileNotFoundException ex) {

            throw new FileNotFoundException("Error. File not found");
        }
        if(in.hasNext()) {
            /******************
             * Read in the file
             ******************/

            //Read a line
            vertices = Integer.parseInt(in.nextLine().trim());

            //The first line in the file contains a number of vertices

            //While there are lines in the input file
            while (in.hasNextLine()) {

                //Read the next line in as an array
                String[] line = (in.nextLine()).split(" ");

                //Assign the origin vertex to the first element in the array
                int origin = Integer.parseInt(line[0]);

                //For every terminating vertex
                for (int i = 1; i < line.length; i++) {
                    //Assign each next element in the line as a terminating vertex
                    int endingVertex =  Integer.parseInt(line[i]);
                    //Create an edge object
                    Edge edge = new Edge(origin, endingVertex);
                    //Add it to the edge list
                    edges.add(edge);
                }
            }
        }
        else {
            //throw a null pointer exception
            throw new NullPointerException("The file is empty");
        }
        UnweightedGraph<Integer> myGraph = new MyCustomGraph<>(edges, vertices);
        return (MyCustomGraph<V>) myGraph;
    }



    public boolean isComplete() {
        //Each vertex must be connected to exactly N-1 other vertices. n(n-1)/2
        for (int i = 0; i < getVertices().size(); i++) {
            if (getNeighbors(i).size() < (getVertices().size() - 1)) {
                return false;
            }
        }
        return true;
    }





    public boolean areAdjacent(V v1, V v2){
        return getNeighbors(getIndex(v1)).contains(getIndex(v2));
    }

    public boolean isConnected() {
        //if there are less than 2 vertices, the graph is not connected
        if (vertices.size() < 2) {
            return false;
        }
        //returns true if the number of vertices in the search tree is equal to the number of vertices in the graph
        else
            return dfs(0).getNumberOfVerticesFound() == vertices.size();
    }



    public List<V> getShortestPath(V begin, V end){
        List<V> path =  bfs(getIndex(end)).getPath(getIndex(begin));
        //if the destination is not equal to the last element of the list, null is returned
        if (begin == end){
            List<V> list = new ArrayList<>();
            list.add(begin);
            return list;
        }
        if ( path.isEmpty() ||end != path.get(path.size()-1)){
            return null;
        }
        //returns path
        return path;
    }

    public boolean hasCycle(){

        // check the vertices if the size is higher than 3
        if(vertices.size() >= 3)
        {
            // for loop through the vertices size
            for(int i = 0; i < vertices.size(); i++)
            {
                //search the i in vertices using the tree
                SearchTree y = dfs(i);
                List<Integer> getSearch = y.getSearchOrder();
                int v = getSearch.get(getSearch.size() -1);
                return true;
            }
        }
        return false;

    }



    public static void main(String[] args){
        MyCustomGraph<Integer> myGraph = new MyCustomGraph<>();
        try{
            MyCustomGraph<Integer> myGraph2 = myGraph.readFile("test2-1.txt");
            System.out.println("NOW GRAPHING: Graph of text2-1.txt");
            System.out.println("is complete -> " + myGraph2.isComplete());
            System.out.println("is Connected -> " + myGraph2.isConnected());
            System.out.println("A path from 0 to 4 is " + myGraph2.getShortestPath(0, 4));
            System.out.println("hasCycle() show " + myGraph2.hasCycle());
            System.out.println("printEdges() displays: ");
            myGraph2.printEdges();
            System.out.println();

            MyCustomGraph<Integer> myGraph3 = myGraph.readFile("test3.txt");
            System.out.println("NOW GRAPHING: Graph of text3.txt");
            myGraph3.printEdges();
            System.out.println("is complete -> " + myGraph3.isComplete());
            System.out.println("is connected -> " + myGraph3.isConnected());
            System.out.println("The shortest path from 0 to 4 is " + myGraph3.getShortestPath(0, 4));
            System.out.println("The shortest path from 5 to 4 is " + myGraph3.getShortestPath(5, 4));
            System.out.println("hasCyclic() show " + myGraph3.hasCycle());
        } catch (Exception e) {
            System.out.println("something went wrong");
            /*e.printStackTrace();*/
        }
    }










}
