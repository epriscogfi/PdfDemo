package com.tinsa.demo.pdf.PdfDemo.elems

import com.tinsa.demo.pdf.PdfDemo.interfaces.IElem2PDF

/**
 * Created by edu on 13/07/17.
 */

class Elems2PDFWrapper {

    String tipoElems
    int elemsPorPagina

    List<IElem2PDF> elem2PDFAbstList

    void addElemToElemList(IElem2PDF elem2PDF){
        if (elem2PDFAbstList != null){
            this.elem2PDFAbstList.add(elem2PDF)
        }
    }

}
