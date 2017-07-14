package com.tinsa.demo.pdf.PdfDemo.impl

import com.tinsa.demo.pdf.PdfDemo.abst.Elem2PDFAbst
import com.tinsa.demo.pdf.PdfDemo.impl.ContentDocImpl
import com.tinsa.demo.pdf.PdfDemo.elems.ElemDoc2PDFImpl
import com.tinsa.demo.pdf.PdfDemo.abst.ContentAbst
import com.tinsa.demo.pdf.PdfDemo.interfaces.IContentFactory

/**
 * Created by edu on 12/07/17.
 */
class ContentDocFactoryImpl implements IContentFactory{

    List<ContentDocImpl> contentDocList
    ContentDocImpl contentDoc
    List<ElemDoc2PDFImpl> elemDoc2PDFList

    @Override
    IContentFactory withElemList(List<Elem2PDFAbst> elem2PDFList) {
        this.elemDoc2PDFList = elemDoc2PDFList
        return this
    }

    @Override
    IContentFactory withElem(Elem2PDFAbst elem2PD) {
        this.elemDoc2PDFList = [elem2PD]
        return this
    }

    @Override
    List<ContentAbst> build() {
        return this.contentDocList
    }

    @Override
    ContentAbst buildOne() {
        return contentDoc
    }
}
