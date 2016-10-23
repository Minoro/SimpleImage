package br.uel.utils;

import br.uel.image.SimpleColorImage;
import br.uel.image.SimpleImage;
import br.uel.image.transform.geometric.GeometricTransform;

import java.util.LinkedList;
import java.util.List;

public class ImageStack {

    private int maxWidth = 0;
    private int maxHeigth = 0;
    private List<SimpleImage> stack;
    private boolean forceResize = false;
    private GeometricTransform transform;

    public ImageStack() {
        stack = new LinkedList<SimpleImage>();
    }

    public ImageStack(boolean forceResize) {
        this.forceResize = forceResize;
        if (forceResize) {
            transform = new GeometricTransform();
        }
        stack = new LinkedList<SimpleImage>();
    }

    public void add(SimpleImage image) {
        if (stack.isEmpty()) {
            maxHeigth = image.getHeight();
            maxWidth = image.getWidth();
        } else {
            if (!forceResize) {
                if (image.getWidth() != maxWidth || image.getHeight() != maxHeigth) {
                    throw new IllegalArgumentException("Images must be the same size!");
                }
            } else {
                boolean change = false;
                if (image.getWidth() > maxWidth) {
                    maxWidth = image.getWidth();
                    change = true;
                }
                if (image.getHeight() > maxHeigth) {
                    maxHeigth = image.getHeight();
                    change = true;
                }
                if (change) {
                    resizeStack();
                }
                image = transform.scale(image, maxWidth, maxHeigth);
            }
        }
        stack.add(image);
    }

    private void resizeStack() {
        List<SimpleImage> newStack = new LinkedList<SimpleImage>();
        for (SimpleImage image : stack) {
            newStack.add(transform.scale(image, maxWidth, maxHeigth));
        }
        stack = newStack;
    }

    public SimpleImage get(int index) {
        return stack.get(index);
    }

    public SimpleImage toHorizontalImage() {
        SimpleImage imageOut = new SimpleColorImage(maxWidth * stack.size(), maxHeigth);

        int xOut = 0;
        for (SimpleImage image : stack) {
            for (int x = 0; x < maxWidth; x++) {
                for (int y = 0; y < maxHeigth; y++) {
                    imageOut.setRGB(xOut, y, image.getRGB(x, y));
                }
                xOut++;
            }
        }

        return imageOut;
    }

    public SimpleImage toVerticalImage() {
        SimpleImage imageOut = new SimpleColorImage(maxWidth, maxHeigth * stack.size());

        int yOut = 0;
        for (SimpleImage image : stack) {
            for (int y = 0; y < maxHeigth; y++) {
                for (int x = 0; x < maxWidth; x++) {
                    imageOut.setRGB(x, yOut, image.getRGB(x, y));
                }
                yOut++;
            }
        }

        return imageOut;
    }

}

