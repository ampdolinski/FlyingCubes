package com.game001.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick(){
        x += velX;
        y += velY;

        x = Game001.clamp((int)x,0, Game001.WIDTH - 38);
        y = Game001.clamp((int)y, 0, Game001.HEIGHT - 60);

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.blue, 32,32 , 0.05f, handler));

        collision();
    }

    public void collision(){
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy
                    || tempObject.getId() == ID.FastEnemy
                    || tempObject.getId() == ID.SmartEnemy){
                if (getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -=2;
                }
            }
        }
    }

    public void render(Graphics g){

        g.setColor(Color.blue);

//        wersja dla dwóch graczy:
//        if (id == ID.Player) g.setColor(Color.blue);
//        else if (id == ID.Player2) g.setColor(Color.red);

        g.fillRect((int)x, (int)y,32,32);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }


}
