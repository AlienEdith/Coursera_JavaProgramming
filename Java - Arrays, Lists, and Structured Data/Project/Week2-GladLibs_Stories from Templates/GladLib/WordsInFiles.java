
/**
 * 在这里给出对类 WordsInFiles 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.*;
import java.io.File;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> wordsMap;
    //Constructor
    public WordsInFiles(){
        wordsMap = new HashMap<String,ArrayList<String>>();
    }
    
    public void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String word : fr.words()){
           if (wordsMap.containsKey(word)){
               ArrayList al = wordsMap.get(word);
               if (!al.contains(f.getName())){
                   al.add(f.getName());
               }
           }
           else{
               ArrayList al = new ArrayList();
               al.add(f.getName());
               wordsMap.put(word,al);
           }
        }
    }
    
    public void buildWordFileMap(){
        wordsMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int maxNum=0;
        for (String key : wordsMap.keySet()){
            int currNum = wordsMap.get(key).size();
            if (currNum > maxNum){
                maxNum = currNum;
            }
        }
        return maxNum;
    }
    
    public ArrayList wordsInNumFiles(int number){
        ArrayList<String> wordAL = new ArrayList<String>();
        for (String key : wordsMap.keySet()){
            int currSize = wordsMap.get(key).size();
            if (currSize == number){
                wordAL.add(key);
            }
        }
        return wordAL;
    }
    
    public void printFilesIn(String word){
        
            ArrayList<String> al = wordsMap.get(word);
            System.out.println("The files that contain \'"+word+"\' is \n");
            for(int i=0; i<al.size(); i++){
                System.out.println(al.get(i));
            }
        
    }
    
    public void tester(){
        System.out.println("NEW------------------------------------------");
        buildWordFileMap();
        printFilesIn("tree");
        //System.out.println("The maximum of files= "+maxNumber());
        //ArrayList<String> wordAL = wordsInNumFiles(4);
        //System.out.println(wordAL.size());
        //for(int i=0; i<wordAL.size(); i++){
        //        System.out.println(wordAL.get(i));
        //        printFilesIn(wordAL.get(i));
        //}
        
    }
}
