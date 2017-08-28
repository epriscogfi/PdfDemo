package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.io.font.FontConstants
import com.itextpdf.kernel.color.Color
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Tab
import com.itextpdf.layout.element.TabStop
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.property.HorizontalAlignment
import com.itextpdf.layout.property.TabAlignment
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.VerticalAlignment
import com.tinsa.demo.pdf.PdfDemo.abst.ParagraphElemAbst
import com.tinsa.demo.pdf.PdfDemo.elems.ParagraphElemTitulo
import com.tinsa.demo.pdf.PdfDemo.interfaces.IParagraphElemFactory

/**
 * Created by edu on 14/07/17.
 */
class ParagraphElemTituloFactoryImpl implements IParagraphElemFactory{

    Text text
    float width

    @Override
    IParagraphElemFactory withText(Text text) {
        this.text = text
        return this
    }

    @Override
    IParagraphElemFactory  withWidth (float width){
        this.width = width
        return this

    }


    @Override
    ParagraphElemAbst build() {

        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA)

        Paragraph paragraph = new Paragraph(this.text)
                .setFont(font)
                .setFontSize(14f)
                .setBold()
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setTextAlignment(TextAlignment.CENTER)

        return new ParagraphElemTitulo(paragraph)
    }

}
