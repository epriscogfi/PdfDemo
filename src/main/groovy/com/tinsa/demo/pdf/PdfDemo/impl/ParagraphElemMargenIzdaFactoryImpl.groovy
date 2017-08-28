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

    Text text
    float width


    @Override
    IParagraphElemFactory withText(Text text) {
        this.text = text
        return this
    }

    @Override
    IParagraphElemFactory withWidth(float width) {
        this.width
        return this
    }

    @Override
    ParagraphElemAbst build() {

        return this.paragraphElemMargenIzda

    }

}
