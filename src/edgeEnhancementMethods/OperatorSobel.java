package edgeEnhancementMethods;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


/* Применяем оператор Собеля с помощью метода operatorSobely(), который возвращает новое изображение с выделенными границами. */
public class OperatorSobel {

    public void operatorSobely () {
        try {

            BufferedImage inputImage = ImageIO.read(new File("C:\\java\\metodiObrabotki-master\\img\\img.jpg"));

            //  Применяем оператор Собеля к изображению
            BufferedImage outputImage = sobelOperator(inputImage);

            File outputFile = new File("C:\\java\\metodiObrabotki-master\\img\\imagechangefilters.OperatorSobely.jpg");
            ImageIO.write(outputImage, "jpg", outputFile);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public BufferedImage sobelOperator(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        // Определение двух ядер
        int[][] kernelX = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        int[][] kernelY = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int pixel00 = new Color(image.getRGB(x-1, y-1)).getRed();
                int pixel01 = new Color(image.getRGB(x-1, y)).getRed();
                int pixel02 = new Color(image.getRGB(x-1, y+1)).getRed();
                int pixel10 = new Color(image.getRGB(x, y-1)).getRed();
                int pixel11 = new Color(image.getRGB(x, y)).getRed();
                int pixel12 = new Color(image.getRGB(x, y+1)).getRed();
                int pixel20 = new Color(image.getRGB(x+1, y-1)).getRed();
                int pixel21 = new Color(image.getRGB(x+1, y)).getRed();
                int pixel22 = new Color(image.getRGB(x+1, y+1)).getRed();

                // применение ядер
                int gx = (pixel00 * kernelX[0][0]) + (pixel01 * kernelX[0][1]) + (pixel02 * kernelX[0][2]) +
                        (pixel10 * kernelX[1][0]) + (pixel11 * kernelX[1][1]) + (pixel12 * kernelX[1][2]) +
                        (pixel20 * kernelX[2][0]) + (pixel21 * kernelX[2][1]) + (pixel22 * kernelX[2][2]);
                int gy = (pixel00 * kernelY[0][0]) + (pixel01 * kernelY[0][1]) + (pixel02 * kernelY[0][2]) +
                        (pixel10 * kernelY[1][0]) + (pixel11 * kernelY[1][1]) + (pixel12 * kernelY[1][2]) +
                        (pixel20 * kernelY[2][0]) + (pixel21 * kernelY[2][1]) + (pixel22 * kernelY[2][2]);

                int magnitude = Math.min(255, (int) Math.sqrt((gx * gx) + (gy * gy)));
                resultImage.setRGB(x, y, new Color(magnitude, magnitude, magnitude).getRGB());
            }
        }

        return resultImage;
    }
}
