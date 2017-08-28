package com.tinsa.demo.pdf.PdfDemo.abst

import com.itextpdf.layout.element.Cell
import groovy.transform.Immutable

/**
 * Created by edu on 12/07/17.
 */
abstract class CeldaElemAbst {

    Cell cellElem

    CeldaElemAbst(Cell cellElem) {
        this.cellElem = cellElem
    }

    Cell getCellElem() {
        return cellElem
    }

    void setCellElem(Cell cellElem) {
        this.cellElem = cellElem
    }
}
