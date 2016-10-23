package br.uel.utils;

import br.uel.image.SimpleColorImage;
import br.uel.image.SimpleImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SimpleImageIO {

    public static SimpleImage open(String path) {
        try {
            return new SimpleColorImage(ImageIO.read(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void save(SimpleImage image, String path) {
        try {
            new File(path).mkdir();
            ImageIO.write(image.getBufferedImage(), "jpg", new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
