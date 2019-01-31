import org.junit.After;
import org.junit.Before;

import java.util.Arrays;

public class MinHeapTest {


//    MinHeap  = new MinHeap( Arrays.asList() );
    MinHeap regular = new MinHeap( Arrays.asList( 1, 4, 5, 8, 2 ) );
//    MinHeap  = new MinHeap( Arrays.asList() );


    @Before
    public void setUp() throws Exception {
        regular = new MinHeap( Arrays.asList( 1, 4, 5, 8, 2 ) );
    }

    @After
    public void tearDown() throws Exception {
        regular = new MinHeap( Arrays.asList( 1, 4, 5, 8, 2 ) );
    }
}