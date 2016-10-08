package src.br.uel.image.process.filter;

import src.br.uel.image.SimpleColorImage;

public interface ColorFilter {

    public abstract SimpleColorImage filter(SimpleColorImage image, int radius);

}
