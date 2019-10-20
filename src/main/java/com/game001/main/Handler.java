package com.game001.main;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Handler {

    List<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    public void clearObject() {
        /*
        ten sposób nie do końca działa...
        in this way this doesn't work well...
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() != ID.Player) {
                removeObject(tempObject);
            }
        }*/

        //więc zróbmy to w śmieszkowy sposób
        //irl ciekawe, czy taka postać miałaby
        //dalej swoje itemki...
        // so let's do it in funny way
        //irl I'm curious, is that replaced
        //player still could have its items?
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() == ID.Player) {
                object.clear();
                addObject(new Player((int) tempObject.getX(),
                        (int) tempObject.getY(),
                        ID.Player, this));
            }

        }
    }
}