package com.github.mrrobust;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @BeforeAll
    static void Hello()
    {
        System.out.println("@BeforeAll Fixture");
    } // Just testing

    /**
     * Sort random ordered numbers.
     * result: Array sorted from low to high
     */
    @Test
    void RandomOrder() {
        ArrayList<Integer> sorted =   new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        ArrayList<Integer> unsorted = new ArrayList<>(Arrays.asList(9,5,7,0,2,8,4,3,6,1));

        QuickSort.sort(unsorted, 0, unsorted.size() - 1);

        assertIterableEquals(unsorted, sorted);
    }

    /**
     * Sort reverse ordered numbers.
     * result: Array sorted from low to high
     */
    @Test
    void ReverseOrder() {
        ArrayList<Integer> sorted =   new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        ArrayList<Integer> unsorted = new ArrayList<>(Arrays.asList(9,8,7,6,5,4,3,2,1,0));

        QuickSort.sort(unsorted, 0, unsorted.size() - 1);

        assertIterableEquals(unsorted, sorted);
    }

    /**
     * Sort already sorted array.
     * result: Array stays in sorted condition
     */
    @Test
    void SortedAlready() {
        ArrayList<Integer> sorted =   new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        ArrayList<Integer> unsorted = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));

        QuickSort.sort(unsorted, 0, unsorted.size() - 1);

        assertIterableEquals(unsorted, sorted);
    }

    /**
     * Sort array of identical numbers.
     * result: Array filled with same numbers
     */
    @Test
    void AllSame() {
        ArrayList<Integer> sorted =   new ArrayList<>(Arrays.asList(-1,-1,-1,-1,-1,-1,-1,-1,-1));
        ArrayList<Integer> unsorted = new ArrayList<>(Arrays.asList(-1,-1,-1,-1,-1,-1,-1,-1,-1));

        QuickSort.sort(unsorted, 0, unsorted.size() - 1);

        assertIterableEquals(unsorted, sorted);
    }

    /**
     * Call sort for empty array.
     * result: Array stays empty, and no index violation errors
     */
    @Test
    void Empty() {
        ArrayList<Integer> sorted =   new ArrayList<>();
        ArrayList<Integer> unsorted = new ArrayList<>();

        QuickSort.sort(unsorted, 0, unsorted.size() - 1);

        assertIterableEquals(unsorted, sorted);
    }

    /**
     * Call divide phase of sort algorithm.
     * result: Array numbers are organized so that the numbers on the left side
     *         are less than the numbers on the right side
     */
    @Test
    void RotateAroundPivot() {
        ArrayList<Integer> unsorted = new ArrayList<>(Arrays.asList(4,5,0,5,1,2,6));
        int pivot = -1;

        try {
            Method partition = QuickSort.class
                    .getDeclaredMethod("partition", ArrayList.class, int.class, int.class);
            partition.setAccessible(true);
            pivot = (int)partition.invoke(null, unsorted, 0, unsorted.size() - 1);

        } catch (Exception e) {
            fail("Reflection failed: " + e);
        }

        int max_left = unsorted.get(0);
        int min_right = unsorted.get(unsorted.size() - 1);
        for (int i = 1; i < pivot; ++i) {
            Integer n = unsorted.get(i);
            max_left = max_left < n ? n : max_left;
        }
        for (int i = unsorted.size() - 2; i > pivot; --i) {
            Integer n = unsorted.get(i);
            min_right = min_right > n ? n : min_right;
        }
        assertTrue(max_left <= min_right,
                "max from left: " + max_left + ", min from right: " + min_right);
    }

}
