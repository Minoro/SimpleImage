package src.br.uel.image.process.filter;

import src.br.uel.image.SimpleGrayImage;
import src.br.uel.image.SimpleImage;

public class MeanFilter implements Filter {
    @Override
    public SimpleImage filter(SimpleImage image, int radius){

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
}
