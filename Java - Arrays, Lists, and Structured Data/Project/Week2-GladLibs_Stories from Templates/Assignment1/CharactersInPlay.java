
/**
 * 在这里给出对类 CharactersInPlay 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> Names;
    private ArrayList<Integer> Freqs;
    
    public CharactersInPlay(){
        Names = new ArrayList<String>();
        Freqs = new ArrayList<Integer>();
    }
    
    public void update(String person){
        int index = Names.indexOf(person);
        if (index==-1){
            Names.add(person); //add to the name list
            Freqs.add(1); //add one element to freq list as 1
        }
        else{   //exist already
            Freqs.set(index,Freqs.get(index)+1); 
        }   
    }
    
    public void findAllCharacters(){
        Names.clear();
        Freqs.clear();
        String name = "";
        FileResource fr = new FileResource();
        for (String word : fr.lines()){
            int periodindex = word.indexOf(".");
            if (periodindex!=-1){ //find the words before period
                name = word.substring(0,periodindex).toLowerCase();
                update(name);
            }
            //else {continue;} //if no period in this line, jump to next line
        }
    }
    
    public void charactersWithNumParts(int num1,int num2){
        for (int i=0; i<Names.size(); i++){
            if (Freqs.get(i) >= num1 && Freqs.get(i) <= num2){
                System.out.println(Names.get(i)+" /Speaking Freq= "+Freqs.get(i));
            }
        }
    }
    
    public void tester(){
        System.out.println("NEW----------------------------------------------");
        findAllCharacters();
        for (int i=0; i<Names.size(); i++){
            if (Freqs.get(i) > 10){
                System.out.println(Names.get(i)+" /Speaking Freq= "+Freqs.get(i));
            }
        }
        
        //charactersWithNumParts(10,15);
    }
}
