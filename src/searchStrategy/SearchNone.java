package searchStrategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SearchNone implements StrategySearch {

    @Override
    public void search(String searchQuery, List<String[]> peopleList, Map<String, List<Integer>> indexMap) {
        String[] searchQueryArray = searchQuery.split("\\s+");
        boolean foundAtAll = false;
        List<Integer> indexListCurrent = new ArrayList<>();
        List<Integer> indexListToExclude = new LinkedList<>();

        for (int i = 0; i < searchQueryArray.length; i++) {
            if (indexMap.containsKey(searchQueryArray[i])) {
                // getting query index list
                indexListCurrent = indexMap.get(searchQueryArray[i]);
                indexListToExclude.addAll(indexListCurrent);
                foundAtAll = true;
            }
        }

        if (foundAtAll) {
            for (int i = 0; i < peopleList.size(); i++) {
                if (indexListToExclude.contains(i)) {

                } else {
                    String[] person = peopleList.get(i);
                    DisplayResult.display(person);
                    System.out.println();
                }
            }
        } else
            System.out.println("No matching people found.");
    }
}
