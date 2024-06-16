package com.uepb.controller;

import com.uepb.service.HashTableService;
import com.uepb.utils.FileUitls;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class HashTableController {
  private HashTableService<String> hashTableService;

  public HashTableController() {
    hashTableService = new HashTableService<>();
  }

  /**
   * Reads the contents of a file and loads the data into the hash table.
   *
   * @param file The file to read from.
   * @throws IOException If an I/O error occurs while reading the file or loading
   *                     the data.
   */
  public void loadDataFromFile(File file) throws IOException {
    List<String> lines = FileUitls.readFileToList(file);
    hashTableService.loadFromList(lines);
  }
}

