package br.uel.utils;

import br.uel.image.SimpleColorImage;
import br.uel.image.SimpleGrayImage;
import br.uel.image.SimpleImage;

public class ChannelSplitter {

    /**
     * Separa uma imagem com 3 canais em um array contendo 3 imagens em tons de cinza, cada uma representa um canal
     * @param image SimpleImage - imagem a ser separada
     * @return Retorna um array,  cada posição representa um canal da image, se for uma imagem em
     * tons de cinza ou binária retorna um array com uma cópia da imagem
     */
    public SimpleImage[] split(SimpleImage image){
        SimpleImage channels[];
        int nChannels = 3;
        if(image.isGray() || image.isBinary()){
            nChannels = 1;
        }
        channels = new SimpleImage[nChannels];

        int w = image.getWidth(), h = image.getHeight();
        for(int i = 0; i < nChannels; i++){
            channels[i] = new SimpleGrayImage(w, h);
        }
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                int rgb[] = image.getRGB(x,y);
                for(int i = 0; i < nChannels; i++){
                    channels[i].putPixel(x,y,rgb[i]);
                }
            }
        }

        return channels;
    }

    /**
     * Faz a junção de 3 canais em uma unica imagem, será considerado que cada imagem tem apenas um canal
     * @return Retorna uma imagem com a combinação dos canais na ordem especificada
     */
    public SimpleImage merge(SimpleImage channel1, SimpleImage channel2, SimpleImage channel3){
        int w = channel1.getWidth(), h = channel1.getHeight();

        //verifica se tem o mesmo tamanho
        if (w != channel2.getWidth() || h != channel2.getHeight() || w != channel3.getWidth() || h != channel3.getHeight()){
            throw new IllegalArgumentException("Os canais devem ter a mesma dimensão");
        }

        SimpleImage imageOut = new SimpleColorImage(w, h);
        int rgb[] = new int[3];
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                rgb[0] = channel1.getPixel(x,y);
                rgb[1] = channel2.getPixel(x,y);
                rgb[2] = channel3.getPixel(x,y);
                imageOut.setRGB(x,y, rgb);
            }
        }

        return imageOut;
    }

    /**
     * Faz a junção de 3 imagens em uma unica
     * @param channels - Array de 3 posições de imagens com um canal a serem unidos em uma imagem
     * @return - combinação dos canais em uma imagem
     */
    public SimpleImage merge(SimpleImage channels[]){
        if (channels.length != 3){
            throw new IllegalArgumentException("Devem ser passados 3 canais");
        }

        return merge(channels[0], channels[1], channels[2]);

    }

}
