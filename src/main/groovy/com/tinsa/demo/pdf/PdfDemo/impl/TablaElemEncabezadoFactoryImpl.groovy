package com.tinsa.demo.pdf.PdfDemo.impl

import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.abst.Elem2PDFAbst
import com.tinsa.demo.pdf.PdfDemo.abst.TablaElemAbst
import com.tinsa.demo.pdf.PdfDemo.interfaces.ITablaElemFactory

/**
 * Created by edu on 14/07/17.
 */
class TablaElemEncabezadoFactoryImpl implements ITablaElemFactory{
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
        return null
    }

    @Override
    ITablaElemFactory withCeldaElem(CeldaElemAbst celdaElem) {
        return null
    }

    @Override
    List<TablaElemAbst> build() {
        return null
    }

    @Override
    TablaElemAbst buildOne() {
        return null
    }
}
