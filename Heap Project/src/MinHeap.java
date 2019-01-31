import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class MinHeap {

    private ArrayList<Integer> heap;
    private int size;

    public MinHeap() {
        heap = new ArrayList<Integer>( 8 );
        heap.add( null );
    }

    public MinHeap( int size ) {
        heap = new ArrayList<>( size );
        heap.add( null );
    }

    public MinHeap( Collection<Integer> collection ) {
        heap.add( null );

        for ( int i : collection ) {
            heap.add( i );
        }

    }

    private boolean add( int obj ){

//        heap.add( 1, obj );
        size++;
        return true;
    }

    public int size() {
        return heap.size() - 1;
    }

    private void reheapDown() {

    }

    private void reheapUp() {
        int index = ( size() - 1 );
        trash( index );
        heapUpHelper( index );
    }

    private void heapUpHelper( int index ) {

        if ( heap.get( index/2 ) < getTrash() ) {
            heapUpHelper( index/2 );
        } else {
            heap.add( getTrash(), index );
        }

    }

    private int trash( int index ) {

        return heap.set( 0, heap.remove( index ) );
    }

    private int getTrash() {
        return heap.get( 0 );
    }

    public static void main( String[] args ) {

    }

}
