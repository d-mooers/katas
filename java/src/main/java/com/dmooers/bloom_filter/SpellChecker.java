package com.dmooers.bloom_filter;

import java.util.function.Predicate;

public class SpellChecker {
    private final Predicate<String> checker;

    public SpellChecker(Predicate<String> checker) {
        this.checker = checker;
    }

    public boolean check(String s) {
        return checker.test(s);
    }
}
