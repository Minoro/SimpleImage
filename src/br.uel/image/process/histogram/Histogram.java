package src.br.uel.image.process.histogram;

import src.br.uel.image.SimpleGrayImage;
import src.br.uel.image.SimpleImage;

public class Histogram {

    public int[] getHistogram(SimpleImage image){
        if( !image.isGray() && !image.isBinary()){
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

    public int[] getNormalizedHistogram(SimpleImage image){
        if( !image.isGray() && !image.isBinary()){
            throw new IllegalArgumentException("The image must be a SimpleGrayImage or a SimpleBinaryImage");
        }

        int w = image.getWidth(), h = image.getHeight();
        int total = w*h;
        int hist[] = new int[256];

        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                hist[image.getPixel(x,y)] += 1;
            }
        }

        for(int i = 0; i < 256; i++){
            hist[i] = hist[i]/total;
        }

        return hist;
    }

    public int[] getEqualizedHistogram(SimpleImage image){
        if( !image.isGray() && !image.isBinary()){
            throw new IllegalArgumentException("The image must be a SimpleGrayImage or a SimpleBinaryImage");
        }

        int w = image.getWidth(), h = image.getHeight();
        int hist[] = getHistogram(image);
        int table[] = new int[256];

        int sum = 0;
        int total = w*h;
        for (int i = 0; i < 256; i++) {
            sum += hist[i];
            table[i] = Math.round(sum*256.F/total);
        }

        return table;
    }

    public SimpleImage toImage(int hist[]){

        int h = 100;
        SimpleImage imageOut = new SimpleGrayImage(256, h);
        int max = 0;
        for(int i = 0; i < 256; i++){
            if(hist[i] > max){
                max = hist[i];
            }
        }

        float f = 1.F*max/h;
        for(int i = 0; i < 256; i++){
            for(int y = 0; y < hist[i]/f; y++){
                imageOut.putPixel(i, h-y-1, 255);
            }
        }

        return imageOut;
    }

}
