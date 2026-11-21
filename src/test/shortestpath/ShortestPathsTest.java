package shortestpath;

import graph.AdjacencyListGraph;
import graph.Graph;
import list.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Runs the cheapest-path scenarios against Dijkstra.shortestPaths, and tests pathTo. */
public class ShortestPathsTest extends DijkstraTest {

  @Override
  protected double distance(Graph<Integer> graph, int source, int target) {
    return Dijkstra.shortestPaths(graph, source).distanceTo(target);
  }

  @Test
  public void pathFromALoneVertexToItselfIsJustThatVertex() {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    graph.addVertex(0);
    List<Integer> path = Dijkstra.shortestPaths(graph, 0).pathTo(0, 0);
    assertEquals("0", route(path));
  }

  @Test
  public void pathToTheSourceIsJustTheSource() {
    List<Integer> path = Dijkstra.shortestPaths(exampleGraph(), 0).pathTo(0, 0);
    assertEquals("0", route(path));
  }

  @Test
  public void pathToANeighborAcrossTheCheapestEdgeIsThatEdge() {
    List<Integer> path = Dijkstra.shortestPaths(exampleGraph(), 0).pathTo(0, 2);
    assertEquals("0 -> 2", route(path));
  }

  @Test
  public void pathToANeighborCanTakeACheaperDetour() {
    List<Integer> path = Dijkstra.shortestPaths(exampleGraph(), 0).pathTo(0, 1);
    assertEquals("0 -> 2 -> 3 -> 1", route(path));
  }

  @Test
  public void pathToFourIsNotTheFewestEdgeRoute() {
    List<Integer> path = Dijkstra.shortestPaths(exampleGraph(), 0).pathTo(0, 4);
    assertEquals("0 -> 2 -> 3 -> 5 -> 4", route(path));
  }

  @Test
  public void pathKeepsTheCheaperRouteWhenACostlierOneIsFoundLater() {
    // 3 is reached through 1 at 1 + 2, before 2 offers a route at 2 + 5.
    Graph<Integer> graph = new AdjacencyListGraph<>();
    for (int vertex = 0; vertex <= 3; vertex++) {
      graph.addVertex(vertex);
    }
    graph.addEdge(0, 1, 1.0);
    graph.addEdge(0, 2, 2.0);
    graph.addEdge(1, 3, 2.0);
    graph.addEdge(2, 3, 5.0);
    List<Integer> path = Dijkstra.shortestPaths(graph, 0).pathTo(0, 3);
    assertEquals("0 -> 1 -> 3", route(path));
  }

  @Test
  public void pathToAnUnreachableVertexIsEmpty() {
    Graph<Integer> graph = exampleGraph();
    graph.addVertex(6);
    List<Integer> path = Dijkstra.shortestPaths(graph, 0).pathTo(0, 6);
    assertEquals(0, path.size());
  }

  // The vertices of path, joined by arrows.
  private String route(List<Integer> path) {
    String text = "";
    for (int i = 0; i < path.size(); i++) {
      if (i > 0) {
        text += " -> ";
      }
      text += path.get(i);
    }
    return text;
  }

  // The chapter's example: six vertices, 0 through 5, with weighted undirected edges.
  private Graph<Integer> exampleGraph() {
    Graph<Integer> graph = new AdjacencyListGraph<>();
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
    return graph;
  }

  private void addUndirected(Graph<Integer> graph, int first, int second, double weight) {
    graph.addEdge(first, second, weight);
    graph.addEdge(second, first, weight);
  }
}
