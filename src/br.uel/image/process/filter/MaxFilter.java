package src.br.uel.image.process.filter;

import src.br.uel.image.SimpleGrayImage;
import src.br.uel.image.SimpleImage;

/**
 * Replace the central pixel of the kernel by the maximum value
 */
public class MaxFilter implements Filter{


    @Override
    public SimpleImage filter(SimpleImage image, int radius){
        if (!image.isGray() && !(image instanceof SimpleGrayImage)){
            throw new IllegalArgumentException("Tipe de imagem não suportado!");
        }

        int w = image.getWidth(), h = image.getHeight();
        SimpleImage imageOut = new SimpleGrayImage(w, h);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int max = Integer.MIN_VALUE;
                int pixel = 0;

                for (int i = -radius; i <= radius ; i++) {
                    for (int j = -radius; j <= radius; j++) {
                        if(x+j < 0 || x+j > w-1 || y+i < 0 || y+i > h-1){
                            continue;
                        }

                        pixel = image.getPixel(x+j, y+i);
                        max = Math.max(max, pixel);
                    }
                }
                imageOut.putPixel(x,y, max);
            }
        }

        return imageOut;
    }
}
