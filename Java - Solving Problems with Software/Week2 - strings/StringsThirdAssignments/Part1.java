
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.StorageResource;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import java.io.File;

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

    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while(true){
        String currgene = findGene(dna,startIndex);
        startIndex = dna.indexOf("ATG",startIndex);
        if(currgene.isEmpty()) break;  //if no gene, break
        geneList.add(currgene);
        //startIndex = dna.indexOf(currgene,startIndex)+currgene.length();
        startIndex = startIndex+currgene.length();
        }
        System.out.println("The number of gene is "+geneList.size());
        return geneList;
    }

    public float cgRatio(String dna){
        /*int len = dna.length();
        int CGCount = 0;

        for(int i=0; i<len; i++){

            if(dna.charAt(i) == 'c' || dna.charAt(i) == 'g')
                CGCount++;*/
        int cindex = 0;
        int gindex = 0;
        int count = 0;
        while(true){
        cindex = dna.indexOf("C",gindex);
        if(cindex==-1) break;  //if no gene, break
        count++;
        gindex = dna.indexOf("G",gindex);
        if(gindex==-1) break;  //if no gene, break
        count++;
        
        cindex = dna.indexOf("C",cindex)+1;
        gindex = dna.indexOf("G",gindex)+1;
        }
        //System.out.println("The number of C and g is " +count);
        return (float)count/dna.length();
    }
    
    public int countCTG(){
        DirectoryResource dr = new DirectoryResource();
        StorageResource genes = new StorageResource();
        String DNA="";
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String dna = fr.asString();
            DNA = dna.toUpperCase();}
        int ctgIndex = 0;
        int count = 0;
        while(true){
        ctgIndex = DNA.indexOf("CTG",ctgIndex);
        if(ctgIndex==-1) break;  //if no gene, break
        count++;
        
        ctgIndex = ctgIndex+3;
        }
        System.out.println("The number of CTG is " +count);
        return count;
    }
    
    public void processGene(StorageResource sr){
        int cntlonger60=0;
        int CGGreater35=0;
        
        for(String gene:sr.data()){
            if(gene.length()>60){
            System.out.println("The gene is " +gene);
            cntlonger60++;
            }
        }   
        System.out.println("The number of gene longer than 60 is " +cntlonger60);
        for(String gene:sr.data()){    
            if(cgRatio(gene)>0.35){
            System.out.println("The gene is " +gene);
            CGGreater35++;
            }
        }
        System.out.println("The number of gene cgratio larger than 0.35 is " +CGGreater35);
        int longlength=0;        
        for(String gene:sr.data()){    
            if(longlength<gene.length()){
               longlength=gene.length(); 
        }
        
        }
        
        
        System.out.println("The longest of gene number is " +longlength);
    }
    
    public void  testProcessGenes(){
    DirectoryResource dr = new DirectoryResource();
    StorageResource genes = new StorageResource();
    
    for(File f : dr.selectedFiles()){
    FileResource fr = new FileResource(f);
    String dna = fr.asString();
    String DNA = dna.toUpperCase();
    
    genes = getAllGenes(DNA);
 
    processGene(genes);
   
    }
    
    /*public void testgetAllgenes(){
    String a = "atgaaataaaaaatgccctga";
    String DNA = a.toUpperCase();
   
    StorageResource genes = getAllGenes(DNA);
    for (String gene:genes.data()){
    System.out.println(gene);
    }
    }*/
}
}
