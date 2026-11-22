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
        Dijkstra.java                 # shortestDistances, shortestPaths, and the nested Step and Paths
        Main.java                     # a small demo
      graph/
        Graph.java                    # unchanged copy of the Graph ADT
        AdjacencyListGraph.java       # unchanged copy of the graph chapter's implementation
      priorityqueue/
        PriorityQueue.java            # unchanged copy of the course PriorityQueue ADT
        ArrayHeap.java                # unchanged copy of the binary heap chapter's array heap
      set/
        Set.java                      # unchanged copy of the course Set ADT
        HashSet.java                  # unchanged copy of the graph search chapter's hash set
      map/
        Map.java                      # unchanged copy of the course Map ADT
        HashMap.java                  # unchanged copy of the course hash table
      list/
        List.java                     # unchanged copy of the course List ADT
        ArrayList.java                # unchanged copy of the course array list
      stack/
        Stack.java                    # unchanged copy of the course Stack ADT
        ArrayStack.java               # unchanged copy of the course array stack
    test/
      shortestpath/
        DijkstraTest.java             # the abstract cheapest-distance scenarios
        ShortestDistancesTest.java    # runs them against shortestDistances
        ShortestPathsTest.java        # runs them against shortestPaths, and tests pathTo
      set/
        SetTest.java                  # unchanged copy of the course Set contract suite
        HashSetTest.java              # runs it against HashSet
  scripts/
    run.sh                            # compile and run the demo
    test.sh                           # compile and run the JUnit tests
```

## How to compile and run

- `scripts/run.sh` — compiles the source and runs `shortestpath.Main`.
- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh shortestpath.ShortestDistancesTest` — compiles everything and runs only that test class.

## What's here

- `shortestpath.Dijkstra` — `shortestDistances` returns the cost of the cheapest path from the source to every vertex. `shortestPaths` also records the predecessor of each vertex. The nested `Step` pairs a vertex with a tentative distance, so the `ArrayHeap` returns the closest vertex first. The nested `Paths` holds the distances and the predecessors, and `pathTo` reads a path back out of them.
- `shortestpath.Main` — runs `shortestDistances` and `shortestPaths` on the chapter's six-vertex weighted example graph.
- `set.HashSet` — the settled set: a `Set` whose elements are the keys of a course `HashMap`. `set.HashSetTest` runs the course `Set` contract suite against it.
