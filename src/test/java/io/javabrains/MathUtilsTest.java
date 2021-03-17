package io.javabrains;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import static org.junit.Assume.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("When running MathUtils")
class MathUtilsTest {

    MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeAll
    void beforeAllInit(){
        System.out.print("This needs to run before all");
    }

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        this.testInfo=testInfo;
        this.testReporter=testReporter;
        mathUtils= new MathUtils();
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
    }

    @AfterEach
    void cleanup(){
        System.out.print("cleaning up...");
    }

    @Nested
    @Tag("Math")
    @DisplayName("add method")
    class Addtest{
        @Test
        @DisplayName("when adding two positive numbers")
        void testAddPositive(){
            assertEquals(2,mathUtils.add(1,1), "should return the right sum");
        }

        @Test
        @DisplayName("when adding two negative numbers")
        void testAddNegative(){
            int expected= -2;
            int actual= mathUtils.add(-1,-1);
            assertEquals(expected, actual, () -> "should return sum " + expected + " but returned " + actual);
        }
    }


    @Tag("Math")
    @Test
    @DisplayName("multiply method")
    void testMultiply(){
        //assertEquals(4,mathUtils.multiply(2,2),"Should return the right product");
        assertAll(
                () -> assertEquals(4,mathUtils.multiply(2,2)),
                () -> assertEquals(0,mathUtils.multiply(2,0)),
                () -> assertEquals(-2,mathUtils.multiply(2,-1))
                );
    }

    @Tag("Math")
    @Test
    @DisplayName("divide method")
    //@EnabledOnOs(OS.LINUX)
    void testDivide(){
        boolean isServerUp=true;
        assumeTrue(isServerUp);
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(1,0), "Divide by 0 should throw an exception" );
    }

    @RepeatedTest(3)
    void testComputeCircleRadius(RepetitionInfo repetitionInfo){
        assertEquals(314.1592653589793, mathUtils.computeCircleArea(10 ), "Should return right circle Area ");
    }

    @Test
    @DisplayName("TDD method. Should not run")
    @Disabled
    void testDisabled(){
        fail("This test should be disabled");
    }

}