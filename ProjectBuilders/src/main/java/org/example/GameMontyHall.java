package org.example;

import lombok.Data;

import java.util.Random;

@Data
public class GameMontyHall {
    private int winnerDoor, selectedDoor;
    Random randomDoor = new Random();

    public double newGame(boolean variable) { //true - решение поменяется, false - решение не поменяется
        winnerDoor = randomDoor.nextInt(1, 4);
        selectedDoor = randomDoor.nextInt(1, 4);
        //System.out.println("winner door - "+winnerDoor+"; select - "+selectedDoor);
        if (selectedDoor == winnerDoor) {
            return variable ? 0 : 1; //если решение поменяется игрок проиграл
        } else {
            return variable ? 1 : 0; //если решение поменяется игрок выиграл
        }
    }
}
