package com.tinsa.demo.pdf.PdfDemo.elems

import com.tinsa.demo.pdf.PdfDemo.abst.Elem2PDFAbst
import com.tinsa.demo.pdf.PdfDemo.impl.ContentDocFactoryImpl
import com.tinsa.demo.pdf.PdfDemo.abst.ContentAbst

/**
 * Created by edu on 12/07/17.
 */
class ElemDoc2PDFImpl extends Elem2PDFAbst {

    private static int elemsPorPagina = 1

    @Override
    ContentAbst generateContent() {
        ContentDocFactoryImpl contentDocFactory = new ContentDocFactoryImpl()
        return contentDocFactory.withElem(this).buildOne()
    }

}
