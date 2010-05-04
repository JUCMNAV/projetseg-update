/**
 * Utility methods to handle escape characters in various export/import/report formats.
 */
package seg.jUCMNav.importexport.utils;

/**
 * @author damyot
 *
 */
public class EscapeUtils {

    /**
     * Convert a String to a new String where the XML special characters have been escaped
     */
    public static final String escapeHTML(String s) {
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c<'?') { // might be a special character to escape, otherwise escapes quickly
                switch (c) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                default:
                    sb.append(c);
                    break;
                }
            }
            else
                sb.append(c);
        }
        return sb.toString();
    }
    
}
