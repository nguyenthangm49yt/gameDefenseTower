package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet {
    public int damage;
    public int speed;
    public int x;
    public int y;
    public Alien alien;
    public Image img;
    public double distanceToAlien ;
    public boolean isActive;
    public int size = 15;

    public Bullet(){};

    public Bullet(int x, int y, Alien alien, int id_tower){
        this.x = x + Config.TILE_SIZE /2;
        this.y = y + Config.TILE_SIZE /2;
        this.isActive = true;
        this.alien = alien;
        this.distanceToAlien = Alien.distance(x,y, alien.x + alien.size/2, alien.y + alien.size/2);
        this.img = new Image("File:./image/bullet.png");
        if(id_tower == 1) {
            this.speed = Config.NORMAL_BULLET_SPEED;
            this.damage = Config.NORMAL_BULLET_DAMAGE;
        }
        else if(id_tower == 2){
            this.speed = Config.SNIPER_BULLET_SPEED;
            this.damage = Config.SNIPER_BULLET_DAMAGE;
        }
        else if(id_tower == 3){
            this.speed = Config.MACHINE_BULLET_SPEED;
            this.damage = Config.MACHINE_BULLET_DAMAGE;
        }


    }

    public void update(){

        this.distanceToAlien = Alien.distance(x,y, alien.x  + alien.size/2, alien.y + alien.size/2);
        double preDis = this.distanceToAlien;
        this.distanceToAlien -= speed;


        this.x = (int) (alien.x  + alien.size/2  - (alien.x  + alien.size/2 - x) * this.distanceToAlien/preDis);
        this.y = (int) (alien.y  + alien.size/2- (alien.y + alien.size/2 - y) * this.distanceToAlien/preDis);
        if(this.distanceToAlien <= 0){
            makeDamage(alien);
            isActive = false;
        }
    }

    public void setSpeed(int v){
        this.speed = v;
    }

    public void makeDamage(Alien alien){
        //sound
        Effect.playSound_bomb();
        // decrease health of alien
        alien.decreaseHealth(this.damage);
    }

    public void draw(GraphicsContext gc){
        update();
        gc.drawImage(this.img, this.x - size/2 , this.y - size/2, this.size, this.size);
    }
}
