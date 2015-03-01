package com.lagopusempire.zmessages;

/**
 *
 * @author MrZoraman
 */
public class Utils
{
    private Utils()
    {
    }
    
    public static String toMessage(String[] array)
    {
        StringBuilder builder = new StringBuilder();
        for(int ii = 0; ii < array.length; ii++)
        {
            builder.append(array[ii]).append(" ");
        }
        
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }
}
