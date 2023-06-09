package firstModule;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


/* Повышение и понижение контраста. Здесь мы просто добавили метод adjustContrast в класс и вызываем его дважды с разными значениями коэффициента контрастности для увеличения и
        уменьшения контрастности изображения соответственно. */

public class ImageContrast {
    public void imageContrast() {
        try {
            // Загружаем изображение
            File input = new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg");
            BufferedImage image = ImageIO.read(input);

            // Увеличиваем контрастность
            adjustContrast(image, 3f);

            // Сохраняем измененный файл изображения
            File output = new File("C:\\java\\metodiObrabotki-master\\img\\img_contrast_high.jpg");
            ImageIO.write(image, "jpg", output);

            // Понижаем контрастность
            adjustContrast(image, 0.1f);

            // Сохраняем измененный файл изображения
            output = new File("C:\\java\\metodiObrabotki-master\\img\\img_contrast_low.jpg");
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void adjustContrast(BufferedImage image, float contrast) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Изменяем контрастность пикселей
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                float red = ((color.getRed() / 255.0f - 0.5f) * contrast + 0.5f) * 255.0f;
                float green = ((color.getGreen() / 255.0f - 0.5f) * contrast + 0.5f) * 255.0f;
                float blue = ((color.getBlue() / 255.0f - 0.5f) * contrast + 0.5f) * 255.0f;
                int newRed = (int) Math.max(0, Math.min(255, red));
                int newGreen = (int) Math.max(0, Math.min(255, green));
                int newBlue = (int) Math.max(0, Math.min(255, blue));
                Color newColor = new Color(newRed, newGreen, newBlue);
                image.setRGB(x, y, newColor.getRGB());
            }
        }
    }
}
