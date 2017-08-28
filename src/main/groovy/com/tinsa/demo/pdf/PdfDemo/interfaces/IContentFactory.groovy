package com.tinsa.demo.pdf.PdfDemo.interfaces
/**
 * Created by edu on 12/07/17.
 */
interface IContentFactory {

    IContentFactory withPath(String path)
    IContent build()

}
