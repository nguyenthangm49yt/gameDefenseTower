package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public abstract class Turret {
    public int x;
    public int y;
    public int id = 0;
    public int scope = Config.NORMAL_TOWER_SCOPE;
    public int numShotAlien = 1;
    private int attack;
    public int cost;
    public Image image;
    public List<Bullet> bullets;

    public Turret(int x, int y) {

        this.x = x;
        this.y = y;
        this.attack = 1 ;
        this.cost = 40;
        bullets = new ArrayList<Bullet>();

    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getAttack() {
        return attack;
    }

    public int getCost() {
        return cost;
    }

    public Image getImage() {
        return this.image;
    }

    public void attack(Alien a) {
        a.decreaseHealth(attack);
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(this.getImage(),x ,y,(int)Config.TILE_SIZE,(int)Config.TILE_SIZE);

    }

    public void drawFire(GraphicsContext gc , List<Alien> alienList){
            int dem = numShotAlien;
            if(alienList.isEmpty()){
                bullets.clear();
            }
            else {
                for (Alien alien : alienList) {
                    if (alien.isShot && Alien.distance(alien.x, alien.y, this.x, this.y) <= this.scope) {

                       //vẽ bullet by line from tower to alien
                        //gc.strokeLine(alien.x + 25, alien.y + 25, this.x + 25, this.y + 25);

                        if (bullets.size() < 1) bullets.add(new Bullet(x, y, alien, id));
                            //vẽ bom
                        else if (!bullets.isEmpty()) {
                            for (Bullet i : bullets) {
                                if (i.isActive)
                                    i.draw(gc);
                                else {
                                    bullets.remove(i);
                                    break;
                                }
                            }
                        }

                        dem--;
                        if (dem > 0) continue;
                        else break;
                    }
                }
            }
    }


}