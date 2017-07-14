package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.layout.element.Image
import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.abst.ContentAbst

/**
 * Created by edu on 12/07/17.
 */
class ContentImgImpl implements ContentAbst{

    Image image

    @Override
    CeldaElemAbst generateCelda() {
        CeldaElemImgFactoryImpl celdaElemImgFactory = new CeldaElemImgFactoryImpl()
        return celdaElemImgFactory.withContent(this).buildOne()
    }
}
