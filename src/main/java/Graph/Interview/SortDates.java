package Graph.Interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Created by Vinod
 * Project :  lend-in-lms
 * On 08/01/20
 */
public class SortDates {

  public static void main(String[] args) {
     sortDate();
  }
  static void sortDate(){
    String dates[] = { /*"24 Jul 2017", "25 Jul 2017", "11 Jun 1996",
        "01 Jan 2019",*/ "12 Jul 1963", "01 May 1963" };
    List<String> datesList = Arrays.asList(dates);
    Map<String,Integer> monthsMap = sortMonths();
    Collections.sort(datesList, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        String month1 = o1.substring(3,6);
        String month2 = o2.substring(3,6);
        System.out.println(month1);
        // Comparing the years
        String year1 = o1.substring(7, 10);
        String year2 = o2.substring(7, 10);
        if (year1.compareTo(year2) != 0) {
          if (year1.compareTo(year2) < 0)
            return -1;
          return 1;
        }
        if(monthsMap.get(month1).compareTo(monthsMap.get(month2))==0){
          String day1 = o1.substring(0, 2);
          String day2 = o2.substring(0, 2);
          System.out.println("date "+ day1);
          if (day1.compareTo(day2) < 0)
            return -1;
          return 1;
        }else if(monthsMap.get(month1).compareTo(monthsMap.get(month2))<0){
          return -1;
        }else return 1;
      }
    });

    datesList.forEach(f->{
      System.out.print(f +", ");
    });

  }
  static Map<String,Integer> sortMonths() {
    Map<String,Integer> monthsMap = new HashMap<>(12);
    monthsMap.put("Jan", 1);
    monthsMap.put("Feb", 2);
    monthsMap.put("Mar", 3);
    monthsMap.put("Apr", 4);
    monthsMap.put("May", 5);
    monthsMap.put("Jun", 6);
    monthsMap.put("Jul", 7);
    monthsMap.put("Aug", 8);
    monthsMap.put("Sep", 9);
    monthsMap.put("Oct", 10);
    monthsMap.put("Nov", 11);
    monthsMap.put("Dec", 12);
    return monthsMap;
  }
}
