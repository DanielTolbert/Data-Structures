import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * This is the class used to test the MyLinkedList
 * Class
 * @author Daniel T
 * @version 10/23/2002
 */
public class MyLinkedListTest {

    private MyLinkedList<Integer> defaultInts;
    private MyLinkedList<String> defaultStrings;
    private MyLinkedList<Integer> intsAndNull;
    private MyLinkedList<Integer> emptyInts;
    private MyLinkedList<String> emptyString;
    private MyLinkedList<String> dupliString;
    private MyLinkedList<String> semiString;
    private MyLinkedList<Integer> myThing;
    private MyLinkedList<Integer> collecThing;

    /**
     * Blegh
     */
    @Before
    public void setUp() {

        defaultStrings = new MyLinkedList<>( "T", "i", "m" );
        defaultInts = new MyLinkedList<>( 1, 2, 3, 4 );
        intsAndNull = new MyLinkedList<>( 1, null, 4, null, 5 );
        emptyInts = new MyLinkedList<>();
        emptyString = new MyLinkedList<>();
        dupliString = new MyLinkedList<>( "T", "i", "m", "m", "m" );
        semiString = new MyLinkedList<>( "i", "i", "i" );
        myThing = new MyLinkedList<>( 8 );
        collecThing = new MyLinkedList<>( Arrays.asList( 1, 2, 3 ) );

    }

    /**
     * Blegh
     */
    @After
    public void cleanUp() {
        defaultStrings = new MyLinkedList<>( "T", "i", "m" );
        defaultInts = new MyLinkedList<>( 1, 2, 3, 4 );
        intsAndNull = new MyLinkedList<>( 1, null, 4, null, 5 );
        emptyInts = new MyLinkedList<>();
        emptyString = new MyLinkedList<>();
        dupliString = new MyLinkedList<>( "T", "i", "m", "m", "m" );
        semiString = new MyLinkedList<>( "i", "i", "i" );
        myThing = new MyLinkedList<>( 8 );
        collecThing = new MyLinkedList<>( Arrays.asList( 1, 2, 3 ) );
    }



    /**
     * Blegh
     */
    @Test
    public void testAddNormalMyString() {

        assertTrue( myThing.add( 8 ) );

    }

    /**
     * Blegh
     */
    @Test
    public void testAddNormalIntAndNull() {

        assertTrue( intsAndNull.add( 5 ) );

    }


    /**
     * Blegh
     */
    @Test
    public void testAddNormalInt() {

        assertTrue( defaultInts.add( 5 ) );

    }

    /**
     * Blegh
     */
    /**
     * Blegh
     */
    @Test
    public void testAddIntsPush() {
        for ( int i = 0; i < 9; i++ ) {
            emptyInts.add( 1 );
        }
        assertTrue( emptyInts.add( 1 ) );
    }


