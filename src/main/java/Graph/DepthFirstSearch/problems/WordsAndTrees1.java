package Graph.DepthFirstSearch.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * Created by Vinod
 * Project :  lend-in-lms
 * On 22/12/19
 */
public class WordsAndTrees1 {

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


  static class Tree{
    String name;
    Set<WordsAndTrees.Tree> subRoot;
    Set<String> lablesNode;
    Integer index;

    public Tree(String name, Integer index, Integer length){
      this.name = name;
      this.index = index;
      this.subRoot = new HashSet<>(length);
      lablesNode = new HashSet<>(length);
      lablesNode.add(this.name);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof WordsAndTrees.Tree)) {
        return false;
      }
      WordsAndTrees.Tree tree = (WordsAndTrees.Tree) o;
      return Objects.equals(name, tree.name) &&
          Objects.equals(index, tree.index);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, index);
    }
  }

//  public static void main(String args[] ) throws Exception {
//
//    WordsAndTrees1.FastReader s=new WordsAndTrees1.FastReader();
//    Integer N = s.nextInt();
//    Integer Q = s.nextInt();
//    String[] nodes = s.nextLine().split(" ");
//    Integer index = 1;
//    Map<Integer, Tree> nodeMap = new HashMap<>(N);
//    Map<Integer, Set<Integer>> mapNodeForEdges=  new HashMap<>(N-1);
//    for(String node : nodes){
//      nodeMap.put(index,new Tree(node, index,N-1));
//      mapNodeForEdges.put(index, new HashSet<>());
//      ++index;
//    }
//    for(int itr = 0; itr<N-1; ++itr) {
//      Integer root = s.nextInt();
//      Integer subRoot = s.nextInt();
////      mapNodeForEdges.put(root, mapNodeForEdges.get(root).add(subRoot))
//      if (mapNodeForEdges.containsKey(root)) {
//        Set<Integer> sets = mapNodeForEdges.get(root);
//        sets.add(subRoot);
//        mapNodeForEdges.put(root, sets);
//      } else {
//        Set<Integer> set = new HashSet<>(N - 1);
//        set.add(subRoot);
//        mapNodeForEdges.put(root, set);
//      }
//      if (mapNodeForEdges.containsKey(subRoot)) {
//        Set<Integer> sets = mapNodeForEdges.get(subRoot);
//        sets.add(root);
//        mapNodeForEdges.put(subRoot, sets);
//      } else {
//        Set<Integer> set = new HashSet<>(N - 1);
//        set.add(root);
//        mapNodeForEdges.put(subRoot, set);
//      }
//    }
//
////      Queue<Integer> queue = new LinkedList<Integer>();
////      queue.add(1);
//    Set<Integer> visited = new HashSet<>(N-1);
//     /* while(!queue.isEmpty()){
//        Integer root = queue.poll();
//        Set<Integer> set = mapNodeForEdges.get(root);
//        visited.add(root);
//        for(Integer subRoot : set){
//          if(!visited.contains(subRoot)){
//            queue.add(subRoot);
//            Tree rootNode = nodeMap.get(root);
//            Tree subRootNode = nodeMap.get(subRoot);
//            rootNode.subRoot.add(subRootNode);
//            rootNode.lablesNode.addAll(subRootNode.lablesNode);
//          }
//        }
//      }*/
//    visited.clear();
//    Integer[][] dp = new Integer[N][27];
//    dfsUtil(mapNodeForEdges, visited, 1, dp, nodeMap);
//
//    PrintWriter pw = new PrintWriter(System.out, false);
//    for(int itr=0; itr<Q; ++itr){
//      String[] query = s.nextLine().split(" ");
//      Integer node = Integer.parseInt(query[0]);
//      String[] labels = query[1].split("");
//      Set<String> nodeLables = nodeMap.get(node).lablesNode;
//      Integer countLable = 0;
//      for(String lable : labels){
//        if(nodeLables.contains(lable)){
//          ++countLable;
//          nodeLables.remove(lable);
//        }
//      }
//      pw.println(labels.length - countLable);
//    }
//    pw.flush();
//  }

  public static void main(String[] args) {
    String str = "abs";
    System.out.println(str.charAt(0)-97);
  }

  static void dfsUtil(Map<Integer, Set<Integer>> nodeMap, Set<Integer> visited, Integer currentNode,
      Integer[][]dp, Map<Integer, Tree> nodeName){
    visited.add(currentNode);
    Set<Integer> setSubRoot = nodeMap.get(currentNode);
    Set<String> labelsSubRoot = new HashSet<>();
    for(Integer node: setSubRoot){
      if(!visited.contains(node)){

        dfsUtil(nodeMap, visited,  node, dp, nodeName);
//        labelsSubRoot.addAll(node);
      }
    }
    ++dp[currentNode][(nodeName.get(currentNode).name.charAt(0) - 'a')];
    for(Integer node: setSubRoot){
      for(int i=0;i<27;++i){
        dfsUtil(nodeMap, visited,  node, dp, nodeName);
//        dp[currentNode][i] += dp[][i];
//        labelsSubRoot.addAll(node);
      }
    }
//    nodeMap.get(currentNode).lablesNode.addAll(labelsSubRoot);
  }
}
