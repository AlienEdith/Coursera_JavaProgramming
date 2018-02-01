
/**
 * Assignment week1-1:WordPlay
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class WordPlay {
    public boolean isVowel(char ch){
        char upperch = Character.toUpperCase(ch);
        char lowerch = Character.toLowerCase(ch);
        boolean isvowel = (upperch=='A' || upperch=='E' || upperch=='I' || upperch=='O' || upperch=='U'
        || lowerch=='a' || lowerch=='e' || lowerch=='i' || lowerch=='o' || lowerch=='u');
        return isvowel;
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder phrasereplace = new StringBuilder(phrase);
        int i=0;
        for (i=0; i<phrase.length(); i++){
            char currCh = phrasereplace.charAt(i);
            if (isVowel(currCh)){
                phrasereplace.setCharAt(i,ch);
            }
        }
        return phrasereplace.toString();
    }
    
    public String emphasize(String phrase,char ch){
        StringBuilder phraseremph = new StringBuilder(phrase);
        char upperch = Character.toUpperCase(ch);
        char lowerch = Character.toLowerCase(ch);
        int i=0;
        for (i=0; i<phrase.length(); i++){
            char currCh = phraseremph.charAt(i);
            if (currCh == upperch || currCh == lowerch){
                if (i%2==0){ //even index,odd number location
                    phraseremph.setCharAt(i,'*');
                } 
                else{
                    phraseremph.setCharAt(i,'+');
                }
            }
        }
        System.out.println(phraseremph.toString());
        return phraseremph.toString();
    }
}
