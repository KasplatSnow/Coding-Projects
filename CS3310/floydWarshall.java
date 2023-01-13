import java.util.*;
public class floydWarshall{
  public static final int INF = Integer.MAX_VALUE;
  public static void main(String[] args) {
    //switches between graph and graph2 in floyd_Warshall method to test set graphs vs randomly generated graphs
    System.out.println("Enter n value: ");
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    int[][] graph2 = new int[n][n];
    Random random = new Random();
    int num = 0;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            num = random.nextInt(100);
            if(i == j){
                graph2[i][j] = 0;
            }
            else{
                graph2[i][j] = num;
            }
            System.out.print(graph2[i][j] + "\t");
        }
        System.out.println();
    }
    
    //set graph
    int[][] graph = {
        {0,3,INF,7},
        {8,0,2,INF},
        {5,INF,0,1},
        {2,INF,INF,0}
    };
    //Time Measure
    long startTime = System.nanoTime();

    floyd_Warshall(graph);

    //Time End and Print
    long endTime = System.nanoTime();
    System.out.println(endTime - startTime);
  }

  public static void floyd_Warshall(int[][] graph) {
    for (int k = 0; k < graph.length; k++) {
      for (int i = 0; i < graph.length; i++) {
        for (int j = 0; j < graph.length; j++) {
          if (graph[i][k] != INF && graph[k][j] != INF && graph[i][k] + graph[k][j] < graph[i][j]){
            graph[i][j] = graph[i][k] + graph[k][j];
          }
        }
      }
    }

    // Print distances
    System.out.println("Matrix of Distances:");
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph.length; j++) {
        if (graph[i][j] == INF) {
          System.out.print("Infinity ");
        } else {
          System.out.print(graph[i][j] + " ");
        }
      }
      System.out.println();
    }
  }
}
