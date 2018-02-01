
/**
 * Week2:1-1:Most Frequent Word
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    public ArrayList<String> myWords;
    public ArrayList<Integer> myFreqs;
    //Constructor
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer> ();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        int freq = 0;
        FileResource fr = new FileResource();
        for (String word:fr.words()){
            String s = word.toLowerCase();
            int index = myWords.indexOf(s);
            if (index==-1){//not exist yet
                myWords.add(s); //add to the words list
                myFreqs.add(1); //add one element to freq list as 1
            }
            else{   //exist already
                myFreqs.set(index,myFreqs.get(index)+1); 
            }
        }
        //System.out.println("The word = yes "+ myFreqs.get(4));
    }
    
    public int findIndexOfMax(){
        int indexOfMax = 0;
        for (int i=0; i<myWords.size(); i++){
            if (myFreqs.get(i) > myFreqs.get(indexOfMax)){
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }
    
    public void tester(){
        System.out.println("NEW---------------------------------------------------");
        myWords.clear();
        myFreqs.clear();
        
        findUnique();
        int number = myWords.size();
        System.out.println("The number of unique words = "+number);
        /*for (int i=0; i<number; i++){
            String word = myWords.get(i);
            int freq = myFreqs.get(i);
            System.out.println("The word = "+word +", freq = "+freq);
        }*/
        int maxIndex = findIndexOfMax();
        System.out.println("The word occurs most= "+myWords.get(maxIndex) +
                            ", freq = "+myFreqs.get(maxIndex));
    }
}
