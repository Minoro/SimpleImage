package br.uel.utils;

import br.uel.image.SimpleBinaryImage;
import br.uel.image.SimpleColorImage;
import br.uel.image.SimpleGrayImage;
import br.uel.image.SimpleImage;

/**
 * Realiza operações matemáticas sobre imagens
 */
public class SimpleImageMath {

    /**
     * Adiciona um valor para cada pixel da imagem, se for uma imagem RGB adiciona o valor a todos os canais
     * @param image SimpleImage, imagem de entrada
     * @param value int, valor a ser adicionado
     * @return SimpleImage, imagem com valor adicionado
     */
    public SimpleImage add(SimpleImage image, int value){
        if(image instanceof SimpleBinaryImage){
            return image;
        }
        if(image instanceof SimpleColorImage){
            return this.add((SimpleColorImage) image, value, value, value);
        }

        int w = image.getWidth(), h = image.getHeight();
        SimpleImage imageOut = new SimpleGrayImage(w, h);
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                int pixel = image.getPixel(x,y) + value;
                if(pixel > 255){
                    pixel = 255;
                }
                imageOut.putPixel(x, y, pixel);
            }
        }
        return imageOut;
    }

    /**
     * Adiciona valores para cada canal de uma imagem RGB para todos os pixels
     * @return SimpleImage, imagem com os valores modificados
     */
    public SimpleColorImage add(SimpleColorImage image, int r, int g, int b){
        int w = image.getWidth(), h = image.getHeight();
        SimpleColorImage imageOut = new SimpleColorImage(w, h);

        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                int rgb[] = image.getRGB(x, y);
                //Evita valores acima do 255
                int pixelR = r+rgb[0] > 255 ? 255 : r+rgb[0];
                int pixelG = r+rgb[1] > 255 ? 255 : g+rgb[1];
                int pixelB = r+rgb[2] > 255 ? 255 : b+rgb[2];

                imageOut.setRGB(x,y, pixelR, pixelG, pixelB);

            }
        }
        return imageOut;
    }


}
