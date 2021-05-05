/*
 * NAME: Bryan Talavera
 * PID:  A14378593
 */
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Sorts class.
 * @param <T> Generic type
 * @author Bryan Talavera
 * @since  05/02/21
 */
public class Sorts<T extends Comparable<? super T>> {
    private static final int MIDDLE_IDX = 2;

    /**
     * This method performs insertion sort on the input arraylist
     * adapted from Zybooks Examples
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */

    public void InsertionSort(ArrayList<T> list, int start, int end) {
        for (int i = 1; i < list.size(); i++) {

            int j = i;
            while (j > 0 && list.get(j).compareTo(list.get(j - 1)) < 0) {
                // We swap the values that are being compared, j and j - 1
                T placeholder = list.get(j);
                list.set(j, list.get(j - 1));
                list.set(j - 1,placeholder);
                j--;
            }
        }
    }

    /**
     * This method performs merge sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void MergeSort(ArrayList<T> list, int start, int end) {

        if (start < end)
        {
            int mid = start + (end - start) / MIDDLE_IDX;
            MergeSort(list, start, mid);
            MergeSort(list , mid + 1, end);

            merge(list, start, mid, end);
        }
    }

    /**
     * merge helper function for MergeSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param m the middle index we want to merge
     * @param r right-most index we want to merge
     */
    private void merge(ArrayList<T> arr, int l, int m, int r) {

        int mergedSize = r - l + 1;

        ArrayList<T> mergedNums = new ArrayList<>();
        int left = l, right = m + 1;
        while (left <= m && right <= r) {
            if (arr.get(left).compareTo(arr.get(right)) <= 0) {
                mergedNums.add(arr.get(left));
                left++;
            }
            else {
                mergedNums.add(arr.get(right));
                right++;
            }
        }

        while (left <= m) {
            mergedNums.add(arr.get(left));
            left++;
        }
        while (right <= r) {
            mergedNums.add(arr.get(right));
            right++;
        }
        for (int i = 0; i < mergedSize; i++) {
            arr.set(l + i, mergedNums.get(i));
        }
    }

    /**
     * This method performs quick sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void QuickSort(ArrayList<T> list, int start, int end) {
        // This is our Base Case, we check if our Start Idx
        // is less than/equal to our End Idx
        if (start >= end) {
            return;
        }
        // If we don't, we partition our list and take a step recursively
        int part = partition(list, start, end);
        QuickSort(list,start,part);
        QuickSort(list,part + 1,end);

    }

    /**
     * partition helper function for QuickSort
     * adapted from Zybooks Examples
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param h right-most index we want to merge
     */
    private int partition(ArrayList<T> arr, int l, int h) {

        // We find our pivot by using arr.get() on our midpoint
        // In order to find our midpoint, we add our leftmost index by the difference of the
        // right & leftmost indices, then divide by the MIDDLE_IDX

        // Adapted line
        int midpoint = l + (h - l)/MIDDLE_IDX;

        T pivot = arr.get(midpoint);
        boolean complete = false;
        while(!complete){
            // We compare all values less than our pivot
            while (arr.get(l).compareTo(pivot) < 0) l ++;
            // We compare all values greater than our pivot
            while (arr.get(h).compareTo(pivot) > 0) h --;
            if (l >= h){
                complete = true;
            }else{
                // otherwise, we swap
                T placeholder = arr.get(l);

                arr.set(l,arr.get(h));

                arr.set(h,placeholder);

                l++;
                h--;

            }
        }
        return h;
    }

    /**
     * This method performs a modified QuickSort that switches to insertion sort
     * after a certain cutoff
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param cutoff the minimum length of an subsection of the arraylist
     *               such that we switch to Insertion Sort
     */
    public void Modified_QuickSort(ArrayList<T> list, int start, int end, int cutoff) {
        // Base Case
        if (start >= end){
            return;
        }else{
            // We first check if the size is less than the cutoff
            if (list.size() <= cutoff){
                // We apply the appropriate sorting operation
                this.InsertionSort(list,start,end);
            }else{
                // If not, we continue partitioning and go a recursive step deeper
                int partition = partition(list,start,end);
                Modified_QuickSort(list,start,partition,cutoff);
                Modified_QuickSort(list,partition + 1, end,cutoff);
            }
        }
    }

    /**
     * This method performs cocktail sort on the input list
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void cocktailSort(ArrayList<T> list, int start, int end){
        int count = 0;
        // Do-while works better b/c while(count!=0) would skip the loop altogether
        do{
            // each loop, init a new count variable, keeping track of the swaps
            count = 0;
            for (int i = start; i < end - 1; i++) {
                // comparing our value and the previous value
                if (list.get(i).compareTo(list.get(i + 1)) > 0){
                    // swapping if our value is larger
                    T placeholder = list.get(i);
                    list.set(i,list.get(i+1));
                    list.set(i+1,placeholder);
                    count++;
                }
            }
            // if there were no swaps passing through once, end the loop
            if (count == 0) break;
            for (int i = end; i < start + 1; i++) {
                // comparing our value and the previous value
                if (list.get(i).compareTo(list.get(i - 1)) < 0){
                    // swapping if our value is smaller
                    T placeholder = list.get(i);
                    list.set(i,list.get(i-1));
                    list.set(i-1,placeholder);

                    count ++;
                }
            }
        }while(count != 0);
    }

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0,12,3,4,15,6,37,8,9,10));
        Sorts<Integer> sorts = new Sorts<>();
        sorts.QuickSort(list,0,9);
        System.out.println(list);
    }


}