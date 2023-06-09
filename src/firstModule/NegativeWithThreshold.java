package firstModule;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class NegativeWithThreshold {
    public void negativeWithThreshold() {
        try {
            // Загрузка исходного изображения
            File input = new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg");
            BufferedImage image = ImageIO.read(input);

            // Определение порогового значения
            int threshold = 128;

            // Применяем негативное преобразование с порогом
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    // Получаем значения цвета каждого пикселя
                    Color color = new Color(image.getRGB(x, y));
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();

                    // Конвертируем в оттенки серого
                    int gray = (red + green + blue) / 3;

                    // Инвертируем цвета, если значение оттенка серого выше порога
                    if (gray > threshold) {
                        red = 255 - red;
                        green = 255 - green;
                        blue = 255 - blue;
                        color = new Color(red, green, blue);
                        image.setRGB(x, y, color.getRGB());
                    }
                }
            }

            // Сохраняем выходное изображение
            File output = new File("C:\\java\\metodiObrabotki-master\\img\\negative_threshold_image.jpg");
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
