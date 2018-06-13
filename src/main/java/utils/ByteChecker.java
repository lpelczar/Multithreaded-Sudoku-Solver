package utils;

public class ByteChecker {

    public static boolean isByte(String s) {
        try {
            Byte.parseByte(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
