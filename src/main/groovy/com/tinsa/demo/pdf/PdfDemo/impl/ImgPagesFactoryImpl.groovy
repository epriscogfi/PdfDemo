package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfPage
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.layout.Canvas
import com.tinsa.demo.pdf.PdfDemo.abst.PagesFactoryAbst
import com.tinsa.demo.pdf.PdfDemo.abst.TablaElemAbst
import com.tinsa.demo.pdf.PdfDemo.elems.Elems2PDFWrapper
import com.tinsa.demo.pdf.PdfDemo.impl.TablaElemContentFactoryImpl
import com.tinsa.demo.pdf.PdfDemo.impl.TablaElemEncabezadoFactoryImpl
import com.tinsa.demo.pdf.PdfDemo.interfaces.IElem2PDF
import groovyx.gpars.ParallelEnhancer

import static java.util.stream.Collectors.toList

/**
 * Created by edu on 24/07/17.
 */
class ImgPagesFactoryImpl extends PagesFactoryAbst{


    @Override
    List<PdfPage> build(){

        List<List<IElem2PDF>> listaImgsPaginada = particionarElem2PDF(numPagesCategory(this.elems2PDFWrapper), this.elems2PDFWrapper)
        List<PdfPage> pdfPageList = new ArrayList<PdfPage>()

        long start = System.nanoTime()

//        listaImgsPaginada
//                .parallelStream()
//                .each {listaImages ->
//
//                    long startPage = System.nanoTime()
//
//                    PdfPage pdfPage = new PdfPage(this.pdfDocument)
//
//                    PdfCanvas pdfCanvas = new  PdfCanvas(pdfPage)
//                    pdfCanvas.rectangle(PageSize.A4)
//
//                    pdfCanvas.stroke()
//
//                    Canvas canvas = new Canvas (pdfCanvas, this.pdfDocument, PageSize.A4)
//
//
//                    // -> Añadir encabezado a la página
//                    canvas.add(this.encabezado.getTableElem())
//
//                    // -> Añadir título a la página
//                    canvas.add(this.paragraphElemTitulo.getParagraph())
//
//                    // -> Añadir tmembrete a la pagina
//                    canvas.add(this.tablaElemMembrete.getTableElem())
//
//                    // -> Crear contenido de tabla
//                    canvas.add(createTableContentList(listaImages).getTableElem())
//
//
//
//                    pdfPageList.add(pdfPage)
//
//                    long durationPage =(System.nanoTime() - startPage) / 1_000_000
//                    System.out.println("Page done in " + durationPage + " msecs ")
//                }

        List<PdfPage> pdfPageListAux = listaImgsPaginada
                .parallelStream()
                .map { listaImages ->

                    //
                        long startPage = System.nanoTime()
                    //

                    PdfPage pdfPage = new PdfPage(this.pdfDocument)

                    PdfCanvas pdfCanvas = new  PdfCanvas(pdfPage)
                    pdfCanvas.rectangle(PageSize.A4)

                    pdfCanvas.stroke()

                    Canvas canvas = new Canvas (pdfCanvas, this.pdfDocument, PageSize.A4)


                    // -> Añadir encabezado a la página
                    canvas.add(this.encabezado.getTableElem())

                    // -> Añadir título a la página
                    canvas.add(this.paragraphElemTitulo.getParagraph())

                    // -> Añadir tmembrete a la pagina
                    canvas.add(this.tablaElemMembrete.getTableElem())

                    // -> Crear contenido de tabla
                    canvas.add(createTableContentList(listaImages).getTableElem())

                    //pdfPageList.add(pdfPage)
                    //
                        long durationPage =(System.nanoTime() - startPage) / 1_000_000
                        System.out.println("Page done in " + durationPage + " msecs ")
                    //
                    return pdfPage
                }
                .collect(toList())

        long duration =(System.nanoTime() - start) / 1_000_000

        System.out.println("Category done in " + duration + " msecs ")

        return pdfPageListAux

    }

    TablaElemAbst createTableContentList(List<IElem2PDF> elem2PDFList){

        // Definir las medidas de la tabla

        // A partir de la lista elementos2pdf se generará el contenido, a partir de este, se generarán las celdas,
        // y a partir de las celdas, se generará la tabla

        int numRows
        int numColumns

        switch (elem2PDFList.size()){

            case 1:
                numRows = 1
                numColumns = 1
                break

            case 2:
                numRows = 1
                numColumns = 2
                break

            default:
                numRows = 2
                numColumns = 2
                break
        }

        TablaElemAbst tablaElemAbst = new TablaElemContentFactoryImplGPars()
                .withColumns(numColumns)
                .withNumRows(numRows)
                .withElemList(elem2PDFList)
                .withWidth(510)
                .withHeight(636)
                .withHMarginLeft(56)
                .build()

        return tablaElemAbst
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
