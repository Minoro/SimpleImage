package br.uel.image.process;

import br.uel.image.SimpleGrayImage;
import br.uel.image.SimpleImage;

public class SimpleGrayConverter {

    public enum GrayTransformMethod {
        GRAY_AVG,
        GRAY_LUMINOSITY,
        GRAY_LIGTHNESS,
        GRAY_DECOMPOSITION_MAX,
        GRAY_DECOMPOSITION_MIN

    }

    ;

    public SimpleImage toGray(SimpleImage image, GrayTransformMethod method) {
        int w = image.getWidth(), h = image.getHeight();
        SimpleImage imageOut = new SimpleGrayImage(w, h);

        if (method == GrayTransformMethod.GRAY_LIGTHNESS) {
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int[] rgb = image.getRGB(x, y);
                    int r = rgb[0];
                    int g = rgb[1];
                    int b = rgb[2];

                    int max = Math.max(r, g);
                    max = Math.max(max, b);

                    int min = Math.min(r, g);
                    min = Math.min(min, b);

                    int color = (max + min) / 2;

                    imageOut.putPixel(x, y, color);
                }
            }
        } else if (method == GrayTransformMethod.GRAY_LUMINOSITY) {
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int[] rgb = image.getRGB(x, y);
                    int r = rgb[0];
                    int g = rgb[1];
                    int b = rgb[2];

                    int color = (int) Math.round(r * 0.21 + g * 0.72 + b * 0.07);

                    imageOut.putPixel(x, y, color);
                }
            }
        } else if (method == GrayTransformMethod.GRAY_DECOMPOSITION_MAX) {
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int[] rgb = image.getRGB(x, y);
                    int r = rgb[0];
                    int g = rgb[1];
                    int b = rgb[2];

                    int max = Math.max(r, g);
                    max = Math.max(max, b);

                    imageOut.putPixel(x, y, max);
                }
            }
        } else if (method == GrayTransformMethod.GRAY_DECOMPOSITION_MIN) {
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int[] rgb = image.getRGB(x, y);
                    int r = rgb[0];
                    int g = rgb[1];
                    int b = rgb[2];

                    int min = Math.min(r, g);
                    min = Math.min(min, b);

                    imageOut.putPixel(x, y, min);
                }
            }
        } else {
            return toGray(image);
        }

        return imageOut;
    }

    public SimpleImage toGray(SimpleImage image) {
        int w = image.getWidth(), h = image.getHeight();

        SimpleImage imageOut = new SimpleGrayImage(w, h);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb[] = image.getRGB(x, y);
                int color = (rgb[0] + rgb[1] + rgb[2]) / 3;

                imageOut.putPixel(x, y, color);
            }
        }

        return imageOut;
    }

}
