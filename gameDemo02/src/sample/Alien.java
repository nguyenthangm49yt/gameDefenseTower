package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class Alien {

    public int x;
    public int y;
    public int size = Config.TILE_SIZE;
    public boolean isShot = false;
    public Direction direction;
    public int wayPointIndex = 0;
    public int health;
    private int attack;
    private int speed ;
    private String fileName;
    public Image image;
    public boolean isActack = false;

    public static final Point[] wayPoints = new Point[] {
            new Point(0 * 50, 5 * 50),
            new Point(1 * 50, 5 * 50),
            new Point(1 * 50, 1 * 50),
            new Point(3 * 50, 1 * 50),
            new Point(3 * 50, 9 * 50),
            new Point(5 * 50, 9 * 50),
            new Point(5 * 50, 8 * 50),
            new Point(11 * 50, 8 * 50),
            new Point(11 * 50, 5 * 50),
            new Point(16 * 50, 5 * 50),
            new Point(16 * 50, 3 * 50)
    };

    public Alien(int health, int speed, String fileName) {
        this.x = 0 ;
        this.y = 250;
        this.size = (int)Config.TILE_SIZE;
        this.direction = Direction.RIGHT;

        this.attack = 50;
        this.health = health;
        this.speed = speed;
        this.fileName = fileName;
        this.image = new Image(fileName);
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttack() {
        return this.attack;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void decreaseHealth(int change) {
        if((health - change) < 0)
            this.health = 0;
        else
            health -= change;
    }

    public void increaseHealth(int change) {
        health += change;
    }

    public void attack(Base b){
        b.decreaseHealth(attack);
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public Image getImage() {
        return this.image;
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(image,x,y, size,size);
        gc.setFill(Color.RED);
        gc.fillRect(x,y, this.size, 5);
        gc.setFill(Color.BLUE);
        gc.fillRect(x,y,this.health/500.0 * this.size, 5);
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Serif",16));
        gc.fillText( String.valueOf(health), x + 10, y);

    };

    public Point getNextWayPoint() {
        if (wayPointIndex < wayPoints.length-1)
            return wayPoints[++wayPointIndex];
        return null;
    }

    void calculateDirection() {
        // Tinh huong di tiep theo cho Object
        if (wayPointIndex >= wayPoints.length) {
            // Enemy den way point cuoi
            return;
        }

        Point currentWP = wayPoints[wayPointIndex];
        if (distance(x, y, currentWP.x, currentWP.y) <= speed) {
            x = currentWP.x;
            y = currentWP.y;
            Point nextWayPoint = getNextWayPoint();

            if (nextWayPoint == null) return;

            double deltaX = nextWayPoint.x - x;
            double deltaY = nextWayPoint.y - y;

            if (deltaX > speed) direction = Direction.RIGHT;
            else if (deltaX < -speed) direction = Direction.LEFT;
            else if (deltaY > speed) direction = Direction.DOWN;
            else if (deltaY <= -speed) direction = Direction.UP;
        }
    }

    public void update(){
        calculateDirection();

        switch (direction) {
            case UP:
                y -= this.speed;
                break;
            case DOWN:
                y += this.speed;
                break;
            case LEFT:
                x -= this.speed;
                break;
            case RIGHT:
                x += this.speed;
                break;
        }
    }

    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}

enum Direction {
    LEFT(180), UP(270), RIGHT(0), DOWN(90);

    int degree;

    Direction(int i) {
        degree = i;
    }

    int getDegree() {
        return degree;
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}