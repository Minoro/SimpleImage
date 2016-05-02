package src.br.uel.image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SimpleBinaryImage extends SimpleImage{

    public static final int BLACK_VALUE = 0;
    public static final int WHITE_VALUE = 255;

    public SimpleBinaryImage(int width, int heigth){
        this.image = new BufferedImage(width, heigth, BufferedImage.TYPE_BYTE_BINARY);
    }

    public SimpleBinaryImage(BufferedImage image){
        this.image = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        BufferedImage binaryImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics g = binaryImage.createGraphics();
        g.drawImage(this.image, 0, 0, null);
        g.dispose();

        this.image = binaryImage;
    }

    public void setBlackPixel(int x, int y){
        this.putPixel(x, y, BLACK_VALUE);
    }

    public void setWhitePixel(int x, int y){
        this.putPixel(x, y, WHITE_VALUE);
    }

    @Override
    public void setRGB(int x, int y, int[] rgb){
        int pixel = (rgb[0]+rgb[1]+rgb[2])/3;
        if(pixel > 127){
            this.putPixel(x,y,WHITE_VALUE);
        }else{
            this.putPixel(x,y,BLACK_VALUE);
        }
    }

    @Override
    public boolean isGray(){
        return false;
    }

    @Override
    public boolean isBinary(){
        return true;
    }
}
