package src.br.uel.image.process.binary;

import src.br.uel.image.SimpleImage;
import src.br.uel.image.process.histogram.Histogram;

public class Threshold {

    Histogram histogram;

    public Threshold(){
        histogram = new Histogram();
    }

    public int otsu(SimpleImage image){
        int total = image.getWidth()*image.getHeight();
        int histData[] = histogram.getHistogram(image);

        float sum = 0;
        for (int t=0 ; t<256 ; t++) sum += t * histData[t];

        float sumB = 0;
        int wB = 0;
        int wF = 0;

        float varMax = 0;
        int threshold = 0;

        for (int t=0 ; t<256 ; t++) {
            wB += histData[t];
            if (wB == 0) continue;

            wF = total - wB;
            if (wF == 0) break;

            sumB += (float) (t * histData[t]);

            float mB = sumB / wB;
            float mF = (sum - sumB) / wF;

            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

            if (varBetween > varMax) {
                varMax = varBetween;
                threshold = t;
            }
        }

        return threshold;
    }

    public int maxEntropy(SimpleImage image){
        float normalizedHistogram[] = histogram.getNormalizedHistogram(image);

        float histAcc[] = new float[256];
        float sum = 0;
        for (int i = 0; i < 256; i++) {
            sum += normalizedHistogram[i];
            histAcc[i] = sum;
        }

        double epsilon = Double.MIN_VALUE;
        float histB[] = new float[256];
        float histW[] = new float[256];
        for (int t = 0; t < 256; t++) {

            if(histAcc[t] > epsilon) {
                float hhb = 0;
                for (int i = 0; i <= t; i++) {
                    if(normalizedHistogram[i] > epsilon) {
                        hhb -= ((normalizedHistogram[i] / histAcc[t]) * (Math.log(normalizedHistogram[i] / histAcc[t])));
                    }
                }
                histB[t] = hhb;
            }else{
                histB[t] = 0;
            }

            float sumHJ = 1 - histAcc[t];
            if(sumHJ > epsilon) {
                float hhw = 0;
                for (int i = t + 1; i < 256; i++) {
                    if(normalizedHistogram[i] > epsilon) {
                        hhw -= ((normalizedHistogram[i] / sumHJ) * (Math.log(normalizedHistogram[i] / sumHJ)));
                    }
                }
                histW[t] = hhw;
            }else{
                histW[t] = 0;
            }
        }

        double jMax = histB[0] + histW[0];
        int tMax = 0;
        for (int t = 1; t < 256; ++t) {
            double j = histB[t] + histW[t];
            if (j > jMax) {
                jMax = j;
                tMax = t;
            }
        }

        return tMax;
    }

    public int bht(SimpleImage image){
        int hist[] = histogram.getHistogram(image);

        int center = 127;
        int pesoEsquerda = 0, pesoDireita = 0;
        for(int i = 0; i < center; i++){
            pesoEsquerda += hist[i];
        }
        for(int i = center; i < 256; i++){
            pesoDireita += hist[i];
        }

        int iE = 0, iD = 255;
        while(iE < iD){
            if(pesoEsquerda < pesoDireita){
                pesoDireita -= hist[iD];
                iD--;
            }else{
                pesoEsquerda -= hist[iE];
                iE++;
            }
        }

        center = (iE+iD)/2;
        return center;
    }

    public int peak(SimpleImage image){
        return peak(image, 0, 256);
    }

    public int peak(SimpleImage image, int start, int end){
        int hist[] = histogram.getHistogram(image);
        int max = hist[start];
        int i = start;
        while(i < end && hist[i]>=max){
            max = hist[i++];
            System.out.println(max);
        }

        return i;
    }
}
