package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfPage
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.tinsa.demo.pdf.PdfDemo.abst.DocumentAbst
import com.tinsa.demo.pdf.PdfDemo.abst.Elem2PDFAbst
import com.tinsa.demo.pdf.PdfDemo.elems.DocumentDefaultImpl
import com.tinsa.demo.pdf.PdfDemo.elems.Elems2PDFWrapper
import com.tinsa.demo.pdf.PdfDemo.interfaces.IDocumentFactory

/**
 * Created by edu on 13/07/17.
 */
class DocumentDefaultFactoryimpl implements IDocumentFactory{

    List<Elem2PDFAbst> elem2PDFList
    String destino
    int elemsPorPagina

    /**
     *
     * @param elems2PDF
     * @return
     */
    @Override
    DocumentDefaultFactoryimpl withElems2Pdf(Elems2PDFWrapper elems2PDF) {
        this.elem2PDFList = elems2PDF.getiElem2PDFList()
        this.elemsPorPagina = elems2PDF.getElemsPorPagina()

        return this
    }

    /**
     *
     * @param destino
     * @return
     */
    @Override
    IDocumentFactory withDestino(String destino) {
        this.destino = destino
    }

    /**
     *
     * @return
     */
    @Override
    DocumentAbst build() {

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(this.destino))

        int numPaginas = numPaginasDoc()
        List<List<Elem2PDFAbst>> listaParticionada = particionarElem2PDF(numPaginas)

        int count = 0

        1.upto(numPaginas) {

            PaginaElemFactoryImpl paginaElemFactoryImpl = new PaginaElemFactoryImpl()

            PdfPage pdfPage = paginaElemFactoryImpl
                                   .withContent(listaParticionada[count])
                                   .withEncabezado()
                                   .withTitulo()
                                   .withMembrete()
                                   .withTextoMargenIzdo()
                                   .build()

            pdfDocument.addPage(pdfPage)

            count ++

        }
        pdfDocument.close()

        Document document = new Document(pdfDocument)
        document.close()

        return new DocumentDefaultImpl(pdfDocument)
    }

    /**
     *
     * @param numPaginas
     * @return
     */

    List<List<Elem2PDFAbst>> particionarElem2PDF(int numPaginas){

        List<List<Elem2PDFAbst>> listOfLists = new ArrayList<List<Elem2PDFAbst>>()

        int count = 0

        1.upto(numPaginas){

            List<Elem2PDFAbst> elem2PDFListAux = new ArrayList<Elem2PDFAbst>()

            1.upto(this.elemsPorPagina){

                elem2PDFListAux.add(elem2PDFList[count])
                count ++

            }

            listOfLists.add(elem2PDFListAux)
        }

        return listOfLists

    }

    /**
     *
     * @return
     */
    int numPaginasDoc(){

        //Obtener el numero de paginas que va a tener el documento

        return (this.elem2PDFList.size() / this.elemsPorPagina).intValue() + (this.elem2PDFList.size() % this.elemsPorPagina == 0 ? 0 : 1)
    }

}
