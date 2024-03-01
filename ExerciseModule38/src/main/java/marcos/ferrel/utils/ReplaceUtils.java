/**
 * 
 */
package marcos.ferrel.utils;

/**
 * @author marcos.ferrel
 * 
 */
public class ReplaceUtils {
	
	public static String replace(String value, String ...patterns) {
		String ret = value;
		for (String pattern : patterns) {
			ret = ret.replace(pattern, "");
		}
		return ret;
	}

}
