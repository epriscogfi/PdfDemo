package com.tinsa.demo.pdf.PdfDemo.impl

import com.tinsa.demo.pdf.PdfDemo.impl.ContentDocImpl
import com.tinsa.demo.pdf.PdfDemo.elems.ElemDoc2PDFImpl
import com.tinsa.demo.pdf.PdfDemo.abst.ContentAbst
import com.tinsa.demo.pdf.PdfDemo.interfaces.IContentFactory
import com.tinsa.demo.pdf.PdfDemo.abst.IElem2PDF

/**
 * Created by edu on 12/07/17.
 */
class ContentDocFactoryImpl implements IContentFactory{

    List<ContentDocImpl> contentDocList
    ContentDocImpl contentDoc
    List<ElemDoc2PDFImpl> elemDoc2PDFList

    @Override
    IContentFactory withElemList(List<IElem2PDF> elem2PDFList) {
        this.elemDoc2PDFList = elemDoc2PDFList
        return this
    }

    @Override
    IContentFactory withElem(IElem2PDF elem2PDF) {
        this.elemDoc2PDFList = [elem2PDF]
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
