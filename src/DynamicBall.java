import javafx.animation.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class DynamicBall extends Circle {


/* >>>> class attributes <<<< */


    private static final double GRAVITY = 0.3; // Acceleration due to gravity
    private static final double X_FRICTION = 0.99; // floor (x-axis) friction
    private static final double ENERGY_LOSS = 0.7; // energy loss due to collision

    private double Vx = 0.0;
    private double Vy = 0.0;


    private double lowerSceneBorder;
    private double upperSceneBorder;
    private double leftSceneBorder;
    private double rightSceneBorder;

    private double xi;
    private double yi;
    private double xf;
    private double yf;








/* >>>> constructors <<<< */


    public DynamicBall(double v2) {
        super(v2);
    }
    public DynamicBall(double v2, Paint paint) {
        super(v2, paint);
    }
    public DynamicBall() {
    }
    public DynamicBall(double v, double v1, double v2) {
        super(v, v1, v2);
    }
    public DynamicBall(double v, double v1, double v2, Paint paint) {
        super(v, v1, v2, paint);
    }



/* >>>> getters and setters <<<< */


    // Scene borders setter
    public void setSceneBorders(double upperSceneBorder, double lowerSceneBorder, double rightSceneBorder, double leftSceneBorder) {
        this.upperSceneBorder = upperSceneBorder;
        this.lowerSceneBorder = lowerSceneBorder;
        this.rightSceneBorder = rightSceneBorder;
        this.leftSceneBorder = leftSceneBorder;
    }

    // velocity getters and setters
    public double getVx() {
        return Vx;
    }
    public void setVx(double Vx) {
        this.Vx = Vx;
    }
    public double getVy() {
        return Vy;
    }
    public void setVy(double Vy) {
        this.Vy = Vy;
    }
    public void setVelocity(double Vx, double Vy) {
        this.Vx = Vx;
        this.Vy = Vy;
        enableMotion();
    }





/* >>>> Update position function <<<< */


    public void updatePosition() {

        // Apply gravity
        Vy += GRAVITY;
        Vx *= X_FRICTION;

        if (Math.abs(Vx) < 0.1) Vx = 0.0;
        if (Math.abs(Vy) < 0.3) Vy = 0.0;


        // Update position
        this.setCenterX(this.getCenterX() + Vx);
        this.setCenterY(this.getCenterY() + Vy);


        // Bounce off left walls
        if (this.getCenterX() - this.getRadius() <= leftSceneBorder) {
            Vx = -Vx * ENERGY_LOSS;
            this.setCenterX(leftSceneBorder + this.getRadius());
        }
        // Bounce off right walls
        if (this.getCenterX() + this.getRadius() >= rightSceneBorder) {
            Vx = -Vx * ENERGY_LOSS;
            this.setCenterX(rightSceneBorder - this.getRadius());
        }
        // Bounce off the floor
        if (this.getCenterY() + this.getRadius() >= lowerSceneBorder) {
            this.setCenterY(lowerSceneBorder - this.getRadius());
            Vy = -Math.abs(Vy)  * ENERGY_LOSS; // Reverse vertical velocity with friction
        }
        // Bounce off the ceiling
        if (this.getCenterY() - this.getRadius() <= upperSceneBorder) {
            this.setCenterY(upperSceneBorder + this.getRadius());
            Vy = Math.abs(Vy)  * ENERGY_LOSS; // Reverse vertical velocity with friction
        }

    }






/* >>>> ....... <<<< */




/* >>>> ....... <<<< */





/* >>>> enable motion function <<<< */


    public void enableMotion() {
        // Animation Timer to update the ball's position
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //System.out.println(Vy);
                updatePosition();
            }
        };
        timer.start();
    }


/* >>>> .......... <<<< */


    public void setInitialMousePosition(double xi, double yi) {
        this.xi = xi;
        this.yi = yi;
    }

    public void setFinalMousePosition(double xf, double yf) {
        this.xf = xf;
        this.yf = yf;
        Vx = (xf - xi) * -0.05;
        Vy = (yf - yi) * 0.05;
        enableMotion();
    }






    /* >>>> ..... <<<< */

}
