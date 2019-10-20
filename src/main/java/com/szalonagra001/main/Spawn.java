package com.szalonagra001.main;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;

    private Random r = new Random();


    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        scoreKeep++;
        if(scoreKeep >= 200){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

//            if(hud.getLevel() == 2){
//                handler.addObject(new EnemyBoss(r.nextInt(Game001.WIDTH - 50), r.nextInt(Game001.HEIGHT - 50),
//                        ID.EnemyBoss, handler));
//            }

            if(hud.getLevel() == 2){
                handler.addObject(new BasicEnemy(r.nextInt(Game001.WIDTH - 50), r.nextInt(Game001.HEIGHT - 50),
                        ID.BasicEnemy, handler));
            } else if (hud.getLevel() == 3){
                handler.addObject(new BasicEnemy(r.nextInt(Game001.WIDTH - 50), r.nextInt(Game001.HEIGHT - 50),
                        ID.BasicEnemy, handler));
            } else if (hud.getLevel() == 4){
                handler.addObject(new FastEnemy(r.nextInt(Game001.WIDTH - 50), r.nextInt(Game001.HEIGHT - 50),
                        ID.FastEnemy, handler));
            } else if (hud.getLevel() == 5){
                handler.addObject(new SmartEnemy(r.nextInt(Game001.WIDTH - 50), r.nextInt(Game001.HEIGHT - 50),
                        ID.SmartEnemy, handler));
            } else if (hud.getLevel() == 6){
                handler.addObject(new FastEnemy(r.nextInt(Game001.WIDTH - 50), r.nextInt(Game001.HEIGHT - 50),
                        ID.FastEnemy, handler));
            } else if (hud.getLevel() == 7){
                handler.addObject(new FastEnemy(r.nextInt(Game001.WIDTH - 50), r.nextInt(Game001.HEIGHT - 50),
                        ID.FastEnemy, handler));
            } else if (hud.getLevel() == 10){
                handler.clearObject();
                handler.addObject(new EnemyBoss((Game001.WIDTH / 2 ) - 48,-120 ,
                        ID.EnemyBoss, handler));
            }
        }
    }

}
