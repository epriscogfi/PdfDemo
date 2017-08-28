package com.tinsa.demo.pdf.PdfDemo.abst

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfPage
import com.tinsa.demo.pdf.PdfDemo.elems.Elems2PDFWrapper
import com.tinsa.demo.pdf.PdfDemo.elems.ParagraphElemTitulo
import com.tinsa.demo.pdf.PdfDemo.elems.TablaElemEncabezado
import com.tinsa.demo.pdf.PdfDemo.elems.TablaElemMembrete
import com.tinsa.demo.pdf.PdfDemo.impl.TablaElemContentFactoryImpl

/**
 * Created by edu on 24/07/17.
 */
abstract class PagesFactoryAbst {

    Elems2PDFWrapper elems2PDFWrapper
    String titulo
    PdfDocument pdfDocument
    TablaElemEncabezado encabezado
    TablaElemMembrete tablaElemMembrete
    ParagraphElemTitulo paragraphElemTitulo

    PagesFactoryAbst withContent(Elems2PDFWrapper elems2PDFWrapper) {
        this.elems2PDFWrapper = elems2PDFWrapper
        return this
    }

    PagesFactoryAbst withPdfDocument(PdfDocument pdfDocument) {
        this.pdfDocument = pdfDocument
        return this
    }

    PagesFactoryAbst withEncabezado(TablaElemEncabezado encabezado) {
        this.encabezado = encabezado
        return this
    }

    PagesFactoryAbst withTitulo(ParagraphElemTitulo paragraphElemTitulo) {
        this.paragraphElemTitulo = paragraphElemTitulo
        return this
    }

    PagesFactoryAbst withMembrete(TablaElemMembrete tablaElemMembrete) {
        this.tablaElemMembrete = tablaElemMembrete
        return this
    }

    PagesFactoryAbst withTextoMargenIzdo() {

        return this
    }

    List<PdfPage> build(){
        return new ArrayList<PdfPage>()
    }
}
