package filters;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/* Меньше/Больше шума. В данном примере мы используем метод applyFilter(), который принимает исходное изображение и ядро фильтрации в виде двумерного массива.
        Метод проходит по каждому пикселу изображения, применяет ядро фильтрации к окрестности пикселя с помощью свертки и записывает результат в новое изображение.
        Затем мы сохраняем отфильтрованное изображение в файл с помощью класса ImageIO. */

public class LowAndHighPassFilter {
    public void lowPassFilter() {
        try {
            // Загрузка изображения
            BufferedImage image = ImageIO.read(new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg"));

            // Размер ядра для фильтрации
            int kernelSize = 3;

            // Создание ядра фильтра
            double[][] kernel = new double[kernelSize][kernelSize];
            for (int i = 0; i < kernelSize; i++) {
                for (int j = 0; j < kernelSize; j++) {
                    kernel[i][j] = 1.0 / (kernelSize * kernelSize);
                }
            }

            // Применение фильтра
            BufferedImage filteredImage = applyFilter(image, kernel);

            // Сохранение отфильтрованного изображения
            String outputImagePath = "C:\\java\\metodiObrabotki-master\\img\\LowPassFilter.jpg";
            File outputFile = new File(outputImagePath);
            ImageIO.write(filteredImage, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void highPassFilter() {
        try {
            // Загрузка изображения
            BufferedImage image = ImageIO.read(new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg"));

            // Создание ядра фильтра (например, ядро фильтра Собеля для выделения горизонтальных границ)
            double[][] kernel = {{-1,-2,-1},{0,0,0},{1,2,1}};

            // Применение фильтра
            BufferedImage filteredImage = applyFilter(image, kernel);
            // Сохранение отфильтрованного изображения
            String outputImagePath = "C:\\java\\metodiObrabotki-master\\img\\HighPassFilter.jpg";
            File outputFile = new File(outputImagePath);
            ImageIO.write(filteredImage, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage applyFilter(BufferedImage image, double[][] kernel) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Создание нового изображения
        BufferedImage filteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Применение фильтра к каждому пикселу изображения
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double r = 0.0, g = 0.0, b = 0.0;
                for (int m = -kernel.length / 2; m <= kernel.length / 2; m++) {
                    for (int n = -kernel[0].length / 2; n <= kernel[0].length / 2; n++) {
                        if (i + m >= 0 && i + m < width && j + n >= 0 && j + n < height) {
                            Color color = new Color(image.getRGB(i + m, j + n));
                            r += color.getRed() * kernel[m + kernel.length / 2][n + kernel[0].length / 2];
                            g += color.getGreen() * kernel[m + kernel.length / 2][n + kernel[0].length / 2];
                            b += color.getBlue() * kernel[m + kernel.length / 2][n + kernel[0].length / 2];
                        }
                    }
                }
                int red = (int) Math.round(r);
                int green = (int) Math.round(g);
                int blue = (int) Math.round(b);
                red = Math.min(255, Math.max(0, red));
                green = Math.min(255, Math.max(0, green));
                blue = Math.min(255, Math.max(0, blue));
                int colorCode = (red << 16) | (green << 8) | blue;
                filteredImage.setRGB(i, j, colorCode);
            }
        }

        return filteredImage;
    }
}
