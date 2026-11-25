# Shortest Paths

The code for the chapter on shortest paths: breadth-first search that records the distance of every vertex, and Dijkstra's algorithm for the cheapest distances on a weighted graph. Each search has a JUnit suite, and so do the hash set and the adaptive heap they use.

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
        BreadthFirst.java             # search, with a distance map
        Dijkstra.java                 # search
      graph/
        Graph.java                    # unchanged copy of the Graph ADT
        AdjacencyListGraph.java       # unchanged copy of the implementation from the chapter on graphs
      adaptiveheap/
        AdaptivePriorityQueue.java    # unchanged copy of the AdaptivePriorityQueue ADT
        IndexedArrayHeap.java         # the heap from the chapter on adaptive priority queues, with a HashMap index map
      queue/
        Queue.java                    # unchanged copy of the course Queue ADT
        LinkedQueue.java              # unchanged copy of the course linked queue
      set/
        Set.java                      # unchanged copy of the course Set ADT
        HashSet.java                  # a hash set with separate chaining, copied from the chapter on separate chaining
      map/
        Map.java                      # unchanged copy of the course Map ADT
        HashMap.java                  # unchanged copy of the course hash table
      demo/
        BreadthFirstDemo.java
        DijkstraDemo.java
    test/
      shortestpath/
        BreadthFirstTest.java
        DijkstraTest.java
      adaptiveheap/
        AdaptivePriorityQueueTest.java  # unchanged copy of the AdaptivePriorityQueue contract suite
        IndexedArrayHeapTest.java       # runs it against IndexedArrayHeap
      set/
        SetTest.java                  # unchanged copy of the course Set contract suite
        HashSetTest.java              # runs it against HashSet
  scripts/
    test.sh                           # compile and run the JUnit tests
    run.sh                            # compile and run one demo
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh shortestpath.DijkstraTest` — compiles everything and runs only that test class.
- `scripts/run.sh demo.DijkstraDemo` — compiles everything and runs that one demo. Every class you implement has its own demo.

## Contents

- `shortestpath.BreadthFirst` — `search` returns the number of edges on a shortest path from the start to every vertex it can reach. A `distance` map takes the place of the visited set, and the queue is called `frontier`.
- `shortestpath.Dijkstra` — `search` returns the cost of the cheapest path from the source to every vertex. The frontier is an `IndexedArrayHeap` with each vertex as the item and its tentative distance as the priority.
- `adaptiveheap.IndexedArrayHeap` — the heap from the chapter on adaptive priority queues. Its index map is a `HashMap` instead of a `TreeMap`, so the vertices need no ordering. `adaptiveheap.IndexedArrayHeapTest` is copied with it: it runs the `AdaptivePriorityQueue` contract suite against `IndexedArrayHeap`, then checks the slots and the index map by reflection.
- `set.HashSet` — the settled set: the hash set from the chapter on separate chaining, copied unchanged. `set.HashSetTest` is copied with it: it runs the course `Set` contract suite against `HashSet`, then checks the buckets by reflection.
- `demo.BreadthFirstDemo` — runs `BreadthFirst.search` on the chapter's six-vertex example graph, without weights.
- `demo.DijkstraDemo` — runs `Dijkstra.search` on the same graph, with weights.
