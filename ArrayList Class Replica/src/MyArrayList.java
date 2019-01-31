import java.util.Collection;


/**
 * z
 * A recreation of the Java ArrayList class
 * it is made to store Objects in a list format
 * with a malleable size. These objects can be modified,
 * retrieved, and removed.
 *
 * @param <E> Datatype of the list
 * @author Daniel T
 * @version 10/1/18
 */
public class MyArrayList<E> {

    private int size;
    private Object[] objs;

    /**
     * Constructor of MyArrayList that sets
     * size to zero and the length to 8
     */
    public MyArrayList() {

        this.size = 0;
        this.objs = new Object[8];

    }

    /**
     * Constructor of MyArrayList that sets
     * size to zero and length to len
     *
     * @param len the length of the background
     *            array
     */
    public MyArrayList( int len ) {

        this.size = 0;
        this.objs = new Object[len];

    }

    /**
     * Constructor of MyArrayList that adds
     * all values in a collection into the list
     *
     * @param c Collection to be added
     */
    public MyArrayList( Collection c ) {

        this();
        addAll( c );

    }


    /**
     * Constructor of MyArrayList that adds
     * all values in a collection into the list
     *
     * @param objs Collection to be added
     */
    public MyArrayList( E... objs ) {

        this();

        for ( int i = 0; i < objs.length; i++ ) {
            add( objs[i] );
        }

    }


    /**
     * adds the specified object to the
     * end of the list
     *
     * @param obj Object to be added
     * @return true or false based on completion of
     * operation
     */
    public boolean add( E obj ) {

        add( size, obj );
        return true;

    }

    /**
     * adds specified object to specified
     * index and shifts all values over one
     * slot.
     *
     * @param index index to add the object to.
     * @param obj Object to be added.
     */
    public void add( int index, E obj ) {

        checkIndexAdd( index );
        size++;
        if ( size >= objs.length ) {
            ensureCapacity( size + 1 );
        }
        for ( int i = size; i > index; i-- ) {
            objs[i] = objs[i - 1];
        }
        objs[index] = obj;
    }

    /**
     * removes the specified object from the list
     * and shifts all values over one slot.
     * If there is more than one instance, than only
     * the first is removed
     *
     * @param obj Object to be removed
     * @return true or false based on successful
     * operation
     */
    public boolean remove( E obj ) {

        remove( indexOf( ( E ) obj ) );
        return true;

    }

    /**
     * removes Object at specified index.
     *
     * @param index index to be examined.
     * @return Object removed
     * @throws IndexOutOfBoundsException if index is
     *                                   greater than size or is negative.
     */
    public Object remove( int index ) {

        checkIndex( index );

        Object o = objs[index];
        size--;

        for ( int i = index; i < size; i++ ) {
            objs[i] = objs[i + 1];
        }

        return o;

    }

    /**
     * returns the object at the given index.
     *
     * @param index index to be examined
     * @return Object at specified index
     * @throws IndexOutOfBoundsException if index is
     *                                   greater than size or is negative.
     */
    public Object get( int index ) {

        checkIndex( index );
        return ( E ) objs[index];

    }

    /**
     * Sets the Object at the given position to
     * the Object obj.
     *
     * @param index index to set.
     * @param obj   Object to be set to.
     * @return Previous Object.
     * @throws IndexOutOfBoundsException if index is
     *                                   greater than size or is negative.
     */
    public Object set( int index, E obj ) {

        checkIndex( index );
        Object o = objs[index];
        objs[index] = obj;
        return ( E ) o;
    }

    /**
     * returns the current size of the MyArrayList
     *
     * @return variable size
     */
    public int size() {

        return size;

    }

    /**
     * Checks to see if the Object obj
     * is found within the list.
     *
     * @param obj Object to be looked for.
     * @return true or false based on discovery of obj
     */
    public boolean contains( E obj ) {
        return indexOf( obj ) >= 0;
    }

    /**
     * Checks to see if the MyArrayList is empty.
     *
     * @return true or false based on emptiness
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Converts the MyArrayList into a String in an
     * array format.
     *
     * @return new String of all objects in the
     * MyArrayList
     */
    public String toString() {

        if ( this.isEmpty() ) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        int nSize = ( size - 1 );
        sb.append( "[" + objs[0] );
        for ( int i = 1; i < nSize + 1; i++ ) {
            sb.append( ", " + objs[i] );
        }
        sb.append( "]" );
        return sb.toString();
    }

