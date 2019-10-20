package com.game001.main;

import java.awt.*;

public class Trail extends GameObject {

    private float alpha = 1, life;
    private Handler handler;
    private Color color;
    private int height, width;

    //life = 0,001 - 0,1

    public Trail(int x, int y, ID id, Color color,
                 int height, int width, float life,
                 Handler handler) {
        super(x, y, id);
        this.color = color;
        this.height = height;
        this.width = width;
        this.life = life;
        this.handler = handler;



    }

//    live = (0.001 - 0.1)


    public void tick() {
        if (alpha > life){
            alpha -=  (life - 0.001);
        } else handler.removeObject(this);
    }

    public void render(Graphics g) {
Graphics2D g2d = (Graphics2D) g;
g2d.setComposite(makeTransparent(alpha));

g.setColor(color);
g.fillRect((int)x, (int)y, width, height);

g2d.setComposite(makeTransparent(1));

//made a sandwich with makeTransparent(alpha -> 1),
// to don't make any unintended thing transparent
    }

    private AlphaComposite makeTransparent(float aplha){
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    public Rectangle getBounds() {
        //bounds here won't be necessary,
        //because trail won't be hurting the player
        return null;
    }

}
