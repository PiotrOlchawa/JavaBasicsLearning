package org.javaprojects.spring.project.functionalinterface;

import org.apache.el.stream.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class FunctionalProg {

    public static void main(String args[]) {
        Try tryy = new Try(1, 2);
        System.out.println(tryy.tryToOperate( (a, b) -> (a + b) )) ;
    }
}


class Try {

    int a;
    int b;

    public Try(int a, int b) {
        this.a = a;
        this.b = b;
    }

    int tryToOperate(Operation operation) {
        return operation.operate(a, b);
    }

}


interface Operation {

    int operate(int a, int b);
}