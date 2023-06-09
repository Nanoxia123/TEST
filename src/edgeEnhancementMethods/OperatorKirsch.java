package edgeEnhancementMethods;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


/* Оператор Кирша - это один из классических операторов обработки изображений, который применяется для выделения границ объектов на изображении.
        Он рассчитывает значение яркости каждого пикселя на основе восьми соседних пикселей и заданного ядра.
        В коде мы используем матрицу 3x3 коэффициентов, которая определяет вес каждого из соседних пикселей. Применение оператора Кирша к
        каждому пикселю изображения помогает выделить контуры объектов формы. */
public class OperatorKirsch {

    public void operatorKirsha() {

        try {
            // Загрузка изображения
            BufferedImage image = ImageIO.read(new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg"));

            // Применение оператора Кирша
            int[][] kirshMatrix = {{-3,-3, 5},
                    {-3, 0, 5},
                    {-3,-3, 5}};
            BufferedImage kirshImage = applyKernel(image, kirshMatrix);

            // Сохранение полученного изображения
            File output = new File("C:\\java\\metodiObrabotki-master\\img\\imagechangefilters.OperatorKirsha.jpg");
            ImageIO.write(kirshImage, "jpg", output);

        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private BufferedImage applyKernel(BufferedImage image, int[][] kernel) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, image.getType());

        // Проходим по всем пикселям изображения
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int sumR = 0;
                int sumG = 0;
                int sumB = 0;

                // Применяем ядро к окрестности пикселя
                int alpha = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int pixel = image.getRGB(x + j, y + i);
                        alpha = (pixel >> 24) & 0xff;
                        int red = (pixel >> 16) & 0xff;
                        int green = (pixel >> 8) & 0xff;
                        int blue = pixel & 0xff;

                        sumR += kernel[i + 1][j + 1] * red;
                        sumG += kernel[i + 1][j + 1] * green;
                        sumB += kernel[i + 1][j + 1] * blue;
                    }
                }

                // Ограничиваем значения пикселей от 0 до 255
                int newR = Math.min(Math.max(sumR, 0), 255);
                int newG = Math.min(Math.max(sumG, 0), 255);
                int newB = Math.min(Math.max(sumB, 0), 255);

                // Записываем новый цвет пикселя в результат
                int newPixel = (alpha << 24) | (newR << 16) | (newG << 8) | newB;
                result.setRGB(x, y, newPixel);
            }
        }

        return result;
    }
}
