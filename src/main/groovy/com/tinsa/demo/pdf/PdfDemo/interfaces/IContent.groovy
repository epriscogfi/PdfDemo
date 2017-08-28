package com.tinsa.demo.pdf.PdfDemo.interfaces

import com.itextpdf.kernel.geom.Rectangle
import com.tinsa.demo.pdf.PdfDemo.abst.CeldaElemAbst

/**
 * Created by edu on 12/07/17.
 */

interface IContent {

    CeldaElemAbst generateCelda(Rectangle rectangle)
}
