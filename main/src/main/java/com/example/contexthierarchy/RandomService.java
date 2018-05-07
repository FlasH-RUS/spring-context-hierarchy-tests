package com.example.contexthierarchy;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomService {

    public int getDiceRollScore(final int diceFaces) {
        return new Random().nextInt(diceFaces + 1) + 1;
    }
}
