
/**
 * 在这里给出对类 GladLibMap 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> usedList;
    private ArrayList<String> usedCategory;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){ //Constructor, initialize the ArrayList
        myMap = new HashMap<String,ArrayList<String>>();
        usedCategory = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        usedList = new ArrayList<String>();
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"adjective","noun","color","country","name","animal",
                            "timeframe","verb","fruit"};
        for (String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s,list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if(label.equals("number")){
                return ""+myRandom.nextInt(50)+5;
        }
        else if (myMap.containsKey(label)){
            return randomFrom(myMap.get(label));
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){ 
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){ //word is not label
            return w;                   //use original word
        }
        String prefix = w.substring(0,first); //extract the label name
        String suffix = w.substring(last+1);
        //String sub = getSubstitute(w.substring(first+1,last)); //substitue the label name
        //if not used before ,add to usedList; else get another one
        while (true){ 
            String sub = getSubstitute(w.substring(first+1,last)); 
            
            String label = w.substring(first+1,last);//label name
            int labelindex = usedCategory.indexOf(label);
            if (labelindex==-1){
                usedCategory.add(sub);
            }
            
            int index = usedList.indexOf(sub);
            if (index==-1){ //not used before,add and return
                usedList.add(sub);
                return prefix+sub+suffix;
            } //not used before,add
        }
      
        
        //return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){ //load in Template
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){ //process each word
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){  
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap(){
        int totalNum=0;
        for (String key : myMap.keySet()){
            totalNum = totalNum + myMap.get(key).size();
        }
        return totalNum;
    }
    
    public int totalWordsConsidered(){
        int totalNum=0;
        for (String labels : usedCategory){
            totalNum = myMap.get(labels).size();
        }
        return totalNum;
    }
    
    public void makeStory(){
        usedList.clear();
        System.out.println("\n");
        System.out.println("NEW---------------------------------------------");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("The total number of words= "+totalWordsInMap());
        System.out.println("The total number of words considered= "+totalWordsConsidered());
    }
}
