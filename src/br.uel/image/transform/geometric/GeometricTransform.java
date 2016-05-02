package src.br.uel.image.transform.geometric;

import src.br.uel.image.SimpleColorImage;
import src.br.uel.image.SimpleGrayImage;
import src.br.uel.image.SimpleImage;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class GeometricTransform {

    public SimpleImage scale(SimpleImage image, int t){
        double scale = 1;

        if (image.getWidth() > image.getHeight()) {
            scale = (t / (double) (image.getWidth()));
        } else {
            scale = (t / (double) (image.getHeight()));
        }

        int newWidth = (int)(scale*image.getWidth());
        int newHeight = (int)(scale*image.getHeight());

        BufferedImage imageOut = new BufferedImage((int) (scale * image.getWidth()),
                (int) (scale * image.getHeight()), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) imageOut.getGraphics();
        Image imageScaled = image.getBufferedImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
        g.drawImage(imageScaled, 0, 0, null);
        g.dispose();

        if (image.isGray()){
            return new SimpleGrayImage(imageOut);
        }
        return new SimpleColorImage(imageOut);
    }

    public SimpleImage scale(SimpleImage image, int newWidth, int newHeight){
        BufferedImage imageOut = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = (Graphics2D) imageOut.getGraphics();
        Image imageScaled = image.getBufferedImage().getScaledInstance(newWidth, newHeight, Image.SCALE_FAST);
        g.drawImage(imageScaled, 0, 0, null);
        g.dispose();

        if (image.isGray()){
            return new SimpleGrayImage(imageOut);
        }
        return new SimpleColorImage(imageOut);
    }

    public SimpleImage rotate(SimpleImage image, double angle){
        return rotate(image, angle, false);
    }

    public SimpleImage rotate(SimpleImage image, double angle, boolean truncate){
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(angle), image.getWidth() / 2.0, image.getHeight() / 2.0);

        Point2D[] aCorners = new Point2D[4];
        aCorners[0] = tx.transform(new Point2D.Double(0.0, 0.0), null);
        aCorners[1] = tx.transform(new Point2D.Double(image.getWidth(), 0.0), null);
        aCorners[2] = tx.transform(new Point2D.Double(0.0, image.getHeight()), null);
        aCorners[3] = tx.transform(new Point2D.Double(image.getWidth(), image.getHeight()), null);

        double dTransX = 0;
        double dTransY = 0;
        if(!truncate) {
            for (int i = 0; i < 4; i++) {
                if (aCorners[i].getX() < 0 && aCorners[i].getX() < dTransX)
                    dTransX = aCorners[i].getX();
                if (aCorners[i].getY() < 0 && aCorners[i].getY() < dTransY)
                    dTransY = aCorners[i].getY();
            }
        }

        AffineTransform translationTransform = new AffineTransform();
        translationTransform.translate(-dTransX, -dTransY);
        tx.preConcatenate(translationTransform);

        BufferedImage imageOut = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR).filter(image.getBufferedImage(), null);

        if(image.isGray()) {
            return new SimpleGrayImage(imageOut);
        }
        return new SimpleColorImage(imageOut);
    }

    public SimpleImage flipVertically(SimpleImage image){
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -image.getHeight());
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage imageOut = op.filter(image.getBufferedImage(), null);

        if(image.isGray()) {
            return new SimpleGrayImage(imageOut);
        }
        return new SimpleColorImage(imageOut);
    }

    public SimpleImage flipHorizontally(SimpleImage image){
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage imageOut = op.filter(image.getBufferedImage(), null);

        if(image.isGray()) {
            return new SimpleGrayImage(imageOut);
        }
        return new SimpleColorImage(imageOut);
    }

}
