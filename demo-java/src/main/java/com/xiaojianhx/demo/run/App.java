package com.xiaojianhx.demo.run;

public class App {

    public static void main(String[] args) {

        F f = new F();
        f = new S();
        f.getClass();
    }
}

class F {

    static {
        System.out.println("F static block");
    }

    {
        System.out.println("F block");
    }

    public F() {
        System.out.println("F construtor");
    }
}

class S extends F {

    static {
        System.out.println("S static block");
    }

    {
        System.out.println("S block");
    }

    public S() {
        System.out.println("S construtor");
    }
}