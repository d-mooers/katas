package com.dmooers.karate_chop;


import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class KarateChopTest {
    static class SearchStrategyTestArgs {
        private final SearchStrategy strategy;
        private final int[] elements;
        private final int element;

        SearchStrategyTestArgs(SearchStrategy _strategy, int[] _elements, int _element) {
            this.strategy = _strategy;
            this.elements = _elements.clone();
            this.element = _element;
        }

        int getExpected() {
            int v = Arrays.binarySearch(elements, element);
            if (v < 0) return -1;

            return v;
        }

        int getActual() {
            return this.strategy.search(element, elements);
        }

        SearchStrategy.StrategyName getStrategyName() {
            return strategy.getName();
        }
    }

    static class SearchStrategyArgumentsProvider implements ArgumentsProvider {

        SearchStrategy[] strategies = new SearchStrategy[]{ new RecursiveSearch() };

        int[][] searchLists = new int[][] {
                {-1, 0, 1, 2, 3, 4, 5},
                {},
                {1, 2, 3, 4, 5},
                {-10, -5, -3},
                {1},
                IntStream.range(-1000, 1000).toArray()
        };
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            ArrayList<SearchStrategyTestArgs> testCases = new ArrayList<>();

            for (SearchStrategy strat : strategies) {
                for (int[] list : searchLists) {
                    int upperBoundIndex = list.length;

                    if (upperBoundIndex == 0) testCases.add(new SearchStrategyTestArgs(strat, list, 1));
                    else {
                        testCases.add(new SearchStrategyTestArgs(strat, list, list[0] - 1));
                        testCases.add(new SearchStrategyTestArgs(strat, list, list[upperBoundIndex - 1] + 1));

                        for (int n : list) testCases.add(new SearchStrategyTestArgs(strat, list, n));
                    }
                }
            }
            return Stream.of(testCases.toArray()).map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(SearchStrategyArgumentsProvider.class)
    public void shouldFindElement(SearchStrategyTestArgs test) {
        assertThat(test.getActual()).as(test.getStrategyName().toString()).isEqualTo(test.getExpected());
    }
}
