package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

class Icon extends Rectangle {
    public int x;
    public int y;
    public int id;
    public int size;
    public Image image;
    public boolean isChoose = false;
    public int cost;

    public Icon(){};
    public Icon(int x, int y,int id){
        this.x= x;
        this.y = y;
        this.id = id;
        this.size = (int)Config.TILE_SIZE;

    }
    public void draw(GraphicsContext gc){
        gc.drawImage(image,x, y, size, size);
        gc.setFont(Font.font("Serif",20));
        gc.setFill(Color.YELLOW);
        gc.fillText("$" + String.valueOf(this.cost), x+25 , y+50);
    }

}
public class Store {
    public int Str_x;
    public int Str_y;
    public int num_icon = 3;
    public Icon[] icons;
    public int isBuying = 0;

    public Store(){
        this.Str_x = 127;
        this.Str_y = 505;
        icons = new Icon[num_icon];
        for(int i =0; i< num_icon; i++) {

            icons[i] = new Icon(Str_x  + i * (int) Config.TILE_SIZE + 5*i, Str_y , i+1);
            icons[i].image = new Image("File:./image/tower" + String.valueOf(i+1) +".png");
            if(i == 0)
                icons[i].cost = 40;
            else if(i == 1)
                icons[i].cost = 60;
            else
                icons[i].cost = 80;

        }
    }

    public void draw(GraphicsContext gc){
        for(int i =0; i< num_icon; i++)
            icons[i].draw(gc);
    }

    public void drawIconDrag(GraphicsContext gc, int mseX, int mseY){
        gc.setFill(Color.BLACK);

        //draw scope
        if(isBuying == 1 )
             gc.strokeOval(mseX  - Config.NORMAL_TOWER_SCOPE, mseY - Config.NORMAL_TOWER_SCOPE, Config.NORMAL_TOWER_SCOPE *2, Config.NORMAL_TOWER_SCOPE*2);
        if(isBuying == 2 )
            gc.strokeOval(mseX - Config.SNIPER_TOWER_SCOPE, mseY - Config.SNIPER_TOWER_SCOPE, Config.SNIPER_TOWER_SCOPE *2, Config.SNIPER_TOWER_SCOPE*2);
        if(isBuying == 3 )
            gc.strokeOval(mseX - Config.MACHINE_TOWER_SCOPE, mseY - Config.MACHINE_TOWER_SCOPE, Config.MACHINE_TOWER_SCOPE *2, Config.MACHINE_TOWER_SCOPE*2);

        //draw img icon
        gc.drawImage(new Image("File:./image/tower" + String.valueOf(isBuying)+ ".png"), mseX-25, mseY-25,50,50);

    }
}
