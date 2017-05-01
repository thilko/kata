package com.thilko.gameoflife;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.core.Is;
import org.junit.Test;

public class StandardRulesTest {
    StandardRules rules = new StandardRules();

    @Test
    public void aDeadCellWithThreeLivingNeighborsWillBeBorn(){
        assertThat(rules.aliveInNextGeneration(Cell.deadAt(1,1), 3), Is.is(true));
    }
    
    @Test
    public void aDeadCellWithMoreThanThreeLivingNeighborsWillNotBeBorn(){
        assertThat(rules.aliveInNextGeneration(Cell.deadAt(1,1), 4), Is.is(false));
    }

    @Test
    public void aDeadCellWithtwoLivingNeighborsWillNotBeBorn(){
        assertThat(rules.aliveInNextGeneration(Cell.deadAt(1,1), 2), Is.is(false));
    }

    @Test
    public void aLivingCellWithMoreThanThreeLivingNeighborsWillDie(){
        assertThat(rules.aliveInNextGeneration(Cell.livingAt(1,1), 4), Is.is(false));
    }

    @Test
    public void aLivingCellWithLessThanTwoLivingNeighborsWillDie(){
        assertThat(rules.aliveInNextGeneration(Cell.livingAt(1,1), 1), Is.is(false));
    }
}
