
/**
 * Finding a Gene Part2
 * 
 * @yxwang
 * @20180108
 */
public class Part2 {
    public String findSimpleGene(String dna,String startCodon,String stopCodon){
    String result="";
    String upperdna = dna.toUpperCase();
    int startindex = upperdna.indexOf(startCodon);
        if (startindex == -1){
            result = "";
        }
    int stopindex = upperdna.indexOf(stopCodon,startindex+3);
        if (stopindex == -1){
            result = "";
        }
        if ((stopindex - startindex)%3==0){
        result = dna.substring(startindex,stopindex+3);
        }
    return result;
    }
    
    public void testSimpleGene(){
    String a1 = "ATGGGTTAAGTC"; //no ATG start condo
    String a2 = "gatgctataat"; 
    //String a2 = "CTGATGACTAGTCG"; //no TAA stop condo
    //String a3 = "ACGATGCCCTACTAA"; //valid
    //String a4 = "ACGATGCCTACTAA"; //not multiple of 3
    //String a5 = "CTGATACTAGTAA"; //no ATG
    String startCodon="ATG";
    String stopCodon="TAA";
    String result1 = findSimpleGene(a1,startCodon,stopCodon);
    System.out.println("The DNA of 1 is "+ a1);
    System.out.println("The gene of 1 is "+ result1);
    String result2 = findSimpleGene(a2,startCodon,stopCodon);
    System.out.println("The DNA of 2 is "+ a2);
    System.out.println("The gene of 2 is "+ result2);
    }
}
