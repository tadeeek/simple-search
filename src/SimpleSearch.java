import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import searchStrategy.*;
import displayResult.*;

public class SimpleSearch {
    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerLine = new Scanner(System.in);
        List<String[]> peopleList = new ArrayList<>();
        Map<String, List<Integer>> indexMap = new LinkedHashMap<>();

        // String pathToFile = args[1];
        String pathToFile = System.getProperty("user.dir") + "\\src\\data.txt";
        File file = new File(pathToFile);
        // Reading file and indexing inputs

        readAndIndexFile(peopleList, indexMap, pathToFile, file);
        theLoop: while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            int input = 0;
            try {
                input = scannerInt.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("\nInput should be a number");
                input = 0;
                scannerInt.nextLine(); // Workaround to consume \n produced by hitting enter if user typed string
                continue theLoop;
            }
            switch (input) {
                case 1:
                    System.out.println();
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String searchStrategy = scannerLine.nextLine();
                    StrategySearchContext strategySearchImpl = null;
                    switch (searchStrategy) {
                        case "ALL":
                            strategySearchImpl = new StrategySearchContext(new SearchAll());
                            break;
                        case "ANY":
                            strategySearchImpl = new StrategySearchContext(new SearchAny());
                            break;
                        case "NONE":
                            strategySearchImpl = new StrategySearchContext(new SearchNone());
                            break;
                        default:
                            System.out.println("\nIncorrect option! Try again.");
                            continue theLoop;
                    }
                    System.out.println();
                    System.out.println("Enter a name or email to search all suitable people.");
                    String searchQuery = scannerLine.nextLine();
                    searchQuery = searchQuery.toLowerCase();
                    System.out.println("\n=== Results ===");
                    strategySearchImpl.search(searchQuery, peopleList, indexMap);

                    break;
                case 2:
                    printPeople(peopleList);
                    break;
                case 0:
                    System.out.println("\nBye!");
                    break theLoop;
                default:
                    System.out.println("\nIncorrect option! Try again.");
                    continue theLoop;
            }
        }
    }

    public static void readAndIndexFile(List<String[]> peopleList, Map<String, List<Integer>> indexMap,
            String pathToFile, File file) {
        try (Scanner scanner = new Scanner(file)) {
            int lineNumber = 0;
            while (scanner.hasNext()) {
                String[] person = scanner.nextLine().split("\\s+");
                peopleList.add(person);
                for (String x : person) {
                    List<Integer> indexList = new ArrayList<>();
                    // creating index map with lowercase. search query is also converted to
                    // lowercase
                    x = x.toLowerCase();
                    if (indexMap.containsKey(x)) {
                        List<Integer> indexListCurrent = indexMap.get(x);
                        indexListCurrent.add(lineNumber);
                        indexMap.put(x, indexListCurrent);
                    } else {
                        indexList.add(lineNumber);
                        indexMap.put(x, indexList);
                    }
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + pathToFile);
        }

    }

    public static void printPeople(List<String[]> peopleList) {
        System.out.println("\n=== List of people ===");
        for (int i = 0; i < peopleList.size(); i++) {
            DisplayResult.display(peopleList.get(i));
            System.out.println();
        }
    }
}