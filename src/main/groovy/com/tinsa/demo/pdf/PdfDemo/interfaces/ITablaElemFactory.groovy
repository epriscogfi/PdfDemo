package com.tinsa.demo.pdf.PdfDemo.interfaces

import com.tinsa.demo.pdf.PdfDemo.abst.TablaElemAbst

/**
 * Created by edu on 12/07/17.
 */
interface ITablaElemFactory {

    ITablaElemFactory withColumns(int numColumns)
    ITablaElemFactory withNumRows(int numRows)
    ITablaElemFactory withHeight(float height)
    ITablaElemFactory withWidth(float width)
    ITablaElemFactory withMarginTop(float marginTop)
    ITablaElemFactory withHMarginLeft(float marginLeft)
    ITablaElemFactory withElemList(List<IElem2PDF> elem2PDFList)
    TablaElemAbst build()
}