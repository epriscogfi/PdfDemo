package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.layout.element.Text
import com.tinsa.demo.pdf.PdfDemo.abst.ParagraphElemAbst
import com.tinsa.demo.pdf.PdfDemo.elems.ParagraphElemMargenIzda
import com.tinsa.demo.pdf.PdfDemo.interfaces.IParagraphElemFactory

/**
 * Created by edu on 14/07/17.
 */
class ParagraphElemMargenIzdaFactoryImpl implements IParagraphElemFactory{

    Rectangle rectangle
    Text text
    ParagraphElemMargenIzda paragraphElemMargenIzda

    @Override
    IParagraphElemFactory withRectangle(Rectangle rectangle) {
        this.rectangle = rectangle
        return this
    }

    @Override
    IParagraphElemFactory withText(Text text) {
        this.text = text
        return null
    }

    @Override
    ParagraphElemAbst build() {

        return this.paragraphElemMargenIzda

    }

}
