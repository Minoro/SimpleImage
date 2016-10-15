package src.br.uel.image.process.filter;

import src.br.uel.image.SimpleColorImage;

/*
*   Given a RGB Image, for each pixel:
*   max = MAX(r,g,b)
*   if channel < max : channel = 0
*
* */
public class MaxRGBFilter implements ColorFilter{

    /**
     *
     * @param image - RGB image
     * @param radius - useless
     * @return RGB image - if channel < max : channel = 0
     */
    @Override
    public SimpleColorImage filter(SimpleColorImage image, int radius){
        int w = image.getWidth(), h = image.getHeight();

        SimpleColorImage imageOut = new SimpleColorImage(w, h);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb[] = image.getRGB(x,y);
                int max = Math.max(Math.max(rgb[0],rgb[1]),rgb[2]); // Max value of pixel

                if(rgb[0] < max){ rgb[0] = 0; }
                if(rgb[1] < max){ rgb[1] = 0; }
                if(rgb[2] < max){ rgb[2] = 0; }

                imageOut.setRGB(x,y, rgb);
            }
        }

        return imageOut;
    }
}
