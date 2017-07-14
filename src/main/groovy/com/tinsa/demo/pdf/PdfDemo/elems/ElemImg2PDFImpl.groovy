package com.tinsa.demo.pdf.PdfDemo.elems

import com.tinsa.demo.pdf.PdfDemo.abst.Elem2PDFAbst
import com.tinsa.demo.pdf.PdfDemo.impl.ContentImgFactoryImpl
import com.tinsa.demo.pdf.PdfDemo.abst.ContentAbst

/**
 * Created by edu on 11/07/17.
 */
class ElemImg2PDFImpl extends Elem2PDFAbst{

    private static int elemsPorPagina = 4

    @Override
    ContentAbst generateContent() {

        ContentImgFactoryImpl contentImgFactory = new ContentImgFactoryImpl();
        return contentImgFactory.withElem(this).buildOne()

    }

}
