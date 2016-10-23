package br.uel.image;

import java.awt.image.BufferedImage;

public class SimpleColorImage extends SimpleImage {

    public SimpleColorImage(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public SimpleColorImage(int width, int height, int type) {
        this.image = new BufferedImage(width, height, type);
    }

    public SimpleColorImage(BufferedImage image) {
        this.image = image;
    }

    public void setRGB(int x, int y, int r, int g, int b) {
        int color = (r << 16) | (g << 8) | b;
        this.image.setRGB(x, y, color);
    }

    public void setRGB(int x, int y, int rgb[]) {
        int color = (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
        this.image.setRGB(x, y, color);
    }

    public boolean isGray() {
        if (image.getType() == BufferedImage.TYPE_BYTE_GRAY || image.getType() == BufferedImage.TYPE_USHORT_GRAY) {
            return true;
        }
        return false;
    }

    public boolean isBinary() {
        if (image.getType() == BufferedImage.TYPE_BYTE_BINARY) {
            return true;
        }
        return false;
    }
}
