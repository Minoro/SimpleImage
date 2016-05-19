package src.br.uel.image.process.histogram;

import src.br.uel.image.SimpleImage;

public class Histogram {

    public int[] getHistogram(SimpleImage image){
        if(!image.isGray() || !image.isBinary()){
            throw new IllegalArgumentException("The image must be a SimpleGrayImage or a SimpleBinaryImage");
        }

        int w = image.getWidth(), h = image.getHeight();
        int hist[] = new int[256];
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                hist[image.getPixel(x,y)] += 1;
            }
        }

        return hist;
    }

}
