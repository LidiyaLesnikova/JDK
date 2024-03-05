package org.example;

import java.util.stream.IntStream;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/*
Убедиться в верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).
Необходимо:
Создать свой Java Maven или Gradle проект;
Подключите зависимость lombok и возможно какую то математическую библиотеку (напр. commons-math3)
Самостоятельно реализовать прикладную задачу;
Сохранить результат игр в одну из коллекций или в какой-то библиотечный класс.
Вывести на экран статистику по победам и поражениям
В качестве ответа прислать ссылку на репозиторий, в котором присутствует все важные файлы проекта (напр pom.xml)
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Парадокс Monty Hall");

        GameMontyHall game = new GameMontyHall();
        DescriptiveStatistics statistics = new DescriptiveStatistics();

        IntStream.range(1, 1001).forEach(newGame -> statistics.addValue(game.newGame(true)));
        System.out.println("Количество выигрышей, если игрок поменяет решение и выберет другую дверь - "+statistics.getSum());

        statistics.clear();
        IntStream.range(1, 1001).forEach(newGame -> statistics.addValue(game.newGame(false)));
        System.out.println("Количество выигрышей, если игрок не поменяет решение и выберет ту же дверь, что и первоначально - "+statistics.getSum());
    }
}