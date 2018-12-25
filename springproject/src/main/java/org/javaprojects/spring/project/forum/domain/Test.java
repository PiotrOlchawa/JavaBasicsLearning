package org.javaprojects.spring.project.forum.domain;

import com.fasterxml.jackson.annotation.JsonFilter;

public class Test {

    private Integer a = 1;
    private Integer b = 2;

    public Test() {
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }
}
