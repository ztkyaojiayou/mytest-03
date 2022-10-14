package com.example.test;

/**
 * 冒泡排序--中国电信一面
 *
 * @author :zoutongkun
 * @date :2022/9/13 1:58 下午
 * @description :
 * @modyified By:
 */
public class Test01 {
  public static int[] method(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length - 1 - i; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
    return arr;
  }

  public static int[] method02(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length - i; j++) {
        if (arr[j + 1] < arr[j]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
    return arr;
  }

  public static void main(String[] args) {
    int[] arr = new int[5];
    arr[0] = 3;
    arr[1] = 2;
    arr[2] = 1;
    arr[3] = 4;
    arr[4] = 5;
    int[] res = Test01.method(arr);
    for (int i = 0; i < res.length; i++) {
      System.out.print(res[i]);
    }
  }
}
