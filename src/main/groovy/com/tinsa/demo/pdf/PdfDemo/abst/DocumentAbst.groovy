package com.tinsa.demo.pdf.PdfDemo.abst

import com.itextpdf.kernel.pdf.PdfDocument

/**
 * Created by edu on 13/07/17.
 */
abstract class DocumentAbst {

    PdfDocument pdfDocument

    DocumentAbst(PdfDocument pdfDocument) {
        this.pdfDocument = pdfDocument
    }

    PdfDocument getPdfDocument() {
        return pdfDocument
    }

    void setPdfDocument(PdfDocument pdfDocument) {
        this.pdfDocument = pdfDocument
    }
}