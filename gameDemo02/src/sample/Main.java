package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Pane root;
    public static Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    public static GraphicsContext gc = canvas.getGraphicsContext2D();
    public static Stage primaryStage;
    // main
    public static void main(String[] args) {
        Application.launch(args);
    }

    // start
    public void start(Stage primary_Stage) throws Exception{
        primaryStage = primary_Stage;
        canvas.setFocusTraversable(true);
        // show main menu
        mainMenu();

        primaryStage.setTitle(Config.GAME_NAME);
        primaryStage.show();



    }
    public static void StartPlayGame(Stage pt){
        // sound
        Effect.playSound_battle_theme();

        primaryStage = pt;

        final Controller gameControl = new Controller(gc);
        root = new Pane();
        canvas.setFocusTraversable(true);
        root.getChildren().add(canvas);

        // mouse events
        canvas.setOnMousePressed(gameControl.gamefield :: mouseDownHandle);
        canvas.setOnMouseMoved(gameControl.gamefield :: mouseMoveHandle);

        primaryStage.setResizable(false);
        primaryStage.setTitle(Config.GAME_NAME);

        primaryStage.setScene(new Scene(root));
        gameControl.start();
    }

    public static void mainMenu(){

        Effect.playSound_mainmenu();
        Pane root1 = new Pane();

        //them background menu
        ImageView background = new ImageView("file:image/Menu.png");
        background.setFitHeight(Config.SCREEN_HEIGHT);
        background.setFitWidth(Config.SCREEN_WIDTH);

        // them button play, exit game
        Button playButton = CreateButton("PlayButton.png", 365,330,250,70);
        Button exitButton = CreateButton("exit.png",365, 430, 250,70);

        // xử lí sự kiện button
        playButton.setOnAction(event->{
            Effect.stop_sound();
            StartPlayGame(primaryStage);

        });
        exitButton.setOnAction(event -> {primaryStage.close(); } );

        //hien thi main menu
        root1.getChildren().addAll(canvas,background, playButton, exitButton);
         primaryStage.setScene(new Scene(root1));
    }

    public static Button CreateButton(String name, int x, int y, int w, int h){
        Button b = new Button();
        b.setLayoutX(x);
        b.setLayoutY(y);
        ImageView image_b = new ImageView("File:./image/" + name );
        image_b.setFitWidth(w);
        image_b.setFitHeight(h);
        b.setGraphic(image_b);
        return b;
    }

}
