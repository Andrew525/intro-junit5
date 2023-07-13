package guru.springframework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GreatingAsserJTest {

    Greating greating;

    @BeforeEach
    void setUp() {
        greating = new Greating();
    }

    @Test
    void helloWorldTest() {
       assertThat(greating)
               .as("should have property WORLD")
               .hasFieldOrProperty("WORLD");

        assertThat(greating.ID)
               .as("'%s' should not be equal to '%s'", greating.helloWorld(), greating.ID)
               .isNotEqualTo("Hello World");
    }
}