package firstModule;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


 /* Негатив. В этом коде мы загружаем изображение из файла "input_image.jpg" и проходим по каждому пикселю.
        Для каждого пикселя мы вычисляем инвертированное значение для негатива и инвертированное значение с порогом яркости.
        Для негатива мы просто инвертируем значения компонент RGB. Для негатива с порогом мы определяем пороговое значение яркости и, если яркость пикселя больше этого порога,
        то оставляем его без изменений. В противном случае мы инвертируем значения компонент RGB для получения негатива с порогом.
        Затем мы записываем полученные изображения в файлы "negative_image.jpg" и "negative_threshold_image.jpg". */

public class Negative {
    public void negativeImage() throws IOException {

        // Загрузка изображения
        File file = new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg");
        BufferedImage image = ImageIO.read(file);

        // Получение ширины и высоты изображения
        int width = image.getWidth();
        int height = image.getHeight();

        // Создание объектов для двух новых изображений
        BufferedImage negativeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

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

                // Вычисление инвертированного значения пикселя для негатива
                int invertedPixel = (alpha << 24) | ((255 - red) << 16) | ((255 - green) << 8) | (255 - blue);

                // Запись инвертированного значения пикселя в изображение для негатива
                negativeImage.setRGB(x, y, invertedPixel);
            }
        }

        // Сохранение полученных изображений
        File outputNegative = new File("C:\\java\\metodiObrabotki-master\\img\\negative_image.jpg");
        ImageIO.write(negativeImage, "jpg", outputNegative);
    }
}
