import edgeEnhancementMethods.*;
import filters.*;
import firstModule.*;
import geometricProcesses.ScaleImage;
import geometricProcesses.Turns;

import java.io.IOException;

/*
java.awt.Color - библиотека для работы с цветами в Java.
java.awt.Graphics - библиотека для рисования на различных поверхностях в Java.
java.awt.image.BufferedImage - библиотека для работы с изображениями в Java.
java.io.File - библиотека для работы с файлами в Java.
javax.imageio.ImageIO - библиотека для чтения и записи изображений в Java.
java.io.IOException - это исключение, которое может быть выброшено при работе с файлами в Java.
java.awt.Graphics2D - предоставляет функциональность для рисования на экране с использованием 2D графики.
java.awt.RenderingHints содержит константы, используемые для управления параметрами рендеринга.
java.awt.geom.AffineTransform позволяет выполнять преобразования над графическими объектами.

Эти библиотеки являются стандартными библиотеками Java.
Они входят в стандартный пакет java.
*/

public class Main {
    public static void main(String[] args) throws IOException {

        ///////////////////////////////////////////////////////////////////
        BlackAndWhite blackAndWhite = new BlackAndWhite();
        blackAndWhite.convertToGrayscale();

        BrightnessHistogram brightnessHistogram = new BrightnessHistogram();
        brightnessHistogram.brightnessHistogram();

        Negative negative = new Negative();
        NegativeWithThreshold negativeWithThreshold = new NegativeWithThreshold();
        negative.negativeImage();
        negativeWithThreshold.negativeWithThreshold();

        ThresholdConversion thresholdConversion = new ThresholdConversion();
        thresholdConversion.porogPreobraz();

        ImageContrast imageContrast = new ImageContrast();
        imageContrast.imageContrast();

        ImageGamma imageGamma = new ImageGamma();
        imageGamma.imageGamma();

        ImageQuantization imageQuantization = new ImageQuantization();
        imageQuantization.imageQuantization();

        PseudoColoring pseudoColoring = new PseudoColoring();
        pseudoColoring.pseudoColoring();

        SolarizeImage solarizeImage = new SolarizeImage();
        solarizeImage.solarizeImage();
        /////////////////////////////////////////////////////////////////////



        ///////////////////////////////////////////////////////////////////
        LowAndHighPassFilter lowAndHighPassFilter = new LowAndHighPassFilter();
        lowAndHighPassFilter.lowPassFilter();
        lowAndHighPassFilter.highPassFilter();

        MedianFilter medianFilter = new MedianFilter();
        medianFilter.medianFilter();

        LaplaceMethod laplaceMethod = new LaplaceMethod();
        laplaceMethod.metodLaplasa();

        ShiftAndDifferenceMethod shiftAndDifferenceMethod = new ShiftAndDifferenceMethod();
        shiftAndDifferenceMethod.metodSdvigRaznost();

        OperatorRoberts operatorRoberts = new OperatorRoberts();
        operatorRoberts.operatorRobertsa();

        OperatorSobel operatorSobel = new OperatorSobel();
        operatorSobel.operatorSobely();

        OperatorPrevita operatorPrevita = new OperatorPrevita();
        operatorPrevita.operatorPrevita();

        OperatorKirsch operatorKirsch = new OperatorKirsch();
        operatorKirsch.operatorKirsha();

        EmbossingOperator embossingOperator = new EmbossingOperator();
        embossingOperator.operatorTisnenia();


        //Тут вызов двух функций сразу
        ScaleImage scaleImage = new ScaleImage();
        scaleImage.scaleImageWithoutInterpolation();
        scaleImage.scaleImageWithInterpolation();

        Turns turns = new Turns();
        turns.rotationImage();
    }
    //////////////////////////////////////////////////////////////////////////////
}