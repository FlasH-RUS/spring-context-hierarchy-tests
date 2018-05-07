package com.example.contexthierarchy;

import java.util.List;

import com.example.contexthierarchy.domain.DiceRoll;
import com.example.contexthierarchy.domain.DiceRollRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class DiceRollServiceIntegrationTest {

    @MockBean
    private RandomService randomService;

    @Autowired
    public DiceRollRepository diceRollRepository;

    @Autowired
    public DiceRollService diceRollService;

    @Before
    public void setUp() {
        diceRollRepository.deleteAll();
    }

    @Test
    public void shouldSaveDiceRoll() {
        // given
        when(randomService.getDiceRollScore(anyInt())).thenReturn(1);

        // when
        diceRollService.roll();
        final List<DiceRoll> rolls = diceRollRepository.findAll();

        // then
        assertThat(rolls, contains(hasProperty("score", equalTo(1))));
    }

    @Test
    public void shouldReturnZeroWhenNoRolls() {
        // given
        diceRollRepository.deleteAll();

        // when
        final double distribution = diceRollService.getDistribution(1);

        // then
        assertThat(distribution, is(0.0));
    }

}
