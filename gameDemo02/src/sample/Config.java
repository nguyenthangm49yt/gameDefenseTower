package sample;

public final class Config {

    public static final String GAME_NAME = "Game Defense Tower";

    // kích thước 1 ô
    public static final int TILE_SIZE = 50;
    // số ô chiều ngang
    public static final long TILE_HORIZONTAL = 20;
    // số ô chiều dọc
    public static final long TILE_VERTICAL = 10;
    // chiều ngang của screen
    public static final long SCREEN_WIDTH = TILE_SIZE * TILE_HORIZONTAL ;
    // chiều dọc của screen
    public static final long SCREEN_HEIGHT = TILE_SIZE * TILE_VERTICAL + 70 ;

    //health of alien
    public static final int NORMAL_ALIEN_HEALTH = 1000;
    public static final int FAST_ALIEN_HEALTH = 700 ;
    public static final int TANKER_ALIEN_HEALTH = 1500;
    public static final int BOSS_ALIEN_HEALTH = 5000;

    //health of Base
    public static final int BASE_HEATLH = 1000;

    //speed of alien
    public static final int NORMAL_ALIEN_SPEED = 2;
    public static final int FAST_ALIEN_SPEED = 3;
    public static final int TANKER_ALIEN_SPEED = 1;
    public static final int BOSS_ALIEN_SPEED = 1;

    //coin of alien
    public static final int NORMAL_ALIEN_COIN = 50;
    public static final int FAST_ALIEN_COIN = 10;
    public static final int TANKER_ALIEN_COIN = 75;
    public static final int BOSS_ALIEN_COIN = 100;

    //tower scope
    public static int NORMAL_TOWER_SCOPE = 120;
    public static int SNIPER_TOWER_SCOPE = 170;
    public static int MACHINE_TOWER_SCOPE = 100;

    // speed of tower's bullet
    public static int NORMAL_BULLET_SPEED = 7;
    public static int SNIPER_BULLET_SPEED = 5;
    public static int MACHINE_BULLET_SPEED = 9;

    // damage of tower's bullet
    public static int NORMAL_BULLET_DAMAGE = 100;
    public static int SNIPER_BULLET_DAMAGE = 150;
    public static int MACHINE_BULLET_DAMAGE = 40;

}