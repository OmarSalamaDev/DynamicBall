import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class DynamicBall extends Circle {


/* >>>> class attributes <<<< */


    // constant attributes
    private static final double GRAVITY = 0.3; // acceleration due to gravity
    private static final double X_FRICTION = 0.99; // x-axis friction
    public static final double ENERGY_LOSS = 0.7; // energy loss

    // velocity attributes
    private double Vx = 0.0;
    private double Vy = 0.0;

    // scene borders relative to the class
    private static double lowerSceneBorder;
    private static double upperSceneBorder;
    private static double leftSceneBorder;
    private static double rightSceneBorder;


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
    }
    public void setVelocity(double xi, double yi, double xf, double yf) {
        Vx = (xf - xi) * -0.05;
        Vy = (yf - yi) * 0.05;
    }


/* >>>> Update position function <<<< */


    public void updatePosition() {


        // add gravity and friction
        Vy += GRAVITY;
        Vx *= X_FRICTION;


        // to set velocity to zero at a certain (negligible) value
        if (Math.abs(Vx) < 0.2) setVx(0);
        if (Math.abs(Vy) < 0.2) setVy(0);

        // update object's position
        this.setCenterX(this.getCenterX() + Vx);
        this.setCenterY(this.getCenterY() + Vy);


        // Bounce off scene borders
        if (this.getCenterX() - this.getRadius() <= leftSceneBorder) {
            Vx = -Vx * ENERGY_LOSS;
            this.setCenterX(leftSceneBorder + this.getRadius());
        }
        if (this.getCenterX() + this.getRadius() >= rightSceneBorder) {
            Vx = -Vx * ENERGY_LOSS;
            this.setCenterX(rightSceneBorder - this.getRadius());
        }
        if (this.getCenterY() + this.getRadius() >= lowerSceneBorder) {
            this.setCenterY(lowerSceneBorder - this.getRadius());
            Vy = -Math.abs(Vy)  * ENERGY_LOSS;
        }
        if (this.getCenterY() - this.getRadius() <= upperSceneBorder) {
            this.setCenterY(upperSceneBorder + this.getRadius());
            Vy = Math.abs(Vy)  * ENERGY_LOSS;
        }

    }



/* >>>> ..... <<<< */



/* >>>> ..... <<<< */



/* >>>> ..... <<<< */



/* >>>> ..... <<<< */



}
