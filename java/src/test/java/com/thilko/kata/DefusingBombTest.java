package com.thilko.kata;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/5e4mde/20161121_challenge_293_easy_defusing_the_bomb/
 * To disarm the bomb you have to cut some wires. These wires are either white, black, purple, red, green or orange.
 * <p>
 * The rules for disarming are simple:
 * <p>
 * If you cut a white cable you can't cut white or black cable.
 * If you cut a red cable you have to cut a green one
 * If you cut a black cable it is not allowed to cut a white, green or orange one
 * If you cut a orange cable you should cut a red or black one
 * If you cut a green one you have to cut a orange or white one
 * If you cut a purple cable you can't cut a purple, green, orange or white cable
 * <p>
 * If you have anything wrong in the wrong order, the bomb will explode.
 * <p>
 * There can be multiple wires with the same colour and these instructions are for one wire at a time. Once you cut a wire you can forget about the previous ones.
 */
public class DefusingBombTest {


    @Test
    public void noWires_bombDefused() {
        assertThat(defuse(Collections.emptyList()), Is.is(true));
    }

    @Test
    public void ifYouCutAWhiteCableYouCantCutWhiteOrBlackCable() {
        assertThat(defuse(Arrays.asList(Wire.WHITE, Wire.WHITE)), Is.is(false));
        assertThat(defuse(Arrays.asList(Wire.WHITE, Wire.BLACK)), Is.is(false));
        assertThat(defuse(Arrays.asList(Wire.WHITE, Wire.GREEN)), Is.is(true));
    }

    @Test
    public void ifYouCutARedCableYouHaveToCutAGreenOne() {
        assertThat(defuse(Arrays.asList(Wire.RED, Wire.GREEN)), Is.is(true));
        assertThat(defuse(Arrays.asList(Wire.RED, Wire.WHITE)), Is.is(false));
        assertThat(defuse(Collections.singletonList(Wire.RED)), Is.is(false));
    }

    private boolean defuse(final List<Wire> wires) {
        BombState state = new StartState();
        for (Wire wire : wires) {
            if (state.cutCable(wire)) {
                return false;
            }

            if (wire.equals(Wire.RED)) {
                state = new RedCutThrough();
            } else {
                state = new WhiteCutThrough();
            }
        }

        return state.end();
    }

    public enum Wire {BLACK, GREEN, RED, WHITE}

    private interface BombState {
        boolean cutCable(Wire wire);

        boolean end();
    }

    private class WhiteCutThrough implements BombState {
        @Override
        public boolean cutCable(final Wire wire) {
            return wire.equals(Wire.WHITE) || wire.equals(Wire.BLACK);
        }

        @Override
        public boolean end() {
            return true;
        }
    }

    private class StartState implements BombState {
        @Override
        public boolean cutCable(Wire wire) {
            return false;
        }

        @Override
        public boolean end() {
            return true;
        }
    }

    private class RedCutThrough implements BombState {
        @Override
        public boolean cutCable(Wire wire) {
            return !wire.equals(Wire.GREEN);
        }

        @Override
        public boolean end() {
            return false;
        }
    }
}
