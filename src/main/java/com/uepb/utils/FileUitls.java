package com.uepb.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
  public static <T> void writeArrayToFile(
      File file,
      T[] lines,
      boolean append) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append))) {
      for (T line : lines) {
        writer.write(line.toString());
        writer.newLine();
      }
    }
  }

  /**
   * Reads the contents of a file and returns a list of strings.
   *
   * @param file The file to read from.
   * @return A list of strings containing the lines read from the file.
   * @throws IOException If an I/O error occurs while reading the file.
   */
  public static List<String> readFileToList(File file) throws IOException {
    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    }
    return lines;
  }
}
