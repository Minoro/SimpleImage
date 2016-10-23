package br.uel.image.process;


import br.uel.image.SimpleGrayImage;
import br.uel.image.SimpleImage;

public class Convolution {

    public SimpleImage convolve(SimpleImage image, int[][] kernel){
        return convolve(image, convertKernelToDouble(kernel));
    }

    public SimpleImage convolve(SimpleImage image, double[][] kernel){
        int widthImage = image.getWidth(), heightImage = image.getHeight();
        int heightKernel = kernel[0].length;

        int radius = (int) Math.floor((heightKernel/2));

        SimpleImage imageOut = new SimpleGrayImage(widthImage, heightImage);

        for (int y = 0; y < heightImage; y++) {
            for (int x = 0; x < widthImage; x++) {
//                int color = image.getPixel(x,y);
                double color = 0;
                for (int i = -radius; i <=radius; i++) {
                    for (int j = -radius; j <= radius; j++) {
                        if(x+j < 0 || x+j > widthImage-1 || y+i < 0 || y+i > heightImage-1){
                            continue;
                        }
                        color += (image.getPixel(x+j,y+i)*kernel[radius+i][radius+j]);

                    }
                }
                if(color > 255){
                    color = 255;
                }else if(color < 0 ){
                    color = 0;
                }
                imageOut.putPixel(x,y,(int)Math.round(color));
            }
        }
        return imageOut;
    }

    private double[][] convertKernelToDouble(int[][] kernel){
        int length = kernel[0].length;//square kernel
        double[][] kernelDouble = new double[length][length];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                kernelDouble[i][j] = (double) kernel[i][j];
            }
        }

        return kernelDouble;
    }

}
