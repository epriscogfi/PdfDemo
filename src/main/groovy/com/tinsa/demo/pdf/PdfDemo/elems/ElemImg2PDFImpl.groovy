package com.tinsa.demo.pdf.PdfDemo.elems

import com.tinsa.demo.pdf.PdfDemo.impl.ContentImgFactoryImpl
import com.tinsa.demo.pdf.PdfDemo.abst.ContentAbst
import com.tinsa.demo.pdf.PdfDemo.abst.IElem2PDF

/**
 * Created by edu on 11/07/17.
 */
class ElemImg2PDFImpl implements IElem2PDF{

    private static int elemsPorPagina = 4

    @Override
    ContentAbst generateContent() {

        ContentImgFactoryImpl contentImgFactory = new ContentImgFactoryImpl();
        return contentImgFactory.withElem(this).buildOne()

    }

}
