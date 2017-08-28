package com.tinsa.demo.pdf.PdfDemo.interfaces
/**
 * Created by edu on 11/07/17.
 */
interface IElem2PDF {

    int getNumElemsPerPage()
    IContent generateContent()
    String getPath()
}