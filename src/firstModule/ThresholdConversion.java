package firstModule;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


 /* Пороговое преобразование. проходим по каждому пикселю. Для каждого пикселя мы вычисляем значение яркости путем среднего арифметического значений компонент RGB.
        Затем мы сравниваем значение яркости с пороговым значением. Если оно больше порога, то устанавливаем значения компонент RGB равными 255 (белый цвет),
        иначе - равными 0 (черный цвет). Затем мы создаем новое значение пикселя на основе измененных значений компонент RGB и записываем его в изображение.
        Наконец, мы сохраняем полученное преобразованное изображение в файл. */

public class ThresholdConversion {
    public void porogPreobraz() throws IOException {

        // Загрузка изображения
        File file = new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg");
        BufferedImage image = ImageIO.read(file);

        // Пороговое значение
        int threshold = 128;

        // Получение ширины и высоты изображения
        int width = image.getWidth();
        int height = image.getHeight();

        // Проход по каждому пикселю изображения
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){

                // Получение текущего значения пикселя
                int pixel = image.getRGB(x, y);

                // Извлечение компонент RGB
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                // Применение порогового преобразования
                int gray = (red + green + blue) / 3;
                if(gray > threshold){
                    red = green = blue = 255;
                } else {
                    red = green = blue = 0;
                }

                // Создание нового значения пикселя с измененными RGB компонентами
                int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;

                // Запись нового значения пикселя в изображение
                image.setRGB(x, y, newPixel);
            }
        }

        // Сохранение полученного изображения
        File output = new File("C:\\java\\metodiObrabotki-master\\img\\imagechangefilters.PorogPreobraz.jpg");
        ImageIO.write(image, "jpg", output);
    }
}
