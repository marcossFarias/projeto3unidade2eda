package com.uepb.utils;

import java.util.Arrays;
import java.util.Random;

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
   * Generates an array of unique integers with the specified size.
   *
   * @param size the desired size of the array
   * @return an array of unique integers
   */
  public static int[] generateUniqueIntArray(int size) {

    return random.ints(size, RANGE_LOWER_BOUND, RANGE_UPPER_BOUND)
        .parallel()
        .distinct()
        .boxed()
        .mapToInt(Integer::intValue)
        .toArray();
  }

  /**
   * Generates an array of unique integers within a specified size and range.
   *
   * @param size            The desired size of the array.
   * @param rangeLowerBound The lower bound of the range (inclusive).
   * @param rangeUpperBound The upper bound of the range (exclusive).
   * @return An array of unique integers within the specified range.
   */
  public static int[] generateUniqueIntArray(
      int size,
      int rangeLowerBound,
      int rangeUpperBound) {
    return random.ints(size, rangeLowerBound, rangeUpperBound)
        .parallel()
        .distinct()
        .boxed()
        .mapToInt(Integer::intValue)
        .toArray();
  }

  /**
   * Generates an array of integers with the specified size and range.
   * The array may contain repeated values.
   *
   * @param size  the desired size of the array
   * @param range the maximum value (inclusive) that can be generated
   * @return an array of integers within the specified range
   */
  public static int[] generateRepeatedIntArray(int size, int range) {
    int[] repeatedArray = new int[size];

    Arrays.setAll(repeatedArray, i -> random.nextInt(range + 1));

    return repeatedArray;
  }
}
