package org.abhishek.snakeandladder.model.factory;


import org.abhishek.snakeandladder.model.AIPlayer;
import org.abhishek.snakeandladder.model.Player;

public class AIPlayerFactory implements PlayerFactory {


    @Override
    public Player getPlayer(String name) {
        return new AIPlayer(name);
    }
}
