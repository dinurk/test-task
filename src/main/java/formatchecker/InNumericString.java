package formatchecker;

public class InNumericString implements StringFormatChecker{

    public InNumericString() {

    }

    @Override
    public boolean check(String string) {
        if (string == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
