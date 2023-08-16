package com.dmooers.bloom_filter;

import java.io.File;
import java.util.Scanner;

public class App {

    private final static String QUIT_TOKEN = "<Q>";
    private static SpellChecker makeChecker(String wordListPath, int filterSize) throws Exception {
        BloomFilter<String> filter = new BloomFilter<>(filterSize, s -> {
            int base = s.hashCode();

            return new int[]{
                    base, base / 2, base / 3, base / 4, base / 5
            };
        });
        try (Scanner sc = new Scanner(new File(wordListPath))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                filter.add(line);
            }
        }
        return new SpellChecker(filter::mightContain);
    }
    public static void main(String[] args) throws Exception {
        SpellChecker checker;
        if (args.length == 2) checker = makeChecker(args[0], Integer.parseInt(args[1]));
        else if (args.length == 1) checker = makeChecker(args[0], 1 << 16);
        else throw new RuntimeException("Bad args");

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Type a string to check against the bloom filter.  Type <Q> to exit");
             while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.equals(QUIT_TOKEN)) break;

                if (checker.check(line)) System.out.println("Valid! ✅\n");
                else System.out.println("Invalid! ❌\n");

                System.out.println("Type a string to check against the bloom filter.  Type <Q> to exit");
            }
            System.out.println("Goodbye!");
        }
    }
}
