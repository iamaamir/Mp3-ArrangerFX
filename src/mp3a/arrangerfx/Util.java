package mp3a.arrangerfx;

import java.util.ResourceBundle;

/**
 *
 * @author Aamir khan
 */
public class Util {
    private static final String PROPERTIES_PATH = "mp3a/resources/properties";
    public static String getResource(String key) {
        return ResourceBundle.getBundle(PROPERTIES_PATH).getString(key);
    }
}
