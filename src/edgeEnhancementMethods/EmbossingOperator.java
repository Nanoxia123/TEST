package edgeEnhancementMethods;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/* Cоздаем пустое изображение с такими же размерами как и оригинальное, проходимся по каждому пикселю оригинального изображения, вычисляем его яркость и
        устанавливаем новый цвет в зависимости от этой яркости.  */
public class EmbossingOperator {

    public void operatorTisnenia() {
        try {
            // Загружаем изображение
            BufferedImage image = ImageIO.read(new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg"));

            // Создаем пустое изображение с такими же размерами как и оригинальное
            BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

            // Проходимся по каждому пикселю и применяем оператор тиснения
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int pixel = image.getRGB(x, y);
                    Color color = new Color(pixel);

                    // Вычисляем яркость пикселя
                    int brightness = (int) (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue());

                    // Устанавливаем новый цвет пикселя
                    if (brightness < 128) {
                        result.setRGB(x, y, Color.BLACK.getRGB());
                    } else {
                        result.setRGB(x, y, Color.WHITE.getRGB());
                    }
                }
            }

            // Сохраняем результат
            ImageIO.write(result, "png", new File("C:\\java\\metodiObrabotki-master\\img\\imagechangefilters.OperatorTisnenia.jpg"));
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
