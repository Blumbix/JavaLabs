package com.company;

import java.util.Comparator;

public class ComparatorAmount implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        int amount =  o1.amount - o2.amount;
        if(amount == 0) {
            return o1.compareTo(o2);
        }
        return amount;
    }
}
