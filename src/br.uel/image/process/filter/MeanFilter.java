package src.br.uel.image.process.filter;

import src.br.uel.image.SimpleColorImage;
import src.br.uel.image.SimpleGrayImage;
import src.br.uel.image.SimpleImage;

public class MeanFilter implements Filter {
    @Override
    public SimpleImage filter(SimpleImage image, int radius){

        if(!image.isGray()){
            return filterColorImage(image, radius);
        }

        int w = image.getWidth(), h = image.getHeight();
        SimpleImage imageOut = new SimpleGrayImage(w, h);

        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                int sum = 0;
                int numPixels = 0;
                for(int i = -radius; i <= radius; i++){
                    for(int j = -radius; j <= radius; j++){
                        if(x+j < 0 || x+j > w-1 || y+i < 0 || y+i > h-1){
                            continue;
                        }

                        numPixels++;
                        sum += image.getPixel(x+j, y+i);

                    }
                }
                sum = sum/numPixels;
                imageOut.putPixel(x, y, sum);
            }
        }

        return imageOut;
    }

    private SimpleImage filterColorImage(SimpleImage image, int radius){
        int w = image.getWidth(), h = image.getHeight();
        SimpleColorImage imageOut = new SimpleColorImage(w, h);

        int sumR, sumG, sumB, numPixels;
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){

                sumR = 0;
                sumG = 0;
                sumB = 0;
                numPixels = 0; //don't count out of bounds pixels
                for(int i = -radius; i <= radius; i++){
                    for(int j = -radius; j <= radius; j++){
                        if(x+j < 0 || x+j > w-1 || y+i < 0 || y+i > h-1){
                            continue;
                        }
                        numPixels++;
                        int rgb[] = image.getRGB(x+j,y+i);
                        sumR += rgb[0];
                        sumG += rgb[1];
                        sumB += rgb[2];
                    }
                }
                sumR = sumR/numPixels;
                sumG = sumG/numPixels;
                sumB = sumB/numPixels;

                imageOut.setRGB(x, y, sumR, sumG, sumB);
            }
        }

        return imageOut;
    }
}
