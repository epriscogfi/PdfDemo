package com.tinsa.demo.pdf.PdfDemo.interfaces

import com.tinsa.demo.pdf.PdfDemo.abst.DocumentAbst
import com.tinsa.demo.pdf.PdfDemo.elems.Elems2PDFWrapper

/**
 * Created by edu on 12/07/17.
 */
interface IDocumentFactory {

    IDocumentFactory withElems2Pdf(Map<String, Elems2PDFWrapper> mapElems2PDF)
    IDocumentFactory withOrderCategories (Map<String, String> mapOrderCategories)
    IDocumentFactory withDestino(String destino)

    DocumentAbst build()

}