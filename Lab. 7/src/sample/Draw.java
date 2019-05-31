package sample;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import java.awt.image.BufferedImage;
import static sample.Equation.calc;

public class Draw extends Task {
    private GraphicsContext gc;

    @Override
    protected Object call() throws Exception {
        BufferedImage bi = new BufferedImage(400, 350, BufferedImage.TYPE_INT_RGB);

        //punkty rysowane
        int xc = 0;
        int yc = 0;
        //punkty losowane
        double x = 0.-8.04;
        double y;

        for (int i = 0; i <= 400; i++) {
            if(this.isCancelled()) {
                break;
            }
            x = x + 0.04;
            y = 0.0 - 8.04;
            for (int j = 0; j <= 350; j++) {
                y = y + 0.04;
                xc = (int) ((x + 8) * 25);
                yc = -(int) (y * 25);
                if (calc(x, y)) {
                    bi.setRGB(xc , yc+150 , 16777215);
                }
                else {
                    bi.setRGB(xc , yc+150 , 0);
                }
            }
            updateProgress(i, 400);
            gc.drawImage(SwingFXUtils.toFXImage(bi,null), 0, 0);
        }
        return null;
    }

    public Draw(GraphicsContext gc) {
        this.gc = gc;
    }
}
