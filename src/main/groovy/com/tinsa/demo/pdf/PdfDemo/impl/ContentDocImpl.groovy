package com.tinsa.demo.pdf.PdfDemo.impl

import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.abst.ContentAbst

/**
 * Created by edu on 12/07/17.
 */
class ContentDocImpl extends ContentAbst{

    @Override
    CeldaElemAbst generateCelda() {

        CeldaElemDocFacotryImpl celdaElemDocFacotry = new CeldaElemDocFacotryImpl()
        celdaElemDocFacotry.withContent(this).buildOne()

    }
}
