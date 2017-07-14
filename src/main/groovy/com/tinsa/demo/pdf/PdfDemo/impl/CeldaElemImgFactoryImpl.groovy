package com.tinsa.demo.pdf.PdfDemo.impl

import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.elems.CeldaElemImg
import com.tinsa.demo.pdf.PdfDemo.impl.ContentImgImpl
import com.tinsa.demo.pdf.PdfDemo.interfaces.ICeldaElemFactory
import com.tinsa.demo.pdf.PdfDemo.abst.ContentAbst

/**
 * Created by edu on 12/07/17.
 */
class CeldaElemImgFactoryImpl implements ICeldaElemFactory{

    List<ContentImgImpl> contentImgList
    ContentImgImpl contentImg
    List<CeldaElemImg> celdaElemImgList
    CeldaElemImg celdaElemImg

    @Override
    ICeldaElemFactory withContentList(List<ContentAbst> contentList) {

        this.contentImgList = contentList
        return this
    }

    @Override
    ICeldaElemFactory withContent(ContentAbst content) {
        this.contentImg = content
        return this
    }

    @Override
    List<CeldaElemAbst> build() {
        return this.celdaElemImgList
    }

    @Override
    CeldaElemAbst buildOne() {
        return this.celdaElemImg
    }
}
