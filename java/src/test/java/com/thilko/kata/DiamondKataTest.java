package com.thilko.kata;

import static java.lang.String.join;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DiamondKataTest {


    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Test
    public void whitespacesAfterLetter() {
        assertThat(whitespacesAfterLetter("A"), is(asList("")));
        assertThat(whitespacesAfterLetter("B"), is(asList("", " ")));
        assertThat(whitespacesAfterLetter("C"), is(asList("", " ", "  ")));
    }

    @Test
    public void whitespacesBeforeLetter() {
        assertThat(whitespacesBeforeLetter("A"), is(asList("")));
        assertThat(whitespacesBeforeLetter("B"), is(asList(" ", "")));
        assertThat(whitespacesBeforeLetter("C"), is(asList("  ", " ", "")));
    }

    @Test
    public void letters() {
        assertThat(letters("A"), is(asList("A")));
        assertThat(letters("B"), is(asList("A", "B")));
        assertThat(letters("C"), is(asList("A", "B", "C")));
    }

    @Test
    public void upperLeftQuadrant() {
        assertThat(upperLeftQuadrant("A"), is(asList("A")));
        assertThat(upperLeftQuadrant("B"), is(asList(" A", "B ")));
        assertThat(upperLeftQuadrant("C"), is(asList("  A", " B ", "C  ")));
    }

    @Test
    public void mirrorFromLeftToRight() {
        assertThat(mirrorFromLeftToRight(upperLeftQuadrant("A")), is(asList("A")));
        assertThat(mirrorFromLeftToRight(upperLeftQuadrant("B")), is(asList(" A ", "B B")));
        assertThat(mirrorFromLeftToRight(upperLeftQuadrant("C")), is(asList("  A  ", " B B ", "C   C")));
    }

    @Test
    public void mirrorFromTopToBottom() {
        assertThat(mirrorFromTopToBottom(upperLeftQuadrant("A")), is(asList("A")));
        assertThat(mirrorFromTopToBottom(upperLeftQuadrant("B")), is(asList(" A", "B ", " A")));
        assertThat(mirrorFromTopToBottom(upperLeftQuadrant("C")), is(asList("  A", " B ", "C  ", " B ", "  A")));
    }

    @Test
    public void printDiamond() {
        assertThat(printDiamond("A"), is("A\n"));
        assertThat(printDiamond("B"), is(" A \nB B\n A \n"));
        assertThat(printDiamond("C"), is("  A  \n B B \nC   C\n B B \n  A  \n"));
    }

    private String printDiamond(String letter) {
        List<String> diamond = mirrorFromTopToBottom(mirrorFromLeftToRight(upperLeftQuadrant(letter)));
        StringBuilder result = new StringBuilder();
        for (String line : diamond) {
            result.append(line + "\n");
        }
        return result.toString();
    }

    private List<String> mirrorFromTopToBottom(List<String> lines) {
        ArrayList<String> mirrored = new ArrayList<>(lines);
        Collections.reverse(mirrored);
        mirrored.remove(0);

        lines.addAll(mirrored);

        return lines;
    }

    private List<String> mirrorFromLeftToRight(List<String> lines) {
        ArrayList<String> mirrored = new ArrayList<>();
        for (String line : lines) {
            mirrored.add(line + new StringBuilder(line).reverse().deleteCharAt(0));
        }
        return mirrored;
    }


    private List<String> upperLeftQuadrant(String letter) {
        List<String> whitespacesBeforeLetter = whitespacesBeforeLetter(letter);
        List<String> whitespacesAfterLetter = whitespacesAfterLetter(letter);
        List<String> letters = letters(letter);

        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i <= numberForLetter(letter); i++) {
            lines.add(whitespacesBeforeLetter.get(i) + letters.get(i) + whitespacesAfterLetter.get(i));
        }
        return lines;
    }

    private List<String> letters(String letter) {
        String substring = LETTERS.substring(0, numberForLetter(letter) + 1);
        return Arrays.asList(substring.split(""));
    }

    private List<String> whitespacesBeforeLetter(String letter) {
        List<String> lines = whitespacesAfterLetter(letter);
        Collections.reverse(lines);

        return lines;
    }

    private List<String> whitespacesAfterLetter(String letter) {
        ArrayList<String> lines = new ArrayList<>();
        int numberForLetter = numberForLetter(letter);
        for (int i = 0; i <= numberForLetter; i++) {
            lines.add(createWhitespaces(i));
        }
        return lines;
    }

    private int numberForLetter(String letter) {
        return LETTERS.indexOf(letter);
    }

    private String createWhitespaces(int i) {
        return join("", Collections.nCopies(i, " "));
    }

}
