Maze Escape - Assignment 3
Student Info
Name: Bakhridenov Alisher
Group: SE-2510
Student Barcode / ID: 253053

Program Output
Broken: 24
Kept: 16
Last broken wall: 38
MST cost: 24
Heaviest broken wall: 1
Path length: 8
DFS visited: 9
Dead ends: 6
Zones: 25
Largest zone: 1
Is DAG: 1
Flow stages: 3
Fastest route cost: 9

Answers to README Questions
Why is Broken always RC-1, and why is Kept always (R-1)*(C-1)?
Answer: A maze is a spanning tree of a grid graph with RC vertices. A spanning tree of V vertices always contains exactly V-1 edges, which means we must break RC-1 walls to make all cells connected without forming cycles. The total number of inner walls in a grid is 2RC-R-C. Therefore, the number of kept walls is (2RC-R-C)-(RC-1)=RC-R-C+1=(R-1)(C-1).
Why does DFS not overflow the stack on a 1000x1000 maze?
Answer: Stack overflow is avoided by using an explicit iterative stack (Stack<Integer>) on the heap rather than relying on deep function call recursion on the call stack. This allows the algorithm to safely scale to millions of cells without exceeding the JVM thread stack limit.
Why does the second pass of Kosaraju go on the reversed graph?
Answer: The first pass computes the finish times of vertices in the original graph. Reversing the edges in the second pass ensures that when visiting vertices in decreasing order of finish time, DFS cannot "leak" into other strongly connected components, thereby isolating each SCC correctly.
Why do the slides have no cycle exactly when Zones equals the number of cells?
Answer: A directed graph is a Directed Acyclic Graph (DAG) if and only if every strongly connected component consists of a single vertex. If Zones equals the total number of cells N, every cell forms its own isolated zone of size 1, proving there are no cycles.
Why is the topological order of zones enough for the fastest route, and what is the cost of the pass?
Answer: In a DAG, processing vertices in topological order guarantees that when evaluating a zone U, the shortest paths to all predecessor zones leading to U have already been computed. The cost of this single dynamic programming pass is linear: O(Vz+Ez), where Vz and Ez are the number of zones and inter-zone slides respectively.

AI Use
Tool used: ChatGPT / Gemini
Usage description: Used as a helper to structure code for graph algorithms (DSU, Kruskal, Prim, iterative DFS, and Kosaraju), verify theoretical questions for the README, and format project documentation.

Timing and Benchmarks (Mission 7)
Results from running Bench.java on generated mazes:
Maze Size
Kruskal Time (ms)
Prim Time (ms)
100x100
12
15
300x300
45
58
1000x1000
420
510

Time growth analysis: Both algorithms show near-linear growth relative to the total number of edges. Kruskal's algorithm spends most of its time sorting the edge array O(WW), while Prim's algorithm utilizes a PriorityQueue O(WV). As the grid size expands to 10001000, Kruskal performs slightly faster due to cache-friendly contiguous memory operations during sorting compared to pointer overhead in Java's PriorityQueue.

Complexity Table
Algorithm / Component
Time Complexity
Space Complexity
DisjointSet (DSU)
O((N)) per operation
O(N)
Mission 1 (Build Maze)
O(W(V))
O(V+W)
Mission 2 (Kruskal)
O(WW)
O(V+W)
Mission 2 (Prim)
O(WV)
O(V+W)
Mission 3 (DFS Escape)
O(V+W)
O(V)
Mission 4 (Kosaraju / Zones)
O(V+S)
O(V+S)
Mission 5 (Fastest Route)
O(Vz+Ez)
O(Vz+Ez)


