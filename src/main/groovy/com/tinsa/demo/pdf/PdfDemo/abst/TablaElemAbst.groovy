package com.tinsa.demo.pdf.PdfDemo.abst

import com.itextpdf.layout.element.Table
import groovy.transform.Immutable

/**
 * Created by edu on 12/07/17.
 */
abstract class TablaElemAbst {

    Table tableElem

    TablaElemAbst (Table tableElem) {
        this.tableElem = tableElem
    }

    Table getTableElem() {
        return tableElem
    }

    void setTableElem(Table tableElem) {
        this.tableElem = tableElem
    }
}
