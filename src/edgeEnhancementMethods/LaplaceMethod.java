package edgeEnhancementMethods;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/* Данный код представляет метод Лапласа для обнаружения границ на изображении. */
public class LaplaceMethod {

    public int[][][] laplacianEdgeDetection(int[][][] image) {
        int height = image.length;
        int width = image[0].length;

        int[][][] result = new int[height][width][3];

        // Матрица коэффициентов фильтра Лапласа 3x3
        int[][] filter = {{-1, -1, -1},
                {-1,  8, -1},
                {-1, -1, -1}};

        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                int[] sum = {0, 0, 0};

                // Применяем фильтр к каждому каналу цвета отдельно
                for (int c = 0; c < 3; c++) {
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            sum[c] += filter[k + 1][l + 1] * image[i + k][j + l][c];
                        }
                    }

                    // Ограничиваем значения пикселей от 0 до 255
                    result[i][j][c] = Math.min(Math.max(sum[c], 0), 255);
                }
            }
        }

        return result;
    }

    public void metodLaplasa() throws IOException {
        // Загружаем изображение
        BufferedImage image = ImageIO.read(new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg"));

        // Получаем размеры изображения
        int height = image.getHeight();
        int width = image.getWidth();

        // Создаем трехмерный массив для хранения значений пикселей
        int[][][] pixels = new int[height][width][3];

        // Заполняем массив значениями пикселей
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = image.getRGB(j, i);
                pixels[i][j][0] = (rgb >> 16) & 0xFF; // Красный канал
                pixels[i][j][1] = (rgb >> 8) & 0xFF; // Зеленый канал
                pixels[i][j][2] = rgb & 0xFF; // Синий канал
            }
        }

        // Применяем метод Лапласа к изображению
        int[][][] result = laplacianEdgeDetection(pixels);

        // Создаем новое изображение с обработанными пикселями
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Заполняем новое изображение значениями пикселей
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = (result[i][j][0] << 16) | (result[i][j][1] << 8) | result[i][j][2];
                output.setRGB(j, i, rgb);
            }
        }

        // Сохраняем новое изображение
        ImageIO.write(output, "JPEG", new File("C:\\java\\metodiObrabotki-master\\img\\imagechangefilters.MetodLaplasa.jpg"));
    }
}
