package com.dmooers.bloom_filter;

import java.util.BitSet;
import java.util.function.Function;

public class BloomFilter<E> {
    private final Function<E, int[]> hashProvider;
    private final BitSet memberList;

    public BloomFilter(int size, Function<E, int[]> hashProvider) {
        this.memberList = new BitSet(size);
        this.hashProvider = hashProvider;
    }

    public BloomFilter(int size) {
        this.memberList = new BitSet(size);
        this.hashProvider = (e) -> new int[]{e.hashCode()};
    }

    public void add(E e) {
        int[] hashes = this.hashProvider.apply(e);
        int bitMapLength = this.memberList.size();
        for (int hash : hashes) {
            if (hash < 1) hash = Math.abs(hash);
            if (hash > bitMapLength) this.memberList.set(hash % bitMapLength);
            else this.memberList.set(hash);
        }
    }

    public <C extends E> boolean mightContain(C o) {
        if (o == null) return false;
        int[] hashes = this.hashProvider.apply(o);
        int bitMapLength = this.memberList.size();

        for (int hash : hashes) {
            if (hash < 1) hash = Math.abs(hash);
            int moduloOrHash = hash > bitMapLength ? hash % bitMapLength : hash;

            if (!memberList.get(moduloOrHash)) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BloomFilter with size: " + memberList.size();
    }
}
