package com.github.mrrobust;

import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(9,8,7,6,5,0,4,3,2,1));
        System.out.println(A);
        QuickSort.sort(A, 0, A.size() - 1);
        System.out.println(A);
    }
}
