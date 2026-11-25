package shortestpath;

import graph.AdjacencyListGraph;
import graph.Graph;
import map.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/** Tests for recording the distance of every vertex in a breadth-first search. */
public class BreadthFirstTest {

  @Test
  public void searchOfALoneVertexGivesItDistanceZero() {
    Map<Integer, Integer> distance = BreadthFirst.search(vertices(1), 0);
    assertEquals(0, distance.get(0));
  }

  @Test
  public void searchOfALoneVertexHasOneEntry() {
    Map<Integer, Integer> distance = BreadthFirst.search(vertices(1), 0);
    assertEquals(1, distance.size());
  }

  @Test
  public void searchGivesTheStartDistanceZero() {
    Map<Integer, Integer> distance = BreadthFirst.search(exampleGraph(), 0);
    assertEquals(0, distance.get(0));
  }

  @Test
  public void searchGivesANeighborOfTheStartDistanceOne() {
    Map<Integer, Integer> distance = BreadthFirst.search(exampleGraph(), 0);
    assertEquals(1, distance.get(2));
  }

  @Test
  public void searchGivesAVertexTwoEdgesAwayDistanceTwo() {
    // 3 is reached from 1 and from 2, and both paths have two edges.
    Map<Integer, Integer> distance = BreadthFirst.search(exampleGraph(), 0);
    assertEquals(2, distance.get(3));
  }

  @Test
  public void searchGivesAVertexThreeEdgesAwayDistanceThree() {
    Map<Integer, Integer> distance = BreadthFirst.search(exampleGraph(), 0);
    assertEquals(3, distance.get(5));
  }

  @Test
  public void searchKeepsTheFirstDistanceItRecords() {
    // 4 is reached from 1 after two edges, and later from 5 after four edges.
    Map<Integer, Integer> distance = BreadthFirst.search(exampleGraph(), 0);
    assertEquals(2, distance.get(4));
  }

  @Test
  public void searchFromAnotherStartCountsFromThatStart() {
    Map<Integer, Integer> distance = BreadthFirst.search(exampleGraph(), 5);
    assertEquals(3, distance.get(0));
  }

  @Test
  public void searchHasAnEntryForEveryReachableVertex() {
    Map<Integer, Integer> distance = BreadthFirst.search(exampleGraph(), 0);
    assertEquals(6, distance.size());
  }

  @Test
  public void searchHasNoEntryForAnUnreachableVertex() {
    Graph<Integer> graph = exampleGraph();
    graph.addVertex(6);
    Map<Integer, Integer> distance = BreadthFirst.search(graph, 0);
    assertFalse(distance.containsKey(6));
  }

  @Test
  public void searchFollowsDirectedEdgesForward() {
    Map<Integer, Integer> distance = BreadthFirst.search(oneWayLine(), 0);
    assertEquals(2, distance.get(2));
  }

  @Test
  public void searchDoesNotFollowDirectedEdgesBackward() {
    Map<Integer, Integer> distance = BreadthFirst.search(oneWayLine(), 2);
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
