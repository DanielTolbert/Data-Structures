import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 *A class that exists
 * @author DanielT
 * @version 9/14/18
 */
public class StringUtilitiesTest {

    private StringUtilities palinUtil;
    private StringUtilities nullUtil;
    private StringUtilities emptyUtil;
    private StringUtilities normUtil;
    private StringUtilities numUtil;
    private StringUtilities capsUtil;
    private StringUtilities lowUtil;
    private StringUtilities mixUtil;
    private StringUtilities posUtil;
    private StringUtilities iPutil;
    private StringUtilities duPtil;
    private StringUtilities multiUtil;
    private StringUtilities dashUtil;


    /**
     * COW
     */
    @Before
    public void setUp()
    {
        palinUtil = new StringUtilities( "racecar" );
        nullUtil = new StringUtilities();
        emptyUtil = new StringUtilities( "" );
        normUtil = new StringUtilities( "Timothy" );
        numUtil = new StringUtilities( "-47.1" );
        capsUtil = new StringUtilities( "TIM" );
        lowUtil = new StringUtilities( "tim" );
        mixUtil = new StringUtilities( "Sm4sh" );
        posUtil = new StringUtilities( "55" );
        iPutil = new StringUtilities( "1.1.1.1" );
        duPtil = new StringUtilities( "shhhh" );
        multiUtil = new StringUtilities( "Muahahahaha" );
        dashUtil = new StringUtilities( "1-1-1-1" );
    }

    /**
     * COW
     */
    @After
    public void cleanup()
    {
        palinUtil = new StringUtilities( "racecar" );
        nullUtil = new StringUtilities();
        emptyUtil = new StringUtilities( "" );
        normUtil = new StringUtilities( "Timothy" );
        numUtil = new StringUtilities( "-47.1" );
        capsUtil = new StringUtilities( "TIM" );
        lowUtil = new StringUtilities( "tim" );
        mixUtil = new StringUtilities( "Sm4sh" );
        posUtil = new StringUtilities( "55" );
        iPutil = new StringUtilities( "1.1.1.1" );
        duPtil = new StringUtilities( "shhhh" );
        multiUtil = new StringUtilities( "Muahahahaha" );
        dashUtil = new StringUtilities( "1-1-1-1" );
    }




    /**
     * COW
     */
    @Test
    public void testReverseSimpleInput()
    {
        assertEquals( "yhtomiT", normUtil.reverse() );
    }

    /**
     * COW
     */
    @Test
    public void testReversePalindrome()
    {
        assertEquals( "racecar", palinUtil.reverse() );
    }

    /**
     * COW
     */
    @Test ( expected = NullPointerException.class )
    public void testReverseNull()
    {
        assertNull( nullUtil.reverse() );
    }

    /**
     * COW
     */
    @Test ( expected = IllegalArgumentException.class )
    public void testReverseBadArguments()
    {
        assertNull( normUtil.reverse( -1, 200 ) );
    }



    /**
     * COW
     */
    @Test
    public void testReverseEmptyString()
    {
        assertEquals( "", emptyUtil.reverse() );
    }

    /**
     * COW
     */
    @Test
    public void testSetString()
    {
        normUtil.setString( "nani" );

        assertEquals( "nani", normUtil.toString() );
    }

    /**
     * COW
     */
    @Test
    public void testSetStringOnNull()
    {
        normUtil.setString( null );
        assertEquals( "null", normUtil.toString() );
    }

    /**
     * COW
     */
    @Test
    public void testToString()
    {
        assertEquals( "Timothy", normUtil.toString() );
    }

    /**
     * COW
     */
    @Test
    public void testToStringNull()
    {
        assertEquals( "null", nullUtil.toString() );
    }

    /**
     * COW
     */
    @Test
    public void testIsAllUpperNorm()
    {
        assertFalse( normUtil.isAllUpper() );
    }

    /**
     * COW
     */
    @Test
    public void testIsAllUpperNums()
    {
        assertTrue( numUtil.isAllUpper() );
    }

    /**
     * COW
     */
    @Test
    public void testIsAllUpperUppers()
    {
        assertTrue( capsUtil.isAllUpper() );
    }

    /**
     * COW
     */
    @Test ( expected = NullPointerException.class )
    public void testIsAllUpperNull()
    {
        assertNull( nullUtil.isAllUpper() );
    }

    /**
     * COW
     */
    @Test
    public void testIsAllLower()
    {
        assertFalse( normUtil.isAllLower() );
    }

    /**
     * COW
     */
    @Test
    public void testAllLowerNums()
    {
        assertTrue( numUtil.isAllLower() );
    }

    /**
     * COW
     */
    @Test ( expected = NullPointerException.class )
    public void testAllLowerNull()
    {
        assertNull( nullUtil.isAllLower() );
    }

    /**
     * COW
     */
    @Test
    public void testAllLowerLower()
    {
        assertTrue( lowUtil.isAllLower() );
    }

