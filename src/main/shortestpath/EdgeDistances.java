package shortestpath;

import graph.Graph;
import map.HashMap;
import map.Map;
import queue.LinkedQueue;
import queue.Queue;

/** Distances counted in edges, from a breadth-first search. */
public final class EdgeDistances {

  private EdgeDistances() {
    // This class should not be instantiated!
  }

  // Returns a map from each vertex reachable from source to the fewest edges
  // on a path from source to it. Unreachable vertices are not in the map.
  // Assumes graph is not null and source is a vertex of graph.
  public static <V> Map<V, Integer> edgeDistances(Graph<V> graph, V source) {
    Map<V, Integer> distance = new HashMap<>();
    Queue<V> toProcess = new LinkedQueue<>();

    distance.put(source, 0);
    toProcess.enqueue(source);

    while (!toProcess.isEmpty()) {
      V v = toProcess.front();
      toProcess.dequeue();
      for (V neighbor : graph.neighbors(v)) {
        if (!distance.containsKey(neighbor)) {
          distance.put(neighbor, distance.get(v) + 1);
          toProcess.enqueue(neighbor);
        }
      }
    }
    return distance;
  }
}
