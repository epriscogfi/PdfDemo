package com.tinsa.demo.pdf.PdfDemo.interfaces

import com.tinsa.demo.pdf.PdfDemo.abst.ContentAbst
import com.tinsa.demo.pdf.PdfDemo.abst.Elem2PDFAbst
import com.tinsa.demo.pdf.PdfDemo.abst.IElem2PDF

/**
 * Created by edu on 12/07/17.
 */
interface IContentFactory {

    IContentFactory withElemList(List<Elem2PDFAbst> elem2PDFList)
    IContentFactory withElem(Elem2PDFAbst elem2PD)
    List<ContentAbst> build()
    ContentAbst buildOne()

}
