package shortestpath;

import graph.Graph;
import map.HashMap;
import map.Map;
import queue.LinkedQueue;
import queue.Queue;

/** Breadth-first search that records the distance of every vertex it reaches. */
public final class BreadthFirst {

  private BreadthFirst() {
    // This class should not be instantiated!
  }

  // Returns a map from every vertex reachable from start to the number of edges
  // on a shortest path from start to it. A vertex that start cannot reach has no
  // entry. Assumes graph is not null and start is a vertex of graph.
  public static <V> Map<V, Integer> search(Graph<V> graph, V start) {
    Map<V, Integer> distance = new HashMap<>();
    Queue<V> frontier = new LinkedQueue<>();

    distance.put(start, 0);
    frontier.enqueue(start);

    while (!frontier.isEmpty()) {
      V vertex = frontier.front();
      frontier.dequeue();
      for (V neighbor : graph.neighbors(vertex)) {
        if (!distance.containsKey(neighbor)) {
          distance.put(neighbor, distance.get(vertex) + 1);
          frontier.enqueue(neighbor);
        }
      }
    }
    return distance;
  }
}
