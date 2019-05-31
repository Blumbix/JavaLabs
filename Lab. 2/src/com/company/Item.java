package com.company;

public class Item implements Comparable<Item> {
    public String name;
    public ItemCondition condition;
    public double mass;
    public int amount;

    public Item(String name, ItemCondition condition, double mass, int amount) {
        this.name = name;
        this.condition = condition;
        this.mass = mass;
        this.amount = amount;
    }

    public Item(Item item) {
        this.name = item.name;
        this.condition = item.condition;
        this.mass = item.mass;
        this.amount = item.amount;
    }

    public void print(){
        System.out.println("Item specifications:\nName: "+name+"\nCondition: "+condition+
                "\nMass: "+mass+"\nAmount: "+amount);
    }

    @Override
    public int compareTo(Item i){
        if(name.compareTo(i.name)==0) {
            return condition.compareTo(i.condition);
        }
        else {
            return name.compareTo(i.name);
        }
    }

    public int compareTo(String s){
        return name.compareTo(s);
    }
}
