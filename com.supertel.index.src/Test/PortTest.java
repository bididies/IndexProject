import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class PortTest {

    List<String> indexes;
    List<List<Integer>> numericArrays;
    int sizeIndexes = 3;

    Port port;

    @BeforeEach
    void setUp() {
        indexes = new ArrayList<>();
        numericArrays = new ArrayList<>();
        port = new Port(indexes);

        for (int i = 0; i < sizeIndexes; i++) {
            generateData();
        }
    }

    @Test
    void convertToUniqueArraysNumbers() {
        List<List<Integer>> actualArrays = port.convertToUniqueArraysNumbers();
        int sizeActualArrays = actualArrays.size();
        Assertions.assertEquals(Math.pow(5, sizeIndexes), sizeActualArrays);

        int randomIndex = new Random().nextInt(sizeActualArrays);
        List<Integer> actualNumbers = actualArrays.get(randomIndex);
        List<Integer> expectedNumbers = calculationExpectedNumbers(sizeActualArrays, randomIndex);

        Assertions.assertArrayEquals(expectedNumbers.toArray(), actualNumbers.toArray());
    }

    @Test
    void convertToNumericArrays() {
        List<List<Integer>> actualArrays = port.convertToNumericArrays();

        Assertions.assertEquals(numericArrays.size(), actualArrays.size());

        for (int i = 0; i < numericArrays.size(); i++) {
            Assertions.assertArrayEquals(numericArrays.get(i).toArray(), actualArrays.get(i).toArray());
        }
    }

    private int getIndex(int i, int sizeSequenceNumbers) {
        return (int) Math.ceil((i + 1) * 1.0 / (sizeSequenceNumbers / 5));
    }

    private void generateData() {
        int a = (int) (Math.random() * 10);
        int b = a + 3;
        int c = b + 2;
        int d = c + 4;

        indexes.add(String.format("%d,%d-%d,%d", a, b, c, d));

        List<Integer> numericArray = new ArrayList<>();
        numericArrays.add(numericArray);

        numericArray.add(a);

        for (int i = b; i <= c;) {
            numericArray.add(i++);
        }

        numericArray.add(d);
    }

    private List<Integer> calculationExpectedNumbers(int sizeActualArrays, int randomIndex) {
        int index1 = getIndex(randomIndex, sizeActualArrays) - 1;
        int index2 = getIndex(randomIndex %25, sizeActualArrays / 5) - 1;
        int index3 = randomIndex %25%5;

        int expectedNumber1 = numericArrays.get(0).get(index1);
        int expectedNumber2 = numericArrays.get(1).get(index2);
        int expectedNumber3 = numericArrays.get(2).get(index3);

        return List.of(expectedNumber1, expectedNumber2, expectedNumber3);
    }
}