    /**
     * COW
     */
    @Test
    public void testContainNumbersAllLetters()
    {
        assertFalse( normUtil.containsNumbers() );
    }

    /**
     * COW
     */
    @Test
    public void testContainsNumbersMix()
    {
        assertTrue( mixUtil.containsNumbers() );
    }

    /**
     * COW
     */
    @Test ( expected = NullPointerException.class )
    public void testContainsNumbersNull()
    {
        assertNull( nullUtil.containsNumbers() );
    }

    /**
     * COW
     */
    @Test
    public void testContainsNumbersAllNumbers()
    {
        assertTrue( numUtil.containsNumbers() );
    }

    /**
     * COW
     */
    @Test
    public void testIsNumberNumber()
    {
        assertTrue( numUtil.isNumber() );
    }

    /**
     * COW
     */
    @Test
    public void testIsNumberBadNumber()
    {
        assertFalse( dashUtil.isNumber() );
    }

    /**
     * COW
     */
    @Test ( expected = NullPointerException.class )
    public void testIsNumberNull()
    {
        assertNull( nullUtil.isNumber() );
    }

    /**
     * COW
     */
    @Test
    public void testIsNumberMix()
    {
        assertFalse( mixUtil.isNumber() );
    }

    /**
     * COW
     */
    @Test
    public void testIsNumberPositive()
    {
        assertTrue( posUtil.isNumber() );
    }

    /**
     * COW
     */
    @Test
    public void testIsNumberIP()
    {
        assertFalse( iPutil.isNumber() );
    }

    /**
     * COW
     */
    @Test
    public void testConsecutiveDuplicatesNormal()
    {
        assertEquals( 0, normUtil.numConsecutiveDuplicates() );
    }

    /**
     * COW
     */
    @Test
    public void testConsecutiveDuplicatesNumber()
    {
        assertEquals( 1, posUtil.numConsecutiveDuplicates() );
    }

    /**
     * COW
     */
    @Test ( expected = NullPointerException.class )
    public void testConsecutiveDuplicatesNull()
    {
        assertNull( nullUtil.numConsecutiveDuplicates() );
    }

    /**
     * COW
     */
    @Test
    public void testConsecutiveDuplicatesConsecString()
    {
        assertEquals( 3, duPtil.numConsecutiveDuplicates() );
    }

    /**
     * COW
     */
    @Test
    public void testNumMatchesNormal()
    {
        assertEquals( 1, normUtil.numMatches( 'T' ) );
    }

    /**
     * COW
     */
    @Test
    public void testNumMatchesNumber()
    {
        assertEquals( 1, mixUtil.numMatches( '4' ) );
    }

    /**
     * COW
     */
    @Test
    public void testNumMatchesEmpty()
    {
        assertEquals( 0, emptyUtil.numMatches( 'r' ) );
    }

    /**
     * COW
     */
    @Test ( expected = NullPointerException.class )
    public void testNumMatchesNull()
    {
        assertNull( nullUtil.numMatches( normUtil.toString() ) );
    }

    /**
     * COW
     */
    @Test
    public void testAsArray()
    {
        assertArrayEquals( new char[]{'t', 'i', 'm'}, lowUtil.asArray() );
    }

    /**
     * COW
     */
    @Test ( expected = NullPointerException.class )
    public void testAsArrayNull()
    {
        assertArrayEquals( nullUtil.asArray(), null );
    }

    /**
     * COW
     */
    @Test
    public void testAsArrayEmpty()
    {
        assertArrayEquals( new char[]{}, emptyUtil.asArray() );
    }

    /**
     * COW
     */
    @Test
    public void testAsArrayWithNumbers()
    {
        assertArrayEquals( new char[]{'5', '5'}, posUtil.asArray() );
    }

    /**
     * COW
     */
    @Test
    public void testAsArrayWithNegative()
    {
        char[] c = new char[]{'-', '4', '7', '.', '1'};
        assertArrayEquals( c , numUtil.asArray() );
    }

    /**
     * COW
     */
    @Test
    public void testAsArrayMix()
    {
        char[] c = new char[]{'S', 'm', '4', 's', 'h'};
        assertArrayEquals( c , mixUtil.asArray() );
    }

    /**
     * COW
     */
    @Test
    public void testNumMatchesStringEmpty()
    {
        assertEquals( 0, emptyUtil.numMatches( "cat" ) );
    }

    /**
     * COW
     */
    @Test
    public void testNumMatchesStringShh()
    {
        assertEquals( 3, duPtil.numMatches( "hh" ) );
    }

    /**
     * COW
     */
    @Test
    public void testNumMatchesStringMultiOccurences()
    {
        assertEquals( 4, multiUtil.numMatches( "ha" ) );
    }

    /**
     * COW
     */
    @Test ( expected = NullPointerException.class )
    public void testNumMatchesStringOnNull()
    {
        assertNull( nullUtil.numMatches( "lmao yeah" ) );
    }
}
