import converter.CsvRowSplitter;
import formatchecker.StringFormatChecker;
import pair.Pair;
import parser.CsvFileParser;
import reader.CsvFileReader;
import formatchecker.InNumericString;

import java.io.InputStreamReader;
import java.util.*;

public class CsvFileCli {

    private String csvFilePath;

    private int columnNumber;

    private CsvFileParser csvFileParser;

    public CsvFileCli(String csvFilePath, int columnNumber) {
        this.csvFilePath = csvFilePath;
        csvFileParser = new CsvFileParser(csvFilePath);
        this.columnNumber = columnNumber;
    }

    public void initialize() {
        if(csvFileParser != null) {
            csvFileParser.parseFile();
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        String query = null;

        do {
            System.out.println("Введите строку:");
            query = scanner.nextLine();

            if(query.equals("!quit")) {
                return;
            }

            long startTime = System.currentTimeMillis();

            List<Pair<List<Integer>, String>> searchByColumnResult = csvFileParser.autocompleteByColumn(columnNumber, query);

            if(searchByColumnResult.size() == 0) {
                long endTime = System.currentTimeMillis();
                System.out.println("Количество найденных строк: "+0 +"  затраченное на поиск время: "+(endTime - startTime) + " мc");
                return;
            }

            StringFormatChecker isNumericString = new InNumericString();
            if(isNumericString.check(searchByColumnResult.get(0).second())) {
                searchByColumnResult.sort((o1, o2) -> {
                    double value1 = Double.parseDouble(o1.second());
                    double value2 = Double.parseDouble(o2.second());
                    return (int)(value1 - value2);
                });
            }

            long endTime = System.currentTimeMillis();

            Map<Integer, String> rowNumberToResultMapper = new HashMap<>();
            SortedSet<Integer> sortedRowNumbers = new TreeSet<>();

            for(Pair<List<Integer>, String> element: searchByColumnResult) {
                for(int rowNumber : element.first()) {
                    rowNumberToResultMapper.put(rowNumber, element.second());
                    sortedRowNumbers.add(rowNumber);
                }
            }

            Map<Integer, String> tempFileRowStorage = new HashMap<>();

            try(CsvFileReader reader = new CsvFileReader(new InputStreamReader(getClass().getResourceAsStream(csvFilePath)))) {
                String row = ",";
                Iterator<Integer> iterator = sortedRowNumbers.iterator();

                while(iterator.hasNext()) {
                    String nextRowNum = Integer.toString(iterator.next());
                    while(!nextRowNum.equals(row.substring(0, row.indexOf(",")))) {
                        row = reader.readLine();
                    }
                    tempFileRowStorage.put(Integer.parseInt(nextRowNum), row);
                }

                for(Pair<List<Integer>, String> element: searchByColumnResult) {
                    for(int rowNum : element.first()) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(CsvRowSplitter.split(tempFileRowStorage.get(rowNum))[columnNumber]);
                        stringBuffer.append("[");
                        stringBuffer.append(tempFileRowStorage.get(rowNum));
                        stringBuffer.append("]");
                        System.out.println(stringBuffer);
                    }
                }

                System.out.println("Количество найденных строк: "+searchByColumnResult.size() +"  затраченное на поиск время: "+(endTime - startTime) + " мc");

            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        } while (!query.equals("!quit"));
    }
}
