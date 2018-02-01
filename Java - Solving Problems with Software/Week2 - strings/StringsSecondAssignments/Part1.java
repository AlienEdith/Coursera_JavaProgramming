
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part1 {

    public int findStopCodon(String dna,int startIndex,String stopCodon){
    int stopIndex = dna.indexOf(stopCodon,startIndex+3);
        while (stopIndex!=-1){
            if ((stopIndex-startIndex)%3==0){
            return stopIndex;
            }
            else{
                stopIndex = dna.indexOf(stopCodon,stopIndex+3);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int where){
    int startIndex = dna.indexOf("ATG",where); //findAllgenes
    if (startIndex==-1) return "";
    
    int taaindex = findStopCodon(dna,startIndex,"TAA");
    int tagindex = findStopCodon(dna,startIndex,"TAG"); 
    int tgaindex = findStopCodon(dna,startIndex,"TGA");
    
    int temp = Math.min(taaindex,tagindex);
    int Minidex= Math.min(temp,tgaindex);
    
    if(Minidex==dna.length()) return "";
    
    return dna.substring(startIndex,Minidex+3);
    }
    
    public void testFindStopCodon(){
    //        0123456789AB
    String a="CCCATGAAATAA"; //valid ATGAAATAA (9
    String b="CCCATGAATAA";  //not multiple of 3 (11
    String c="CCCATGAAA";    //no stop (9
    //System.out.println("The 1st stopIndex of a is "+ resulta);
    //System.out.println("The 1st stopIndex of b is "+ resultb);
    //System.out.println("The 1st stopIndex of c is "+ resultc);
    }
    
    /*public void testFindGene(){
    //        0123456789ABCDEF
    String a="CCCAAATAA"; //no ATG empty
    String b="CCCATGAAATAA";  //valid ATGAAATAA
    String c="CCCATGAATAAATAG";    //valid ATGAATAAATAG
    String d="CCCATGAATAAAATAGAATGA"; //valid ATGAATAAAATAGAATGA
    String e="CCCATGAATAAAATAG"; //no valid stop
    
    String resulta=findGene(a);
    String resultb=findGene(b);
    String resultc=findGene(c);
    String resultd=findGene(d);
    String resulte=findGene(e);
    
    System.out.println("The dna of a is "+ a);
    System.out.println("The gene of a is "+ resulta);
    System.out.println("The dna of b is "+ b);
    System.out.println("The gene of b is "+ resultb);
    System.out.println("The dna of c is "+ c);
    System.out.println("The gene of c is "+ resultc);
    System.out.println("The dna of d is "+ d);
    System.out.println("The gene of d is "+ resultd);
    System.out.println("The dna of e is "+ e);
    System.out.println("The gene of e is "+ resulte);
    }*/
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
        String currgene = findGene(dna,startIndex);
        startIndex = dna.indexOf("ATG",startIndex);
        if(currgene.isEmpty()) break;  //if no gene, break
        System.out.println("The gene of is "+ currgene);
        //startIndex = dna.indexOf(currgene,startIndex)+currgene.length();
        startIndex = startIndex+currgene.length();
        }
    }
    
    public void testingAllgenes(){
    String a = "CCCATGAAATAACCATGCCCTAG";
    printAllGenes(a);
    }
}
