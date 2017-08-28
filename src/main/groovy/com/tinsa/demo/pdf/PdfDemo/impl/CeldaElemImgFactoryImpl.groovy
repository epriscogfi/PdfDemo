package com.tinsa.demo.pdf.PdfDemo.impl


import com.itextpdf.io.source.ByteArrayOutputStream
import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.kernel.pdf.PdfName
import com.itextpdf.kernel.pdf.PdfNumber
import com.itextpdf.kernel.pdf.PdfStream
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject
import com.itextpdf.layout.border.Border
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Div
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.property.HorizontalAlignment
import com.itextpdf.layout.property.VerticalAlignment
import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.elems.CeldaElemImg
import com.tinsa.demo.pdf.PdfDemo.interfaces.ICeldaElemFactory

import javax.imageio.ImageIO
import java.awt.Graphics2D
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage

/**
 * Created by edu on 12/07/17.
 */
class CeldaElemImgFactoryImpl implements ICeldaElemFactory{

    Rectangle rectangle
    Image image
    float padding

    @Override
    CeldaElemImgFactoryImpl withRectangle(Rectangle rectangle) {
        this.rectangle = rectangle
        return this
    }

    @Override
    CeldaElemImgFactoryImpl withPadding(float padding) {
        this.padding = padding
        return this
    }

    CeldaElemImgFactoryImpl withImage(Image image) {
        this.image = image
        return this
    }



    @Override
    CeldaElemAbst build() {

        Cell cell = new Cell()
        cell.setBorder(Border.NO_BORDER)
        cell.setHeight(rectangle.getHeight())
        cell.setWidth(rectangle.getWidth())
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER)
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE)

        //cell.add(new Div().add(this.image.setAutoScale(true)).setPaddings(18,18,18,18))
        cell.add(new Div()
                .add(compressThisImage().setAutoScale(true))
                .setPaddings(padding,padding,padding,padding)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setHorizontalAlignment(HorizontalAlignment.CENTER))

//        cell.add(compressThisImage().setAutoScale(true))
//                .setHorizontalAlignment(HorizontalAlignment.CENTER)
//                .setVerticalAlignment(VerticalAlignment.MIDDLE)

        CeldaElemImg celdaElemImg = new CeldaElemImg(cell)
        return celdaElemImg

    }

    Image compressThisImage() {

        PdfImageXObject imageXObject = this.image.getXObject()
        BufferedImage buffOriginalImage = imageXObject.getBufferedImage()

        float factor = getFactor(buffOriginalImage)

        int width = (int) (buffOriginalImage.getWidth() * factor - padding)
        int height = (int) (buffOriginalImage.getHeight() * factor - padding)

        PdfStream stream

        if (factor != 1){

            BufferedImage bufferdImageFinal = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

            AffineTransform affineTransform = AffineTransform.getScaleInstance(factor, factor)
            Graphics2D graphics2D = bufferdImageFinal.createGraphics()

            graphics2D.drawRenderedImage(buffOriginalImage, affineTransform)

            ByteArrayOutputStream imgBytes = new ByteArrayOutputStream()
            ImageIO.write(bufferdImageFinal, "JPG", imgBytes)

            stream = new PdfStream(imgBytes.toByteArray())

            stream.put(PdfName.Type, PdfName.XObject)
            stream.put(PdfName.Subtype, PdfName.Image)
            stream.put(PdfName.Filter, PdfName.DCTDecode)
            stream.put(PdfName.Width, new PdfNumber(width))
            stream.put(PdfName.Height, new PdfNumber(height))
            stream.put(PdfName.BitsPerComponent, new PdfNumber(8))
            stream.put(PdfName.ColorSpace, PdfName.DeviceRGB)

            return new Image(new PdfImageXObject(stream))

        }

        else{

            return new Image(imageXObject)

        }


    }

    float getFactor(BufferedImage bufferedImage){

        float factor = (bufferedImage.getWidth() >= bufferedImage.getHeight()) ?
                (this.rectangle.width / bufferedImage.getWidth()).floatValue() :
                (this.rectangle.height / bufferedImage.getHeight()).floatValue()

        if (factor < 0.2f) return 0.2f
        else if (factor > 0.4f) return 1f
        else return factor

        //return (factor < 0.3f) ? 0.3f : factor

    }


}
