package DZ;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        System.out.printf("25 / 0 = %s\n",calculator.divide(25, 0));
        System.out.printf("25 - 20 = %s\n",calculator.subtract(25L, 20.0)+"\n");


        /*Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true,
        если они одинаковые, и false в противном случае.
        Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа.
         */
        System.out.println("TASK2:");
        List<Object> arr1 = new ArrayList<>();
        arr1.add(1);
        arr1.add(5.0);
        arr1.add(8L);

        List<Object> arr2 = new ArrayList<>();
        arr2.add(4);
        arr2.add(10.0);
        arr2.add(1L);

        List<Object> arr3 = new ArrayList<>();
        arr3.add(2);
        arr3.add(2.0);
        arr3.add("2L");

        System.out.printf("%s = %s - %s\n", arr1,arr2,compareArrays(arr1,arr2));
        System.out.printf("%s = %s - %s\n", arr1,arr3,compareArrays(arr1,arr3));


        /*Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
        Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары,
        а также переопределение метода toString(), возвращающее строковое представление пары.
        */
        System.out.println("TASK3:");
        Pair pair = new Pair();
        //System.out.println(pair.getFirst());
        //System.out.println(pair.getSecond());



    }

    private static <T> boolean compareArrays(List<T> arr1, List<T> arr2) {
        if (arr1.size()!=arr2.size()) {
            return false;
        } else {
            for (int i = 0; i < arr1.size(); i++) {
                if (arr1.get(i).getClass()!=arr2.get(i).getClass()) {
                    return false;
                }
            }
        }
        return true;
    }
}
