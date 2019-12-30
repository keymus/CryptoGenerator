import java.io.Serializable;

/**
 * Объект для тестирования фанкций
 */
public class TestObject implements Serializable {
    private String x = "x";
    private String y = "y";

    @Override
    public String toString() {
        return x + " " + y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
