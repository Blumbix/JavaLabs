package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class FulfillmentCenterContainer {
    public Map<String, FulfillmentCenter> container = new TreeMap<>();

    public void addCenter(String name, double massLimit){
        FulfillmentCenter temp = new FulfillmentCenter(name, massLimit);
        container.put(name, temp);
    }

    public void removeCenter(String name) {
        container.remove(name);
    }

    public List<FulfillmentCenter> findEmpty() {
        List<FulfillmentCenter> temp = new ArrayList<>();
        List<FulfillmentCenter> list = new ArrayList<>(container.values());
        for(FulfillmentCenter next : list){
            if(next.isEmpty()) {
                temp.add(next);
            }
        }
        return temp;
    }

    public void summary(){
        System.out.println("Centers summary: ");
        Set<Entry<String,FulfillmentCenter>> entrySet = container.entrySet();
        for(Entry<String, FulfillmentCenter> entry: entrySet) {
            System.out.format(entry.getKey() + " : %.3f", entry.getValue().fulfillment()*100);
            System.out.println("%");
        }
    }
}
