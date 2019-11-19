package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public final class Controller extends AnimationTimer {

    private final GraphicsContext gc;
    private volatile long tick;
    public int i = 1 ;
    public static boolean isGame;
    public static gameField gamefield;

    public Controller(GraphicsContext gc){
        this.gc = gc;
        //khởi tạo new game
        newGame();
    }

    void render(GraphicsContext gc) {
        gamefield.draw(gc);

    }

    // game loop
    @Override
    public void handle(long now) {

        if(isGame && gamefield.base.getHealth() >0) {

            // draw a new frame, as fast as possible.
            gamefield.update();
            render(gc);
        }
        else {
            checkEndGame();
        }
    }

    public void newGame(){
        this.gamefield = new gameField();
        this.isGame = true;
    }

    @Override
    public void start() {
            super.start();
    }

    @Override
    public void stop() {
            super.stop();
         //   Main.primaryStage.close();
    }

    public void renderOver(){
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Serif",48));
        gc.fillText("GAME OVER",Config.SCREEN_WIDTH/3 ,Config.SCREEN_HEIGHT/2 - 50);
        gc.setFont(Font.font("Serif",24));
        gc.fillText("Your Score: " + String.valueOf(gamefield.base.getScore()),Config.SCREEN_WIDTH/2 - 100,Config.SCREEN_HEIGHT/2);

    }
    public void renderWin(){
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Serif",48));
        gc.fillText("CONGRATULATION !",Config.SCREEN_WIDTH/3 - 80,Config.SCREEN_HEIGHT/2 - 50);
        gc.setFont(Font.font("Serif",30));
        gc.fillText("Your Score: " + String.valueOf(gamefield.base.getScore()),Config.SCREEN_WIDTH/2 - 100,Config.SCREEN_HEIGHT/2);

    }

    public void checkEndGame(){
        stop(); // stop game loop
        //draw board end game
        Image end_game_board = new Image("File:./image/result-board.png");
        gc.drawImage(end_game_board, 180,100, 635,350);

        //render status
        if(gamefield.base.getHealth() <= 0) {
            Effect.playSound_loser();
            renderOver();
        }
        else
        {
            Effect.playSound_winner();
            renderWin();
        }
        // các loại button trên board
        Button play_b = Main.CreateButton("Play-Again.png", 400,330,50,50);
        Button exit_b  = Main.CreateButton("quit.png",480, 330, 50,50);
        Button home_b = Main.CreateButton("home.png", 560,330,50,50);

        Pane root2 = new Pane();
        root2.getChildren().addAll(Main.canvas,play_b,home_b,exit_b);

        //xử lí sự kiện các button
        exit_b.setOnAction(event -> {
            Main.primaryStage.close(); // thoát game
        });

        play_b.setOnAction(event -> {
            Effect.stop_sound();
            Main.StartPlayGame(Main.primaryStage); // chơi màn mới
        });

        home_b.setOnAction(event -> {
            // trở lại main menu
            Effect.stop_sound();
            Main.mainMenu();
        });
        Main.primaryStage.setScene(new Scene(root2));

    }
}
