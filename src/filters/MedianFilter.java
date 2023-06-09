package filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;



 /* В этом коде используется метод getMedian(), который получает медиану из массива значений. Он сортирует массив и возвращает значение посередине.
        Чтобы применить медианный фильтр к изображению, мы проходим по каждому пикселю и получаем значения его окна.
        Затем мы находим медиану этих значений и устанавливаем ее в пиксель. Размер окна можно задать переменной size. */

public class MedianFilter {
    public void medianFilter() throws IOException {
        // чтение изображения из файла
        BufferedImage image = ImageIO.read(new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg"));

        // применение медианного фильтра
        int size = 3; // размер окна
        int offset = size / 2; // смещение от края изображения
        for (int x = offset; x < image.getWidth() - offset; x++) {
            for (int y = offset; y < image.getHeight() - offset; y++) {
                int[] values = new int[size * size];
                int counter = 0;
                for (int i = x - offset; i <= x + offset; i++) {
                    for (int j = y - offset; j <= y + offset; j++) {
                        values[counter] = image.getRGB(i, j);
                        counter++;
                    }
                }
                int median = getMedian(values);
                image.setRGB(x, y, median);
            }
        }

        // сохранение измененного изображения
        File output = new File("C:\\java\\metodiObrabotki-master\\img\\imagechangefilters.MedianFilter.jpg");
        ImageIO.write(image, "jpg", output);
    }

    // получение медианы из массива значений
    private int getMedian(int[] values) {
        int length = values.length;
        int[] sortedValues = new int[length];
        System.arraycopy(values, 0, sortedValues, 0, length);
        Arrays.sort(sortedValues);
        int medianIndex = length / 2;
        return sortedValues[medianIndex];
    }
}
