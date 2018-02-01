
/**
 * week2:3-1 Condon Count
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.*;
public class CodonCount {
    private HashMap<String,Integer> codonMap;
    //Constructor
    public CodonCount(){
        codonMap = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start,String dna){
        int index = 0;
        String codon ="";
        for (int i=start; i<dna.length(); i=i+3){
            if(i+3>dna.length()){break;}
            codon = dna.substring(i,i+3);
            if (codonMap.containsKey(codon)){
                codonMap.put(codon,codonMap.get(codon)+1);
            }
            else{
                codonMap.put(codon,1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        String mostCodon = "";
        int maxNum = 0;
        for (String s : codonMap.keySet()){
            if (codonMap.get(s) > maxNum){
                mostCodon = s;
                maxNum = codonMap.get(s);
            }
        }
        return mostCodon;
    }
    
    public void printCodonCounts(int start,int end){
        for (String s : codonMap.keySet()){
            if (codonMap.get(s) >= start || codonMap.get(s) <= end){
                System.out.println("The codon is \'"+s+"\' and the count = "+codonMap.get(s));
            }
        }
    }
    
    public void tester(){
        System.out.println("NEW---------------------------------------------------");
        codonMap.clear();
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        System.out.println("Frame=0");
        buildCodonMap(0,dna);
        System.out.println("The total number of codon = "+codonMap.size());
        System.out.println("The most common codon = "+getMostCommonCodon()
                            +" its count= "+codonMap.get(getMostCommonCodon()));
        System.out.println("The counts of codon in [1,5]： "); 
        printCodonCounts(1,5);
        System.out.println("Frame=1");
        codonMap.clear();
        buildCodonMap(1,dna);
        System.out.println("The total number of codon = "+codonMap.size());
        System.out.println("The most common codon = "+getMostCommonCodon()
                            +" its count= "+codonMap.get(getMostCommonCodon()));
        System.out.println("The counts of codon in [1,5]： "); 
        printCodonCounts(1,5);
        System.out.println("Frame=2");
        codonMap.clear();
        buildCodonMap(2,dna);
        System.out.println("The total number of codon = "+codonMap.size());
        System.out.println("The most common codon = "+getMostCommonCodon()
                            +" its count= "+codonMap.get(getMostCommonCodon()));
        System.out.println("The counts of codon in [1,5]: "); 
        printCodonCounts(1,5);
    }
}
