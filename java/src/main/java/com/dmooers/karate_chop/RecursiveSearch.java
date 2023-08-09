package com.dmooers.karate_chop;

class RecursiveSearch implements SearchStrategy{
    private int _search(int l, int r, int elem, int[] elements) {
        if (l > r) return -1;

        int middle = r - (r - l) / 2;
        int comparison = Integer.compare(elem, elements[middle]);

        // Case 2 - element is less than middle
        if (comparison < 0) return _search(l, middle - 1, elem, elements);

        // Case 2 - element is greater than middle
        if (comparison > 0) return _search(middle + 1, r, elem, elements);

        return middle;
    }
    public int search(
            int elem, int[] elements
    ) {
        return _search(0, elements.length - 1, elem, elements);
    }

    @Override
    public StrategyName getName() {
        return StrategyName.RECURSIVE;
    }
}
