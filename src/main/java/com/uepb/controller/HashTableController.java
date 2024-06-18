package com.uepb.controller;

import com.uepb.model.HashTableOpenAddressingBase;
import com.uepb.model.HashTableSeparateChaining;
import com.uepb.service.HashTableService;
import com.uepb.utils.FileUitls;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class HashTableController {
  public static void testarImplementacoesHash(HashTableOpenAddressingBase<Integer, String> hashTable) {
    File file = new File("resources/databases/unique100kArray.txt");

    try {
      List<String> lines = FileUitls.readFileToList(file);
      long startTime = System.nanoTime();

      for (String line : lines) {
        try {
          int intValue = Integer.parseInt(line);
          hashTable.put(intValue, line);
        } catch (NumberFormatException e) {
          System.err.println("Erro ao converter linha para inteiro: " + line);
        }
      }

      long finalTime = (System.nanoTime() - startTime) / 1_000_000;
      System.out.println(
          "Tempo gasto para preencher usando" + hashTable.getClass().getSimpleName() + ": " + finalTime + " ms");

    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }

    System.out.println("Numero de colisões " + hashTable.collisionCount);

    File searchFile = new File("resources/databases/repeated300kArray.txt");
    try {
      List<String> lines = FileUitls.readFileToList(searchFile);
      long startTime = System.nanoTime();

      for (String line : lines) {
        try {
          int intValue = Integer.parseInt(line.trim());
          if (hashTable.containsValue(intValue)) {
            //System.out.println("Número " + intValue + " encontrado na hash table.");
          } else {
            // System.out.println("Número " + intValue + " não encontrado na hash table.");
          }
        } catch (NumberFormatException e) {
          System.err.println("Erro ao converter linha para inteiro: " + line);
        }
      }

      long finalTime = (System.nanoTime() - startTime) / 1_000_000;
      System.out.println(
          "Tempo gasto para pesquisar usando " + hashTable.getClass().getSimpleName() + ": " + finalTime + " ms");

    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }
  }

  public static void testarImplementacoesHash(HashTableSeparateChaining<Integer, String> hashTable) {
    File file = new File("resources/databases/unique100kArray.txt");

    try {
      List<String> lines = FileUitls.readFileToList(file);
      long startTime = System.nanoTime();

      for (String line : lines) {
        try {
          int intValue = Integer.parseInt(line.trim());
          hashTable.put(intValue, line);
        } catch (NumberFormatException e) {
          System.err.println("Erro ao converter linha para inteiro: " + line);
        }
      }

      long finalTime = (System.nanoTime() - startTime) / 1_000_000;
      System.out.println(
          "Tempo gasto para preencher usando " + hashTable.getClass().getSimpleName() + ": " + finalTime + " ms");

    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }

    System.out.println("Número de colisões: " + hashTable.collisionCount);

    File searchFile = new File("resources/databases/repeated300kArray.txt");
    try {
      List<String> lines = FileUitls.readFileToList(searchFile);
      long startTime = System.nanoTime();

      for (String line : lines) {
        try {

          if (hashTable.containsValue(line)) {
            //System.out.println("Número " + line + " encontrado na hash table.");
          } else {
            // System.out.println("Número " + intValue + " não encontrado na hash table.");
          }
        } catch (NumberFormatException e) {
          System.err.println("Erro ao converter linha para inteiro: " + line);
        }
      }

      long finalTime = (System.nanoTime() - startTime) / 1_000_000;
      System.out.println(
          "Tempo gasto para pesquisar usando " + hashTable.getClass().getSimpleName() + ": " + finalTime + " ms");

    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }
  }

}