    /**
     * adds all items in Collection c to the
     * MyArrayList.
     *
     * @param c the Collection to be added to
     *          MyArrayList.
     */
    public void addAll( Collection c ) {

        for ( Object o : c ) {
            E other = ( E ) o;
            add( other );
        }

    }

    /**
     * Void method that rids the MyArrayList
     * of every Object that it holds.
     */
    public void clear() {
        size = 0;
    }

    /**
     * Makes sure the background array has greater than
     * or equal to minCapacity slots. If it does not,
     * then the length is either doubled, or set to 8,
     * depending on which one is longer. The maximum
     * length in Integer.MAX_VALUE.
     *
     * @param minCapacity the minimum capacity that
     *                    the background is expected to hold.
     */
    public void ensureCapacity( int minCapacity ) {

        if ( minCapacity > Integer.MAX_VALUE >> 1 ) {
            minCapacity = Integer.MAX_VALUE;
        }

        if ( minCapacity < objs.length ) {
            return;
        }
        int len = size;
        while ( len < minCapacity ) {
            len = ( Math.max( len * 2, 8 ) );
        }

        Object[] tempObjs = new Object[len];

        for ( int i = 0; i < objs.length; i++ ) {
            tempObjs[i] = objs[i];
        }

        objs = tempObjs;

    }

    /**
     * Finds the index of the specified object in
     * the MyArrayList.
     *
     * @param obj the Object to be searched for.
     * @return the index of the Object, or -1, if
     * it is not there.
     */
    public int indexOf( Object obj ) {

        for ( int i = 0; i < size; i++ ) {

            if ( objs[i].equals( obj ) ) {
                return i;
            }

        }
        return -1;
    }

    /**
     * Looks for the final index of the specified
     * Object in the MyArrayList.
     *
     * @param obj The Object to be searched for.
     * @return the final index obj is located, or
     * -1, if it is not located at all.
     */
    public int lastIndexOf( Object obj ) {
        for ( int i = size - 1; i >= 0; i-- ) {
            if ( objs[i].equals( obj ) ) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes all objects in the MyArrayList
     * between the two specified indexes. The second
     * index is non-inclusive.
     *
     * @param fromIndex the index to begin removing from
     * @param toIndex   the index to stop removing from
     */
    public void removeRange( int fromIndex, int toIndex ) {
        checkIndexTwo( fromIndex, toIndex );

        int times = toIndex - fromIndex;
        while ( times > 0 ) {
            remove( fromIndex );
            times--;
        }
    }

    /**
     * Creates an array with all objects from
     * the MyArrayList.
     *
     * @return an Object array with all contents of
     * the MyArrayList.
     */
    public Object[] toArray() {
        Object[] nObjs = new Object[size];
        for ( int i = 0; i < size; i++ ) {
            nObjs[i] = objs[i];
        }
        return nObjs;
    }

    /**
     * Makes the background array only
     * as long as the value of size variable
     */
    public void trimToSize() {
        objs = toArray();
    }

    /**
     * Compares the size and contents of a
     * MyArrayList and an Object to see if they
     * are equivalent.
     *
     * @param o The object to be compared to.
     * @return true if the size and contents of Object
     * o are equivalent to that of the MyArrayList
     */
    public boolean equals( Object o ) {

        if ( !( o instanceof MyArrayList ) ) {
            return false;
        }

        MyArrayList other = ( MyArrayList ) o;
        if ( size() == other.size() ) {
            for ( int i = 0; i < size(); i++ ) {
                if ( !( other.get( i ).equals( get( i ) ) ) ) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Checks to see if an index
     * would be out of bounds
     * in an array.
     *
     * @param index the index to be checked.
     * @throws IndexOutOfBoundsException if index is
     *                                   greater than size or is negative.
     */
    private void checkIndex( int index ) {
        if ( index < 0 || index > size - 1 ) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Checks to see if an index
     * would be out of bounds
     * in an array.
     *
     * @param index the index to be checked.
     * @throws IndexOutOfBoundsException if index is
     *                                   greater than size or is negative.
     */
    private void checkIndexAdd( int index ) {
        if ( index < 0 || index > size ) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Checks to see if indexes
     * would be out of bounds
     * in an array.
     *
     * @param fromIndex the index to be checked.
     * @param toIndex   the index to be checked
     * @throws IndexOutOfBoundsException if index is
     *                                   greater than size or is negative.
     */
    private void checkIndexTwo( int fromIndex, int toIndex ) {
        checkIndexAdd( fromIndex );
        checkIndexAdd( toIndex );
        if ( fromIndex > toIndex ) {
            throw new IllegalArgumentException();
        }

    }


}
