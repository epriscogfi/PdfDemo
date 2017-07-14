package com.tinsa.demo.pdf.PdfDemo.abst

import com.itextpdf.layout.element.Paragraph
import groovy.transform.Immutable

/**
 * Created by edu on 14/07/17.
 */
abstract class ParagraphElemAbst {

    Paragraph paragraph

    ParagraphElemAbst(Paragraph paragraph) {
        this.paragraph = paragraph
    }

    Paragraph getParagraph() {
        return paragraph
    }

    void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph
    }
}
