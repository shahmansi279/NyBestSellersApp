package project.com.nybestsellerbooksapp.util;

/**
 * Created by mansshah on 6/22/18.
 */

public class StringUtil {

    /* Have customized this function to print the book titles from input as nicely as can be done.*/
    public static String camelCase(String value){

        if(value == null || value.length() == 0)
            return value;

        String input = value.replaceAll("[^a-zA-Z .$#'0-9]","");
        String[] tokens = input.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        //Some Strings have first few char of title in punctuation like { " ' #  .} etc - so removing them.
        String prefix = "";

       if(tokens[0].charAt(0) == '\'' && tokens[0].length() > 1)
           tokens[0] = tokens[0].substring(1);

       //Capitalize the first letter for first word
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


    public static String capitalizeFirstLetterofSentence(String input){

      char[] inputArr = input.toCharArray();
      inputArr[0] = Character.toUpperCase(inputArr[0]);
      return new String(inputArr);


    }
}
