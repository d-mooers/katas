package com.dmooers.bloom_filter;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
public class BloomFilterTest {

    @Test
    public void shouldAddAnElement() {
        BloomFilter<Integer> filter = new BloomFilter<>(5, (n) -> new int[]{n % 5});
        filter.add(2);

        boolean contained = filter.mightContain(2);

        assertThat(contained).isTrue();
    }

    @Test
    public void shouldReturnTrueForConflictingHash() {
        BloomFilter<Integer> filter = new BloomFilter<>(5, (n) -> new int[]{n % 5});
        filter.add(2);

        boolean conflicted = filter.mightContain(7);
        assertThat(conflicted).isTrue();
    }

    @Test
    public void shouldReturnFalseIfNotPresent() {
        BloomFilter<Integer> filter = new BloomFilter<>(5, (n) -> new int[]{n % 5});
        filter.add(1);
        assertThat(filter.mightContain(2)).isFalse();
    }

    @Test
    public void shouldAddString() {
        BloomFilter<String> filter = new BloomFilter<>(1 << 16);
        String s1 = "hello";
        String s2 = "hello";
        filter.add(s1);

        assertThat(filter.mightContain(s2)).isTrue();
    }
}
