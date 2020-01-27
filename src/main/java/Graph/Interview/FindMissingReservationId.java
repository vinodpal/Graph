package Graph.Interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Created by Vinod
 * Project :  lend-in-lms
 * On 05/01/20
 */
public class FindMissingReservationId {

  static class UserReservation {
    public long userId;
    public long timestamp;

    public UserReservation(long userId, long timestamp) {
      this.userId = userId;
      this.timestamp = timestamp;
    }
  }
  public static void main(String[] args) {
    Map<Long, Set<Long>> userReservationMap = new HashMap<>();
    List<UserReservation> userReservationList = new ArrayList<>();
    userReservationList.add(new UserReservation(1234, 532632));
    userReservationList.add(new UserReservation(234, 632633));
    userReservationList.add(new UserReservation(2354, 732634));
    userReservationList.add(new UserReservation(2354, 7326543));
    for (UserReservation user : userReservationList){
      if(userReservationMap.containsKey(user.userId)) {
        userReservationMap.get(user.userId).add(user.timestamp);
      }else{
        Set<Long> timeStamp = new HashSet<>();
        timeStamp.add(user.timestamp);
        userReservationMap.put(user.userId, timeStamp);
      }
    }
    System.out.println(userReservationMap.values());


  }
}
