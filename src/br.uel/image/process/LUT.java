package br.uel.image.process;

import br.uel.image.SimpleGrayImage;
import br.uel.image.SimpleImage;

public class LUT {

    public SimpleImage applay(SimpleImage image, int table[]) {
        int w = image.getWidth(), h = image.getHeight();

        SimpleImage imageOut = new SimpleGrayImage(w, h);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                imageOut.putPixel(x, y, table[image.getPixel(x, y)]);
            }
        }

        return imageOut;
    }

}
