package project.com.nybestsellerbooksapp.svc.util;

/**
 * Created by mansshah on 6/22/18.
 */

public class StringUtil {

    public static String camelCase(String value){

        if(value == null || value.length() == 0)
            return value;

        String[] tokens = value.split("\\s+");
        StringBuilder result = new StringBuilder();
        for(String token : tokens)
        {
            result.append(String.valueOf(token.charAt(0)).toUpperCase() + token.substring(1).toLowerCase() + " ");

        }

        return new String(result);
    }
}
