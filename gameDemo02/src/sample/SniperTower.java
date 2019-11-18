package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SniperTower extends Turret {
    public SniperTower(int x, int y){
        super(x, y);
        this.image = new Image("File:./image/tower2.png");
        this.scope = Config.SNIPER_TOWER_SCOPE;
        this.id = 2;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
    }
}
