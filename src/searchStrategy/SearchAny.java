package searchStrategy;

import java.util.List;
import java.util.Map;
import displayResult.*;

public class SearchAny implements StrategySearch {
    @Override
    public void search(String searchQuery, List<String[]> peopleList, Map<String, List<Integer>> indexMap) {
        String[] searchQueryArray = searchQuery.split("\\s+");
        boolean foundAtAll = false;
        for (int i = 0; i < searchQueryArray.length; i++) {
            if (indexMap.containsKey(searchQueryArray[i])) {
                List<Integer> indexList = indexMap.get(searchQueryArray[i]);
                for (int j = 0; j < indexList.size(); j++) {
                    String[] person = peopleList.get(indexList.get(j));
                    DisplayResult.display(person);
                    System.out.println();
                }
                foundAtAll = true;
            }
        }
        if (!foundAtAll) {
            System.out.println("No matching people found.");
        }
    }
}