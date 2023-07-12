package guru.springframework;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class GreatingTest {

    Greating greating;

    @BeforeEach
    void setUp() {
        greating = new Greating();
    }

    @AfterEach
    void afterEachTest() {
        System.out.println("After");
    }

    @AfterAll
    static void cleanUp() { // note the method is static for @BeforeAll/@AfterAll
        System.out.println("After all");
    }

    @AfterAll
    static void cleanUp2() {
        System.out.println("After all 2");
    }

    @Test
    void helloWorldTest() {
        System.out.println(greating.helloWorld());
    }

    @Test
    void helloWorldTest1() {
        System.out.println(greating.helloWorld("John"));
        assertTrue(1 - 1 == 0, "Cheap msg");
        assertFalse(1 - 2 == 0, () -> "Heavy message to build +++");
    }

    @Test
    @Disabled
    void helloWorldGroup() {
        assertAll("Group of assertion",
                () -> assertNotEquals("abc", "abc", "That assertion failed"),
                () -> assertNotEquals("abc", "ABC", "Always false")
        );
    }

    @Test
    @DisplayName("Assumption will be ignore if not true")
    void assumptionTrue() {
        assumeTrue(Boolean.FALSE);
    }

    @Test
    @DisplayName("Assumption That")
    void assumptionThat() {
        assumingThat(Boolean.FALSE, () -> assertFalse(true, "Ignored if assumption is false"));
    }

    @Test
    @DisabledOnOs(OS.MAC)
//    @DisabledForJreRange(min = JRE.JAVA_14, max = JRE.JAVA_15)
    @DisplayName("Timeout in the same thread")
    void testTimeout() {
        assertTimeout(Duration.ofMillis(200),
                () -> {
                    Thread.sleep(2000);
                    assertFalse(true);
                },
                "Timeout Failed");
    }

    @Test
    @DisabledIfSystemProperty(named = "file.separator", matches = "[/]")
    @DisplayName("Timeout in a new thread")
    void testTimeoutNewThread() {
        assertTimeoutPreemptively(Duration.ofMillis(200),
                () -> {
                    Thread.sleep(5000);
                    assertFalse(true);
                },
                "Timeout Failed");
    }
}