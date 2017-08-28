package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.layout.element.Image
import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.interfaces.IContent

/**
 * Created by edu on 12/07/17.
 */

class ContentImgImpl implements IContent{


    Image image

    ContentImgImpl(Image image) {
        this.image = image
    }

    Image getImage() {
        return image
    }

    void setImage(Image image) {
        this.image = image
    }

    @Override
    CeldaElemAbst generateCelda(Rectangle rectangle) {

        return new CeldaElemImgFactoryImpl()
                .withPadding(18)
                .withRectangle(rectangle)
                .withImage(image)
                .build()


    }


}
