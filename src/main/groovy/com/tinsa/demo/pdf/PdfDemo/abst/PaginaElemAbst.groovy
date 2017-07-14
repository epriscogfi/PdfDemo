package com.tinsa.demo.pdf.PdfDemo.abst

import com.itextpdf.kernel.pdf.PdfPage
import groovy.transform.Immutable

/**
 * Created by edu on 12/07/17.
 */
abstract class PaginaElemAbst {

    PdfPage pdfPage

    PaginaElemAbst(PdfPage pdfPage) {
        this.pdfPage = pdfPage
    }

    PdfPage getPdfPage() {
        return pdfPage
    }

    void setPdfPage(PdfPage pdfPage) {
        this.pdfPage = pdfPage
    }
}
