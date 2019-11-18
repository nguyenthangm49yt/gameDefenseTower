package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

public class NormalAlien extends Alien {
    public NormalAlien(){
        super(Config.NORMAL_ALIEN_HEALTH, Config.NORMAL_ALIEN_SPEED, "File:./image/Pixel-Normal-Alien.png");
    }
    public NormalAlien(int health, int speed, String filename){
        super(Config.NORMAL_ALIEN_HEALTH, Config.NORMAL_ALIEN_SPEED, "File:./image/Pixel-Normal-Alien.png");
    }


    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image,x,y, size,size);
        gc.setFill(Color.RED);
        gc.fillRect(x,y, this.size, 5);
        gc.setFill(Color.BLUE);
        gc.fillRect(x,y,1.0*this.health/Config.NORMAL_ALIEN_HEALTH * this.size, 5);
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Serif",14));
        gc.fillText( String.valueOf(health)+ "/" + String.valueOf(Config.NORMAL_ALIEN_HEALTH), x , y);
    }
}
