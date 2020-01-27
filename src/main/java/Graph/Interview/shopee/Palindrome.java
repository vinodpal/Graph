package Graph.Interview.shopee;

/*
 * Created by Vinod
 * Project :  lend-in-lms
 * On 27/01/20
 */
public class Palindrome {

  public static void main(String[] args) {
    String str = "RACE"; //ECARACE
//    String str = "ASABSA"; //ASABASA
//    String str = "ASSABA"; //ASABASA
    System.out.println(findLexicographicallyAlphbeticallyWithMininumAddStringPlaindromeString(str));
  }

  static String findLexicographicallyAlphbeticallyWithMininumAddStringPlaindromeString
      (String str){
    StringBuilder rightStr = new StringBuilder("");
    StringBuilder leftStr = new StringBuilder("");
    int l = 0;
    int r = str.length()-1;
    while(l<r){
      if(str.charAt(l) == str.charAt(r)){
        rightStr.append(str.charAt(l));
        if(l+1!=r)
        leftStr.append(str.charAt(r));
        ++l;--r;
      }else if(str.charAt(l)>str.charAt(r)){
        rightStr.append(str.charAt(r));
        leftStr.append(str.charAt(r));
        --r;
      }else{
        rightStr.append(str.charAt(l));
        leftStr.append(str.charAt(l));
        ++l;
      }
    }
    rightStr.append(str.charAt(l));
    System.out.println("Left: "+leftStr.toString()+ ", Right: "+rightStr);
        return rightStr.append(leftStr.reverse()).toString();
  }
}
