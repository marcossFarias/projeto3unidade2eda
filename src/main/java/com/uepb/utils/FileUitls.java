package com.uepb.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A utility class for operations with files.
 */
public class FileUitls {
  /**
   * Private constructor to prevent instantiation of the utility class.
   */
  private FileUitls() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Writes an array of strings to a file.
   *
   * @param file   The file to write the array to.
   * @param lines  The array of strings to write to the file.
   * @param append If true, the lines will be appended to the file;
   *               otherwise, the file will be overwritten.
   * @throws IOException If an I/O error occurs while writing to the file.
   */
  public static void writeArrayToFile(
      File file,
      String[] lines,
      boolean append) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append))) {
      for (String line : lines) {
        writer.write(line);
        writer.newLine();
      }
    }
  }
}
