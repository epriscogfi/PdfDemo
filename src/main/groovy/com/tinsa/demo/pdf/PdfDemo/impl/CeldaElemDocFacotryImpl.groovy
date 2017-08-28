package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.layout.element.Cell
import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.elems.CeldaElemDoc
import com.tinsa.demo.pdf.PdfDemo.interfaces.ICeldaElemFactory

/**
 * Created by edu on 12/07/17.
 */
class CeldaElemDocFacotryImpl implements ICeldaElemFactory{


    Rectangle rectangle

    @Override
    ICeldaElemFactory withRectangle(Rectangle rectangle) {
        this.rectangle = rectangle
        return this
    }

    @Override
    ICeldaElemFactory withPadding(float padding) {
        return this
    }

    @Override
    CeldaElemAbst build() {

        Cell cell

        CeldaElemDoc celdaElemDoc = new CeldaElemDoc(cell)

        return CeldaElemDoc
    }
}
