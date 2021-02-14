package searchStrategy;

import java.util.List;
import java.util.Map;

interface StrategySearch {
    void search(String searchQuery, List<String[]> peopleList, Map<String, List<Integer>> indexMap);
}