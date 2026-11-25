package shortestpath;

import graph.Graph;
import map.Map;

/** Dijkstra's algorithm: the cheapest paths from one source in a weighted graph. */
public final class Dijkstra {

  private Dijkstra() {
    // This class should not be instantiated!
  }

  // Returns a map from every vertex of graph to the cost of the cheapest path
  // from source to it. A vertex that source cannot reach has the distance
  // Double.POSITIVE_INFINITY. Assumes graph is not null, source is a vertex of
  // graph, and no edge weight is negative.
  public static <V> Map<V, Double> search(Graph<V> graph, V source) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}
