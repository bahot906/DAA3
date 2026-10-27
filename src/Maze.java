import java.util.*;

public class Maze {
    public static class Wall implements Comparable<Wall> {
        public int u, v, weight, index;

        public Wall(int u, int v, int weight, int index) {
            this.u = u;
            this.v = v;
            this.weight = weight;
            this.index = index;
        }

        @Override
        public int compareTo(Wall other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static class Mission1Result {
        public int broken;
        public int kept;
        public int lastBrokenWall;
        public List<Wall> mazeWalls;

        public Mission1Result(int broken, int kept, int lastBrokenWall, List<Wall> mazeWalls) {
            this.broken = broken;
            this.kept = kept;
            this.lastBrokenWall = lastBrokenWall;
            this.mazeWalls = mazeWalls;
        }
    }

    public static Mission1Result buildMaze(int V, List<Wall> walls) {
        DisjointSet dsu = new DisjointSet(V);
        int broken = 0;
        int kept = 0;
        int lastBroken = -1;
        List<Wall> mazeWalls = new ArrayList<>();

        for (Wall w : walls) {
            if (dsu.union(w.u, w.v)) {
                broken++;
                lastBroken = w.index;
                mazeWalls.add(w);
            } else {
                kept++;
            }
        }
        return new Mission1Result(broken, kept, lastBroken, mazeWalls);
    }

    public static class MSTResult {
        public int mstCost;
        public int heaviestWall;

        public MSTResult(int mstCost, int heaviestWall) {
            this.mstCost = mstCost;
            this.heaviestWall = heaviestWall;
        }
    }

    public static MSTResult kruskalMST(int V, List<Wall> walls) {
        List<Wall> sortedWalls = new ArrayList<>(walls);
        Collections.sort(sortedWalls);

        DisjointSet dsu = new DisjointSet(V);
        int totalCost = 0;
        int maxWeight = 0;

        for (Wall w : sortedWalls) {
            if (dsu.union(w.u, w.v)) {
                totalCost += w.weight;
                maxWeight = Math.max(maxWeight, w.weight);
                if (dsu.count() == 1) break;
            }
        }
        return new MSTResult(totalCost, maxWeight);
    }
}
