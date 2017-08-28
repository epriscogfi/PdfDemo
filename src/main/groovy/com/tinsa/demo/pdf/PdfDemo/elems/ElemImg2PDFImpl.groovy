package com.tinsa.demo.pdf.PdfDemo.elems

import com.tinsa.demo.pdf.PdfDemo.interfaces.IElem2PDF
import com.tinsa.demo.pdf.PdfDemo.impl.ContentImgFactoryImpl
import com.tinsa.demo.pdf.PdfDemo.interfaces.IContent
import groovy.transform.Immutable

/**
 * Created by edu on 11/07/17.
 */
@Immutable
class ElemImg2PDFImpl implements IElem2PDF{

    private static int elemsPorPagina = 4
    private String pathElem

    @Override
    int getNumElemsPerPage() {
        return elemsPorPagina
    }

    @Override
    IContent generateContent() {

        return new ContentImgFactoryImpl()
                .withPath(this.pathElem)
                .build()

    }

    @Override
    String getPath() {
        return this.pathElem
    }
}
