import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {


    DynamicBall ball = new DynamicBall();
    DynamicBall secondaryBall = new DynamicBall();


    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene primaryScene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(primaryScene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Bouncing Ball");
        primaryStage.show();

    // primary ball setup
        ball.setRadius(20);
        ball.setFill(Color.WHITE);
        ball.setCenterX(50);
        ball.setCenterY(primaryScene.getHeight() - ball.getRadius());
        ball.setSceneBorders(0, primaryScene.getHeight(), primaryScene.getWidth(), 0);
        root.getChildren().add(ball);

    // secondary ball setup
        /*
        secondaryBall.setRadius(20);
        secondaryBall.setFill(Color.RED);
        secondaryBall.setCenterX(primaryScene.getWidth() /3);
        secondaryBall.setCenterY(primaryScene.getHeight() - primaryBall.getRadius());
        secondaryBall.setSceneBorders(0, primaryScene.getHeight(), primaryScene.getWidth(), 0);
        secondaryBall.enableMotion();
        root.getChildren().add(secondaryBall);*/


        //primaryBall.getBoundsInParent().intersects(primaryBall.getBoundsInParent());



        primaryScene.setOnMousePressed(e -> {
            ball.setInitialMousePosition(e.getSceneX(), e.getSceneY());
        });
        primaryScene.setOnMouseReleased(e -> {
            ball.setFinalMousePosition(e.getSceneX(), e.getSceneY());
        });

        primaryScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE){
                ball.setCenterY(200);
                ball.enableMotion();
            }
        });

        /*primaryScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                ball.setVx(50);
                ball.enableMotion();
            }
        });*/





    }

    public static void main(String[] args) {
        launch(args);
    }
}
