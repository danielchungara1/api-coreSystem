package com.tplate.coresystem.shared.business;

public class StringUtil {

    /**
     * Truncate a string until reaching substring.
     * If not match then returns default string.
     *
     * @param str        string to truncate
     * @param substring  substring expression
     * @param defaultStr default string
     * @return truncated string or defaultStr in case not match
     */
    public static String truncateBySubstringOrElseReturnDefaultString(String str, String substring, String defaultStr) {

        if (str != null && substring != null) {

            int index = str.indexOf(substring);
            if (index == -1) {
                return defaultStr;
            } else {
                return str.substring(0, index).trim();
            }

        } else {
            return defaultStr;
        }

    }

    /**
     * Check if the string is an empty string.
     * A string is empty when is null or blank
     * @param str        string
     * @return boolean that indicates if string is empty.
     */
    public static Boolean isEmpty(String str) {
        return str == null || str.equals("");
    }
}
