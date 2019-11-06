package de.fklappan.app.webnotes.common;

public class StringUtil {

    public static boolean nullOrEmpty(String text) {
        if (text == null) {
            return false;
        }
        return text.isEmpty();
    }
}
