package com.tinsa.demo.pdf.PdfDemo.impl

import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.abst.Elem2PDFAbst
import com.tinsa.demo.pdf.PdfDemo.abst.TablaElemAbst
import com.tinsa.demo.pdf.PdfDemo.elems.CeldaElemDoc
import com.tinsa.demo.pdf.PdfDemo.abst.IElem2PDF
import com.tinsa.demo.pdf.PdfDemo.interfaces.ITablaElemFactory

/**
 * Created by edu on 12/07/17.
 */
class TablaElemContentFactoryImpl implements ITablaElemFactory{

    List<CeldaElemDoc> celdaElemDocList
    CeldaElemDoc celdaElemDoc
    List<TablaElemAbst> tablaElemList
    TablaElemAbst tablaElem


    @Override
    ITablaElemFactory withContentList(List<Elem2PDFAbst> elem2PDFList) {
        return null
    }

    @Override
    ITablaElemFactory withHeighAndWidth(float height, float width) {
        return null
    }

    @Override
    ITablaElemFactory withCeldaElemList(List<CeldaElemAbst> celdaElemList) {
        this.celdaElemDocList = celdaElemList
        return this
    }

    @Override
    ITablaElemFactory withCeldaElem(CeldaElemAbst celdaElem) {
        this.celdaElemDoc = celdaElem
        return this
    }

    @Override
    List<TablaElemAbst> build() {
        return tablaElemList
    }

    @Override
    TablaElemAbst buildOne() {
        return tablaElem
    }
}
