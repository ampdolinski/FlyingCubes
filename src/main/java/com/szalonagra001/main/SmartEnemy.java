package com.szalonagra001.main;

import java.awt.*;

public class SmartEnemy extends GameObject{

private Handler handler;
private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player) {
                player = handler.object.get(i);
            }
        }

    }

    public void tick() {

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        //Edit the number that -diffX or -diffY are divided by in order to control speed.

        if(diffX < 0) velX = (-diffX/20 + 1);
        else if(diffX > 0) velX = (-diffX/20 - 1);
//This else if line tells the enemy to stop moving if already in player on x axis
        else if(diffX == 0) velX = 0;
        if(diffY < 0) velY = (-diffY/20 + 1);
        else if(diffY > 0) velY = (-diffY/20 - 1);
//This  else if line tells the enemy to stop moving if already in player on y axis
        else if(diffY == 0) velY = 0;

        x += velX;
        y += velY;

        /**
         * The plus ones and minus ones ensure that
         * it will still have velocity at all times
         * so the cube doesn't just . . . stop.
         * (which is the reason ints didn't work for him,
         * as java will round for example 7/20 to a 0)
         * while it still gains movement based on how far
         * the player is from you for fluid circular movement.
         * When he also used  Math.sqrt() and the floats he had
         * a way to make sure the square was at a consistent
         * speed while mine increases based on how far the player
         * is, but if you're lazy like me and you
         * still want something that follows the player fluently.
         */


//        float distance = (float)
//                Math.sqrt(((x-player.getX())*(x-player.getX()))
//                        - ((y-player.getY())*(y-player.getY())));
//
//        velX = (float) ((-1.0/distance) * diffX * 3);
//        velY = (float) ((-1.0/distance) * diffY * 3);

        if(y <= 0 || y > Game001.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x > Game001.WIDTH - 32) velX *= -1;

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
