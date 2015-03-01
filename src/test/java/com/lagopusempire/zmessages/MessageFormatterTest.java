package com.lagopusempire.zmessages;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author MrZoraman
 */
public class MessageFormatterTest
{
    @Test
    public void testFormatter()
    {
        String in = "%A% %b% %c% %d%";
        String expected = "1 2 3 4";
        
        String result = MessageFormatter
                .create(in)
                .replace("a", "1")
                .replace("b", "2")
                .replace("c", "3")
                .replace("d", "4")
                .toString();
        
        Assert.assertEquals(null, result, expected);
    }
}
