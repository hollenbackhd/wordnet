import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;


public class ShortestCommonAncestor {
    Digraph G;

   // constructor takes a rooted DAG as argument
   public ShortestCommonAncestor(Digraph G) {
       this.G = new Digraph(G); //don't forget format this.X specifies digraph G from above
   }

   // length of shortest ancestral path between v and w
   public int length(int v, int w) {
       BreadthFirstDirectedPaths searchV = new BreadthFirstDirectedPaths(G, v);
       BreadthFirstDirectedPaths searchW = new BreadthFirstDirectedPaths(G, w);

       int shortest = 999;
       for (int i = 0; i < G.V(); i++) {
           if (searchV.hasPathTo(i) && searchW.hasPathTo(i)) { //checking the vertices to see which ones have a path to both v & w
               int dist = searchV.distTo(i) + searchW.distTo(i); //distance from vertice i to both v & w
               if (dist < shortest) { //if distance from s is shorter than correct shortest path
                   shortest = dist;
               }
           }
       }
       return shortest;

   }

   // a shortest common ancestor of vertices v and w
   public int ancestor(int v, int w) {
       BreadthFirstDirectedPaths searchV = new BreadthFirstDirectedPaths(G, v);
       BreadthFirstDirectedPaths searchW = new BreadthFirstDirectedPaths(G, w);

       int shortest = 999;
       int ancestor = -1;
       for (int i = 0; i < G.V(); i++) {
           if (searchV.hasPathTo(i) && searchW.hasPathTo(i)) { //checking the vertices to see which ones have a path to both v & w
               int dist = searchV.distTo(i) + searchW.distTo(i); //distance from vertice s to both v & w
               if (dist < shortest) { //if distance from s is shorter than correct shortest path
                   shortest = dist;
                   ancestor = i; //ancestor = current shortest path vertice
               }
           }
       }
       return ancestor;
   }
   


   // length of shortest ancestral path of vertex subsets A and B
   public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
       BreadthFirstDirectedPaths searchA = new BreadthFirstDirectedPaths(G, subsetA);
       BreadthFirstDirectedPaths searchB = new BreadthFirstDirectedPaths(G, subsetB);

       int shortest = 999;
       for (int i = 0; i < G.V(); i++) {
           if (searchA.hasPathTo(i) && searchB.hasPathTo(i)) { //checking the vertices to see which ones have a path to both v & w
               int dist = searchA.distTo(i) + searchB.distTo(i); //distance from vertice i to both v & w
               if (dist < shortest) { //if distance from s is shorter than correct shortest path
                   shortest = dist;
               }
           }
       }
       return shortest;
       // Output shortest length of all pairs
   }

   // a shortest common ancestor of vertex subsets A and B
   public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
       BreadthFirstDirectedPaths searchA = new BreadthFirstDirectedPaths(G, subsetA);
       BreadthFirstDirectedPaths searchB = new BreadthFirstDirectedPaths(G, subsetB);

       int shortest = 999;
       int ancestor = -1;
       for (int i = 0; i < G.V(); i++) {
           if (searchA.hasPathTo(i) && searchB.hasPathTo(i)) { //checking the vertices to see which ones have a path to both v & w
               int dist = searchA.distTo(i) + searchB.distTo(i); //distance from vertice s to both v & w
               if (dist < shortest) { //if distance from s is shorter than correct shortest path
                   shortest = dist;
                   ancestor = i; //ancestor = current shortest path vertice
               }
           }
       }
       return ancestor;
       // Output shortest common ancestor of all pairs
   }



   
   // do unit testing of this class
   public static void main(String[] args) {

       // Build unit tests
       if (args.length < 1) {
           manualUnitTest();
       } else {
           In in = new In(args[0]);
           Digraph G = new Digraph(in);
           ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
           while (!StdIn.isEmpty()) {
               int v = StdIn.readInt();
               int w = StdIn.readInt();
               int length   = sca.length(v, w);
               int ancestor = sca.ancestor(v, w);
               StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
           }
       }
   }
   
   // Unit test made by me
   public static void manualUnitTest() {
    // Basic tree test
       int numVertices = 6;// or whatever
       Digraph d1 = new Digraph(numVertices);
       d1.addEdge(1, 0); // add a bunch of these, to form some tree-like shape, e.g.:
       d1.addEdge(2,0);
       d1.addEdge(3,1);
       d1.addEdge(4,1);
       d1.addEdge(5,2);
       /*
        *             0
        *          /      \
        *         1        2
        *        / \      / \
        *       3   4    5 
        */
       
       ShortestCommonAncestor sca = new ShortestCommonAncestor(d1);
       int w = 3; // fixme
       int x = 2; // fixme
       int y = 1; // fixme
       int z = 4; // fixme

       StdOut.println("Testing Case: 1");
       StdOut.println("length: " + sca.length(x, y));
       StdOut.println("ancestor: " + sca.ancestor(x, y));


       // testing sets with some iterable type
       // ({1,2},{3,4})
       Bag<Integer> b1 = new Bag<Integer>();
       Bag<Integer> b2 = new Bag<Integer>();

       b1.add(x);
       b1.add(y);
       b2.add(w);
       b2.add(z);

       StdOut.println("Testing Case: 2");
       StdOut.println("length: " + sca.length(b1, b2));
       StdOut.println("ancestor: " + sca.ancestor(b1, b2));
   }
}