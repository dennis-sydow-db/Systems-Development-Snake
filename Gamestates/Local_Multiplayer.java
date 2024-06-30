package Gamestates;

import Objekts.Snake;
import Objekts.SnakeJon;
import UIElement.Food;
import UIElement.PowerUp;
import imageLoader.ImageLoaderabstract;
import main.Game;
import main.Score;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Local_Multiplayer extends State implements StartMethods{
    private ImageLoaderabstract imageLoader = new ImageLoaderabstract("/Gamebackground_.png");
    private Snake snake;
    private SnakeJon snake1;
    private SnakeJon snake2;
    private BufferedImage backgroundImg;
    private int singleX, singleY, singeWidth, singleHeight;
    private int scorep1 = 0;
    private Score score;
    private Food food;
    Random rand = new Random();
    private PowerUp pup;
    private boolean darkMode=false;

    public Local_Multiplayer(Game game){
        super(game);
        snake1 = new SnakeJon(new Point(100,100),'R',"snake-graphics32.png");
        snake2 = new SnakeJon(new Point(300,100),'L',"snake2-graphics32.png");
        food = new Food(rand.nextInt(20)*32, rand.nextInt(20)*32 );
        pup= new PowerUp(new Point(rand.nextInt(20)*32, rand.nextInt(20)*32));
        loadBackground();
        score = new Score();
        score.readScoreFile(scorep1);

    }
    private void loadBackground() {
        backgroundImg = imageLoader.getLoadedImage();
        singeWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        singleHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        singleX = Game.GAME_WIDTH / 2 - singeWidth / 2;
        singleY = (int) (45 * Game.SCALE);

    }

    @Override
    public void update() {
        snake1.update();
        snake2.update();
        food.foodhit(snake1.getHitbox(), snake1);
    }
    public void setDarkMode(){
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                darkMode=true;

                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                darkMode=false;
            }
        }).start();
    }
    @Override
    public void draw(Graphics g) {
        if (darkMode) {
            g.setColor(Color.black);
            g.fillRect(0,0, 640, 640);
            snake1.drawHeadOnly(g);
            snake2.drawHeadOnly(g);
        } else {
            g.drawImage(backgroundImg, 0, 0, 640, 640, null);

            snake1.draw(g);
            snake2.draw(g);
            pup.draw(g);
            food.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (snake1.getDirection() != 'R') {
                    snake1.setDirection('L');
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (snake1.getDirection() != 'L') {
                    snake1.setDirection('R');
                }
                break;
            case KeyEvent.VK_UP:
                if (snake1.getDirection() != 'D') {
                    snake1.setDirection('U');
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snake1.getDirection() != 'U') {
                    snake1.setDirection('D');
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
