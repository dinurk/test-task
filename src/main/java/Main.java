public class Main {

    public static void main(String ...args) {

        int columnNumber = Integer.parseInt(args[0])-1;

        CsvFileCli cli = new CsvFileCli("/airports.csv", columnNumber);
//        CsvFileCli cli = new CsvFileCli("/test-file.csv", 2);
        cli.initialize();
        cli.start();
    }
}
