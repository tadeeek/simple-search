package searchStrategy;

import java.util.List;
import java.util.Map;

public class StrategySearchImpl {
    private StrategySearch strategy;

    public StrategySearchImpl(StrategySearch strategy) {
        this.strategy = strategy;
    }

    public void search(String searchQuery, List<String[]> peopleList, Map<String, List<Integer>> indexMap) {
        this.strategy.search(searchQuery, peopleList, indexMap);
    }
}