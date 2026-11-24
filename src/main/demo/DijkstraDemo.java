package demo;

import graph.AdjacencyListGraph;
import graph.Graph;
import list.List;
import map.Map;
import shortestpath.Dijkstra;

/** A demo of Dijkstra on the chapter's six-vertex weighted graph. */
public final class DijkstraDemo {

  private DijkstraDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    Graph<Integer> graph = buildExampleGraph();
    System.out.println("edges: 0-1 (50), 0-2 (6), 1-3 (9), 1-4 (30), 2-3 (7), 3-5 (15), 4-5 (11)");

    // Expect 0.0, 22.0, 6.0, 13.0, 39.0, and 28.0 for the vertices 0 through 5.
    Map<Integer, Double> distance = Dijkstra.shortestDistances(graph, 0);
    System.out.println("cheapest cost from 0:");
    for (int vertex = 0; vertex <= 5; vertex++) {
      System.out.println("  to " + vertex + ": " + distance.get(vertex));
    }

    // Expect 0 -> 2 -> 3 -> 1 for vertex 1 and 0 -> 2 -> 3 -> 5 -> 4 for vertex 4.
    Dijkstra.Paths<Integer> paths = Dijkstra.shortestPaths(graph, 0);
    System.out.println("cheapest paths from 0:");
    for (int vertex = 0; vertex <= 5; vertex++) {
      List<Integer> path = paths.pathTo(0, vertex);
      String line = "  to " + vertex + ": " + path.get(0);
      for (int i = 1; i < path.size(); i++) {
        line += " -> " + path.get(i);
      }
      System.out.println(line + " (" + paths.distanceTo(vertex) + ")");
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
