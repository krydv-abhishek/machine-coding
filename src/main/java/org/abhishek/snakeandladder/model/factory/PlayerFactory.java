package org.abhishek.snakeandladder.model.factory;


import org.abhishek.snakeandladder.model.Player;

public interface PlayerFactory {

    Player getPlayer(String name);
}
