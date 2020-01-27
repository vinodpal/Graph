package Graph.DepthFirstSearch.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * Created by Vinod
 * Project :  lend-in-lms
 * On 12/12/19
 */
public class IslandGirlfriend {


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


  public static void main(String[] args) throws Exception {
    FastReader s=new FastReader();
    Integer N = s.nextInt();
    Map<Integer, Set<Integer>> map = new HashMap<>(N);
    Integer v1;
    Integer v2;
    String [] str= "A B".split(" ");
    for(int i=0; i<N-1; ++i){
      v1 = s.nextInt();
      v2 = s.nextInt();
      updateMap(map,v1,v2);
      updateMap(map,v2,v1);
    }
    Integer Q = s.nextInt();
    Set<Integer> girlsVertex = new HashSet<>(Q);
    for(int i=0;i<Q; ++i){
      girlsVertex.add(s.nextInt());
    }
    Integer[] minDistanceSoFar = new Integer[1];
    minDistanceSoFar[0] = Integer.MAX_VALUE;
    Integer[] vertexIndex = new Integer[1];
    vertexIndex[0] = Integer.MAX_VALUE;
    Set<Integer> visited = new HashSet<>(N);
    dfs(map, visited, minDistanceSoFar, 0, girlsVertex, 1, vertexIndex);
    PrintWriter pw = new PrintWriter(System.out, false);
    pw.println(vertexIndex[0]);
    pw.flush();
    System.out.println(minDistanceSoFar[0] +" "+ vertexIndex[0] );
  }

  static void updateMap(Map<Integer, Set<Integer>> map, Integer v1, Integer v2){
    if(map.containsKey(v1)){
      Set<Integer> setVertex = map.get(v1);
      setVertex.add(v2);
      map.put(v1,setVertex);
    }else{
      Set<Integer> setVertex = new HashSet<>();
      setVertex.add(v2);
      map.put(v1,setVertex);
    }
  }
  static void dfs(Map<Integer, Set<Integer>> map,
        Set<Integer> visited, Integer[] minDistanceSoFar,
        Integer currentDistance, Set<Integer> girlVertex,
        Integer currentVertex, Integer[] vertexIndex){

        visited.add(currentVertex);
        if(currentDistance > minDistanceSoFar[0]){
          return;
        }else if(currentDistance == minDistanceSoFar[0] ){
        if(vertexIndex[0]> currentVertex) {
            vertexIndex[0] = currentVertex;
          }
        }
        if(girlVertex.contains(currentVertex) &&
          currentDistance<minDistanceSoFar[0]){
          minDistanceSoFar[0] = currentDistance;
          vertexIndex[0] = currentVertex;
          return;
        }
        Set<Integer> setVertex = map.get(currentVertex);
        for(Integer vertex : setVertex){
          if(visited.contains(vertex)){
            continue;
          }
          dfs(map, visited, minDistanceSoFar, currentDistance+1,
              girlVertex, vertex, vertexIndex);
        }
  }
}
