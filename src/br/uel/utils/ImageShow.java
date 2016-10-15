package br.uel.utils;

import br.uel.image.SimpleColorImage;
import br.uel.image.SimpleImage;
import br.uel.image.transform.geometric.GeometricTransform;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class ImageShow {

    int w = 500, h = 500;

    public ImageShow() {
    }

    public ImageShow(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public void show(SimpleImage image) {
        show(image.getBufferedImage(), "");
    }

    public void show(BufferedImage image) {
        show(image, "");
    }

    public void show(SimpleImage image, String title, boolean keepDimensions) {
        if (keepDimensions) {
            show(image.getBufferedImage(), title, true);
        } else {
            show(image.getBufferedImage(), title);
        }
    }

    public void show(BufferedImage image, String title, boolean keepDimensions) {
        if (keepDimensions) {
            this.w = image.getWidth();
            this.h = image.getHeight();
        }
        show(image, title);
    }

    public void show(SimpleImage image, int windowWidth, int windowHeight) {
        this.w = windowWidth;
        this.h = windowHeight;

        show(image.getBufferedImage());
    }

    public void show(BufferedImage image, int windowWidth, int windowHeight) {
        this.w = windowWidth;
        this.h = windowHeight;
        show(image);
    }


    public void show(SimpleImage image, String title) {
        show(image.getBufferedImage(), title);
    }

    public void show(BufferedImage image, String title) {
        SimpleImage simpleImage = new SimpleColorImage(image);
        GeometricTransform transform = new GeometricTransform();
        simpleImage = transform.scale(simpleImage, w, h);

        JFrame frame = new JFrame();
        frame.setSize(w, h);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        final JLabel label = new JLabel("X:0 | Y:0", new ImageIcon(simpleImage.getBufferedImage()), SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setHorizontalTextPosition(SwingConstants.CENTER);

        final SimpleImage finalSimpleImage = simpleImage;
        label.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (e.getX() >= 0 && e.getX() < w && e.getY() >= 0 && e.getY() < h) {
                    int[] rgb = finalSimpleImage.getRGB(e.getX(), e.getY());
                    label.setText("X:" + e.getX() + " |Y:" + e.getY() + " RGB:(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")");
                }
            }
        });

        frame.getContentPane().add(label);
        frame.setTitle(title);
        frame.pack();

        frame.setVisible(true);

    }


}