package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MachineTower extends Turret {
    public MachineTower(int x, int y){
        super(x, y);
        this.image = new Image("File:./image/tower3.png");
        this.scope = Config.MACHINE_TOWER_SCOPE;
        this.id = 3;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
    }
}
