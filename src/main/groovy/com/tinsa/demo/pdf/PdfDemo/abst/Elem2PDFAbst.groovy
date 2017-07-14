package com.tinsa.demo.pdf.PdfDemo.abst
/**
 * Created by edu on 11/07/17.
 */
abstract class Elem2PDFAbst {

    ContentAbst content

    ContentAbst generateContent(){
        return this.content
    }

}