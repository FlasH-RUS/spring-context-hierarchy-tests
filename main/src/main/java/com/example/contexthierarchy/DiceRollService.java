package com.example.contexthierarchy;

import com.example.contexthierarchy.domain.DiceRoll;
import com.example.contexthierarchy.domain.DiceRollRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiceRollService {

    private final DiceRollRepository diceRollRepository;
    private final RandomService randomService;

    @Value("${dice.faces}")
    private Integer diceFaces;

    public void roll() {
        final DiceRoll roll = DiceRoll.builder().score(randomService.getDiceRollScore(diceFaces)).build();
        diceRollRepository.save(roll);
    }

    public double getDistribution(final int score) {
        final long rollCount = diceRollRepository.count();
        if (rollCount == 0) {
            return 0;
        }

        final DiceRoll exampleDiceRoll = DiceRoll.builder().score(score).build();
        final long scoreCount = diceRollRepository.count(
                Example.of(exampleDiceRoll, ExampleMatcher.matching().withMatcher("score", GenericPropertyMatchers.exact())));

        return ((double) scoreCount) / rollCount;
    }

}
