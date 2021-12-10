import edu.princeton.cs.algs4.StdOut;

import java.io.IOException;
import java.util.HashMap;

public class Outcast {
   WordNet wordnet;
   // constructor takes a WordNet object
   public Outcast(WordNet wordnet) {
       this.wordnet = wordnet;
       //initialize
   }
   
   // given an array of WordNet nouns, return an outcast
   public String outcast(String[] nouns) {
       int outcast_id = Integer.MIN_VALUE;
       int largestDistance = 0;
       for(int i = 0; i< nouns.length; i++){
           int distance = 0;
           for(int j = 0; j<nouns.length;j++){
                   int curdistance = wordnet.distance(nouns[i], nouns[j]);
                   distance = distance + curdistance;

           }
           if(distance>largestDistance){
               largestDistance = distance;
               outcast_id = i;
           }


       }

       return nouns[outcast_id];
   }
   
   // Unit Test client
   public static void main(String[] args) throws IOException { //throw because WordNet throws
       WordNet wordnet = new WordNet(args[0], args[1]);
       Outcast outcast = new Outcast(wordnet);

       for(int t=2; t<args.length;t++){
           In in = new In(args[t]);
           String[] nouns = in.readAllStrings();
           StdOut.println(args[t]+": "+ outcast.outcast(nouns));
       }


   }
}