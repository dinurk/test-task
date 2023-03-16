public class Main {

    public static void main(String ...args) {
        int columnNumber = Integer.parseInt(args[0]) - 1;

        CsvFileCli cli = new CsvFileCli("/airports.csv", columnNumber);
        cli.initialize();
        cli.start();
    }
}
