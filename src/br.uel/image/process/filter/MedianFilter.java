package br.uel.image.process.filter;

import br.uel.image.SimpleColorImage;
import br.uel.image.SimpleGrayImage;
import br.uel.image.SimpleImage;

import java.util.Arrays;

public class MedianFilter implements Filter, ColorFilter {

    @Override
    public SimpleImage filter(SimpleImage image, int radius){
        if(!image.isGray()){
            if (image instanceof SimpleColorImage){
                return filter((SimpleColorImage) image, radius);
            }
            throw new IllegalArgumentException("Tipe de imagem não suportado!");
        }
        int w = image.getWidth(), h = image.getHeight();
        SimpleImage imageOut = new SimpleGrayImage(w,h);

        int sizeBuffer = (2*radius+1)*(2*radius+1);
        int buffer[] = new int[sizeBuffer];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int index = 0;
                int numPixels = 0;
                for (int i = -radius; i <= radius ; i++) {
                    for (int j = -radius; j <= radius; j++) {
                        if(x+j < 0 || x+j > w-1 || y+i < 0 || y+i > h-1){
                            continue;
                        }
                        numPixels++;
                        buffer[index++] = image.getPixel(x+j,y+i);
                    }
                }
                Arrays.sort(buffer);
                imageOut.putPixel(x,y, buffer[numPixels/2]);
            }
        }

        return imageOut;
    }

    /*
    *   Median Filter based on Vector Median Filter (VMF)
    *   Get the pixel (RGB) that has the less minimum accumulated distance
    *   Don't create new colors in the output image
    * */
    @Override
    public SimpleColorImage filter(SimpleColorImage image, int radius){
        int w = image.getWidth(), h = image.getHeight();
        SimpleColorImage imageOut = new SimpleColorImage(w,h);

        int sizeBuffer = (2*radius+1)*(2*radius+1);
        int buffer[][] = new int[sizeBuffer][3];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int index = 0;
                for (int i = -radius; i <= radius ; i++) {
                    for (int j = -radius; j <= radius; j++) {
                        if(x+j < 0 || x+j > w-1 || y+i < 0 || y+i > h-1){
                            continue;
                        }
                        buffer[index++] = image.getRGB(x + j, y + i);
                    }
                }
                imageOut.setRGB(x, y, buffer[minDistance(buffer, sizeBuffer)]);
            }
        }
        return imageOut;
    }

    /*
    *  Find the min. distance in the window filtered
    *
    *  @return int - index of the min. distance
    *
    * */
    private int minDistance(int rgb[][], int sizeBuffer){
        int sum, index = 0, min = Integer.MAX_VALUE;

        for (int i = 0; i < sizeBuffer; i++) {
            sum = 0;
            for (int j = 0; j < sizeBuffer; j++) {
                sum += Math.abs(rgb[i][0] - rgb[j][0]);
                sum += Math.abs(rgb[i][1] - rgb[j][1]);
                sum += Math.abs(rgb[i][2] - rgb[j][2]);
            }
            if (sum < min){
                min = sum;
                index = i;
            }
        }

        return index;
    }
}
