package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfPage
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfResources
import com.tinsa.demo.pdf.PdfDemo.interfaces.IContent
import com.tinsa.demo.pdf.PdfDemo.interfaces.IContentFactory

/**
 * Created by edu on 12/07/17.
 */
class ContentDocFactoryImpl implements IContentFactory{

    String path

    @Override
    IContentFactory withPath(String path) {
        this.path = path
        return this
    }

    @Override
    IContent build() {

        PdfDocument srcDoc = new PdfDocument(new PdfReader(this.path));

        1.upto(srcDoc.getNumberOfPages()) {

            PdfPage pdfPage = srcDoc.getPage(it)

            PdfResources pdfResources = pdfPage.getResources()


        }


        ContentDocImpl contentDoc = new ContentDocImpl()
        return contentDoc
    }
}
