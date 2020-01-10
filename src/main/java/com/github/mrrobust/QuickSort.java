package com.github.mrrobust;

import java.util.*;

public class QuickSort
{
    private static int partition(ArrayList<Integer> A, int lo, int hi)
    {
        Integer pivot = A.get((lo + hi) / 2);
        --lo;
        ++hi;

        for (;;)
        {
            do {
                ++lo;
            }
            while (A.get(lo) < pivot);
            do {
                --hi;
            }
            while (A.get(hi) > pivot);
            if (lo >= hi)
                return hi;
            Collections.swap(A, lo, hi);
        }

    }

    public static void sort(ArrayList<Integer> A, int lo, int hi)
    {
        if (lo < hi)
        {
            int pivot = partition(A, lo, hi);
            sort(A, lo, pivot);
            sort(A, pivot + 1, hi);
        }
    }
}
