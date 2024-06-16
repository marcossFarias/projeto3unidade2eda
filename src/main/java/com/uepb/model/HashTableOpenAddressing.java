package com.uepb.model;

/**
 * Hash table with open addressing.
 * This contains some common methods of Map interface, including size, isEmpty,
 * put, get, remove,
 * containsKey
 * For open addressing hash table, we use 100% of the inner array space, and it
 * is a close
 * hashing. When collision occurs, it has to find an empty position to store the
 * element. Pedro
 * provided few methods to probe a new index. I am not quite familiar with
 * quadratic probing, so the
 * default probing method is linear probing, which means if the index is
 * occupied, it automatically
 * move to the next index.
 * I have improved it by adding resize method. When the space is full, it will
 * automatically
 * expand the capacity for 2 times. Also, when then size is one fourth of the
 * capacity, it will
 * shrink its capacity to half. So we can add elements constantly and use the
 * space efficiently.
 * I also added test main method, you can invoke this class to see what is
 * happening during the
 * put and remove methods.
 *
 * @author Pedro Furtado
 * @author River Lin
 */
public class HashTableOpenAddressing<K, V> {

  private HashTableNode<K, V>[] table;
  private int size;

  /** Constructor method. */
  @SuppressWarnings("unchecked")
  HashTableOpenAddressing(int capacity) {
    this.size = 0;
    this.table = new HashTableNode[capacity];
  }

  public HashTableOpenAddressing() {
    this(5);
  }

  /*
   * The default capacity of the hash table if 5, and we can initiate it with
   * generic type.
   * Luckily, I observed that the hashcode of "a" is 97, "b" is 98, "c" is 99, and
   * so on.
   * So we can create collision on purpose, I put 6 entries of keys from a to f,
   * so collision must occur.
   * 
   * When the sixth entry is put, we can see the capacity is doubled.
   * 
   * And then we keep removing entry, when the size is reached 2, which is 1 / 4
   * of the capacity, the capacity shrank.
   */
  public static void main(String[] args) {
    HashTableOpenAddressing<String, String> ht = new HashTableOpenAddressing<>();
    int h;
    System.out.println("hash(a) = " + (h = ht.hash("a")) + ", index(a) = " + ht.getIndex(h));
    System.out.println("hash(b) = " + (h = ht.hash("b")) + ", index(b) = " + ht.getIndex(h));
    System.out.println("hash(c) = " + (h = ht.hash("c")) + ", index(c) = " + ht.getIndex(h));
    System.out.println("hash(d) = " + (h = ht.hash("d")) + ", index(d) = " + ht.getIndex(h));
    System.out.println("hash(e) = " + (h = ht.hash("e")) + ", index(e) = " + ht.getIndex(h));
    System.out.println("hash(f) = " + (h = ht.hash("f")) + ", index(f) = " + ht.getIndex(h));
    ht.put("a", "a");
    ht.put("a", "b");
    ht.put("b", "c");
    ht.put("c", "c");
    ht.put("d", "d");
    ht.put("e", "e");
    ht.put("f", "f");
    System.out.println("ht.get(\"f\") = " + ht.get("f"));
    System.out.println("ht.get(\"b\") = " + ht.get("b"));
    ht.print();
    ht.remove("f");
    ht.remove("e");
    ht.remove("d");
    ht.remove("c");
    ht.remove("b");
    ht.print();
    System.out.println("ht.get(\"f\") = " + ht.get("f"));
    System.out.println("ht.get(\"b\") = " + ht.get("b"));
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * @param key   key
   * @param value value
   */
  public void put(K key, V value) {
    int hash = hash(key);
    int index = getIndex(hash);
    int startIndex = index;
    HashTableNode<K, V> e;
    while ((e = table[index]) != null) {
      if (hash == e.hash && e.key.equals(key)) {
        table[index].value = value;
        return;
      }
      index = linearProbing(index);
      if (index == startIndex) {
        resize(capacity() * 2);
        index = getIndex(hash);
        startIndex = index;
      }
    }
    table[index] = new HashTableNode<>(hash, key, value);
    size++;
    printInnerVar();
  }

  private int capacity() {
    return table.length;
  }

  @SuppressWarnings("unchecked")
  private void resize(int newCapacity) {
    HashTableNode<K, V>[] oldTab = table;
    table = new HashTableNode[newCapacity];
    size = 0;
    for (HashTableNode<K, V> hashNode : oldTab) {
      if (hashNode != null) {
        put(hashNode.key, hashNode.value);
      }
    }
  }

  /**
   * Search method.
   * Search an element in hash table.
   */
  public V get(K key) {
    int hash = hash(key);
    HashTableNode<K, V> e;
    int index = getIndex(hash);
    int startIndex = index;
    while ((e = table[index]) != null) {
      if (hash == e.hash && e.key.equals(key)) {
        return table[index].value;
      }
      index = linearProbing(index);
      if (index == startIndex) {
        return null;
      }
    }
    printInnerVar();
    return null;
  }

  public boolean containsKey(K key) {
    return get(key) == null;
  }

  /**
   * Remove method.
   * Remove an element from the hash table.
   */
  public V remove(K key) {
    int hash = hash(key);
    int index = getIndex(hash);
    int startIndex = index;
    HashTableNode<K, V> e;
    V oldVal = null;
    while ((e = table[index]) != null) {
      if (hash == e.hash && e.key.equals(key)) {
        oldVal = table[index].value;
        table[index] = null;
        size--;
      }
      index = linearProbing(index);
      if (index == startIndex) {
        break;
      }
    }
    if (size == capacity() / 4 && capacity() / 2 != 0) {
      resize(capacity() / 2);
    }
    printInnerVar();
    return oldVal;
  }

  private void printInnerVar() {
    System.out.println("Capacity : " + capacity() + " , Size : " + size);
  }

  /** Linear probing. */
  private int linearProbing(int index) {
    return (index + 1) % capacity();
  }

  /** Quadratic probing. */
  public int quadraticProbing(int index) {
    return ((index * index) & Integer.MAX_VALUE) % this.capacity();
  }

  /** Double hashing. */
  public int h_double(int key, int i) {

    return (this.h_double2(key) + i) % this.capacity();
  }

  /** Auxiliar hash function to Double hashing made by h_double() method. */
  public int h_double2(int key) {
    return (1 + key) % (this.capacity() - 1);
  }

  /**
   * We get hashcode of key object, and use & MAX integer to convert negative hash
   * to positive,
   * which is more cost-efficient than Math.abs(n)
   *
   * @param key key
   * @return the index in the hashNodes array
   */
  private int hash(K key) {
    return key == null ? 0 : Integer.MAX_VALUE & key.hashCode();
  }

  private int getIndex(int hash) {
    return hash % capacity();
  }

  public void print() {
    for (int i = 0; i < this.capacity(); i++) {
      System.out.print("(" + i + ") : ");
      if (this.table[i] == null) {
        System.out.println("null");
      } else {
        System.out.println(this.table[i].key.toString() + " : " + this.table[i].value.toString());
      }
    }
  }

  /** Element of hash table. */
  static class HashTableNode<K, V> {
    final int hash;
    K key;
    V value;

    HashTableNode(int hash, K key, V value) {
      this.hash = hash;
      this.key = key;
      this.value = value;
    }
  }
}
