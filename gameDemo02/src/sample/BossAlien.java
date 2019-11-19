package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

public class BossAlien extends Alien {
    public BossAlien() {
        super(Config.BOSS_ALIEN_HEALTH, Config.BOSS_ALIEN_SPEED, "File:./image/Boss-Alien.png");
    }

    private BossAlien(int health, int speed, String fileName) {
        super(Config.BOSS_ALIEN_HEALTH, Config.BOSS_ALIEN_SPEED, "File:./image/Pixel-Boss-Alien.png");;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image,x,y, size,size);
        gc.setFill(Color.RED);
        gc.fillRect(x,y, this.size, 5);
        gc.setFill(Color.BLUE);
        gc.fillRect(x,y,1.0*this.health/Config.BOSS_ALIEN_HEALTH * this.size, 5);
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Serif",14));
        gc.fillText( String.valueOf(health)+ "/" + String.valueOf(Config.BOSS_ALIEN_HEALTH), x , y);
    }
}
