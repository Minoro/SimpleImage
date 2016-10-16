package br.uel.image.process;

import br.uel.image.SimpleGrayImage;
import br.uel.image.SimpleImage;

public class SimpleProcess {

    public SimpleImage toGray(SimpleImage image){
        int w = image.getWidth(), h = image.getHeight();

        SimpleImage imageOut = new SimpleGrayImage(w,h);

        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                int rgb[] = image.getRGB(x,y);
                int color = (rgb[0]+rgb[1]+rgb[2])/3;

                imageOut.putPixel(x,y, color);
            }
        }

        return imageOut;
    }

}
