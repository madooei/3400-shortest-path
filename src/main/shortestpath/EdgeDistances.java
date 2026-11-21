package shortestpath;

import graph.Graph;
import map.Map;

/** Distances counted in edges, from a breadth-first search. */
public final class EdgeDistances {

  private EdgeDistances() {
    // This class should not be instantiated!
  }

  // Returns a map from each vertex reachable from source to the fewest edges
  // on a path from source to it. Unreachable vertices are not in the map.
  // Assumes graph is not null and source is a vertex of graph.
  public static <V> Map<V, Integer> edgeDistances(Graph<V> graph, V source) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}
