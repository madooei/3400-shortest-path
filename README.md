# Shortest Paths

The code for the shortest path chapter: Dijkstra's algorithm for the cheapest distances on a weighted graph, and recovering the cheapest path from the predecessor map. `Dijkstra` has a JUnit suite, and so does the hash set the algorithm uses.

## Prerequisites

- JDK 17+ (JUnit 6 requires it).
- The JUnit jar is already vendored in `lib/`; there is nothing to download.

## Repository layout

```plaintext
code/
  README.md
  .gitignore
  lib/
    junit-platform-console-standalone-6.1.0.jar
  src/
    main/
      shortestpath/
        Dijkstra.java                 # shortestDistances, shortestPaths, and the nested Paths
      graph/
        Graph.java                    # unchanged copy of the Graph ADT
        AdjacencyListGraph.java       # unchanged copy of the graph chapter's implementation
      adaptiveheap/
        AdaptivePriorityQueue.java    # unchanged copy of the AdaptivePriorityQueue ADT
        IndexedArrayHeap.java         # the adaptive heap chapter's heap, with a HashMap index map
      set/
        Set.java                      # unchanged copy of the course Set ADT
        HashSet.java                  # a hash set with separate chaining, copied from the chaining chapter
      map/
        Map.java                      # unchanged copy of the course Map ADT
        HashMap.java                  # unchanged copy of the course hash table
      list/
        List.java                     # unchanged copy of the course List ADT
        ArrayList.java                # unchanged copy of the course array list
      stack/
        Stack.java                    # unchanged copy of the course Stack ADT
        ArrayStack.java               # unchanged copy of the course array stack
      demo/
        DijkstraDemo.java
    test/
      shortestpath/
        DijkstraTest.java             # the abstract cheapest-distance scenarios
        ShortestDistancesTest.java    # runs them against shortestDistances
        ShortestPathsTest.java        # runs them against shortestPaths, and tests pathTo
      set/
        SetTest.java                  # unchanged copy of the course Set contract suite
        HashSetTest.java              # runs it against HashSet
  scripts/
    test.sh                           # compile and run the JUnit tests
    run.sh                            # compile and run one demo
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh shortestpath.ShortestDistancesTest` — compiles everything and runs only that test class.
- `scripts/run.sh demo.DijkstraDemo` — compiles everything and runs that one demo. Every class you implement has its own demo.

## What's here

- `shortestpath.Dijkstra` — `shortestDistances` returns the cost of the cheapest path from the source to every vertex. `shortestPaths` also records the predecessor of each vertex. The frontier is an `IndexedArrayHeap` with each vertex as the item and its tentative distance as the priority. The nested `Paths` holds the distances and the predecessors, and `pathTo` reads a path back out of them.
- `adaptiveheap.IndexedArrayHeap` — the adaptive heap chapter's heap. Its index map is a `HashMap` instead of a `TreeMap`, so the vertices need no ordering.
- `set.HashSet` — the settled set: the separate chaining chapter's hash set, copied unchanged. `set.HashSetTest` is copied with it: it runs the course `Set` contract suite against `HashSet`, then checks the buckets by reflection.
- `demo.DijkstraDemo` — runs `shortestDistances` and `shortestPaths` on the chapter's six-vertex weighted example graph.
