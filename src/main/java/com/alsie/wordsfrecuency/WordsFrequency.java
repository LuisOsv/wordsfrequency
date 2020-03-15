package com.alsie.wordsfrecuency;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@SuppressWarnings("ALL")
public class WordsFrequency {
    public static final String WHITE_SPACES = "\\s";
    public static final int ZERO_CHARACTERS = 0;
    public static final String NON_LETTER = "[^a-zA-Z]";
    public static final String EMPTY_STRING = "";
    public static final int ONE_INCIDENCE = 1;

    public static void main(String[] args) throws IOException {
        try{
            Path path = Paths.get(args[0]);

            Map<String, Long> wordCount = Files.lines(path)

                    .flatMap(line -> Arrays.stream(line.trim().split(WHITE_SPACES)))

                    .map(word -> word.replaceAll(NON_LETTER, EMPTY_STRING).toLowerCase().trim())

                    .filter(word -> word.length() > ZERO_CHARACTERS)

                    .map(word -> new AbstractMap.SimpleEntry<>(word, ONE_INCIDENCE))

                    .collect(groupingBy(AbstractMap.SimpleEntry::getKey, counting()));

            wordCount.forEach((k, v) ->
                    System.out.println(String.format("word:'%s' = %d (incidences)", k, v)));

        } catch (NoSuchFileException exception) {
            System.out.println("File was not found");
        }
        catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Please provide");
        }
    }
}
