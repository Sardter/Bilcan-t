package com.badlogic.mygame.models.maps;

import com.badlogic.mygame.models.GameObject;
import com.badlogic.mygame.views.windows.InteractiveWindow;

public class Door extends GameObject {
    private final MapRouter router;
    private final int destination;

    public Door(String textureUrl, String name, String description,
                int width, int height, int posX, int posY, MapRouter router, int destination) {
        super(textureUrl, name, description, width, height, posX, posY);

        this.router = router;
        this.destination = destination;
    }

    @Override
    public void interact(InteractiveWindow interactiveWindow) {
        router.setMap(destination);
    }
}
