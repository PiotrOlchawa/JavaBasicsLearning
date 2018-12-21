package org.javaprojects.spring.project.stream;


import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingBy {

    public static void main(String args[]) {

        Map<String, String> map = Arrays.asList("a", "b", "c")
                .stream()
                .map(Function.identity())
                .map(str -> str)
                .collect(Collectors.toMap(
                        Function.identity(),
                        str -> str));

        // Sortowanie listy Stringów
        List<String> result = Arrays.asList("a", "s", "h", "g").stream().sorted((o1, o2) -> o1.
                compareTo(o2)).
                collect(Collectors.toList());
        //Lub
        List<String> result2 = Stream.of("a", "s", "h", "g").sorted(Comparator.naturalOrder()).
                collect(Collectors.toList());
        // Grupowanie stringów po ilosci wystąpień i sortowanie w odwrotnej kolejnosci do ilosci wystapien

        Map<String, Long> result1 =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya").stream().collect(
                        Collectors.groupingBy(t -> t,/* same as Function.identity*/ Collectors.counting()));

        Map<String, Long> finalMap = new LinkedHashMap<>();

        result1.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue()
                .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));


        // grupowanie po ilosci wystapien name - obiekty
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );

        Map<String, Long> counting = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.counting()));

        System.out.println(counting);

        /*Group by + Count
        {
	    papaya=1, banana=2, apple=3, orang=1, watermelon=1
        }*/


        // grupowanie obiekow po ilosci qty
        Map<String, Integer> sum = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

        System.out.println(sum);

        /*
        //Group by + Sum qty
        {
	    papaya=20, banana=30, apple=40, orang=10, watermelon=10
        }*/


        //group by price
        Map<BigDecimal, List<Item>> groupByPriceMap =
                items.stream().collect(Collectors.groupingBy(Item::getPrice));

        System.out.println(groupByPriceMap);

        /*
        {
	    19.99=[
		    	Item{name='banana', qty=20, price=19.99},
			    Item{name='banana', qty=10, price=19.99}
		    ],
	    29.99=[
		    	Item{name='orang', qty=10, price=29.99},
			    Item{name='watermelon', qty=10, price=29.99}
		    ],
	    9.99=[
		    	Item{name='apple', qty=10, price=9.99},
			    Item{name='papaya', qty=20, price=9.99},
			    Item{name='apple', qty=10, price=9.99},
			    Item{name='apple', qty=20, price=9.99}
		    ]
        }*/


        // group by price, uses 'mapping' to convert List<Item> to Set<String>
        Map<BigDecimal, Set<String>> result3 =
                items.stream().collect(
                        Collectors.groupingBy(Item::getPrice,
                                Collectors.mapping(Item::getName, Collectors.toSet())
                        )
                );

        System.out.println(result3);

        /*
        //group by + mapping to Set
        {
	    19.99=[banana],
	    29.99=[orang, watermelon],
	    9.99=[papaya, apple]
        }
        */

        // grupuje po nazwie(Item::getName) z listą jakie ilosci(Item::getQty) posiada nazwa
        // ile pozycji w sztukach danego produktu posiadam ?
        Map<String,List<Integer>> groupingByName = items.stream()
                .collect(Collectors.groupingBy(Item::getName,Collectors.mapping(Item::getQty,Collectors.toList())));

        System.out.println("////////////////////////////////////////////////");
        System.out.println(groupingByName);
        System.out.println("////////////////////////////////////////////////");

        // jakich pozycji mam 10 sztuk ?
        Map<Integer,List<String>> pieces = items.stream().filter(l->l.getQty()==10).collect(Collectors.groupingBy(Item::getQty
                ,Collectors.mapping(Item::getName,Collectors.toList())));
        System.out.println("////////////////////////////////////////////////");
        System.out.println(pieces);
        System.out.println("////////////////////////////////////////////////");
    }


}


class Item {

    private String name;
    private int qty;
    private BigDecimal price;

    public Item(String name, int qty, BigDecimal price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    //constructors, getter/setters
}
