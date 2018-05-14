package com.example.contexthierarchy.domain;

import javax.persistence.EntityManager;

import com.example.contexthierarchy.CommonConfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = CommonConfiguration.class)
public class EntityRepositoryIntegrationTest {

    @Autowired
    private DiceRollRepository entityRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void shouldLoad() {
        // given
        final DiceRoll diceRoll = DiceRoll.builder().score(0).build();
        entityRepository.saveAndFlush(diceRoll);
        entityManager.clear();

        // when
        final DiceRoll loaded = entityRepository.findOne(diceRoll.getId());

        // then
        assertThat(loaded, is(notNullValue()));
    }

}
