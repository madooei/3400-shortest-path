package shortestpath;

import graph.Graph;
import map.Map;

/** Breadth-first search that records the distance of every vertex it reaches. */
public final class BreadthFirst {

  private BreadthFirst() {
    // This class should not be instantiated!
  }

  // Returns a map from every vertex reachable from start to the number of edges
  // on a shortest path from start to it. A vertex that start cannot reach has no
  // entry. Assumes graph is not null and start is a vertex of graph.
  public static <V> Map<V, Integer> search(Graph<V> graph, V start) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}
