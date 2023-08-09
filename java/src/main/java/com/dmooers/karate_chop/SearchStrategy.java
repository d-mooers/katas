package com.dmooers.karate_chop;

interface SearchStrategy {

    enum StrategyName {
        RECURSIVE
    }
    int search(int element, int[] nums);

    StrategyName getName();
}
