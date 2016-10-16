package br.uel.image.process.edge;

import br.uel.image.SimpleImage;

public interface EdgeDetector {

    public abstract SimpleImage findEdges(SimpleImage image);

}
