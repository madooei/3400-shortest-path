package shortestpath;

import graph.Graph;
import list.List;
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
  public static <V> Map<V, Double> shortestDistances(Graph<V> graph, V source) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Returns the cost of the cheapest path from source to every vertex of
  // graph, and the paths themselves. Assumes graph is not null, source is a
  // vertex of graph, and no edge weight is negative.
  public static <V> Paths<V> shortestPaths(Graph<V> graph, V source) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // The result of shortestPaths: the cost of the cheapest path from the source
  // to every vertex, and the vertex before each one on that path.
  public static class Paths<V> {
    private Map<V, Double> distance;
    private Map<V, V> predecessor;

    Paths(Map<V, Double> distance, Map<V, V> predecessor) {
      this.distance = distance;
      this.predecessor = predecessor;
    }

    // Returns the cost of the cheapest path to target, or
    // Double.POSITIVE_INFINITY if the source cannot reach target. Assumes
    // target is a vertex of the graph.
    public double distanceTo(V target) {
      return distance.get(target);
    }

    // Returns the vertices of a cheapest path from source to target, source
    // first. Returns an empty list if target is unreachable. Assumes source is
    // the vertex the search started from and target is a vertex of the graph.
    public List<V> pathTo(V source, V target) {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }
  }
}
