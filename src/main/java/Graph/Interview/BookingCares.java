package Graph.Interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * Created by Vinod
 * Project :  lend-in-lms
 * On 05/01/20
 */

/* INPUT
7
3
1 1481122000 1481122020
3 1481122000 1481122020
1 1481122020 1481122040
2 1481122020 1481122040
3 1481122040 1481122060
1 1481122050 1481122060
3 1481122070 1481122090
2

* */
public class BookingCares {

  static class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    public FastReader() {

    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }

  public static void main(String[] args) throws Exception{
    Calendar dateOfBirth = Calendar.getInstance();
    dateOfBirth.set(1992, 7, 21);
//    LocalDate localDate = new LocalDate(dateOfBirth.getTime());
//    System.out.println(" sfasf sf  d".replaceAll("\\s+","").trim());
//    String transactionDate = "Tue Nov 26 00:00:00 IST 2019";
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
//    Date date= simpleDateFormat.parse(transactionDate);
//    System.out.println( date);
//    String dateStr = "Mon Jun 18 00:00:00 IST 2012";
//    DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//    Date date1 = (Date)formatter.parse(dateStr);
//    System.out.println(date1);


  }



//  public static void main(String[] args) {
//    Map<Long, Long>  caresMap = new HashMap<>();
//    Map<Long, Long> caresMapCountBreak = new HashMap<>();
//    FastReader s=new FastReader();
//    int n = s.nextInt();
//    int m = s.nextInt();
//    for(int i=0;i<n;++i){
//      Long id = s.nextLong();
//      Long startTime = s.nextLong();
//      Long endTime = s.nextLong();
//      if(caresMap.containsKey(id)){
//        if(caresMap.get(id).equals(startTime)){
//          caresMap.put(id, endTime);
//        }else{
//          caresMapCountBreak.put(id, caresMapCountBreak.get(id)+1L);
//          caresMap.put(id, endTime);
//        }
//      }else{
//        caresMap.put(id, endTime);
//        caresMapCountBreak.put(id, 0L);
//      }
//    }
//    Long k = s.nextLong();
//    Set<Long> ids = caresMapCountBreak.keySet();
//    Long countBreak = 0L;
//    for (Long id: ids){
//      if(caresMapCountBreak.get(id).compareTo(k)<0){
//        ++countBreak;
//        System.out.println("Id " + id+ " count : "+caresMapCountBreak.get(id));
//      }
//    }
//    System.out.println(countBreak);
//  }
}
