package com.uepb.service;

import java.util.HashMap;
import java.util.List;

/**
 * A generic hash table service that loads elements from a list and provides
 * methods to check for element existence and retrieve the size of the table.
 *
 * @param <T> The type of elements to be stored in the hash table.
 */
public class HashTableService<T> {
  private HashMap<T, T> hashTable;

  public HashTableService() {
    hashTable = new HashMap<>();
  }

  /**
   * Loads the elements from the provided list into the hash table.
   *
   * @param elements The list of elements to be loaded into the hash table.
   */
  public void loadFromList(List<T> elements) {
    for (T element : elements) {
      hashTable.put(element, element);
    }
  }

  public boolean containsElement(T element) {
    return hashTable.containsKey(element);
  }

  public int getSize() {
    return hashTable.size();
  }
}
