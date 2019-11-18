package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

public class TankerAlien extends Alien {
    public TankerAlien(){
        super(Config.TANKER_ALIEN_HEALTH, Config.TANKER_ALIEN_SPEED, "File:./image/Pixel-Tank-Alien.png");
    }
    private TankerAlien(int health, int speed, String fileName) {
        super(Config.TANKER_ALIEN_HEALTH, Config.TANKER_ALIEN_SPEED, "File:./image/Pixel-Tank-Alien.png");
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image,x,y, size,size);
        gc.setFill(Color.RED);
        gc.fillRect(x,y, this.size, 5);
        gc.setFill(Color.BLUE);
        gc.fillRect(x,y,1.0*this.health/Config.TANKER_ALIEN_HEALTH * this.size, 5);
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Serif",14));
        gc.fillText( String.valueOf(health)+ "/" + String.valueOf(Config.TANKER_ALIEN_HEALTH), x , y);
    }
}
