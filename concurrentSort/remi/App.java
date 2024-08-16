package remi;

import java.util.Arrays;
import java.util.Random;


public class App
{
    public static void main(String args[])
    {
        Random rand = new Random(System.currentTimeMillis());
        int testCnt = 5;
        
        for (int exp = 5; exp <= 7; ++exp) {
            int size = (int) Math.pow(10, exp);
            Integer[] originArr = new Integer[size];
            
            for(int i = 0; i < originArr.length; ++i) {
                originArr[i] = rand.nextInt(100);
            }
            
            for (int i = 1; i <= 4; ++i)
            {
                long sum = 0;
                
                for (int j = 0; j < testCnt; ++j) {
                    Integer[] arr = Arrays.copyOf(originArr, originArr.length);
                    ConcurrentMergeSort sorter = new ConcurrentMergeSort(i);
                    
                    long st = System.currentTimeMillis();
                    sorter.sort(arr);
                    long en = System.currentTimeMillis();
                    sum += en - st;
                }
                System.out.println("Size = 1e" + exp + "; Parallel level = " + i + "; AVG Sort time = " + (sum / testCnt) + "ms");
            }
            System.out.println();
        }
    }
}
