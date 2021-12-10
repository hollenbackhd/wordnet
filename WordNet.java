import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Multiway;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class WordNet {
    private final Digraph digraph;
    SeparateChainingHashST<String, LinkedList<Integer>> hashSets = new SeparateChainingHashST<>();//key, value -> used for finding strings to ints
    SeparateChainingHashST<Integer, String> nounHash = new SeparateChainingHashST<>(); // -> used for finding ints to strings
    ShortestCommonAncestor SCA;


    // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) throws IOException /* "throw" required for FileReader*/ {



       // Read in all synsets (and do something with them)
       BufferedReader input = new BufferedReader(new FileReader(synsets));
       String line = input.readLine();
       int numText = 0;
       while (line != null) {
           String parts[] = line.split(",");
           int synId = Integer.parseInt(parts[0]);
           String synStr = parts[1];
           String[] synset = synStr.split(" ");

           //notice: the definitions are in parts[2];  we're ignoring those
           for(int i= 0; i<synset.length;i++){
               if(!hashSets.contains(synset[i])) {
                   hashSets.put(synset[i], new LinkedList<Integer>());
               }
               hashSets.get(synset[i]).add(numText);
               nounHash.put(numText, synset[i]);

               //adding the synsets to the hashmap
           }


           // need to do more here (and elsewhere, too)
           // Read next line from file and ..
           line = input.readLine();
           numText++;
       }

       ////digraph stuff////////////////////////////////////////////

       digraph = new Digraph(numText);

       input.close();

       input = new BufferedReader(new FileReader(hypernyms));
       line = input.readLine();
       numText = 0;
       while (line != null) {
           String parts[] = line.split(",");
           int synId = Integer.parseInt(parts[0]);
           for(int i=1; i<parts.length; i++) {
               digraph.addEdge(synId, Integer.parseInt(parts[i]));
           }
           //notice: the definitions are in parts[2];  we're ignoring those
           // need to do more here (and elsewhere, too)
           // Read next line from file and ..
           line = input.readLine();
           numText++;
       }
       input.close();



       // Read in all hypernyms with some similar code

   }

   // all WordNet nouns
   public Iterable<String> nouns(){
       return hashSets.keys(); //
   }


   // is the word a WordNet noun?
   public boolean isNoun(String word) {
       return !(hashSets.get(word) == null); //
   }


   // a synset (second field of synsets.txt) that is a shortest common ancestor
   // of noun1 and noun2 (defined below)
   public String sca(String noun1, String noun2) {
       SCA = new ShortestCommonAncestor(digraph);
       LinkedList<Integer> newInt1 = hashSets.get(noun1);
       LinkedList<Integer> newInt2 = hashSets.get(noun2);
       int ancestor = SCA.ancestor(newInt1, newInt2);

        return nounHash.get(ancestor); //
   }

   // distance between noun1 and noun2 (defined below)
   public int distance(String noun1, String noun2) {
       SCA = new ShortestCommonAncestor(digraph);
       LinkedList<Integer> newInt1 = hashSets.get(noun1);
       LinkedList<Integer> newInt2 = hashSets.get(noun2);


       return SCA.length(newInt1, newInt2); //
   }
   

   // do unit testing of this class
   public static void main(String[] args) throws IOException{ //"throw" because the constructor throws.
       WordNet wnet = new WordNet("synsets.txt", "hypernyms.txt");
       System.out.println(wnet.sca("grass","tree"));
       System.out.println(wnet.distance("grass","tree"));
        //how to test
   }
}