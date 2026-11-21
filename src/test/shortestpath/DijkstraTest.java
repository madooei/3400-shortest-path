package shortestpath;

import graph.AdjacencyListGraph;
import graph.Graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The scenarios for the cost of a cheapest path. A concrete subclass supplies
 * distance() to pick the method under test.
 */
public abstract class DijkstraTest {

  // Returns the cost of the cheapest path from source to target, found by the
  // method under test.
  protected abstract double distance(Graph<Integer> graph, int source, int target);

  @Test
  public void distanceFromALoneVertexToItselfIsZero() {
    assertEquals(0.0, distance(vertices(1), 0, 0));
  }

  @Test
  public void distanceToAnotherVertexWithNoEdgesIsInfinite() {
    assertEquals(Double.POSITIVE_INFINITY, distance(vertices(2), 0, 1));
  }

  @Test
  public void distanceToTheSourceIsZero() {
    assertEquals(0.0, distance(exampleGraph(), 0, 0));
  }

  @Test
  public void distanceToANeighborAcrossTheCheapestEdgeIsThatEdge() {
    assertEquals(6.0, distance(exampleGraph(), 0, 2));
  }

  @Test
  public void distanceToAVertexTwoEdgesAwayAddsBothWeights() {
    assertEquals(13.0, distance(exampleGraph(), 0, 3));
  }

  @Test
  public void distanceToANeighborCanTakeACheaperDetour() {
    // The edge 0-1 costs 50, and the route 0, 2, 3, 1 costs 6 + 7 + 9.
    assertEquals(22.0, distance(exampleGraph(), 0, 1));
  }

  @Test
  public void distanceToFiveGoesThroughThree() {
    assertEquals(28.0, distance(exampleGraph(), 0, 5));
  }

  @Test
  public void distanceToFourIsNotTheFewestEdgeRoute() {
    // The route 0, 1, 4 costs 80, and the route 0, 2, 3, 5, 4 costs 39.
    assertEquals(39.0, distance(exampleGraph(), 0, 4));
  }

  @Test
  public void distanceFromAnotherSourceToZero() {
    // From 3, the route 3, 2, 0 costs 13, and the route 3, 1, 0 costs 59.
    assertEquals(13.0, distance(exampleGraph(), 3, 0));
  }

  @Test
  public void distanceFromAnotherSourceToFour() {
    // From 3, the route 3, 5, 4 costs 26, and the route 3, 1, 4 costs 39.
    assertEquals(26.0, distance(exampleGraph(), 3, 4));
  }

  @Test
  public void distanceToAVertexWithNoEdgesIsInfinite() {
    Graph<Integer> graph = exampleGraph();
    graph.addVertex(6);
    assertEquals(Double.POSITIVE_INFINITY, distance(graph, 0, 6));
  }

  @Test
  public void distanceFollowsDirectedEdgesForward() {
    assertEquals(7.0, distance(oneWayLine(), 0, 2));
  }

  @Test
  public void distanceDoesNotFollowDirectedEdgesBackward() {
    assertEquals(Double.POSITIVE_INFINITY, distance(oneWayLine(), 2, 0));
  }

  @Test
  public void distanceCanUseZeroWeightEdges() {
    // The route 0, 1, 2 costs 0 + 0, and the edge 0-2 costs 5.
    Graph<Integer> graph = vertices(3);
    graph.addEdge(0, 1, 0.0);
    graph.addEdge(1, 2, 0.0);
    graph.addEdge(0, 2, 5.0);
    assertEquals(0.0, distance(graph, 0, 2));
  }

  @Test
  public void searchAsksEachVertexForItsNeighborsOnce() {
    // On the example graph, 1 and 4 each get a cheaper distance after their
    // first entry is on the frontier. Those stale entries must not be processed.
    CountingGraph graph = new CountingGraph();
    addExampleEdges(graph);
    distance(graph, 0, 4);
    assertEquals(6, graph.neighborCalls);
  }

  @Test
  public void searchDoesNotRelaxAnEdgeIntoASettledVertex() {
    // The example graph has 7 undirected edges, stored as 14 directed edges.
    // Each undirected edge leads into an unsettled vertex in one direction only.
    CountingGraph graph = new CountingGraph();
    addExampleEdges(graph);
    distance(graph, 0, 4);
    assertEquals(7, graph.weightCalls);
  }

  // The chapter's example: six vertices, 0 through 5, with weighted undirected edges.
  private Graph<Integer> exampleGraph() {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    addExampleEdges(graph);
    return graph;
  }

  // Adds the vertices and edges of the chapter's example to an empty graph.
  private void addExampleEdges(Graph<Integer> graph) {
    for (int vertex = 0; vertex <= 5; vertex++) {
      graph.addVertex(vertex);
    }
    addUndirected(graph, 0, 1, 50);
    addUndirected(graph, 0, 2, 6);
    addUndirected(graph, 1, 3, 9);
    addUndirected(graph, 1, 4, 30);
    addUndirected(graph, 2, 3, 7);
    addUndirected(graph, 3, 5, 15);
    addUndirected(graph, 4, 5, 11);
  }

  // The directed line 0 -> 1 -> 2, with weights 3 and 4.
  private Graph<Integer> oneWayLine() {
    Graph<Integer> graph = vertices(3);
    graph.addEdge(0, 1, 3.0);
    graph.addEdge(1, 2, 4.0);
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

  private void addUndirected(Graph<Integer> graph, int first, int second, double weight) {
    graph.addEdge(first, second, weight);
    graph.addEdge(second, first, weight);
  }

  // An adjacency-list graph that counts its calls to neighbors and weight.
  private static class CountingGraph extends AdjacencyListGraph<Integer> {
    private int neighborCalls;
    private int weightCalls;

    @Override
    public Iterable<Integer> neighbors(Integer vertex) {
      neighborCalls++;
      return super.neighbors(vertex);
    }

    @Override
    public double weight(Integer from, Integer to) {
      weightCalls++;
      return super.weight(from, to);
    }
  }
}
