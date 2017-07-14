package com.tinsa.demo.pdf.PdfDemo.interfaces

import com.itextpdf.kernel.pdf.PdfDocument
import com.tinsa.demo.pdf.PdfDemo.abst.Elem2PDFAbst
import com.tinsa.demo.pdf.PdfDemo.abst.PaginaElemAbst

/**
 * Created by edu on 12/07/17.
 */
interface IPaginaElemFactory {

    IPaginaElemFactory withContent(List<Elem2PDFAbst> elem2PDFList)
    IPaginaElemFactory withPdfDocument(PdfDocument pdfDocument)
    IPaginaElemFactory withEncabezado()
    IPaginaElemFactory withTitulo()
    IPaginaElemFactory withMembrete()
    IPaginaElemFactory withTextoMargenIzdo()

    PaginaElemAbst build()
}
