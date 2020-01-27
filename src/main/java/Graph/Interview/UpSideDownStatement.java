package Graph.Interview;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Stream;

/*
 * Created by Vinod
 * Project :  lend-in-lms
 * On 09/01/20
 */
public class UpSideDownStatement {


  public static void main(String[] args) {
   /* String str = "as sa df ";
    StringBuffer sb = new StringBuffer("");
    String [] strTemp = str.split("\\s+");
    Arrays.stream(strTemp).forEach(f->{
      System.out.print(f+", ");
    });
    System.out.println();
    for(int i= strTemp.length-1; i>=0; --i){
      sb.append(strTemp[i]+ " ");
    }
    System.out.println(sb.toString());

    String num = "01";
    System.out.println(((Double)Double.parseDouble(num)).longValue());
*/
   String s;
   int size[] = new int[0];
  Stack<Integer> stack = new Stack<>();
    System.out.println(stack.empty());

    System.out.println(shortestPalindrome("AsabsA"));
  }

  public static String shortestPalindrome(String s) {
    if (s == null || s.length() <= 1)
      return s;

    String result = null;

    int len = s.length();
    int mid = len / 2;

    for (int i = mid; i >= 1; i--) {
      if (s.charAt(i) == s.charAt(i - 1)) {
        if ((result = scanFromCenter(s, i - 1, i)) != null)
          return result;
      } else {
        if ((result = scanFromCenter(s, i - 1, i - 1)) != null)
          return result;
      }
    }

    return result;
  }

  private static String scanFromCenter(String s, int l, int r) {
    int i = 1;

    //scan from center to both sides
    for (; l - i >= 0; i++) {
      if (s.charAt(l - i) != s.charAt(r + i))
        break;
    }

    //if not end at the beginning of s, return null
    if (l - i >= 0)
      return null;

    StringBuilder sb = new StringBuilder(s.substring(r + i));
    sb.reverse();

    return sb.append(s).toString();
  }
}
