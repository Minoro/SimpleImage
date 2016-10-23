package br.uel.image.process.filter;

import br.uel.image.SimpleColorImage;

public interface ColorFilter {

    public abstract SimpleColorImage filter(SimpleColorImage image, int radius);

}
