import java.util.*;
public class Path2{
  public static final int INF = Integer.MAX_VALUE;

  public static void floydWarshall(int[][] graph, int n) {
    int[][] dist = new int[n][n];

    // Initialize distances
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        dist[i][j] = graph[i][j];
      }
    }

    // Find shortest paths
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }

    // Print distances
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (dist[i][j] == INF) {
          System.out.print("INF ");
        } else {
          System.out.print(dist[i][j] + " ");
        }
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[][] graph = {
      {0, 3, INF, 7},
      {8, 0, 2, INF},
      {5, INF, 0, 1},
      {2, INF, INF, 0}
    };

    floydWarshall(graph, 4);
  }
}
