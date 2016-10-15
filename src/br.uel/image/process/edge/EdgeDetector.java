package src.br.uel.image.process.edge;

import src.br.uel.image.SimpleImage;

public interface EdgeDetector {

    public abstract SimpleImage findEdges(SimpleImage image);

}
