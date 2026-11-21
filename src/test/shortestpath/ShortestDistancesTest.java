package shortestpath;

import graph.AdjacencyListGraph;
import graph.Graph;
import map.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Runs the cheapest-path scenarios against Dijkstra.shortestDistances. */
public class ShortestDistancesTest extends DijkstraTest {

  @Override
  protected double distance(Graph<Integer> graph, int source, int target) {
    return Dijkstra.shortestDistances(graph, source).get(target);
  }

  @Test
  public void shortestDistancesHasAnEntryForEveryVertex() {
    // 0 reaches 1, and 2 has no edges.
    Graph<Integer> graph = new AdjacencyListGraph<>();
    graph.addVertex(0);
    graph.addVertex(1);
    graph.addVertex(2);
    graph.addEdge(0, 1, 4.0);
    Map<Integer, Double> distance = Dijkstra.shortestDistances(graph, 0);
    assertEquals(3, distance.size());
  }
}
