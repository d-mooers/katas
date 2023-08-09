package com.dmooers.data_munging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

public class DatReader {

    final private String regex;
    final private boolean skipFirstLine = true;

    public DatReader(String regex) {
        this.regex = regex;
    }

    public DatReader() {
        this.regex = "\\s+";
    }

    public List<List<String>> read(File file) {
        List<List<String>> contents = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            if (skipFirstLine) sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                contents.add(Arrays.asList(line.split(regex)));
            }
        } catch (FileNotFoundException err) {
            // fall through
        }
        return contents;
    }

    public <E> List<E> read(File file, Function<String[], Optional<E>> parser) {
        List<E> contents = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            if (skipFirstLine) sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                if (line.isEmpty()) continue;

                String[] tokens = line.split(regex);
                parser.apply(tokens).ifPresent(contents::add);

            }
        } catch (FileNotFoundException err) {
            // fall through
        }
        return contents;
    }
}
