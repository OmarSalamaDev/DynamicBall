import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {


/* >>>> global variables and objects  <<<< */


    // objects
    public DynamicBall A = new DynamicBall();
    public double rA = 30;
    public double mA = 1;
    public DynamicBall B = new DynamicBall();
    public double rB = 30;
    public double mB = 1;

    // collision velocity
    public double vxA;
    public double vxA_;
    public double vyA;
    public double vyA_;
    public double vxB;
    public double vxB_;
    public double vyB;
    public double vyB_;

    // mouse pointer position
    public double initialX;
    public double initialY;
    public double finalX;
    public double finalY;

    // motion animation timer
    public AnimationTimer timer;


    @Override
    public void start(Stage primaryStage) {

        // setup
        Pane root = new Pane();
        Scene primaryScene = new Scene(root, 800, 400, Color.BLACK);
        primaryStage.setScene(primaryScene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Bouncing Ball");
        primaryStage.show();

        // object A <Dynamic ball>
        A.setRadius(rA);
        A.setFill(Color.WHITE);
        A.setCenterX(50);
        A.setCenterY(primaryScene.getHeight() - A.getRadius());
        A.setSceneBorders(0, primaryScene.getHeight(), primaryScene.getWidth(), 0);
        root.getChildren().add(A);
        enableMotion(A);

        //object B <Dynamic ball>
        B.setRadius(rB);
        B.setFill(Color.RED);
        B.setCenterX(300);
        B.setCenterY(primaryScene.getHeight() - B.getRadius());
        B.setSceneBorders(0, primaryScene.getHeight(), primaryScene.getWidth(), 0);
        root.getChildren().add(B);
        enableMotion(B);

        // collision handling
        AnimationTimer collision = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (Math.sqrt(Math.pow((A.getCenterX() - B.getCenterX()), 2) + Math.pow((A.getCenterY() - B.getCenterY()), 2)) <= Math.abs(A.getRadius() + B.getRadius())) {

                    // velocity just before collision
                    vxA = A.getVx();
                    vyA = A.getVy();
                    vxB = B.getVx();
                    vyB = B.getVy();

                    // velocity just after collision
                    vxA_ = (mA-mB)*vxA/(mA+mB)+2*mB*vxB/(mA+mB);
                    vyA_ = (mA-mB)*vyA/(mA+mB)+2*mB*vyB/(mA+mB);
                    vxB_ = 2*mA*vxA/(mA+mB)-(mA-mB)*vxB/(mA+mB);
                    vyB_ = 2*mA*vyA/(mA+mB)-(mA-mB)*vyB/(mA+mB);

                    // check for overlapping 
                    if (Math.abs(Math.sqrt(Math.pow((A.getCenterX() - B.getCenterX()), 2) + Math.pow((A.getCenterY() - B.getCenterY()), 2)) - (A.getRadius() + B.getRadius())) > 0) {

                        // set separation to prevent overlapping
                        double dx = (B.getCenterX() - A.getCenterX()) * 0.02;
                        double dy = (B.getCenterY() - A.getCenterY()) * 0.02;

                        // add separation
                        A.setCenterX(A.getCenterX() - dx);
                        A.setCenterY(A.getCenterY() - dy);
                        B.setCenterX(B.getCenterX() + dx);
                        B.setCenterY(B.getCenterY() + dy);
                    }

                    // apply the new velocity
                    A.setVx(vxA_);
                    A.setVy(vyA_);
                    B.setVx(vxB_);
                    B.setVy(vyB_);
                }
            }
        };
        collision.start();

        // mouse events
        primaryScene.setOnMousePressed(e -> {
            initialX = e.getSceneX();
            initialY = e.getSceneY();
        });
        primaryScene.setOnMouseReleased(e -> {
            finalX = e.getSceneX();
            finalY = e.getSceneY();
            A.setVelocity(initialX, initialY, finalX, finalY);
        });
    }


/* >>>> enable motion for the object  <<<< */


    public void enableMotion(DynamicBall ball) {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ball.updatePosition();
            }
        };
        timer.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
