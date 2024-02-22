package DZ;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
         /*Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
        sum(), multiply(), divide(), subtract().
        Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
         */
        System.out.println("TASK1:");
        Calculator calculator = new Calculator();
        System.out.printf("25 + 25 = %s\n",calculator.sum(25, 25f));
        System.out.printf("5 * 5 = %s\n",calculator.multiply(5, 5d));
        System.out.printf("25 / 20 = %s\n",calculator.divide(25L, 20));
        System.out.printf("25 - 20 = %s\n",calculator.subtract(25L, 20.0)+"\n");


        /*Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
        Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары,
        а также переопределение метода toString(), возвращающее строковое представление пары.
        */
        System.out.println("TASK2:");


        /*Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true,
        если они одинаковые, и false в противном случае.
        Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа.
         */
        System.out.println("TASK3:");
        int [] arr1 = {1, 5, 8, 9};
        double [] arr2 = {1.0, 5.0, 8.0, 9.0, 10.0};
        String [] arr3 = {"a", "b", "c", "d"};
        System.out.printf("%s = %s - %s\n", Arrays.toString(arr1),Arrays.toString(arr2),compareArrays(arr1,arr2));
        System.out.printf("%s = %s - %s\n", Arrays.toString(arr1),Arrays.toString(arr3),compareArrays(arr1,arr3));
    }

    private static <T> boolean compareArrays(T arr1, T arr2) {
        return Array.getLength(arr1)==Array.getLength(arr2);
    }
}
