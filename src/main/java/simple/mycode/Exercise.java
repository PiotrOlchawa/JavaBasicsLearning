package com.in28minutes.java.mycode;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Exercise {

    public static void main(String[] args ){

        List<String> lista = Arrays.asList(new String("A"),new String("B"));
        String[] tablica = {"A","B"};
        List<String> lista1 = Arrays.asList(tablica);

        List<String> lista2 = new ArrayList<>(Arrays.asList(tablica));

        List<String> lista3 = new ArrayList<>(Arrays.asList("A","B"));

        String[] tablica1 = lista1.toArray(new String[0]);

        HashMap<String,String> stringStringHashMap =new HashMap<>();


        stringStringHashMap.put("jeden","jeden");
        stringStringHashMap.put("dwa","dwa");


        for (Map.Entry<String,String> stringStringEntry : stringStringHashMap.entrySet()){
            System.out.println(stringStringEntry.getKey());
        }

        IntStream.range(1,10).mapToObj(l->new Integer(l)).forEach(l->System.out.println(l));

        Person person = new Person("x","x");

        IntStream.range(0,10)
                .mapToObj(l->new Person(String.valueOf(l),String.valueOf(l)))
                .collect(Collectors.toMap(Person::getFirstName,Person::getLastName))
                .forEach((l,v)-> System.out.println(l + v));

    }



    static class Person{

        private String firstName;
        private String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

}
