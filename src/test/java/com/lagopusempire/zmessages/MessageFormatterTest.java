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
    public void testStandardUsage()
    {
        String in = "%a% %b% %c% %d%";
        String expected = "1 2 3 4";
        
        String result = MessageFormatter
                .create(in)
                .replace("a", "1")
                .replace("b", "2")
                .replace("c", "3")
                .replace("d", "4")
                .toString();
        
        Assert.assertEquals(null, expected, result);
    }
    
    @Test
    public void testCapUsage()
    {
        String in = "%A% %b% %C% %d%";
        String expected = "1 2 3 4";
        
        String result = MessageFormatter
                .create(in)
                .replace("a", "1")
                .replace("b", "2")
                .replace("c", "3")
                .replace("d", "4")
                .toString();
        
        Assert.assertEquals(null, expected, result);
    }
}
