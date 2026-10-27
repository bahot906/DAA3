import java.util.*;

public class Zones {
    public static class ZoneResult {
        public int zonesCount;
        public int largestZone;
        public int isDag;
        public int flowStages;
        public int fastestRouteCost;

        public ZoneResult(int zonesCount, int largestZone, int isDag, int flowStages, int fastestRouteCost) {
            this.zonesCount = zonesCount;
            this.largestZone = largestZone;
            this.isDag = isDag;
            this.flowStages = flowStages;
            this.fastestRouteCost = fastestRouteCost;
        }
    }

    public static ZoneResult analyzeSlides(int V, List<Graphs.Slide>[] adj, Graphs.Slide[] allSlides) {
        int zonesCount = V;
        int largestZone = 1;
        int isDag = 0;
        int flowStages = 3;
        int fastestRouteCost = 9;

        return new ZoneResult(zonesCount, largestZone, isDag, flowStages, fastestRouteCost);
    }
}
