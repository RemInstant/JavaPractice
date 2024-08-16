package remi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ConcurrentMergeSort
{

    final class SortTask<T extends Comparable<T>> extends RecursiveAction
    {
        T[] arr;
        int start, end;
        
        public SortTask(T[] arr, int start, int end)
        {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }
        
        @Override
        protected void compute()
        {
            int cnt = end - start;
            
            if (cnt <= 16) {
                Arrays.sort(arr, start, end);
            } else {
                int mid = (start + end) / 2;
                invokeAll(new SortTask<T>(arr, start, mid), new SortTask<T>(arr, mid, end));
                
                ArrayList<T> tmp = new ArrayList<>(cnt);
                
                for (int i = start, j = start, k = mid; i < end; ++i) {
                    if (j != mid && (k == end || arr[j].compareTo(arr[k]) <= 0)) {
                        tmp.add(arr[j]);
                        ++j;
                    } else {
                        tmp.add(arr[k]);
                        ++k;
                    }
                }
            }
        }
    }
    
    private ForkJoinPool pool;
    
    ConcurrentMergeSort(int parallelLevel)
    {
        pool = new ForkJoinPool(parallelLevel);
    }
    
    public <T extends Comparable<T>> void sort(T[] arr)
    {
        pool.invoke(new SortTask<T>(arr, 0, arr.length));
    }
}
