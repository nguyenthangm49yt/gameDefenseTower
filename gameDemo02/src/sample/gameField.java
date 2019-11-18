package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class gameField  {

    public Timer timer1 = new Timer();
    public int mseX ;
    public int mseY;
    public int isChose = 0;
    private int map_width = (int)Config.TILE_HORIZONTAL;
    private int map_height = (int)Config.TILE_VERTICAL;
    public  Image image ;
    public int [][] map ;
    public Store store;
    public Base base;
    public List<Turret> turretList = new ArrayList<Turret>();
    public List<Alien> alienList = new ArrayList<Alien>();
    public int level = 1;
    public int num_alien_wave = (int)(Math.random() * 4 + 3);

    public void initWaveEnemy(int level, int num_alien){

        System.out.println("wave" + level);
        for(int i =0; i< num_alien; i++){
           if(level == 1) alienList.add(new NormalAlien());
           else if(level == 2) alienList.add(new FastAlien());
           else if(level == 3) alienList.add(new TankerAlien());
           else if(level == 4) alienList.add(new BossAlien());
        }

        int k = 0;
        for(Alien i : alienList){
            i.x -= k;
            k += 80;
        }
    }

    public gameField(){
        base = new Base(16 * 50, 3 * 50); // khởi tạo base
        this.image  = new Image("File:./image/MapCropped.png");//ảnh map
        Turret b = new NormalTower(17 * 50, 3 * 50);
        turretList.add(b);

        initWaveEnemy(level,num_alien_wave); // khởi tạo wave alien

        store = new Store(); // tạo shop
        map = new int[map_height][map_width]; // tạo ma trận trên sân chơi
        try {
            // đọc file txt là ma trận 2 chiều chứa đường đi
            FileReader fileReader = new FileReader("C:\\Users\\ADMIN\\IdeaProjects\\gameDemo02-12-11(1)\\gameDemo02\\src\\field.txt");

            Scanner in = new Scanner(fileReader);
            while (in.hasNextInt()) {
                for (int y = 0; y < map_height; y++) {
                    for (int x = 0; x < map_width; x++) {
                        map[y][x] = in.nextInt();
                    }
                }
            }
            in.close();
        }catch (Exception e){

        }

    }

    public void draw(GraphicsContext gc){

        // vẽ nền
            gc.drawImage(image,0,0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
            store.draw(gc);

        //score
            gc.setFont(Font.font("Serif",22));
            gc.setFill(Color.YELLOW);
            gc.fillText("Coin: " + String.valueOf(base.getScore()),5,Config.SCREEN_HEIGHT- 20);

        // vẽ đường
            Image image_road = new Image("File:./image/red.png");

            for (int i =0 ; i< map_height; i++)
                for (int j = 0; j< map_width; j++){
                    if(map[i][j] == 0) gc.drawImage(image_road, j* Config.TILE_SIZE, i* Config.TILE_SIZE, Config.TILE_SIZE, Config.TILE_SIZE);

                }

        // vẽ kéo icon
        if(isChose == 1 )
                store.drawIconDrag(gc,mseX,mseY);
        //vẽ alien
        for(Alien i : alienList){
            i.draw(gc);
        }

        // vẽ tower
            if(turretList.size() !=0){
                for (Turret i : turretList) {
                    i.draw(gc);
                    i.drawFire(gc, alienList);
                 }
            }
            base.draw(gc);

    }

    public void update() {

        if (alienList.size() == 0 && level != 4){

            initWaveEnemy(++level, num_alien_wave);

            timer1.run();

        }else if(alienList.size() == 0 && level == 4){
            System.out.println("game over");
            Controller.isGame = false;
        }
        else {
            attackTower();
            xuliban();
            for (Alien i : alienList) {
                i.update();
            }
        }
    }

    public void xuliban(){
       /** duyệt danh sách alien, nếu alien chết thì cộng điểm
        nếu còn sống, duyệt danh sách tháp xem alien có nằm trong tầm bắn của tháp nào không*/
        for(Alien j : alienList){
            if(j.isDead())
            {
                alienList.remove(j);
                if(j instanceof NormalAlien)
                    base.growScore(Config.NORMAL_ALIEN_COIN);
                else if(j instanceof FastAlien)
                    base.growScore(Config.FAST_ALIEN_COIN);
                else if(j instanceof TankerAlien)
                    base.growScore(Config.TANKER_ALIEN_COIN);
                else if(j instanceof BossAlien)
                    base.growScore(Config.BOSS_ALIEN_COIN);
                break;
            }
            for(Turret i : turretList){

                if(Alien.distance(j.x + j.size/2 , j.y + j.size/2, i.x + Config.TILE_SIZE /2, i.y + Config.TILE_SIZE /2) <= i.scope) {
                    j.isShot = true;
                    break;
                }
                else {
                    j.isShot = false;
                }
            }
        }
    }

    // xử lí khi quái tới đươc base
    public void attackTower(){
        for(Alien j : alienList){
            if(j.x >= 16*50 && j.x <= 17*50 && j.y <= 3*50 && j.y >= 2*50){
                base.decreaseHealth(100);
               j.health = 0;
            }
        }
    }

    final void mouseDownHandle(MouseEvent mouseEvent){
        int _mseX =  (int)mouseEvent.getX();
        int _mseY  = (int)mouseEvent.getY();

        //chọn icon trong menu shop
        if(isChose == 0 && base.getScore() >= 40){
            for(int i=0; i< store.icons.length; i++) {
                if ((_mseX > store.Str_x + 5*i + i*50) && _mseX < (store.Str_x + 5*i + (i + 1) * 50) && _mseY > store.Str_y && _mseY < store.Str_y + 50) {
                isChose = 1;
                store.isBuying = i + 1;
                }
            }
        }

        // đặt tháp trên map
        else if (isChose == 1 && _mseY < store.Str_y  ){
            int check = map[(int)_mseY/50][(int)_mseX/50];
            if(check == 1) {

                //đặt đúng vị trí

                if (store.isBuying == 1 && base.getScore() >= 40) {
                    Turret a = new NormalTower((int) _mseX / 50 * 50, (int) _mseY / 50 * 50);
                    turretList.add(a);
                    base.buyTurret(store.isBuying);
                }
                else if (store.isBuying == 2 && base.getScore() >= 60) {
                    Turret a = new SniperTower((int) _mseX / 50 * 50, (int) _mseY / 50 * 50);
                    turretList.add(a);
                    base.buyTurret(store.isBuying);
                }
                else if (store.isBuying == 3 && base.getScore() >= 80) {
                    Turret a = new MachineTower((int) _mseX / 50 * 50, (int) _mseY / 50 * 50);
                    turretList.add(a);
                    base.buyTurret(store.isBuying);
                }


                isChose = 0;
            }
            else{

                // đặt sai vị trí
                System.out.println("CHỌN SAI CHỖ KÌA !") ;
            }
        }

    }

    final void mouseMoveHandle(MouseEvent mouseEvent){
        this.mseX = (int)mouseEvent.getX();
        this.mseY = (int)mouseEvent.getY();
    }

}
