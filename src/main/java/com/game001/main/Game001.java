package com.game001.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game001 extends Canvas implements Runnable {

    public static final long serialVersionUID = -677045933309107067L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;

    public Game001(){

        //lepiej najpierw zainicjować
        //handler, bo jeżeli okno będzie pierwsze,
        //może się wykrzaczać
        //bo okno wywołuje później handler,
        //a będąc pierwszym, handlera jeszcze nie będzie

        handler = new Handler();

        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Ticket to the future!", this);

        hud = new HUD();
        spawner = new Spawn(handler, hud);
        r = new Random();

        handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32,ID.Player, handler));


//        handler.addObject(new EnemyBoss((Game001.WIDTH / 2) - 48, -100, ID.EnemyBoss, handler));

        //        Tu jest pierwszy BasicEnemy
        handler.addObject(new BasicEnemy(
                r.nextInt(Game001.WIDTH - 50),
                r.nextInt(Game001.HEIGHT - 50),
                ID.BasicEnemy, handler));

//        Tu jest pierwszy BasicEnemy
//        handler.addObject(new BasicEnemy(
//        r.nextInt(WIDTH), r.nextInt(HEIGHT),
//        ID.BasicEnemy, handler));

        //      tu jest player 2
        //        handler.addObject(new Player(WIDTH/2+64, HEIGHT/2-32,ID.Player2));
//        for (int i = 0; i < 50; i++) {
//
//        }
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
//        dzięki temu poleceniu, od razu klawiatura działa na okienku
//        thanks for this, keyboard works on the window

        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTickets = 60.0;
        double ns = 1000000000 / amountOfTickets;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        hud.tick();
        spawner.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

//        depth
        hud.render(g);

        g.dispose();
        bs.show();

    }

    public static float clamp(float var, float min, float max) {
        if(var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
            else
                return var;
    }


    public static void main(String[] args) {
        new Game001();
    }

}