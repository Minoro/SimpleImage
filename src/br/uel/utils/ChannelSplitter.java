package src.br.uel.utils;


import src.br.uel.image.SimpleGrayImage;
import src.br.uel.image.SimpleImage;

public class ChannelSplitter {

    /**
     * Separa uma imagem RGB em um array contendo 3 imagens em tons de cinza, cada uma representa um canal
     * @param image SimpleImage - imagem a ser separada
     * @return Retorna um array, se a imagem for RGB cada posição representa um canal da image, se for uma imagem em
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



}
