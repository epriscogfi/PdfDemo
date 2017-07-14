package com.tinsa.demo.pdf.PdfDemo.interfaces

import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.abst.ContentAbst

/**
 * Created by edu on 12/07/17.
 */
interface ICeldaElemFactory {

    ICeldaElemFactory withContentList (List<ContentAbst> contentList)
    ICeldaElemFactory withContent (ContentAbst content)
    List<CeldaElemAbst> build()
    CeldaElemAbst buildOne()

}
