package demo;

import graph.AdjacencyListGraph;
import graph.Graph;
import map.Map;
import shortestpath.BreadthFirst;

/** A demo of BreadthFirst on the chapter's six-vertex graph, without weights. */
public final class BreadthFirstDemo {

  private BreadthFirstDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    Graph<Integer> graph = buildExampleGraph();
    System.out.println("edges: 0-1, 0-2, 1-3, 1-4, 2-3, 3-5, 4-5");

    // Expect 0, 1, 1, 2, 2, and 3 for the vertices 0 through 5.
    Map<Integer, Integer> distance = BreadthFirst.search(graph, 0);
    System.out.println("distance from 0:");
    for (int vertex = 0; vertex <= 5; vertex++) {
      System.out.println("  to " + vertex + ": " + distance.get(vertex));
    }
  }

  // The chapter's example: six vertices, 0 through 5, with undirected edges.
  private static Graph<Integer> buildExampleGraph() {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    for (int vertex = 0; vertex <= 5; vertex++) {
      graph.addVertex(vertex);
    }
    addUndirected(graph, 0, 1);
    addUndirected(graph, 0, 2);
    addUndirected(graph, 1, 3);
    addUndirected(graph, 1, 4);
    addUndirected(graph, 2, 3);
    addUndirected(graph, 3, 5);
    addUndirected(graph, 4, 5);
    return graph;
  }

  // Adds an undirected edge as a directed edge in each direction.
  private static void addUndirected(Graph<Integer> graph, int first, int second) {
    graph.addEdge(first, second);
    graph.addEdge(second, first);
  }
}
