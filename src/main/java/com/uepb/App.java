package com.uepb;

import com.uepb.controller.HashTableController;
import com.uepb.model.HashTableDoubleHashing;
import com.uepb.model.HashTableLinearProbing;
import com.uepb.model.HashTableOpenAddressingBase;
import com.uepb.model.HashTableQuadraticProbing;
import com.uepb.model.HashTableSeparateChaining;
import com.uepb.utils.FileUitls;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    HashTableOpenAddressingBase<Integer, String> hashTable1 = new HashTableLinearProbing();
    HashTableController.testarImplementacoesHash(hashTable1);

    HashTableQuadraticProbing<Integer, String> hashTable2 = new HashTableQuadraticProbing();
    HashTableController.testarImplementacoesHash(hashTable2);

    //HashTableOpenAddressingBase<Integer, String> hashTable3 = new HashTableDoubleHashing();
    //HashTableController.testarImplementacoesHash(hashTable3);

    HashTableSeparateChaining<Integer, String> hashTable4 = new HashTableSeparateChaining();
    HashTableController.testarImplementacoesHash(hashTable4);
  }
}
