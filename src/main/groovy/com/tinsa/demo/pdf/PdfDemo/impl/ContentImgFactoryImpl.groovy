package com.tinsa.demo.pdf.PdfDemo.impl

import com.tinsa.demo.pdf.PdfDemo.abst.ContentAbst
import com.tinsa.demo.pdf.PdfDemo.abst.Elem2PDFAbst
import com.tinsa.demo.pdf.PdfDemo.interfaces.IContentFactory

/**
 * Created by edu on 12/07/17.
 */
class ContentImgFactoryImpl implements IContentFactory{


    @Override
    IContentFactory withElemList(List<Elem2PDFAbst> elem2PDFList) {
        return null
    }

    @Override
    IContentFactory withElem(Elem2PDFAbst elem2PD) {
        return null
    }

    @Override
    List<ContentAbst> build() {
        return null
    }

    @Override
    ContentAbst buildOne() {
        return null
    }
}
