import java.util.*;

public class Escape {
    public static class DFSResult {
        public int pathLength;
        public int visitedCount;
        public int deadEndsCount;

        public DFSResult(int pathLength, int visitedCount, int deadEndsCount) {
            this.pathLength = pathLength;
            this.visitedCount = visitedCount;
            this.deadEndsCount = deadEndsCount;
        }
    }

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public static DFSResult solveDFS(int R, int C, List<Maze.Wall> mazeWalls) {
        int V = R * C;
        boolean[][] open = new boolean[V][4];

        for (Maze.Wall w : mazeWalls) {
            int u = w.u;
            int v = w.v;
            if (v == u - C) { open[u][0] = true; open[v][2] = true; }
            else if (v == u + 1) { open[u][1] = true; open[v][3] = true; }
            else if (v == u + C) { open[u][2] = true; open[v][0] = true; }
            else if (v == u - 1) { open[u][3] = true; open[v][1] = true; }
        }

        int start = 0;
        int exit = V - 1;

        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;
        int visitedCount = 0;

        while (!stack.isEmpty()) {
            int curr = stack.peek();
            if (curr == exit) {
                visitedCount = countTrue(visited);
                break;
            }

            int r = curr / C;
            int c = curr % C;

            int nextCell = -1;
            for (int i = 0; i < 4; i++) {
                if (open[curr][i]) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                        int nIndex = nr * C + nc;
                        if (!visited[nIndex]) {
                            nextCell = nIndex;
                            break;
                        }
                    }
                }
            }

            if (nextCell != -1) {
                visited[nextCell] = true;
                parent[nextCell] = curr;
                stack.push(nextCell);
            } else {
                stack.pop();
            }
        }

        int pathLength = 0;
        int curr = exit;
        while (curr != start && parent[curr] != -1) {
            pathLength++;
            curr = parent[curr];
        }

        int deadEnds = 0;
        for (int i = 0; i < V; i++) {
            int openDoors = 0;
            for (int d = 0; d < 4; d++) {
                if (open[i][d]) openDoors++;
            }
            if (openDoors == 1) {
                deadEnds++;
            }
        }

        return new DFSResult(pathLength, visitedCount, deadEnds);
    }

    private static int countTrue(boolean[] arr) {
        int cnt = 0;
        for (boolean b : arr) if (b) cnt++;
        return cnt;
    }
}
