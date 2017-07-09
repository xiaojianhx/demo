package com.xiaojianhx.demo.grammar;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Test01 {

    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> ((String) str).startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> ((String) str).endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> ((String) str).length() > 4);

        Collections.sort(languages, (a, b) -> a.length() - b.length());
        System.out.println(languages);
    }

    private static void filter(List names, Predicate condition) {
        // names.stream().filter((name) ->
        // (condition.test(name))).forEach((name) -> {
        // System.out.println(name + " ");
        // });
        names.stream().filter(condition::test).forEach(System.out::print);
    }
}
