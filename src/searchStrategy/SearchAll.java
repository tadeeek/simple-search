package searchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchAll implements StrategySearch {
    @Override
    public void search(String searchQuery, List<String[]> peopleList, Map<String, List<Integer>> indexMap) {
        String[] searchQueryArray = searchQuery.split("\\s+");
        // creating list of indexes of first query
        List<Integer> indexList = indexMap.get(searchQueryArray[0]);
        List<Integer> indexListCurrent = new ArrayList<>();
        boolean haveCommonIndexes = false;
        for (int i = 0; i < searchQueryArray.length; i++) {
            if (indexMap.containsKey(searchQueryArray[i])) {
                // getting query index list
                indexListCurrent = indexMap.get(searchQueryArray[i]);
                // if two arrays share common element, update current array with common indexes
                indexListCurrent.retainAll(indexList);
                if (indexListCurrent.isEmpty()) {
                    haveCommonIndexes = false;
                    break;
                } else {
                    haveCommonIndexes = true;
                }
            }
        }
        if (haveCommonIndexes) {
            for (int i = 0; i < indexListCurrent.size(); i++) {
                String[] person = peopleList.get(indexListCurrent.get(i));
                DisplayResult.display(person);
                System.out.println();
            }
        } else
            System.out.println("No matching people found.");
    }
}