package com.tinsa.demo.pdf.PdfDemo.elems

import com.tinsa.demo.pdf.PdfDemo.abst.Elem2PDFAbst
import jdk.nashorn.internal.ir.annotations.Immutable

/**
 * Created by edu on 13/07/17.
 */
@Immutable
class Elems2PDFWrapper {

    int elemsPorPagina
    List<Elem2PDFAbst> iElem2PDFList

}
