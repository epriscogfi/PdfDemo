package com.tinsa.demo.pdf.PdfDemo.abst

import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst

/**
 * Created by edu on 12/07/17.
 */
abstract class ContentAbst {

    CeldaElemAbst celdaElemAbst

    CeldaElemAbst generateCelda(){
        return this.celdaElemAbst
    }

}
