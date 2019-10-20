package com.szalonagra001.main;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends GameObject{

private Handler handler;
Random r = new Random();

private int timer1 = 80;
private int timer2 = 50;

    public EnemyBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 2;
    }

    public void tick() {
        x += velX;
        y += velY;


        if (timer1 <= 0) { velY = 0;}
        else { timer1--; }

        if (timer1 <= 0) { timer2--; }

        if (timer2 <= 0 ) {
            if (velX == 0) {
                velX = 3;
            }

            if (velX >= 0) {
                velX += 0.005f;
            } else {
                velX -= 0.005f;
            }

            velX = Game001.clamp(velX, -10, 10);


            int spawn = r.nextInt(10);
            if (spawn == 0) handler.addObject(new EnemyBossBullet((int)x + 48,(int)y + 48 , ID.BasicEnemy, handler));
        }

        //if(y <= 0 || y > Game001.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x > Game001.WIDTH - 92) velX *= -1;

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 92,92 , 0.05f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 92, 92);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 92, 92);
    }

}
