package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Base {
    private int health;
    private int score;
    public int x;
    public int y;
    public Image image;

    public Base(int x, int y){
        this.health = Config.BASE_HEATLH;
        this.score = 160;
        this.x = x;
        this.y = y;
        this.image = new Image("File:./image/base.png");
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }

    public void buyTurret(int id_icon) {
        if (haveScore()) {
            if(id_icon == 1)
                score = score - 40;
            else if(id_icon == 2)
                score = score - 60;
            else
                score = score - 80;
        }
    }

    public void growScore(int amount){
        this.score += amount;
    }
    public void decreaseHealth(int amount) {
        this.health = (this.health - amount < 0) ? 0 : (this.health - amount);
    }

    public boolean isDead() {
        return health <= 0;
    }

    public boolean haveScore() {
        return score != 0;
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(image,x-50,y-50*3, 150,150);
        gc.setFill(Color.RED);
        gc.fillRect(x-50,y-50*2, 150, 10);
        gc.setFont(Font.font("Serif",20));
        gc.fillText(String.valueOf(this.health) +"/"+ String.valueOf(Config.BASE_HEATLH),x-50,y-50*2 );
        gc.setFill(Color.GREEN);
        gc.fillRect(x-50,y-50*2,150.0*this.health/Config.BASE_HEATLH , 10);
    }
}
