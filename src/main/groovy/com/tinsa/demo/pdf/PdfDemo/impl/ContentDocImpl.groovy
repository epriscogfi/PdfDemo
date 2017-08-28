package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.kernel.geom.Rectangle
import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.elems.CeldaElemDoc
import com.tinsa.demo.pdf.PdfDemo.interfaces.IContent

/**
 * Created by edu on 12/07/17.
 */
class ContentDocImpl implements IContent{

    @Override
    CeldaElemAbst generateCelda(Rectangle rectangle) {

        CeldaElemDoc celdaElemDoc = new CeldaElemDocFacotryImpl().withRectangle(rectangle).build()

        return celdaElemDoc
    }
}
