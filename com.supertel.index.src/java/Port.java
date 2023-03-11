import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Port {

    private List<String> indexes = new ArrayList<>();

    Port() {}

    Port(List<String> indexes) {
        this.indexes = indexes;
    }

    public List<String> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<String> indexes) {
        this.indexes = indexes;
    }

    public static final String SPLITERATORS = "[-–]";

    /**
     * Метод генерации уникальных упорядоченных групп элементов на основе массивов чисел
     */
    public List<List<Integer>> convertToUniqueArraysNumbers() {
        List<List<Integer>> indexes = convertToNumericArrays();
        List<List<Integer>> uniqueArraysNumbers = new ArrayList<>();
        int indexesSize = indexes.size();

        cycle(indexes, uniqueArraysNumbers, new int[indexesSize], 0, indexesSize - 1);

        return uniqueArraysNumbers;
    }

    /**
     * Метод преобразования массива строк в массив последовательностей чисел
     */
    public List<List<Integer>> convertToNumericArrays() {
        List<List<String>> separateIndexesByComma = indexes.stream()
                .map(it -> Arrays.asList(it.split(",")))
                .collect(Collectors.toList());

        return separateIndexesByComma.stream()
                .map(this::convertIndexToSequenceOfNumbers)
                .collect(Collectors.toList());
    }

    private void cycle(List<List<Integer>> indexes, List<List<Integer>> uniqueArraysNumbers,
                       int [] currentArray, int currentIndex, int lastIndex) {
        List<Integer> sequenceIndexes = indexes.get(currentIndex);

        for (Integer sequenceIndex : sequenceIndexes) {
            currentArray[currentIndex] = sequenceIndex;

            if (currentIndex == lastIndex) {
                uniqueArraysNumbers.add(Arrays.stream(currentArray).boxed().collect(Collectors.toList()));
            } else {
                cycle(indexes, uniqueArraysNumbers, currentArray, currentIndex + 1, lastIndex);
            }
        }
    }

    private List<Integer> convertIndexToSequenceOfNumbers(List<String> indexes) {
        List<Integer> sequenceOfNumbers = new ArrayList<>();

        indexes.forEach(at -> {
            if (at.split(SPLITERATORS).length == 1) {
                sequenceOfNumbers.add(Integer.parseInt(at));
            } else if (at.split(SPLITERATORS).length > 1) {
                sequenceOfNumbers.addAll(convertIndexRangeToSequenceOfNumbers(at));
            }
        });

        return sequenceOfNumbers;
    }

    private List<Integer> convertIndexRangeToSequenceOfNumbers(String indexRange) {
        List<Integer> sequenceOfNumbers = new ArrayList<>();

        List<Integer> numbersRange = Stream.of(indexRange.split(SPLITERATORS))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        int min = numbersRange.get(0);
        int max = numbersRange.get(1);
        int iterationCount = max - min;

        for (int i = 0; i <= iterationCount; i++) {
            sequenceOfNumbers.add(min++);
        }

        return sequenceOfNumbers;
    }
}
