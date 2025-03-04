package com.JavaCoffee.BackEndJC.model.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
//Parte feita pelo Inteliji, toDO descobrir como executar corretamente pelo eclipse.

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Test
    void findByLogin() {
    }
}