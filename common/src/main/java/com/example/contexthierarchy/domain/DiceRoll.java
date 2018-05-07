package com.example.contexthierarchy.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@javax.persistence.Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiceRoll {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "SCORE")
    private int score;

}
