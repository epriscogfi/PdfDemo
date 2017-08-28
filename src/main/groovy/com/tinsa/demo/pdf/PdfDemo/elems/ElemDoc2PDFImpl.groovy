package com.tinsa.demo.pdf.PdfDemo.elems

import com.tinsa.demo.pdf.PdfDemo.interfaces.IElem2PDF
import com.tinsa.demo.pdf.PdfDemo.impl.ContentDocFactoryImpl
import com.tinsa.demo.pdf.PdfDemo.interfaces.IContent
import groovy.transform.Immutable

/**
 * Created by edu on 12/07/17.
 */
@Immutable
class ElemDoc2PDFImpl implements IElem2PDF {

    static int elemsPorPagina = 1
    private String pathElem

    @Override
    int getNumElemsPerPage() {
        return elemsPorPagina
    }

    @Override
    IContent generateContent() {

        return new ContentDocFactoryImpl()
                .withPath(pathElem)
                .build()
    }

    @Override
    String getPath() {
        return this.pathElem
    }
}
