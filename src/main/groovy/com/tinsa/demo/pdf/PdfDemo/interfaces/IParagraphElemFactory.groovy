package com.tinsa.demo.pdf.PdfDemo.interfaces

import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.layout.element.Text
import com.tinsa.demo.pdf.PdfDemo.abst.ParagraphElemAbst

/**
 * Created by edu on 14/07/17.
 */
interface IParagraphElemFactory {

    IParagraphElemFactory withRectangle (Rectangle rectangle)
    IParagraphElemFactory withText (Text text)
    ParagraphElemAbst build()

}