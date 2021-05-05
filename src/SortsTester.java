
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

class SortsTester {
    Sorts<Integer> sorts = new Sorts<>();
    ArrayList<Integer> rand = new ArrayList<>(Arrays.asList(6,4,5,2,1,3));
    ArrayList<Integer> inOrder = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
    ArrayList<Integer> reversed = new ArrayList<>(Arrays.asList(6,5,4,3,2,1));



    @Test
    public void insertionSort() {
        sorts.InsertionSort(rand,0,rand.indexOf(0));
        assertEquals(inOrder,rand);
        sorts.InsertionSort(reversed,0,reversed.indexOf(1));
        assertEquals(inOrder,reversed);
    }

    @Test
    public void mergeSort() {
        sorts.MergeSort(rand,0,rand.indexOf(0));
        assertEquals(inOrder,rand);
        sorts.InsertionSort(reversed,0,reversed.indexOf(1));
        assertEquals(inOrder,reversed);
    }

    @Test
    public void quickSort() {
        sorts.QuickSort(reversed,0,reversed.indexOf(1));
        assertEquals(inOrder,reversed);
        sorts.QuickSort(rand,0,rand.indexOf(0));
        assertEquals(inOrder,rand);
    }

    @Test
        public void modified_QuickSort() {
        sorts.Modified_QuickSort(rand,0,rand.size()-1,8);
        assertEquals(inOrder,rand);
        sorts.Modified_QuickSort(reversed,0,reversed.size()-1,8);
        assertEquals(inOrder,reversed);
    }

    @Test
    public void cocktailSort() {
        sorts.cocktailSort(rand,0,rand.size());
        assertEquals(inOrder,rand);
        sorts.cocktailSort(reversed,0,reversed.size());
        assertEquals(inOrder,reversed);
    }
    public static void main(String[] pp){
        SortsTester sortsTester = new SortsTester();
        sortsTester.insertionSort();
        sortsTester.mergeSort();
        sortsTester.cocktailSort();
        sortsTester.modified_QuickSort();
        sortsTester.quickSort();
    }
}