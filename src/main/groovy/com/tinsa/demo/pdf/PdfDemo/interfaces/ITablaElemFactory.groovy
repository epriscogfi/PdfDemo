package com.tinsa.demo.pdf.PdfDemo.interfaces

import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.abst.Elem2PDFAbst
import com.tinsa.demo.pdf.PdfDemo.abst.TablaElemAbst

/**
 * Created by edu on 12/07/17.
 */
interface ITablaElemFactory {

    ITablaElemFactory withContentList(List<Elem2PDFAbst> elem2PDFList)
    ITablaElemFactory withHeighAndWidth(float height, float width)


    ITablaElemFactory withCeldaElemList (List<CeldaElemAbst> celdaElemList)
    ITablaElemFactory withCeldaElem(CeldaElemAbst celdaElem)
    List<TablaElemAbst> build()
    TablaElemAbst buildOne()
}