package br.uel.image.process.filter;

import br.uel.image.SimpleImage;

public interface Filter {

    public abstract SimpleImage filter(SimpleImage image, int radius);

}
