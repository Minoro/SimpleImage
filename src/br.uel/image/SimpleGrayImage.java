package br.uel.image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SimpleGrayImage extends SimpleImage {

    public SimpleGrayImage(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    }

    public SimpleGrayImage(BufferedImage image) {
        this.image = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        BufferedImage grayImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = grayImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        this.image = grayImage;
    }


    @Override
    public void setRGB(int x, int y, int rgb[]) {
        int color = (rgb[0] + rgb[1] + rgb[2]) / 3;
        this.putPixel(x, y, color);
    }

    @Override
    public boolean isGray() {
        return true;
    }

    @Override
    public boolean isBinary() {
        if (image.getType() == BufferedImage.TYPE_BYTE_BINARY) {
            return true;
        }
        return false;
    }
}
