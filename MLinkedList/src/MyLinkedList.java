import java.util.Collection;

/**
 * This class is a remake of the LinkedList dtata
 * Structure class.
 * @author Daniel T
 * @version 10/23/2002
 * @param <E> The generic parameter type.
 */
public class MyLinkedList<E> {

    private int size;
    private Node<E> head;

    /**
     * A thing of data that records a value
     * and the location of the next thing of data
     * @param <E> The type of variable of Node
     */
    private class Node<E> {
        private Object data;
        private Node<E> next;

        /**
         * Creates a node with an object and a pointer
         * @param data the object that it holds
         * @param next the pointer to the next node
         */
        private Node( Object data, Node next ) {
            this.data = data;
            this.next = next;
        }

        /**
         * Creates a Node with an object and a null
         * pointer.
         * @param data
         */
        private Node( Object data ) {
            this( data, null );
        }
    }

    /**
     * Creates a LinkedList with the next node as null
     */
    public MyLinkedList() {
        head = null;
        calcSize();
    }

    /**
     * Creates a linkedlist with a node for every
     * object in a collection c;
     * @param c Collection to be added
     */
    public MyLinkedList( Collection c ) {
        calcSize();
        addAll( c );
    }

    /**
     * Creates an MyLinkedList for every one of
     * things.
     * @param things a collection of things to be made into
     * nodes.
     */
    public MyLinkedList( E...things ) {
        calcSize();
        for ( int i = 0; i < things.length; i++ ) {
            add( things[i] );
        }
    }

    /**
     * Adds the specified node to the end of the
     * MyLinkedList
     * @param o the object to be added
     * @return true or false based on completion of
     * addition
     */
    public boolean add( Object o ) {

        add( size, o );
        return true;

    }

    /**
     * calculates the size of the MyLinkedList
     */
    private void calcSize() {
        int len = 0;
        Node node = head;
        while ( node != null ) {
            node = node.next;
            len++;
        }
        size = len;
    }

    /**
     * adds specified object to specified
     * index and shifts all values over one
     * slot.
     *
     * @param index index to add the object to.
     * @param o Object to be added.
     */
    public void add( int index, Object o ) {

        checkIndexAdd( index );

        if ( isEmpty() ) {
            head = new Node( o );
            size++;
            return;
        }

        if ( index == 0 ) {
            Node temp = new Node( o );
            temp.next = head;
            head = temp;
            size++;
            return;
        }

        Node node = new Node( o, null );
        int x = 0;
        Node i = head;
        for ( ; x < index - 1; i = i.next ) {
            x++;
        }

        if ( index == size ) {
            node.next = null;
        }
        else {
            node.next = i.next;
        }
        i.next = node;
        size++;
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
     * @param index index to be examined.
     * @return Object removed
     * @throws IndexOutOfBoundsException if index is
     * greater than size or is negative.
     */
    public Object remove( int index ) {

        checkIndex( index );

        Object toRet = getNode( index ).data;

        size--;

        if ( head == getNode( index ) ) {
            toRet = head.data;
            head = head.next;
            return toRet;
        }

        Node i = getNode( index - 1 );
        if ( index == size - 1 ) {
            i.next = null;
        }
        else {
            i.next = i.next.next;
        }
        return toRet;
    }

    /**
     * returns the object at the given index.
     * @param index index to be examined
     * @return Object at specified index
     * @throws IndexOutOfBoundsException if index is
     * greater than size or is negative.
     */
    public Object get( int index ) {

        checkIndex( index );
        Node i = head;
        int x = 0;
        while ( x < index ) {
            i = i.next;
            x++;
        }
        return i.data;
    }

    /**
     * Sets the Object at the given position to
     * the Object obj.
     *
     * @param index index to set.
     * @param obj   Object to be set to.
     * @return Previous Object.
     * @throws IndexOutOfBoundsException if index is
     * greater than size or is negative.
     */
    public Object set( int index, E obj ) {

        Object toReturn = get( index );
        Node node = new Node( obj );
        node.next = index == size ? null : getNode( index ).next;
        if ( index != 0 ) {
            getNode( index - 1 ).next = node;
            return toReturn;
        }
        head = node;
        return toReturn;
    }

    /**
     * returns the current size of the MyLinkedList
     *
     * @return variable size
     */
    public int size() {
        calcSize();
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
     * Checks to see if the MyLinkedList is empty.
     *
     * @return true or false based on emptiness
     */
    public boolean isEmpty() {
        calcSize();
        return size == 0;
    }

    /**
     * Converts the MyLinkedList into a String in an
     * array format.
     *
     * @return new String of all objects in the
     * MyLinkedList
     */
    public String toString() {
        if ( isEmpty() ) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Node node = head;
        stringBuilder.append( "[" + node.data );
        node = node.next;
        while ( node != null ) {
            stringBuilder.append( ", " + node.data );
            node = node.next;
        }
        stringBuilder.append( "]" );
        return stringBuilder.toString();
    }

    /**
     * adds all items in Collection c to the
     * MyLinkedList.
     * @param c the Collection to be added to
     *          MyLinkedList.
     */
    public void addAll( Collection c ) {
        for ( Object o : c ) {
            add( ( o ) );
        }
    }

    /**
     * Void method that rids the MyLinkedList
     * of every Object that it holds.
     */
    public void clear() {
        head = null;
        size = 0;
    }


    /**
     * Finds the index of the specified object in
     * the MyLinkedList.
     *
     * @param obj the Object to be searched for.
     * @return the index of the Object, or -1, if
     * it is not there.
     */
    public int indexOf( Object obj ) {

        int x = 0;
        for ( Node i = head; i != null; i = i.next ) {
            if ( i.data.equals( obj ) ) {
                return x;
            }
            x++;
        }
        return -1;
    }

    /**
     * Looks for the final index of the specified
     * Object in the MyLinkedList.
     *
     * @param obj The Object to be searched for.
     * @return the final index obj is located, or
     * -1, if it is not located at all.
     */
    public int lastIndexOf( Object obj ) {

        int x = 0;
        int found = -2;
        for ( Node i = head; i.next != null; i = i.next ) {
            if ( i.data.equals( obj ) ) {
                found = x;
            }
            x++;
        }
        return found + 1;
    }

    /**
     * Removes all objects in the MyLinkedList
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
     * the MyLinkedList.
     *
     * @return an Object array with all contents of
     * the MyLinkedList.
     */
    public Object[] toArray() {
        calcSize();
        Object[] objs = new Object[size];
        Node i = head;
        int j = 0;
        while ( i != null ) {
            objs[j] = i.data;
            i = i.next;
            j++;
        }
        return objs;
    }

    /**
     * Compares the size and contents of a
     * MyLinkedList and an Object to see if they
     * are equivalent.
     *
     * @param o The object to be compared to.
     * @return true if the size and contents of Object
     * o are equivalent to that of the MyLinkedList
     */
    public boolean equals( Object o ) {

        if ( !( o instanceof MyLinkedList ) ) {
            return false;
        }

        MyLinkedList other = ( MyLinkedList ) o;
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
        calcSize();
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
     *  greater than size or is negative.
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

    /**
     * Retrieves the node at the specified index
     * @param index the index to find the node at
     * @return The node at the specified index
     */
    private Node getNode( int index ) {
        checkIndex( index );
        Node i = head;
        int x = 0;
        while ( x < index ) {
            i = i.next;
            x++;
        }
        return i;
    }




}

