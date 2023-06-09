package firstModule;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


 /* Гистограмма яркости. Для создания гистограммы программа проходит по всем пикселям изображения и подсчитывает количество пикселей с каждой возможной яркостью.
        Затем она рисует прямоугольники на новом изображении (размером 256x256), соответствующие значениям яркости из гистограммы. */

public class BrightnessHistogram {
    public void brightnessHistogram() throws IOException {
        String inputImagePath = "C:\\java\\metodiObrabotki-master\\img\\img.jpg";
        BufferedImage inputImage = ImageIO.read(new File(inputImagePath));

        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        int[] brightnessHistogram = new int[256];

        // Проходим по всем пикселям изображения и для каждого пикселя вычисляем яркость (взвешенную сумму значений каналов RGB)
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = inputImage.getRGB(j, i);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb & 0xFF);
                int brightness = (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
                brightnessHistogram[brightness]++;
            }
        }

        // Создаем новое изображение для гистограммы
        int histogramWidth = 256;
        int histogramHeight = 256;
        BufferedImage histogramImage = new BufferedImage(histogramWidth, histogramHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = histogramImage.getGraphics();

        // Находим максимальное значение в гистограмме (для нормализации значений)
        int maxBrightnessCount = 0;
        for (int j : brightnessHistogram) {
            if (j > maxBrightnessCount) {
                maxBrightnessCount = j;
            }
        }

        // Рисуем прямоугольники на изображении, соответствующие значениям яркости из гистограммы
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, histogramWidth, histogramHeight);
        for (int i = 0; i < brightnessHistogram.length; i++) {
            int y = (int) ((double) brightnessHistogram[i] / maxBrightnessCount * (histogramHeight - 1));
            g.setColor(Color.BLACK);
            g.drawLine(i, histogramHeight - 1, i, histogramHeight - y - 1);
        }

        /* Гистограмма яркости. Для каждого из 256 возможных значений яркости будет указано количество пикселей в изображении с такой яркостью.
        for (int i = 0; i < 256; i++) {
            System.out.println(i + ": " + brightnessHistogram[i]);
        } */

        // Сохраняем изображение гистограммы в файл
        String outputImagePath = "C:\\java\\metodiObrabotki-master\\img\\histogram.jpg";
        ImageIO.write(histogramImage, "jpg", new File(outputImagePath));
    }
}
