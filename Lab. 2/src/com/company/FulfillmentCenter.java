package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FulfillmentCenter {
    public String name;
    private List<Item> items = new ArrayList<>();
    private double massLimit;

    public FulfillmentCenter(String name, double massLimit) {
        this.name = name;
        this.massLimit = massLimit;
    }

    public void addProduct(Item item) {
        if ((currentMass() + item.mass * item.amount) <= massLimit) {
            boolean bool = true;
            for (Item next : items) {
                if (next.compareTo(item)==0) {
                    bool = false;
                    next.amount += item.amount;
                    break;
                }
            }
            if (bool) {
                items.add(item);
            }
        }
        else {
            System.out.println("Mass overload!");
        }
    }

    public Item getProduct(Item item) {
        Item taken = new Item(search(item.name));
        if(taken == null) {
            return null;
        }
        else {
            Item temp = search(item.name);
            temp.amount--;
            if (temp.amount == 0) {
                items.remove(temp);
            }
            taken.amount = 1;
            return taken;
        }
    }

    public void removeProduct(Item item) {
        items.remove(search(item.name));
    }

    public Item search (String name) {
        for (Item next : items) {
            if(next.compareTo(name)==0) {
                return next;
            }
        }
        System.out.println("\nSearch error!\nNo product was found with the given name: " + name);
        return null;
    }

    public List<Item> searchPartial(String name){
        List<Item> temp = new ArrayList<>();
        for (Item next : items) {
            if(next.name.contains(name)) {
                temp.add(next);
            }
        }
        return temp;
    }

    public int countByCondition(ItemCondition condition) {
        int temp = 0;
        for(Item next : items){
            if(next.condition.equals(condition)){
                temp+=next.amount;
            }
        }
        return temp;
    }

    public void summary() {
        if(isEmpty()) {
            System.out.println("Center is empty..");
        }
        else {
            for (Item next : items) {
                System.out.print(items.indexOf(next)+1+". ");
                next.print();
            }
        }
    }

    public List<Item> sortByName(){
        List<Item> temp = new ArrayList<>(items);
        Collections.sort(temp);
        return temp;
    }

    public List<Item> sortByAmount() {
        List<Item> temp = new ArrayList<>(items);
        Collections.sort(temp, new ComparatorAmount());
        Collections.reverse(temp);
        return temp;
    }

    public Item max(){
        Item temp = Collections.max(items, new ComparatorAmount());
        return temp;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public double fulfillment() {
        return currentMass()/massLimit;
    }

    private double currentMass() {
        double sum = 0;
        for (Item next : items) {
            sum += (next.mass * next.amount);
        }
        return sum;
    }
}