    /**
     * Blegh
     */
    @Test
    public void testAddNormalString() {

        assertTrue( defaultStrings.add( "sucks" ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testAddNormalCollec() {

        assertTrue( collecThing.add( 5 ) );

    }

    /**
     * Blegh
     */
    @Test( expected = IndexOutOfBoundsException.class )
    public void testAddNormalStringBadIndex() {

        defaultStrings.add( -20, "Don't let me in" );
        assertEquals( 8, defaultStrings.hashCode() );
    }

    /**
     * Blegh
     */
    @Test( expected = IndexOutOfBoundsException.class )
    public void testAddNormalIntBadIndex() {

        int index = ( 4 + 1 );
        defaultInts.add( index, 20 );
        assertEquals( 8, defaultInts.hashCode() );
    }



    /**
     * Blegh
     */
    @Test
    public void testAddMiddleIndex() {
        String[] toBe = new String[]{"T", "2", "i", "m"};
        defaultStrings.add( 1, "2" );
        assertArrayEquals( toBe, defaultStrings.toArray() );
    }


    /**
     * Blegh
     */
    @Test
    public void testAddBeginIndex() {
        Integer[] toBe = new Integer[]{20, 1, 2, 3, 4};
        defaultInts.add( 0, 20 );
        assertArrayEquals( toBe, defaultInts.toArray() );
    }


    /**
     * Blegh
     */
    @Test
    public void testAddEmpty() {
        Integer[] toBe = new Integer[]{20};
        emptyInts.add( 0, 20 );
        assertArrayEquals( toBe, emptyInts.toArray() );
    }

    /**
     * Blegh
     */
    @Test
    public void testRemoveBeginningReturns() {
        assertEquals( 1, defaultInts.remove( 0 ) );
    }

    /**
     * Blegh
     */
    @Test
    public void removeBeginningResult() {
        String[] toBe = new String[]{"i", "m"};
        defaultStrings.remove( 0 );
        assertArrayEquals( toBe, defaultStrings.toArray() );
    }

    /**
     * Blegh
     */
    @Test
    public void testRemoveObject() {
        String[] ancient = new String[]{"T", "i"};
        defaultStrings.remove( "m" );
        assertArrayEquals( ancient, defaultStrings.toArray() );
//        System.out.println( defaultStrings.indexOf( "m" ) );
    }

    /**
     * Blegh
     */
    @Test( expected = IndexOutOfBoundsException.class )
    public void testRemoveBadIndex() {
        assertEquals( 5678, defaultInts.remove( defaultInts.size() + 5 ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testGetInts() {
        assertEquals( 2, defaultInts.get( 1 ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testGetStrings() {
        assertEquals( "m", defaultStrings.get( 2 ) );
    }

    /**
     * Blegh
     */
    @Test( expected = IndexOutOfBoundsException.class )
    public void testGetBadIndex() {
        assertEquals( 456, defaultInts.get( defaultInts.size() + 4 ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testSet() {
        String[] thing = new String[]{"T", "yay", "m"};
        defaultStrings.set( 1, "yay" );
        assertArrayEquals( thing, defaultStrings.toArray() );
    }

    /**
     * Blegh
     */
    @Test
    public void testSetFirstIndex() {
        String[] thing = new String[]{"yay", "i", "m"};
        defaultStrings.set( 0, "yay" );
        assertArrayEquals( thing, defaultStrings.toArray() );
    }

    /**
     * Blegh
     */
    @Test
    public void testSize() {
        assertEquals( 3, defaultStrings.size() );
    }

    /**
     * Blegh
     */
    @Test
    public void testSizeEmpty() {
        assertEquals( 0, emptyString.size() );
    }

    /**
     * Blegh
     */
    @Test
    public void testContainsIsThere() {
        assertTrue( defaultStrings.contains( "m" ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testContainsIsNotThere() {
        assertFalse( defaultStrings.contains( "x" ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testIsEmptyIsEmpty() {
        assertTrue( emptyString.isEmpty() );
    }

    /**
     * Blegh
     */
    @Test
    public void testIsEmptyFull() {
        assertFalse( defaultStrings.isEmpty() );
    }

    /**
     * Blegh
     */
    @Test
    public void testToString() {
        String thing = "[T, i, m]";
        assertEquals( thing, defaultStrings.toString() );
    }

    /**
     * Blegh
     */
    @Test
    public void testToStringEmpty() {
        String thing = "[]";
        assertEquals( thing, emptyString.toString() );
    }

    /**
     * Blegh
     */
    @Test
    public void testToStringInts() {
        String thing = "[1, 2, 3, 4]";
        assertEquals( thing, defaultInts.toString() );
    }

    /**
     * Blegh
     */
    @Test
    public void testAddAll() {
        String[] thing = new String[]{"T", "i", "m", "ur", "bad"};
        defaultStrings.addAll( Arrays.asList( "ur", "bad" ) );
        assertArrayEquals( thing, defaultStrings.toArray() );
//        System.out.println( defaultStrings.toString() );
    }

    /**
     * Blegh
     */
    @Test
    public void testClear() {
        defaultInts.clear();
        assertTrue( defaultInts.isEmpty() );
    }

    /**
     * Blegh
     */
    @Test
    public void testIndexOf() {
        assertEquals( 0, defaultInts.indexOf( 1 ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testIndexOfNotThere() {
        assertEquals( -1, defaultInts.indexOf( 20 ) );
    }


    /**
     * Blegh
     */
    @Test
    public void testLastIndexOf() {
        assertEquals( 4, dupliString.lastIndexOf( "m" ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testLastIndexOfNotThere() {
        assertEquals( -1, defaultInts.lastIndexOf( 20 ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testRemoveRange() {
        Integer[] thing = new Integer[]{1, 4};
        defaultInts.removeRange( 1, 3 );
        assertArrayEquals( thing, defaultInts.toArray() );
    }

    /**
     * Blegh
     */
    @Test
    public void testRemoveRangeStop() {
        Integer[] thing = new Integer[]{1, 2, 3, 4};
        defaultInts.removeRange( 0, 0 );
        assertArrayEquals( thing, defaultInts.toArray() );
    }

    /**
     * Blegh
     */
    @Test( expected = IndexOutOfBoundsException.class )
    public void testRemoveRangeOutOfBounds() {
        Integer[] thing = new Integer[]{1, 2, 3, 4};
        defaultInts.removeRange( 20, 80 );
        assertArrayEquals( thing, defaultInts.toArray() );
    }

    /**
     * Blegh
     */
    @Test( expected = IllegalArgumentException.class )
    public void testRemoveRangeOverlap() {
        Integer[] thing = new Integer[]{1, 2, 3, 4};
        defaultInts.removeRange( 2, 0 );
        assertArrayEquals( thing, defaultInts.toArray() );
    }

    /**
     * Blegh
     */
    @Test
    public void testToArray() {
        String[] thing = new String[]{"T", "i", "m"};
        assertArrayEquals( thing, defaultStrings.toArray() );
    }

    /**
     * Blegh
     */
    @Test
    public void testEqualsTrue() {
        MyLinkedList<String> thing = new MyLinkedList<>( "T", "i", "m" );
        assertTrue( defaultStrings.equals( thing ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testEqualsFalseSameLen() {
        assertFalse( defaultStrings.equals( semiString ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testEqualsFalse() {
        MyLinkedList<String> thing;
        thing = new MyLinkedList<>( "T", "i", "m", "rfg" );
        assertFalse( defaultStrings.equals( thing ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testEqualsNotInstanceOf() {
        Character g = 'c';
        assertFalse( defaultStrings.equals( g ) );
    }

    /**
     * Blegh
     */
    @Test
    public void testEqualsForeignInstance() {
        MyLinkedList<Integer> thing = new MyLinkedList<>( 1, 2, 3, 4 );
        defaultStrings.add( "m" );
        defaultStrings.equals( thing );
        assertFalse( defaultStrings.equals( thing ) );
    }
}