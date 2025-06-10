package org.abhishek.snakeandladder.model.factory;


import org.abhishek.snakeandladder.model.HumanPlayer;
import org.abhishek.snakeandladder.model.Player;

public class HumanPlayerFactory implements PlayerFactory {

    @Override
    public Player getPlayer(String name) {
        return new HumanPlayer(name);
    }
}
