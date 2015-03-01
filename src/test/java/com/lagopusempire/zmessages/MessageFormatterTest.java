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
        String in = "%A% X %b% y %C% Z %d%";
        String expected = "1 X 2 y 3 Z 4";
        
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
    public void testSpacedVariables()
    {
        String in = "%a w% %b x% %c y% %d z%";
        String expected = "1 2 3 4";
        
        String result = MessageFormatter
                .create(in)
                .replace("a w", "1")
                .replace("b x", "2")
                .replace("c y", "3")
                .replace("d z", "4")
                .toString();
        
        Assert.assertEquals(null, expected, result);
    }
    
    @Test
    public void testStrippingColors()
    {
        String in = "&ab &cd";
        String expected = "b d";
        
        String result = MessageFormatter
                .create(in)
                .stripColors()
                .toString();
        
        Assert.assertEquals(null, expected, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyStringCreation()
    {
        MessageFormatter.create("");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullStringCreation()
    {
        MessageFormatter.create(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyVariableArgument()
    {
        MessageFormatter.create("stuff").replace("", "stuff");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullVariableArgument()
    {
        MessageFormatter.create("stuff").replace(null, "stuff");
    }
}
