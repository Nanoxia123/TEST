package firstModule;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


 /* Соляризация изображения. Этот код реализует алгоритм соляризации изображения. Алгоритм проходит через каждый пиксель входного изображения, применяет к нему соляризацию и
        устанавливает новый цвет пикселя в выходном изображении.*/

public class SolarizeImage {

    public void solarizeImage() {
        try {
            // Загрузка входного изображения
            File inputFile = new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg");
            BufferedImage inputImage = ImageIO.read(inputFile);

            // Создание нового пустого изображения того же размера, что и входное изображение
            BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);

            // Задание порогового значения для соляризации
            int threshold = 128;

            // Обход каждого пикселя входного изображения и применение к нему соляризации
            for (int y = 0; y < inputImage.getHeight(); y++) {
                for (int x = 0; x < inputImage.getWidth(); x++) {
                    Color color = new Color(inputImage.getRGB(x, y));
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();

                    // Применение соляризации к каждой цветовой компоненте
                    if (red < threshold) {
                        red = 255 - red;
                    }
                    if (green < threshold) {
                        green = 255 - green;
                    }
                    if (blue < threshold) {
                        blue = 255 - blue;
                    }

                    // Задание соляризованного цвета пикселя в выходном изображении
                    Color outputColor = new Color(red, green, blue);
                    outputImage.setRGB(x, y, outputColor.getRGB());
                }
            }

            // Сохранение выходного изображения в файл
            File outputFile = new File("C:\\java\\metodiObrabotki-master\\img\\imagechangefilters.SolarizeImage.jpg");
            ImageIO.write(outputImage, "jpg", outputFile);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

}
