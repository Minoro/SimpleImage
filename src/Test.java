import br.uel.image.SimpleImage;
import br.uel.utils.ImageShow;
import br.uel.utils.SimpleImageIO;

public class Test {

    public static void main(String args[]) {

        SimpleImage image = SimpleImageIO.open("/home/minoro/Imagens/insetos/70.jpg");

        ImageShow imageShow = new ImageShow();
        imageShow.show(image,"Teste", true);

    }

}
