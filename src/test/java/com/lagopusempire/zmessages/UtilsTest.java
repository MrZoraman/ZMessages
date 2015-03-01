package com.lagopusempire.zmessages;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author MrZoraman
 */
public class UtilsTest
{
    @Test
    public void testArrayToMessage()
    {
        String[] array = {"a", "b", "c", "d"};
        String expected = "a b c d";
        
        String result = Utils.toMessage(array);
        Assert.assertEquals(null, expected, result);
    }
}
