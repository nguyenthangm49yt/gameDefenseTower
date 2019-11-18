package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NormalTower extends Turret {
    public NormalTower(int x, int y){
        super(x, y);
        this.image = new Image("File:./image/tower1.png");
        this.scope = Config.NORMAL_TOWER_SCOPE;
        this.id = 1;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
    }
}
