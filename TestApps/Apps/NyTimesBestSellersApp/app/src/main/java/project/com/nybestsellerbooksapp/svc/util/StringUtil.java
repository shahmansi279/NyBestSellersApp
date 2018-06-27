package project.com.nybestsellerbooksapp.svc.util;

/**
 * Created by mansshah on 6/22/18.
 */

public class StringUtil {

    public static String camelCase(String value){

        if(value == null || value.length() == 0)
            return value;

        String input = value.replaceAll("[^a-zA-Z .'1-9]","");
        String[] tokens = input.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        //Some Strings have first few char of title in punctuation like { " ' #  .} etc - so removing them.
        String prefix = "";
        while(!Character.isLetterOrDigit(tokens[0].charAt(0))){

            prefix = tokens[0].charAt(0) + "";
            tokens[0] = tokens[0].substring(1);
        }


        result.append(prefix);

        //Making first letter of each word capital.
        for(String token : tokens)
        {
            result.append(String.valueOf(token.charAt(0)).toUpperCase() + token.substring(1).toLowerCase() + " ");
        }

        return new String(result);
    }
}
