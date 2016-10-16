package br.uel.image.process.edge;

import br.uel.image.SimpleGrayImage;
import br.uel.image.SimpleImage;
import br.uel.image.process.Convolution;

public class RobertsCrossEdgeDetector implements EdgeDetector{

    private static final int[][] HORIZONTAL_KERNEL = {{0, 0,  0},
                                                      {0, 0,  1},
                                                      {0, -1, 0}};

    private static final int[][] VERTICAL_KERNEL = {{0, 0,  0},
                                                    {0, 1,  0},
                                                    {0, 0, -1}};

    private Convolution convolution = new Convolution();

    @Override
    public SimpleImage findEdges(SimpleImage image) {
        int w = image.getWidth(), h = image.getHeight();

        SimpleImage imageOut = new SimpleGrayImage(w,h);
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                int gx = 0;
                int gy = 0;
                int pixel = 0;
                for(int i = -1; i <= 1; i++){
                    for(int j = -1; j <= 1; j++){
                        if(x+j < 0 || x+j > w-1 || y+i < 0 || y+i > h-1){
                            continue;
                        }
                        gx += image.getPixel(x+j,y+i)* HORIZONTAL_KERNEL[1+i][1+j];
                        gy += image.getPixel(x+j,y+i)* VERTICAL_KERNEL[1+i][1+j];

                    }
                }

                pixel = (int) Math.sqrt((gx*gx)+(gy*gy));
                if(pixel > 255){
                    pixel = 255;
                }else if(pixel < 0 ){
                    pixel = 0;
                }
                imageOut.putPixel(x,y,pixel);
            }
        }

        return imageOut;
    }
}
