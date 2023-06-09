package firstModule;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

 /* Псевдораскрашивание изображения. Этот код читает изображение в формате JPEG и создает новое изображение, в котором каждый пиксел заменяется цветом на основе его яркости.
        Сначала каждый пиксель конвертируется в оттенки серого, затем значение яркости преобразуется в цвет. В этом примере мы используем синий, зеленый и красный цвета для
        представления темных, средних и светлых областей изображения соответственно. */

public class PseudoColoring {
    public void pseudoColoring() throws IOException {
        BufferedImage originalImage = ImageIO.read(new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg"));
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Проходим по каждому пикселю в изображении
        for(int x=0; x<width; x++) {
            for(int y=0; y<height; y++) {
                Color color = new Color(originalImage.getRGB(x, y));

                // Конвертируем цвет RGB в оттенки серого
                int grayValue = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

                // Отображаем оттенок серого в соответствующий цвет
                if(grayValue < 85) {
                    newImage.setRGB(x, y, Color.BLUE.getRGB());
                } else if(grayValue < 170) {
                    newImage.setRGB(x, y, Color.GREEN.getRGB());
                } else {
                    newImage.setRGB(x, y, Color.RED.getRGB());
                }
            }
        }

        ImageIO.write(newImage, "jpg", new File("C:\\java\\metodiObrabotki-master\\img\\imagechangefilters.PseudoColoring.jpg"));
    }
}
