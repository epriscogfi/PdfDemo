package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.kernel.geom.AffineTransform
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfPage
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfStream
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject
import com.itextpdf.layout.Canvas
import com.tinsa.demo.pdf.PdfDemo.abst.PagesFactoryAbst
import com.tinsa.demo.pdf.PdfDemo.impl.TablaElemEncabezadoFactoryImpl

import java.util.stream.StreamSupport

import static java.util.stream.Collectors.toList

/**
 * Created by edu on 24/07/17.
 */
class DocsPagesFactoryImpl extends PagesFactoryAbst{

    @Override
    List<PdfPage> build(){


        List<List<PdfPage>> pdfPageListOfLists = this.elems2PDFWrapper.getElem2PDFAbstList().parallelStream().map{ elem ->

            long start = System.nanoTime()

            PdfDocument srcDoc = new PdfDocument(new PdfReader(elem.getPath()))

            int n = srcDoc.getNumberOfPages()

            List<PdfPage> pdfPageListAux = new ArrayList<PdfPage>()

            1.upto(n){numPagina ->

                PdfPage pdfPageOrigen = srcDoc.getPage(numPagina)

                Rectangle rectangleOrigen = pdfPageOrigen.getPageSize()

                PdfPage pdfPage = new PdfPage(this.pdfDocument, PageSize.A4)
                PdfCanvas pdfCanvas = new  PdfCanvas(pdfPage)

                pdfCanvas.stroke()

                Canvas canvas = new Canvas (pdfCanvas, this.pdfDocument, PageSize.A4)

                // -> Añadir encabezado a la página
                canvas.add(this.encabezado.getTableElem())

                // -> Añadir título
                canvas.add(this.paragraphElemTitulo.getParagraph())

                //-> Añadir membrete
                canvas.add(this.tablaElemMembrete.getTableElem())

                //-> Height
                float encabezadoH = this.encabezado.getTableElem().height.floatValue()
                float membreteH = this.tablaElemMembrete.getTableElem().height.floatValue()

                float height = encabezadoH + 80 + membreteH

                //-> Margin left
                float marginLeft = this.encabezado.getTableElem().getMarginLeft().floatValue()


                float scaleFactor = rectangleOrigen.height > rectangleOrigen.width ?  (PageSize.A4.height - height) / rectangleOrigen.height : (PageSize.A4.width - marginLeft) / rectangleOrigen.width

                AffineTransform affineTransform = AffineTransform.getScaleInstance(scaleFactor.doubleValue(), scaleFactor.doubleValue())
                pdfCanvas.concatMatrix(affineTransform)

                PdfFormXObject xPageObject = pdfPageOrigen.copyAsFormXObject(this.pdfDocument)

                PdfStream pdfStream = xPageObject.getPdfObject()

                pdfStream.setCompressionLevel(9)

                pdfStream.release()

                PdfFormXObject xObjectAux = new PdfFormXObject(pdfStream)

                pdfCanvas.addXObject(xObjectAux, marginLeft, 10)

                pdfPageListAux.add(pdfPage)

            }

            srcDoc.close()

            System.out.println("Document duration " + (System.nanoTime() - start) /1_000_000+ " msecs")

            return pdfPageListAux

        }.collect(toList())

        return pdfPageListOfLists.stream().flatMap{ pdfPageList -> pdfPageList.stream() }.collect(toList())

        //return pdfPageList.stream().collect(new ArrayList<>(), , )

//        List<PdfPage> pdfPageList = new ArrayList<PdfPage>()
//
//            this.elems2PDFWrapper.getElem2PDFAbstList().eachParallel{ elem ->
//
//            long start = System.nanoTime()
//
//            PdfDocument srcDoc = new PdfDocument(new PdfReader(elem.getPath()))
//
//            int n = srcDoc.getNumberOfPages()
//
//            1.upto(n){numPagina ->
//
//                PdfPage pdfPageOrigen = srcDoc.getPage(numPagina)
//
//                Rectangle rectangleOrigen = pdfPageOrigen.getPageSize()
//
//                PdfPage pdfPage = new PdfPage(this.pdfDocument, PageSize.A4)
//                PdfCanvas pdfCanvas = new  PdfCanvas(pdfPage)
//
//                pdfCanvas.stroke()
//
//                Canvas canvas = new Canvas (pdfCanvas, this.pdfDocument, PageSize.A4)
//
//                // -> Añadir encabezado a la página
//                canvas.add(this.encabezado.getTableElem())
//
//                // -> Añadir título
//                canvas.add(this.paragraphElemTitulo.getParagraph())
//
//                //-> Añadir membrete
//                canvas.add(this.tablaElemMembrete.getTableElem())
//
//
//                AffineTransform affineTransform = AffineTransform.getScaleInstance((pdfPage.getArtBox().width / rectangleOrigen.width) / 2, (pdfPage.getArtBox().height / rectangleOrigen.height) / 2 )
//                pdfCanvas.concatMatrix(affineTransform)
//
//                PdfFormXObject xPageObject = pdfPageOrigen.copyAsFormXObject(this.pdfDocument)
//
//                PdfStream pdfStream = xPageObject.getPdfObject()
//
//                pdfStream.setCompressionLevel(9)
//
//                pdfStream.release()
//
//                PdfFormXObject xObjectAux = new PdfFormXObject(pdfStream)
//
//                pdfCanvas.addXObject(xObjectAux, 0,0)
//
//                pdfPageList.add(pdfPage)
//            }
//
//
//            srcDoc.close()
//
//            System.out.println("Document duration " + System.nanoTime() - start + " msecs")
//
//        }


//        return pdfPageListOfLists

    }

}
