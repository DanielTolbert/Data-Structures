import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilitiesTest {

    @Test
    public void testReverseSimpleInput()
    {
        StringUtilities util = new StringUtilities("meow");
        assertEquals( "woem", util.reverse() );
    }

    @Test
    public void testReversePalindrome()
    {
        StringUtilities util = new StringUtilities("racecar");
        assertEquals( "racecar", util.reverse());
    }


    @Test
    public void testReverseEmptyString()
    {
        StringUtilities util = new StringUtilities("");
        assertEquals( "", util.reverse());
    }

    @Test (expected = NullPointerException.class)
    public void testReverseOnNull()
    {
        StringUtilities util = new StringUtilities();
        util.reverse();
    }


}
