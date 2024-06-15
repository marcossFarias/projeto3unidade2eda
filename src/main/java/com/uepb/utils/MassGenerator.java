package com.uepb.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MassGenerator {

    public static void main(String[] args) {
        int[] uniqueArray = generateUniqueArray(1_000_000);
        int[] repeatedArray = generateRepeatedArray(1_000_000, 300_000);
        
        exportArrayToFile(uniqueArray, "/home/user/Documents/projects/projeto-unidade-dois-eda/resources/busca/1m-sem-repetir.txt");
        exportArrayToFile(repeatedArray, "/home/user/Documents/projects/projeto-unidade-dois-eda/resources/busca/1m-repetindo.txt");
    }

    public static int[] generateUniqueArray(int size) {
        Set<Integer> uniqueSet = new HashSet<>();
        Random random = new Random();

        while (uniqueSet.size() < size) {
            uniqueSet.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] uniqueArray = new int[size];
        int index = 0;
        for (int num : uniqueSet) {
            uniqueArray[index++] = num;
        }

        return uniqueArray;
    }

    public static int[] generateRepeatedArray(int size, int range) {
        Random random = new Random();
        int[] repeatedArray = new int[size];

        for (int i = 0; i < size; i++) {
            repeatedArray[i] = random.nextInt(range + 1);
        }

        return repeatedArray;
    }
    
    public static void exportArrayToFile(int[] array, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
