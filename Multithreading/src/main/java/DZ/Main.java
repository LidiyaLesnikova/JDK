package DZ;

import java.util.Random;

/*
Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
Вилки лежат на столе между каждой парой ближайших философов.
Каждый философ может либо есть, либо размышлять.
Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)

Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */
public class Main {
    public static void main(String[] args) {
        Philosopher philosopher1 = new Philosopher();
        Philosopher philosopher2 = new Philosopher();
        Philosopher philosopher3 = new Philosopher();
        Philosopher philosopher4 = new Philosopher();
        Philosopher philosopher5 = new Philosopher();

        Fork fork12 = new Fork(); // для 1 и 2 философа
        Fork fork23 = new Fork(); // для 2 и 3 философа
        Fork fork34 = new Fork(); // для 3 и 4 философа
        Fork fork45 = new Fork(); // для 4 и 5 философа
        Fork fork51 = new Fork(); // для 5 и 1 философа

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                eatOrNotEat(philosopher1, fork12, fork51, 1);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                eatOrNotEat(philosopher2, fork12, fork23, 2);
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                eatOrNotEat(philosopher3, fork23, fork34, 3);
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                eatOrNotEat(philosopher4, fork34, fork45, 4);
            }
        });
        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                eatOrNotEat(philosopher5, fork45, fork51, 5);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

    private static void eatOrNotEat(Philosopher philosopher, Fork fork1, Fork fork2, int num) {
        Random random = new Random();
        int count=1;
        do {
            if (philosopher.getStatusPhilosopher()==StatusPhilosopher.thinks) {
                if (philosopher.toEat(fork1, fork2) == StatusPhilosopher.eats) {
                    count++;
                }
            } else {
                philosopher.toThinks(fork1, fork2);
                try {
                    Thread.sleep(random.nextInt(100, 10000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } while (count<=3);
        philosopher.toThinks(fork1, fork2);
        System.out.println("Философ "+num+" поел 3 раза");
    }
}
