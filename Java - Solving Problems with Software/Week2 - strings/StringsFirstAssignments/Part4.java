
/**
 * 在这里给出对类 Part4 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.URLResource;

public class Part4 {

    public String findingLinks(){
    URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    String youtube = "youtube.com";
    String result="";
    for (String word : url.words()){
        int youtubeindex = word.toLowerCase().indexOf(youtube);
        if (youtubeindex!=-1){
        int firstquote = word.lastIndexOf("\"",youtubeindex);
        int secondquote = word.indexOf("\"",youtubeindex+1);
        result = word.substring(firstquote+1,secondquote);
        System.out.println(result);
        }
    }
    return result;
    }
    
}
