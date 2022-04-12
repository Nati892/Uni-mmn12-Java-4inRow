import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GameDisk extends Circle {

    private static int RADIUS = 20;

    GameDisk( Paint c) {
        super( RADIUS);
        this.setFill( c);
    }
}
