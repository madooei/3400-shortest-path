package shortestpath;

import graph.AdjacencyListGraph;
import graph.Graph;
import map.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/** Tests for distances counted in edges. */
public class EdgeDistancesTest {

  @Test
  public void edgeDistanceFromALoneVertexToItselfIsZero() {
    Map<Integer, Integer> distance = EdgeDistances.edgeDistances(vertices(1), 0);
    assertEquals(0, distance.get(0));
  }

  @Test
  public void edgeDistanceToTheSourceIsZero() {
    Map<Integer, Integer> distance = EdgeDistances.edgeDistances(exampleGraph(), 0);
    assertEquals(0, distance.get(0));
  }

  @Test
  public void edgeDistanceToANeighborIsOne() {
    Map<Integer, Integer> distance = EdgeDistances.edgeDistances(exampleGraph(), 0);
    assertEquals(1, distance.get(1));
  }

  @Test
  public void edgeDistanceToAVertexInTheSecondRingIsTwo() {
    Map<Integer, Integer> distance = EdgeDistances.edgeDistances(exampleGraph(), 0);
    assertEquals(2, distance.get(3));
  }

  @Test
  public void edgeDistanceCountsTheFewestEdges() {
    // 4 is two edges away through 1, and four edges away through 2, 3, and 5.
    Map<Integer, Integer> distance = EdgeDistances.edgeDistances(exampleGraph(), 0);
    assertEquals(2, distance.get(4));
  }

  @Test
  public void edgeDistanceToAVertexInTheThirdRingIsThree() {
    Map<Integer, Integer> distance = EdgeDistances.edgeDistances(exampleGraph(), 0);
    assertEquals(3, distance.get(5));
  }

  @Test
  public void edgeDistancesHasAnEntryForEveryReachableVertex() {
    Map<Integer, Integer> distance = EdgeDistances.edgeDistances(exampleGraph(), 0);
    assertEquals(6, distance.size());
  }

  @Test
  public void edgeDistancesHasNoEntryForAnUnreachableVertex() {
    Graph<Integer> graph = exampleGraph();
    graph.addVertex(6);
    Map<Integer, Integer> distance = EdgeDistances.edgeDistances(graph, 0);
    assertFalse(distance.containsKey(6));
  }

  @Test
  public void edgeDistancesFollowsDirectedEdgesForward() {
    Map<Integer, Integer> distance = EdgeDistances.edgeDistances(oneWayLine(), 0);
    assertEquals(2, distance.get(2));
  }

  @Test
  public void edgeDistancesDoesNotFollowDirectedEdgesBackward() {
    Map<Integer, Integer> distance = EdgeDistances.edgeDistances(oneWayLine(), 2);
    assertFalse(distance.containsKey(0));
  }

  // The chapter's example: six vertices, 0 through 5, with undirected edges.
  private Graph<Integer> exampleGraph() {
    Graph<Integer> graph = vertices(6);
    addUndirected(graph, 0, 1);
    addUndirected(graph, 0, 2);
    addUndirected(graph, 1, 3);
    addUndirected(graph, 1, 4);
    addUndirected(graph, 2, 3);
    addUndirected(graph, 3, 5);
    addUndirected(graph, 4, 5);
    return graph;
  }

  // The directed line 0 -> 1 -> 2.
  private Graph<Integer> oneWayLine() {
    Graph<Integer> graph = vertices(3);
    graph.addEdge(0, 1);
    graph.addEdge(1, 2);
    return graph;
  }

  // A graph with the vertices 0 through count - 1 and no edges.
  private Graph<Integer> vertices(int count) {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    for (int vertex = 0; vertex < count; vertex++) {
      graph.addVertex(vertex);
    }
    return graph;
  }

  private void addUndirected(Graph<Integer> graph, int first, int second) {
    graph.addEdge(first, second);
    graph.addEdge(second, first);
  }
}
