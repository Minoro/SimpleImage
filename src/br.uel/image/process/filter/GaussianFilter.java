package src.br.uel.image.process.filter;

import src.br.uel.image.SimpleImage;
import src.br.uel.image.process.Convolution;

public class GaussianFilter implements Filter {

    private Convolution convolution = new Convolution();

    @Override
    public SimpleImage filter(SimpleImage image, int radius) {
        return filter(image, radius, (float) Math.ceil(radius / 3.0F));
    }

    public SimpleImage filter(SimpleImage image, int radius, float sigma){
        double mask[][] = buildGaussianKernel(radius, sigma);
        return convolution.convolve(image, mask);
    }

    private double[][] buildGaussianKernel(int radius, float sigma){
        int size = 2*radius+1;
        double div = 2*Math.pow(sigma,2);
        double constPart = 1/(div*Math.PI);
        double mask[][] = new double[size][size];

        int ix = 0;
        double sum = 0;
        for (int i = -radius; i <= radius; i++) {
            int jy = 0;
            for (int j = -radius; j <= radius; j++) {
                double exp = ((i*i)+(j*j))/div;
                double gaussian = constPart*Math.pow(Math.E, -exp);

                mask[ix][jy++] = gaussian;
                sum += gaussian;
            }
            ix++;
        }

        //normaliza o kernel para evitar escurecer a imagem
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mask[i][j] = mask[i][j]/sum;
            }
        }

        return mask;
    }
}
