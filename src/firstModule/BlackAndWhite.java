package firstModule;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


// Черно-белое изображение из цветного.
public class BlackAndWhite {
    public void convertToGrayscale() {
        try {
            File input = new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg");
            BufferedImage image = ImageIO.read(input);

            int width = image.getWidth();
            int height = image.getHeight();

            // Создаем новое черно-белое изображение
            BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

            // Копируем каждый пиксель из исходного изображения в новое черно-белое изображение
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Color c = new Color(image.getRGB(i, j));
                    int gray = (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
                    Color grayColor = new Color(gray, gray, gray);
                    grayImage.setRGB(i, j, grayColor.getRGB());
                }
            }

            // Сохраняем черно-белое изображение
            File output = new File("C:\\java\\metodiObrabotki-master\\img\\blackNwhite.jpg");
            ImageIO.write(grayImage, "jpg", output);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}