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
        if(image.isBinary()){
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

    /**
     * Adiciona duas imagens do mesmo tamanho e mesmo tipo.
     * @return SimpleImage, imagem resultante da adição
     */
    public SimpleImage add(SimpleImage image1, SimpleImage image2){

        if(image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()){
            throw  new IllegalArgumentException("As imagens devem ter o mesmo tamanho!");
        }

        if((!image1.isGray() || !image2.isGray())
                && (!image1.isBinary() || !image2.isBinary())
                && (!(image1 instanceof SimpleColorImage) || !(image2 instanceof SimpleColorImage))){
            throw new IllegalArgumentException("As imagem devem ser do mesmo tipo!");
        }

        int w = image1.getWidth(), h = image1.getHeight();
        SimpleImage imageOut;
        if(image1.isGray()){
            imageOut = new SimpleGrayImage(w, h);
        }else if(image1.isBinary()){
            imageOut = new SimpleBinaryImage(w, h);
        }else {
            imageOut = new SimpleColorImage(w, h);
        }
        int rgb[] = new int[3];
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                int rgb1[] = image1.getRGB(x, y);
                int rgb2[] = image2.getRGB(x, y);

                rgb[0] = rgb1[0] + rgb2[0];
                rgb[1] = rgb1[1] + rgb2[1];
                rgb[2] = rgb1[2] + rgb2[2];

                //evita valores maiores que 255
                rgb[0] =  rgb[0] > 255 ? 255 : rgb[0];
                rgb[1] =  rgb[1] > 255 ? 255 : rgb[1];
                rgb[2] =  rgb[2] > 255 ? 255 : rgb[2];

                imageOut.setRGB(x, y, rgb);
            }
        }
        return imageOut;
    }




}
