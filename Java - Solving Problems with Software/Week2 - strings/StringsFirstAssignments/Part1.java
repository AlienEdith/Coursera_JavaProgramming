
/**
 * Finding a Gene
 * 
 * @yxwang
 * @20180108
 */
public class Part1 {

    public String findSimpleGene(String dna){
    String result="";
    int startindex = dna.indexOf("ATG");
        if (startindex == -1){
            result = "";
        }
    int stopindex = dna.indexOf("TAA",startindex+3);
        if (stopindex == -1){
            result = "";
        }
        if ((stopindex - startindex)%3==0){
        result = dna.substring(startindex,stopindex+3);
        }
    return result;
    }
    
    public void testSimpleGene(){
    String a1 = "AAATGCCCTAACTAGATTAAGAAACC"; //no ATG start condo
    String a2 = "CTGATGACTAGTCG"; //no TAA stop condo
    String a3 = "ACGATGCCCTACTAA"; //valid
    String a4 = "ACGATGCCTACTAA"; //not multiple of 3
    String a5 = "CTGATACTAGTAA"; //no ATG
    String result1 = findSimpleGene(a1);
    System.out.println("The DNA of 1 is "+ a1);
    System.out.println("The gene of 1 is "+ result1);
    }
}
