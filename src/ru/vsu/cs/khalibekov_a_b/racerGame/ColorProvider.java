package ru.vsu.cs.khalibekov_a_b.racerGame;

import ru.vsu.cs.khalibekov_a_b.racerGame.models.Spectator;

import java.awt.*;

public interface ColorProvider {

    Spectator.ColorSchema createSpectatorSchema();
    Color createTribuneColor();
}
