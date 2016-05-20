package src.br.uel.image.process.binary;

import src.br.uel.image.SimpleBinaryImage;
import src.br.uel.image.SimpleImage;

public class SimpleBinaryConverter {

    private static final int WHITE = 255;
    private static final int BLACK = 0;

    public SimpleImage binarize(SimpleImage image, int threshold){
        int w = image.getWidth(), h = image.getHeight();

        SimpleImage imageOut = new SimpleBinaryImage(w,h);
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                int color = image.getPixel(x,y);
                if(color > threshold){
                    imageOut.putPixel(x,y, WHITE);
                }else{
                    imageOut.putPixel(x,y, BLACK);
                }
            }
        }

        return imageOut;
    }






}
