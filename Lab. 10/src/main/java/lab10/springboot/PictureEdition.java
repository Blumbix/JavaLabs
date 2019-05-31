package lab10.springboot;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class PictureEdition {

    public static BufferedImage getGrayImage(BufferedImage image) {
        BufferedImage result = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = result.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return result;
    }

    public static BufferedImage getBlurredImage(BufferedImage image, int val){
        int size = val * 2 + 1;
        float weight = 1.0f / (size * size);
        float[] data = new float[size * size];
        for (int i = 0; i < data.length; i++) {
            data[i] = weight;
        }
        Kernel kernel = new Kernel(size, size, data);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        BufferedImage i = op.filter(image, null);

        return i;
    }

    public static BufferedImage cropImage(BufferedImage img, Rectangle rect) {
        BufferedImage result = img.getSubimage(rect.x, rect.y, rect.width, rect.height);
        return result;
    }

    public static BufferedImage scaleImage(BufferedImage img, double scale) {

        int w = (int)(img.getWidth() * scale);
        int h = (int)(img.getWidth() * scale);

        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();

        at.scale(scale, scale);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        after = scaleOp.filter(img, after);

        return after;
    }
}
