package com.ezbob.shuffle.service;

import org.springframework.stereotype.Service;

@Service
public class ShuffleService {
    private final int minValue = 0;
    private final int maxValue = 1000;

    public String validateValue(String inNumber){
        if(isNotInt(inNumber)){
            return "The input value is not integer. Please put correct value.";
        }
        int inputNumber = Integer.parseInt(inNumber);
        if(inputNumber > minValue && inputNumber <= maxValue){
            return "OK";
        }
          else {
            return "Error: The value is not correct. Should be integer between" +
                    minValue + " to " + maxValue;
        }
    }

    public int[] createArray(int inputNumber){
        int myArray[] = new int[inputNumber];
        for(int i=0; i < inputNumber; i++){
            myArray[i] = i+1;
        }
        return myArray;
    }

    public int[] shuffle(int[] simpleArray){
        int arrayLength = simpleArray.length;
        int temp = 0;
        for(int i=0; i < arrayLength; i++){
            int ni = i + (int)(Math.random()*(arrayLength-i));
            temp = simpleArray[ni];
            simpleArray[ni] = simpleArray[i];
            simpleArray[i] = temp;
        }
        return simpleArray;
    }

    public static boolean isNotInt(String str) {
        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return false; //String is an Integer
        } catch (NumberFormatException e) {
            return true; //String is not an Integer
        }

    }
}
