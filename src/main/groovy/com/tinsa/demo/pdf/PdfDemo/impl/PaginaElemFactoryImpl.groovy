package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfPage
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.layout.Canvas
import com.itextpdf.layout.element.Text
import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst
import com.tinsa.demo.pdf.PdfDemo.abst.Elem2PDFAbst
import com.tinsa.demo.pdf.PdfDemo.abst.PaginaElemAbst
import com.tinsa.demo.pdf.PdfDemo.abst.TablaElemAbst
import com.tinsa.demo.pdf.PdfDemo.elems.PaginaElemImpl
import com.tinsa.demo.pdf.PdfDemo.elems.ParagraphElemTitulo
import com.tinsa.demo.pdf.PdfDemo.elems.TablaElemEncabezado
import com.tinsa.demo.pdf.PdfDemo.interfaces.IPaginaElemFactory
import com.tinsa.demo.pdf.PdfDemo.interfaces.ITablaElemFactory

/**
 * Created by edu on 12/07/17.
 */
class PaginaElemFactoryImpl implements IPaginaElemFactory{


    List<Elem2PDFAbst> elem2PDFList
    PdfDocument pdfDocument

    List<TablaElemAbst> tablaElemList
    TablaElemAbst tablaElem
    List<PaginaElemAbst> paginaElemList
    PaginaElemAbst paginaElem
    Elem2PDFAbst elem2PDF


    @Override
    IPaginaElemFactory withContent(List<Elem2PDFAbst> elem2PDFList) {
        this.elem2PDFList = elem2PDFList
        return this
    }

    @Override
    IPaginaElemFactory withPdfDocument(PdfDocument pdfDocument) {
        this.pdfDocument = pdfDocument
        return this
    }

    @Override
    IPaginaElemFactory withEncabezado() {
        return this
    }

    @Override
    IPaginaElemFactory withTitulo() {
        return this
    }

    @Override
    IPaginaElemFactory withMembrete() {

        return this
    }

    @Override
    IPaginaElemFactory withTextoMargenIzdo() {
        return this
    }

    @Override
    PaginaElemAbst build() {

        PdfPage pdfPage = new PdfPage()
        PdfCanvas pdfCanvas = new  PdfCanvas(pdfPage)
        pdfCanvas.rectangle(PageSize.A4)
        pdfCanvas.stroke()

        Canvas canvas = new Canvas (pdfCanvas, this.pdfDocument, PageSize.A4)

        // Encabezado
        TablaElemEncabezado tablaElemEncabezado = new TablaElemEncabezadoFactoryImpl()
                                                            .withHeighAndWidth()
                                                            .build()
        // -> Añadir encabezado a la página
        canvas.add(tablaElemEncabezado.getTableElem())


        // Titulo
        Rectangle recTitulo
        Text text

        ParagraphElemTitulo paragraphElemTitulo = new ParagraphElemTituloFactoryImpl()
                                                            .withRectangle(recTitulo)
                                                            .withText(text)
                                                            .build()
        // -> Añadir título a la página
        canvas.add(paragraphElemTitulo.getParagraph())


        // Membrete
        // -> Añadir membrete a la página


        // Tabla de contenidos

        TablaElemAbst tablaElemAbst = createTableContent()

        // -> Añadir tabla de contenidos a la página
        canvas.add(tablaElemAbst.getTableElem())

        PaginaElemAbst paginaElem = new PaginaElemImpl(pdfPage)

        return paginaElem

    }

    TablaElemAbst createTableContent(){

        // Definir las medidas de la tabla

        // A partir de la lista elementos2pdf se generará el contenido, a partir de este, se generarán las celdas,
        // y a partir de las celdas, se generará la tabla

        List<CeldaElemAbst> celdaElemList = new ArrayList<CeldaElemAbst>()

        elem2PDFList.each { elem ->

            celdaElemList.add(elem.generateContent().generateCelda())

        }

        // Con la lista de celdas, genero la tabla

        float height
        float width

        TablaElemAbst tablaElemAbst = new TablaElemContentFactoryImpl().withHeighAndWidth(height, width).withCeldaElemList(celdaElemList).buildOne()

    }

}
