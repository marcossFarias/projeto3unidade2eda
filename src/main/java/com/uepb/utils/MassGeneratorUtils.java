package com.uepb.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * A utility class for generating arrays of integers with various properties.
 */
public class MassGeneratorUtils {
  private static Random random = new Random();
  private static final int RANGE_UPPER_BOUND = Integer.MAX_VALUE;
  private static final int RANGE_LOWER_BOUND = Integer.MIN_VALUE;

  /**
   * Private constructor to prevent instantiation of the utility class.
   */
  private MassGeneratorUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Generates an array of unique Integers with the specified size.
   *
   * @param size the desired size of the array
   * @return an array of unique Integers
   */
  public static Integer[] generateUniqueIntegerArray(int size) {
    return random.ints(size, RANGE_LOWER_BOUND, RANGE_UPPER_BOUND)
        .parallel()
        .distinct()
        .boxed()
        .toArray(Integer[]::new);
  }

  /**
   * Generates an array of unique Integers within a specified size and range.
   *
   * @param size            The desired size of the array.
   * @param rangeLowerBound The lower bound of the range (inclusive).
   * @param rangeUpperBound The upper bound of the range (exclusive).
   * @return An array of unique Integers within the specified range.
   */
  public static Integer[] generateUniqueIntegerArray(int size, int rangeLowerBound, int rangeUpperBound) {
    if (size > (rangeUpperBound - rangeLowerBound)) {
      throw new IllegalArgumentException("Size cannot be greater than the range of unique numbers.");
    }

    Set<Integer> generated = new HashSet<>();
    while (generated.size() < size) {
      int next = random.nextInt(rangeUpperBound - rangeLowerBound) + rangeLowerBound;
      generated.add(next);
    }

    return generated.toArray(new Integer[0]);
  }

  /**
   * Generates an array of Integers with the specified size and range.
   * The array may contain repeated values.
   *
   * @param size  the desired size of the array
   * @param range the maximum value (inclusive) that can be generated
   * @return an array of Integers within the specified range
   */
  public static Integer[] generateRepeatedIntegerArray(int size, int range) {
    Integer[] repeatedArray = new Integer[size];

    Arrays.setAll(repeatedArray, i -> random.nextInt(range + 1));

    return repeatedArray;
  }

  /**
   * It generates two arrays:
   * - An array of 100,000 unique integers between 0 and 1,000,000(inclusive),
   * saved to "unique100kArray.txt".
   * - An array of 300,000 repeated integers between 0 and 1,000,000 (inclusive),
   * saved to "repeated300kArray.txt".
   */
  public static void main(String[] args) throws IOException {
    Integer[] unique100kArray = generateUniqueIntegerArray(100_000, 0, 1_000_000);
    Path unique100kArrayPath = Paths.get("resources/databases/unique100kArray.txt");
    File unique100kArrayFile = unique100kArrayPath.toFile();

    FileUitls.writeArrayToFile(unique100kArrayFile, unique100kArray, false);

    Integer[] repeated300kArray = generateRepeatedIntegerArray(300_000, 1_000_000);
    Path repeated300kPath = Paths.get("resources/databases/repeated300kArray.txt");
    File repeated300kArrayFile = repeated300kPath.toFile();

    FileUitls.writeArrayToFile(repeated300kArrayFile, repeated300kArray, false);
  }
}
