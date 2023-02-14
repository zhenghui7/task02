package myapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        
        for (String a: args) {
            System.out.printf(">>>%s\n", a);
        }

        Path p = Paths.get(args[0]);
        File f = p.toFile();

        Map<String, Integer> wordFreq = new HashMap<>();
        Map<String, Integer> pairWords = new HashMap<>();

        //open a file as input stream
        InputStream is = new FileInputStream(f);

        //codes to read 
        InputStreamReader isr = new InputStreamReader(is);

        //read whole lines
        BufferedReader br = new BufferedReader(isr);

        String line;
        
        while((line = br.readLine()) != null) {
                       
            //to remove all puncutations and change to lowercase
            String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

            //to scan through and identify all the words. attempt to add in a 2nd loop to count the subsequent word
            String wordprevious = "TESTABCDEFGH";

            for (String w: words) {
                if (words.length<0); {
                    
                    String wordPair = wordprevious + " " + w;
                    
                    int count = pairWords.getOrDefault(wordPair, 0);
                    pairWords.put(wordPair, count +1);

                    // to update wordprevious
                    wordprevious = w;
                }
            }

            //printing the count of pair words
            for (String pair : pairWords.keySet()){
                System.out.println(pair + ": count: " + pairWords.get(pair) );
            }
            
        }


        //2nd part of task 2

        while((line = br.readLine()) != null) {
                       
            //to remove all puncutations and change to lowercase
            String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

            //     to count for individual words   
            for (String w: words) {
                if (!wordFreq.containsKey(w)) {
                    wordFreq.put(w, 1);
                    
                } else {
                    //if word is in map, increment the count
                    int c = wordFreq.get(w);
                    wordFreq.put(w, c + 1);
                }
            }
                 
         }
        
        //to calculate the probability to each of the unique words
        Set<String> uniqueWords = wordFreq.keySet();
            for (String w: uniqueWords) {
                int count = wordFreq.get(w);
                System.out.printf(": %s - %d\n", w, count);

                
                }
        System.out.printf("Number of unique words: %d\n", uniqueWords.size());

    }
    
}