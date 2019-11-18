package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FastAlien extends Alien {

    public FastAlien() {
        super(Config.FAST_ALIEN_HEALTH, Config.FAST_ALIEN_SPEED, "File:./image/Pixel-Alien-7.png");
    }


    private FastAlien(int health, int speed, String fileName) {
        super(Config.FAST_ALIEN_HEALTH, 1, "File:./image/Pixel-Alien-7.png");
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image,x,y, size,size);
        gc.setFill(Color.RED);
        gc.fillRect(x,y, this.size, 5);
        gc.setFill(Color.BLUE);
        gc.fillRect(x,y,1.0*this.health/Config.FAST_ALIEN_HEALTH * this.size, 5);
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Serif",14));
        gc.fillText( String.valueOf(health)+ "/" + String.valueOf(Config.FAST_ALIEN_HEALTH), x , y);
    }
}