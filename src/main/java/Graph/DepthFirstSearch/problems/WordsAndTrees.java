package Graph.DepthFirstSearch.problems;

/*
 * Created by Vinod
 * Project :  lend-in-lms
 * On 22/12/19
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*

LINK :- https://www.hackerearth.com/practice/algorithms/graphs/depth-first-search/practice-problems/algorithm/words-and-trees-f9ef202c/description/


* You are given a rooted tree with N nodes. Each node contains a lowercase English letter. Node with label 1 is the root.There are Q questions of the form

X S: Here X is the root of subtree and S is a string.

For each question, let T be the string built using all the characters in the nodes of subtree with root X (each subtree node character comes exactly once) .
For each question, print minimum number of characters to be added to T , so that the we can build S using some characters of string T (each character of string T can be used at most once).


Hint: Find all the characters coming in each subtree and use it to get the answer to each question.

Input Format

The first line of input consists of two space separated integers N and Q that are number of nodes in the tree and number of questions respectively.
Next line will contain N space separated lowercase English letters, where  letter will be the letter stored in node with label i .
Each of the next  lines contains two space separated integers u and v that denote there is an edge between nodes with labels u and v .
Next Q lines follow. Each line will contain an integer X that denotes the node label and a string S separated by a single space.

Output Format

For each query, print the required answer in a new line.

Input Constraints





All characters in nodes and string are lowercase English letters.

Sum of lengths of strings in all the questions is at most

SAMPLE INPUT
8 3
o v s l v p d i
1 3
8 3
4 8
6 1
5 3
7 6
2 3
7 ifwrxl
4 eyljywnm
3 llvse
SAMPLE OUTPUT
6
7
2
*
* Explanation
Query 1- Character in the subtree with root 7 is d, we need 6 characters(i,f,w,r,x,l) to make S=(ifwrxl).

Query 2- Character in the subtree with root 4 is l, we need 7 characters(e,y,j,y,w,n,m) to make S=(eyljywnm).

Query 3- Characters in the subtree with root 3 are (v,s,i,l), we need 2 characters(l,e) to make S=(llvse).
* */
public class WordsAndTrees {

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
      Set<Tree> subRoot;
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
        if (!(o instanceof Tree)) {
          return false;
        }
        Tree tree = (Tree) o;
        return Objects.equals(name, tree.name) &&
            Objects.equals(index, tree.index);
      }

      @Override
      public int hashCode() {
        return Objects.hash(name, index);
      }
    }

    public static void main(String args[] ) throws Exception {

      FastReader s=new FastReader();
      Integer N = s.nextInt();
      Integer Q = s.nextInt();
      String[] nodes = s.nextLine().split(" ");
      Integer index = 1;
      Map<Integer, Tree> nodeMap = new HashMap<>(N);
      for(String node : nodes){
        nodeMap.put(index,new Tree(node, index,N-1));
        ++index;
      }
      Map<Integer, Set<Integer>> mapNodeForEdges=  new HashMap<>(N-1);
      for(int itr = 0; itr<N-1; ++itr) {
        Integer root = s.nextInt();
        Integer subRoot = s.nextInt();
        nodeMap.get(root).subRoot.add(nodeMap.get(subRoot));
        nodeMap.get(subRoot).subRoot.add( nodeMap.get(root));
        /*if (mapNodeForEdges.containsKey(root)) {
          Set<Integer> sets = mapNodeForEdges.get(root);
          sets.add(subRoot);
          mapNodeForEdges.put(root, sets);
        } else {
          Set<Integer> set = new HashSet<>(N - 1);
          set.add(subRoot);
          mapNodeForEdges.put(root, set);
        }
        if (mapNodeForEdges.containsKey(subRoot)) {
          Set<Integer> sets = mapNodeForEdges.get(subRoot);
          sets.add(root);
          mapNodeForEdges.put(subRoot, sets);
        } else {
          Set<Integer> set = new HashSet<>(N - 1);
          set.add(root);
          mapNodeForEdges.put(subRoot, set);
        }*/
      }

//      Queue<Integer> queue = new LinkedList<Integer>();
//      queue.add(1);
      Set<Integer> visited = new HashSet<>(N-1);
     /* while(!queue.isEmpty()){
        Integer root = queue.poll();
        Set<Integer> set = mapNodeForEdges.get(root);
        visited.add(root);
        for(Integer subRoot : set){
          if(!visited.contains(subRoot)){
            queue.add(subRoot);
            Tree rootNode = nodeMap.get(root);
            Tree subRootNode = nodeMap.get(subRoot);
            rootNode.subRoot.add(subRootNode);
            rootNode.lablesNode.addAll(subRootNode.lablesNode);
          }
        }
      }*/
      visited.clear();
      synNode(nodeMap, visited, 1);

      PrintWriter pw = new PrintWriter(System.out, false);
      for(int itr=0; itr<Q; ++itr){
        String[] query = s.nextLine().split(" ");
        Integer node = Integer.parseInt(query[0]);
        String[] labels = query[1].split("");
        Set<String> nodeLables = new HashSet<>(nodeMap.get(node).lablesNode);
        Integer countLable = 0;
        for(String lable : labels){
          if(nodeLables.contains(lable)){
            ++countLable;
            nodeLables.remove(lable);
          }
        }
        pw.println(labels.length - countLable);
      }
      pw.flush();
  }

  static void synNode(Map<Integer, Tree> nodeMap, Set<Integer> visited, Integer currentNode){
      visited.add(currentNode);
      Set<Tree> setSubRoot = nodeMap.get(currentNode).subRoot;
      Set<String> labelsSubRoot = new HashSet<>();
      for(Tree node: setSubRoot){
        if(!visited.contains(node.index)){
          synNode(nodeMap, visited,  node.index);
          labelsSubRoot.addAll(node.lablesNode);
        }
      }
    nodeMap.get(currentNode).lablesNode.addAll(labelsSubRoot);
  }
}
