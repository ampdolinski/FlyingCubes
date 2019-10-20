package com.szalonagra001.main;

import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject{

private Handler handler;

Random r = new Random();

    public EnemyBossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = (r.nextInt(5 - -5) + -5);
        velY = 5;
    }

    public void tick() {
        x += velX;
        y += velY;

//        if(y <= 0 || y > Game001.HEIGHT - 32) velY *= -1;
//        if(x <= 0 || x > Game001.WIDTH - 32) velX *= -1;

        if (y >= Game001.HEIGHT) handler.removeObject(this);

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.white, 16,16 , 0.05f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 16, 16);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }

}
