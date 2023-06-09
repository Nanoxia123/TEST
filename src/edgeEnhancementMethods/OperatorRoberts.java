package edgeEnhancementMethods;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


/* Применяем оператор Робертса с помощью метода operatorRobertsa(), который возвращает новое изображение с выделенными границами. */
public class OperatorRoberts {

    public void operatorRobertsa() {
        try {

            BufferedImage inputImage = ImageIO.read(new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg"));

            // добавляем оператор Робертса в изображение
            BufferedImage outputImage = robertsOperator(inputImage);


            File outputFile = new File("C:\\java\\metodiObrabotki-master\\img\\imagechangefilters.OperatorRobertsa.jpg");
            ImageIO.write(outputImage, "jpg", outputFile);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public BufferedImage robertsOperator(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        // Определение двух ядер
        int[][] kernelX = {{1, 0}, {0, -1}};
        int[][] kernelY = {{0, 1}, {-1, 0}};

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel00 = new Color(image.getRGB(x, y)).getRed();
                int pixel01 = (y < height-1) ? new Color(image.getRGB(x, y+1)).getRed() : 0;
                int pixel10 = (x < width-1) ? new Color(image.getRGB(x+1, y)).getRed() : 0;
                int pixel11 = (x < width-1 && y < height-1) ? new Color(image.getRGB(x+1, y+1)).getRed() : 0;

                // применение ядер
                int gx = Math.abs((pixel00 * kernelX[0][0]) + (pixel01 * kernelX[0][1]) +
                        (pixel10 * kernelX[1][0]) + (pixel11 * kernelX[1][1]));
                int gy = Math.abs((pixel00 * kernelY[0][0]) + (pixel01 * kernelY[0][1]) +
                        (pixel10 * kernelY[1][0]) + (pixel11 * kernelY[1][1]));

                int magnitude = Math.min(255, (int) Math.sqrt((gx * gx) + (gy * gy)));
                resultImage.setRGB(x, y, new Color(magnitude, magnitude, magnitude).getRGB());
            }
        }

        return resultImage;
    }
}
