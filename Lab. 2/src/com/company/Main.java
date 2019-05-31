package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {

    	FulfillmentCenterContainer poland = new FulfillmentCenterContainer();
    	poland.addCenter("Warsaw", 8000);
		poland.addCenter("Krakow", 6000);
		poland.addCenter("Rzeszow", 5000);

        Item motorbikes = new Item("Motorbike", ItemCondition.NEW, 210, 8);
		Item cars = new Item("Car", ItemCondition.USED, 1430, 3);
		Item bikes = new Item("Bike", ItemCondition.REFURBISHED, 16, 35);
		poland.container.get("Warsaw").addProduct(cars);
		poland.container.get("Warsaw").addProduct(motorbikes);
		poland.container.get("Warsaw").addProduct(bikes);
		poland.container.get("Krakow").addProduct(motorbikes);
		poland.container.get("Krakow").addProduct(bikes);
		poland.summary();

		List<FulfillmentCenter> temp;
		temp = poland.findEmpty();
		System.out.println("\nNames of empty centers: ");
		for(FulfillmentCenter next : temp) {
			System.out.print(next.name + ", ");
		}

		System.out.println("\n");
		poland.removeCenter("Rzeszow");
		poland.summary();

		System.out.println("\nItems in Warsaw: ");
		poland.container.get("Warsaw").summary();


		List<Item> sortedByName = poland.container.get("Warsaw").sortByName();
		System.out.println("\nItems in Warsaw sorted by Name: ");
		for(Item next : sortedByName){
			System.out.print(sortedByName.indexOf(next)+1+". ");
			next.print();
		}

		List<Item> sortedByAmount = poland.container.get("Warsaw").sortByAmount();
		System.out.println("\nItems in Warsaw sorted by amount: ");
		for(Item next : sortedByAmount){
			System.out.print(sortedByAmount.indexOf(next)+1+". ");
			next.print();
		}

		System.out.println("\nItems in Krakow: ");
		poland.container.get("Krakow").summary();
		poland.container.get("Krakow").addProduct(motorbikes);
		System.out.println("\nAdded another motorbikes to Krakow center.\nAmount of new items in Krakow: " +
				poland.container.get("Krakow").countByCondition(ItemCondition.NEW));

		List<Item> ikes = poland.container.get("Krakow").searchPartial("ike");
		System.out.println("\nItems in Krakow with -ike- in names: ");
		for(Item next : ikes){
			System.out.print(ikes.indexOf(next)+1+". ");
			next.print();
		}

		Item maxAmount = poland.container.get("Krakow").max();
		System.out.println("\nItem in Krakow in the largest amount: " + maxAmount.name);
    }
}
