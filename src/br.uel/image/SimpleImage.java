package src.br.uel.image;

import java.awt.image.BufferedImage;

public abstract class SimpleImage {

    protected BufferedImage image = null;

    public void putPixel(int x, int y, int pixel) {
        int color = (pixel << 16) | (pixel << 8) | pixel;
        image.setRGB(x, y, color);
    }
    public int getPixel(int x, int y){
        return ( this.image.getRGB(x,y) >> 16) & 0xFF;
    }
    public abstract void setRGB(int x, int y, int rgb[]);

    public int[] getRGB(int x, int y) {
        int[] rgb = new int[3];
        rgb[0] = (image.getRGB(x,y) >> 16) & 0xFF;
        rgb[1] = (image.getRGB(x,y) >> 8) & 0xFF;
        rgb[2] = image.getRGB(x,y) & 0xFF;

        return rgb;
    }

    public abstract boolean isGray();
    public abstract boolean isBinary();
    public int getHeight(){
        return this.image.getHeight();
    }

    public int getWidth(){
        return this.image.getWidth();
    }

    public void setBufferedImage(BufferedImage image){
        this.image = image;
    }

    public BufferedImage getBufferedImage() {
        return this.image;
    }

}
