package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfPage
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.WriterProperties
import com.itextpdf.layout.element.Text
import com.tinsa.demo.pdf.PdfDemo.abst.DocumentAbst
import com.tinsa.demo.pdf.PdfDemo.elems.ParagraphElemTitulo
import com.tinsa.demo.pdf.PdfDemo.elems.TablaElemEncabezado
import com.tinsa.demo.pdf.PdfDemo.elems.TablaElemMembrete
import com.tinsa.demo.pdf.PdfDemo.interfaces.IElem2PDF
import com.tinsa.demo.pdf.PdfDemo.elems.DocumentDefaultImpl
import com.tinsa.demo.pdf.PdfDemo.elems.Elems2PDFWrapper
import com.tinsa.demo.pdf.PdfDemo.interfaces.IDocumentFactory
import com.tinsa.demo.pdf.PdfDemo.resource.PdfResource

/**
 * Created by edu on 13/07/17.
 */
class DocumentDefaultFactoryimpl implements IDocumentFactory{

    static final Map<String, String> CATEGORIES_DESC = ['CAT_IMG_001': 'CATEGORIA DE IMAGENES 1',
                                                        'CAT_IMG_002': 'CATEGORIA DE IMAGENES 2',
                                                        'CAT_IMG_003': 'CATEGORIA DE IMAGENES 3',
                                                        'CAT_IMG_004': 'CATEGORIA DE IMAGENES 4',
                                                        'CAT_IMG_005': 'CATEGORIA DE IMAGENES 5',
                                                        'CAT_IMG_006': 'CATEGORIA DE IMAGENES 6',
                                                        'CAT_DOC_001': 'CATEGORIA DE DOUCMENTOS 1',
                                                        'CAT_DOC_002': 'CATEGORIA DE DOUCMENTOS 2']

    Map<String, Elems2PDFWrapper> mapElems2PDF
    Map<String, String> mapOrderCategories
    String destino

    @Override
    IDocumentFactory withElems2Pdf(Map<String, Elems2PDFWrapper> mapElems2PDF) {
        this.mapElems2PDF = mapElems2PDF
        return this
    }

    @Override
    IDocumentFactory withOrderCategories(Map<String, String> mapOrderCategories) {
        this.mapOrderCategories = mapOrderCategories
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
        return this
    }

    /**
     *
     * @return
     */
    @Override
    DocumentAbst build() {


        WriterProperties writerProperties = new WriterProperties()
        writerProperties.setFullCompressionMode(true)

        PdfWriter pdfWriter = new PdfWriter(this.destino, writerProperties)

        PdfDocument pdfDocument = new PdfDocument(pdfWriter)

        Long starDoc = System.nanoTime()

        // Encabezado
        TablaElemEncabezado tablaElemEncabezado = new TablaElemEncabezadoFactoryImpl()
                .withColumns(3)
                .withHeight(102)
                .withWidth(510)
                .withHMarginLeft(56)
                .withNumRows(1)
                .build()

        //Membrete
        TablaElemMembrete tablaElemMembrete = new TablaElemMembreteFactoryImpl()
                .withColumns(2)
                .withHeight(52)
                .withWidth(510)
                .withHMarginLeft(56)
                .build()


        this.mapOrderCategories.each {category ->

            //Título del documento
            ParagraphElemTitulo paragraphElemTitulo = new ParagraphElemTituloFactoryImpl()
                    .withText(new Text(CATEGORIES_DESC.get(category.value)))
                    .withWidth(PageSize.A4.width)
                    .build()

            if (this.mapElems2PDF.get(category.value) != null){

                Elems2PDFWrapper elems2PDFWrapper = this.mapElems2PDF.get(category.value)

                List<PdfPage> pdfPageList

                switch (elems2PDFWrapper.getTipoElems()){

                    case PdfResource.TIPO_ELEMS_DOC:

                        Long starDocs = System.nanoTime()
                        pdfPageList = new DocsPagesFactoryImpl()
                                .withPdfDocument(pdfDocument)
                                .withContent(elems2PDFWrapper)
                                .withTitulo(paragraphElemTitulo)
                                .withEncabezado(tablaElemEncabezado)
                                .withMembrete(tablaElemMembrete)
                                .withTextoMargenIzdo()
                                .build()

                        Long durationDocs = (System.nanoTime() - starDocs) / 1_000_000
                        System.out.println("Documents proccessed in " + durationDocs + " msecs")
                        break

                    case PdfResource.TIPO_ELEMS_IMG:

                        Long startImages = System.nanoTime()

                        pdfPageList = new ImgPagesFactoryImpl()
                                .withPdfDocument(pdfDocument)
                                .withContent(elems2PDFWrapper)
                                .withEncabezado(tablaElemEncabezado)
                                .withTitulo(paragraphElemTitulo)
                                .withMembrete(tablaElemMembrete)
                                .withTextoMargenIzdo()
                                .build()

                        Long durationImages = (System.nanoTime() - startImages) / 1_000_000
                        System.out.println("Images proccessed in " + durationImages + " msecs")

                        break

                    default:

                        pdfPageList = new ArrayList<PdfPage>()

                        break

                }

                pdfPageList.parallelStream().each {pdfPage -> pdfDocument.addPage(pdfPage)}

            }

        }

        Long durationDoc = (System.nanoTime() - starDoc) / 1_000_000
        System.out.println("Doc proccessed in " + durationDoc + " msecs")

        pdfDocument.close()

        return new DocumentDefaultImpl(pdfDocument)
    }

    /**
     *
     * @param numPaginas
     * @return
     */

    List<List<IElem2PDF>> particionarElem2PDF(int numPaginas, Elems2PDFWrapper elems2PDFWrapper){

        List<List<IElem2PDF>> listOfLists = new ArrayList<List<IElem2PDF>>()

        int count = 0

        1.upto(numPaginas){

            List<IElem2PDF> elem2PDFListAux = new ArrayList<IElem2PDF>()

            1.upto(elems2PDFWrapper.getElemsPorPagina()){

                if (elems2PDFWrapper.getElem2PDFAbstList()[count] != null) {

                    elem2PDFListAux.add(elems2PDFWrapper.getElem2PDFAbstList()[count])
                    count ++
                }

            }

            listOfLists.add(elem2PDFListAux)
        }

        return listOfLists

    }

    /**
     *
     * @return
     */
    int numPagesCategory(Elems2PDFWrapper elems2PDFWrapper){

        //Obtener el numero de paginas que va a tener el documento
        return (elems2PDFWrapper.getElem2PDFAbstList().size() / elems2PDFWrapper.getElemsPorPagina()).intValue() +
               (elems2PDFWrapper.getElem2PDFAbstList().size() % elems2PDFWrapper.getElemsPorPagina() == 0 ? 0 : 1)
    }

}
