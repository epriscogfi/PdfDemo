package com.tinsa.demo.pdf.PdfDemo.impl

import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.elems.CeldaElemDoc
import com.tinsa.demo.pdf.PdfDemo.interfaces.ICeldaElemFactory
import com.tinsa.demo.pdf.PdfDemo.abst.ContentAbst

/**
 * Created by edu on 12/07/17.
 */
class CeldaElemDocFacotryImpl implements ICeldaElemFactory{

    List<ContentAbst> contentList
    ContentAbst content
    List<CeldaElemDoc> celdaElemDocList
    CeldaElemDoc celdaElemDoc

    @Override
    ICeldaElemFactory withContentList(List<ContentAbst> contentList) {
        this.contentList = contentList
        return this
    }

    @Override
    ICeldaElemFactory withContent(ContentAbst content) {
        this.content = content
    }

    @Override
    List<CeldaElemAbst> build() {
        return this.celdaElemDocList
    }

    @Override
    CeldaElemAbst buildOne() {
        return this.celdaElemDoc
    }
}
