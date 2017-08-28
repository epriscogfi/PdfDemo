package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.io.font.FontConstants
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.layout.border.Border
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Div
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.property.HorizontalAlignment
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.VerticalAlignment
import com.tinsa.demo.pdf.PdfDemo.abst.TablaElemAbst
import com.tinsa.demo.pdf.PdfDemo.elems.TablaElemMembrete
import com.tinsa.demo.pdf.PdfDemo.interfaces.IElem2PDF
import com.tinsa.demo.pdf.PdfDemo.interfaces.ITablaElemFactory

class TablaElemMembreteFactoryImpl implements ITablaElemFactory {

    static final String MEMBRETE_SOLICITANTE="SOLICITANTE: "
    static final String MEMBRETE_SOLICITANTE_DUMMY="LAURA QUEVEDO PÉREZ"
    static final String MEMBRETE_SITUACION="SITUACIÓN: "
    static final String MEMBRETE_SITUACION_DUMMY="Calle CARMEN BRAVO VILLASANTE, Nº 6-C, Planta 2ª, Letra B en el municipio de Santander, provincia de CANTABRIA (39011)"
    static final String MARGEN_LATERAL="Para validar y descaragar nuevamente el informe acceda a: https://www.tinsa.es./validador-informes/ y use el codigo de verificación "
    static final String CODIGO_VERIFICACION="HZNU37AFR"

    int numColumns
    int numRows
    float height
    float width
    float marginTop
    float marginLeft


    @Override
    ITablaElemFactory withColumns(int numColumns) {
        this.numColumns = numColumns
        return this
    }

    @Override
    ITablaElemFactory withNumRows(int numRows) {
        this.numRows = numRows
        return this
    }

    @Override
    ITablaElemFactory withHeight(float height) {
        this.height = height
        return this
    }

    @Override
    ITablaElemFactory withWidth(float width) {
        this.width = width
        return this
    }

    @Override
    ITablaElemFactory withMarginTop(float marginTop) {
        this.marginTop = marginTop
        return this
    }

    @Override
    ITablaElemFactory withHMarginLeft(float marginLeft) {
        this.marginLeft = marginLeft
        return this
    }

    @Override
    ITablaElemFactory withElemList(List<IElem2PDF> elem2PDFList) {
        return null
    }

    @Override
    TablaElemAbst build() {


        Table table =  new Table(this.numColumns)

        table.setHeight(this.height)
        table.setWidth(this.width)
        table.setMarginLeft(this.marginLeft)

        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA)

        Div div = new Div()
        div.setWidth(this.width)
        Paragraph paragraph = new Paragraph(new Text(MEMBRETE_SOLICITANTE + MEMBRETE_SOLICITANTE_DUMMY)).setFont(font).setFontSize(8f).setTextAlignment(TextAlignment.LEFT)
        div.add(paragraph)
        paragraph = new Paragraph(new Text(MEMBRETE_SITUACION + MEMBRETE_SITUACION_DUMMY)).setFont(font).setFontSize(8f).setTextAlignment(TextAlignment.LEFT)
        div.add(paragraph)

        Cell cell = new Cell().setWidth(this.width).setHeight(this.height).setVerticalAlignment(VerticalAlignment.MIDDLE).add(div)

        table.addCell(cell)

        return new TablaElemMembrete(table)

    }
}
