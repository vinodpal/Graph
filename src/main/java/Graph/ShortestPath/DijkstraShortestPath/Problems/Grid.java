package Graph.ShortestPath.DijkstraShortestPath.Problems;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * Created by Vinod
 * Project :  lend-in-lms
 * On 12/12/19
 */
public class Grid {
  static class Cell {
    int pi;
    int pj;
    public Cell(int i, int j){
      pi = i;
      pj = j;
    }
  }

  static PrintWriter pw = new PrintWriter(System.out, false);
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

///////////////////////////////////////////////////////

  static class Parser{
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;
    public Parser(InputStream in){
      din = new DataInputStream(in);
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0; }
    public long nextLong() throws Exception {
      long ret = 0;
      byte c = read();
      while (c <= ' ') c = read();
      boolean neg = c == '-';
      if (neg) c = read();
      do { ret = ret * 10 + c - '0';
        c = read();
      } while (c > ' ');
      if (neg) return -ret;
      return ret; }
    //reads in the next string
    public String next() throws Exception{
      StringBuilder ret = new StringBuilder();
      byte c = read();
      while (c <= ' ') c = read();
      do {
        ret = ret.append((char)c);
        c = read();
      } while (c > ' ');
      return ret.toString(); }
    public int nextInt() throws Exception {
      int ret = 0;
      byte c = read();
      while (c <= ' ') c = read();
      boolean neg = c == '-';
      if (neg) c = read();
      do {
        ret = ret * 10 + c - '0';
        c = read(); } while (c > ' ');
      if (neg) return -ret;
      return ret; }
    private void fillBuffer() throws Exception{
      bytesRead = din.read(buffer,
          bufferPointer = 0, BUFFER_SIZE);
      if (bytesRead == -1) buffer[0] = -1; }
    private byte read() throws Exception {
      if (bufferPointer == bytesRead)
        fillBuffer();
      return buffer[bufferPointer++]; }}


///////////////////////////////////////////////////////






  public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT
*/
    //Scanner
//    Scanner s = new Scanner(System.in);

//    FastReader s = new FastReader();
    Parser s=new Parser(System.in);

    Integer N = s.nextInt();
    Integer M = s.nextInt();
    Integer Q = s.nextInt();
    Integer [][] ans = new Integer[N][M];

//    s.nextLine();
    Character [][]cell = new Character[N][M];
    for(int i=0;i<N;++i){
//      String[] cellIndex = s.nextLine().split("");
      //below one using parser
      String[] cellIndex = s.next().split("");
      Arrays.fill(ans[i],-1);
      for(int j =0;j<M;++j){
        cell[i][j] = cellIndex[j].charAt(0);
      }
    }
    for(int i=0;i<N;++i){
      for(int j=0;j<M;++j){
        System.out.print(cell[i][j] + " ");
      }
        System.out.println();
    }
    Integer s1 = s.nextInt();
    System.out.println(s1);
    Integer s2 = s.nextInt();
    System.out.println(s2);

    dfs(cell,ans, s1-1, s2-1);
    for(int i=0;i<N;++i){
      for(int j=0;j<M;++j){
        System.out.print(ans[i][j] + " ");
      }
      System.out.println();
    }

    PrintWriter pw = new PrintWriter(System.out, false);
    for(int i=0;i<Q;++i){
      Integer c1 = s.nextInt();
//      System.out.println(c1);
      Integer c2 = s.nextInt();
//      System.out.println(c2);
//      System.out.println(ans[c1-1][c2-1]);
      pw.println(ans[c1-1][c2-1]);
    }
    // Write your code here
  pw.flush();
  }

  static void dfs(Character [][]cellValue,  Integer [][] ans, int s1, int s2){
    Queue<Cell> queue = new LinkedList<Cell>();
    ans[s1][s2] = 0;
    queue.add(new Cell(s1,s2));
    int [] dr = {0, 0, -1, 1};
    int [] dc = {-1, 1, 0, 0};
    boolean[][]visited = new boolean[ans.length][ans[0].length];
    int r = 0;
    int c = 0;
    while(!queue.isEmpty()){
      Cell cell = queue.poll();
      for(int i=0;i<dr.length;++i) {
        r = cell.pi + dr[i];
        c = cell.pj + dc[i];
        if (r>=0 && r<ans.length &&
            c>=0 && c<ans[0].length &&
            cellValue[r][c].equals('O') &&
            !visited[r][c]
        ){
          visited[r][c] = true;
          ans[r][c] = ans[cell.pi][cell.pj]+1;
          queue.add(new Cell(r,c));
        }
      }
    }
  }
}
