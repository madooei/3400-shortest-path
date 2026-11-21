package shortestpath;

import graph.AdjacencyListGraph;
import graph.Graph;
import list.List;
import map.Map;

/** A small demo of edge distances and Dijkstra's algorithm on the chapter's example graph. */
public final class Main {

  private Main() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    Graph<Integer> graph = buildExampleGraph();

    Map<Integer, Integer> edges = EdgeDistances.edgeDistances(graph, 0);
    System.out.println("Fewest edges from 0:");
    for (int vertex = 0; vertex <= 5; vertex++) {
      System.out.println("  to " + vertex + ": " + edges.get(vertex));
    }

    Map<Integer, Double> distance = Dijkstra.shortestDistances(graph, 0);
    System.out.println("Cheapest cost from 0:");
    for (int vertex = 0; vertex <= 5; vertex++) {
      System.out.println("  to " + vertex + ": " + distance.get(vertex));
    }

    Dijkstra.Paths<Integer> paths = Dijkstra.shortestPaths(graph, 0);
    System.out.println("Cheapest paths from 0:");
    for (int vertex = 0; vertex <= 5; vertex++) {
      List<Integer> path = paths.pathTo(0, vertex);
      String line = "  to " + vertex + ": " + path.get(0);
      for (int i = 1; i < path.size(); i++) {
        line += " -> " + path.get(i);
      }
      System.out.println(line);
    }
  }

  // The chapter's example: six vertices, 0 through 5, with weighted undirected edges.
  private static Graph<Integer> buildExampleGraph() {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    for (int vertex = 0; vertex <= 5; vertex++) {
      graph.addVertex(vertex);
    }
    addUndirected(graph, 0, 1, 50);
    addUndirected(graph, 0, 2, 6);
    addUndirected(graph, 1, 3, 9);
    addUndirected(graph, 1, 4, 30);
    addUndirected(graph, 2, 3, 7);
    addUndirected(graph, 3, 5, 15);
    addUndirected(graph, 4, 5, 11);
    return graph;
  }

  // Adds an undirected edge as a directed edge in each direction, with the same weight.
  private static void addUndirected(Graph<Integer> graph, int first, int second, double weight) {
    graph.addEdge(first, second, weight);
    graph.addEdge(second, first, weight);
  }
}
