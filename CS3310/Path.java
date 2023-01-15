import java.util.*;
import java.io.*;
import java.lang.Object;
public class Path{
    public static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
    //set graph
    int[][] graph = {
        {0,3,INF,7},
        {8,0,2,INF},
        {5,INF,0,1},
        {2,INF,INF,0}
    };
    int[] dist = new int[graph.length];
    dijkstra(graph, 0, dist);

    //Output
    System.out.println("Shortest distance from S = 0:");
    System.out.println("Node \tDistance");
    for (int i = 0; i < dist.length; i++) {
      System.out.println(i + " \t" + dist[i]);
    }
  }

  public static void dijkstra(int[][] graph, int source, int[] dist) {
    int n = graph.length;
    boolean[] visited = new boolean[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[source] = 0;

    for (int i = 0; i < n - 1; i++) {
      int min = minDistance(dist, visited);
      visited[min] = true;
      for (int j = 0; j < n; j++) {
        if (!visited[j] && graph[min][j] != 0 && dist[min] != Integer.MAX_VALUE && dist[min] + graph[min][j] < dist[j]) {
          dist[j] = dist[min] + graph[min][j];
        }
      }
    }
  }

  private static int minDistance(int[] dist, boolean[] visited) {
    int min = Integer.MAX_VALUE; 
    int minIndex = -1;
    for (int i = 0; i < dist.length; i++) {
      if (visited[i] == false && dist[i] <= min) {
        min = dist[i];
        minIndex = i;
      }
    }
    return minIndex;
  }
}


