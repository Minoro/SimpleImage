package src.br.uel.image.process.binary;

import src.br.uel.image.SimpleBinaryImage;
import src.br.uel.image.SimpleImage;

public class MorphologicalOperation {

    public SimpleImage dilation(SimpleImage image){
        int w = image.getWidth(), h = image.getHeight();
        SimpleImage imageOut = new SimpleBinaryImage(w, h);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                for (int i = -1; i <= 1 ; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if(x+j < 0 || x+j > w-1 || y+i<0 || y+i>h-1){continue;}
                        if(image.getPixel(x+j,y+i) == 255){
                            imageOut.putPixel(x, y, 255);
                        }
                    }
                }
            }
        }
        return imageOut;
    }

    public SimpleImage erosion(SimpleImage image){
        int w = image.getWidth(), h = image.getHeight();
        SimpleImage imageOut = new SimpleBinaryImage(w, h);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if(image.getPixel(x,y) == 255) {
                    imageOut.putPixel(x,y,255);
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (x + j < 0 || x + j > w - 1 || y + i < 0 || y + i > h - 1) {
                                continue;
                            }
                            if (image.getPixel(x + j, y + i) == 0) {
                                imageOut.putPixel(x, y, 0);
                            }
                        }
                    }
                }
            }
        }
        return imageOut;
    }

    public SimpleImage opening(SimpleImage image){
        SimpleImage imageOut = erosion(image);
        imageOut = dilation(imageOut);
        return imageOut;
    }

    public SimpleImage closing(SimpleImage image){
        SimpleImage imageOut = dilation(image);
        imageOut = erosion(imageOut);
        return imageOut;
    }


}
