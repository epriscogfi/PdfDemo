package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.layout.element.Table
import com.tinsa.demo.pdf.PdfDemo.interfaces.IElem2PDF
import com.tinsa.demo.pdf.PdfDemo.abst.TablaElemAbst
import com.tinsa.demo.pdf.PdfDemo.elems.TablaElemContent
import com.tinsa.demo.pdf.PdfDemo.interfaces.ITablaElemFactory

/**
 * Created by edu on 12/07/17.
 */
class TablaElemContentFactoryImpl implements ITablaElemFactory{

    int numColumns
    int numRows
    float height
    float width
    float marginTop
    float marginLeft
    List<IElem2PDF> elem2PDFList

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
        this.elem2PDFList = elem2PDFList
        return this
    }

    @Override
    TablaElemAbst build() {

        Table table = new Table(this.numColumns)

        table.setHeight(this.height)
        table.setWidth(this.width)
        table.setMarginLeft(this.marginLeft)

        Rectangle cellRectangle = calculateCellRectangle()

        int count  = 0

        long startCells = System.nanoTime()
        1.upto(this.numRows){

            1.upto(this.numColumns){

                if (this.elem2PDFList[count] != null){

                    long startCell = System.nanoTime()
                    table.addCell(this.elem2PDFList[count].generateContent().generateCelda(cellRectangle).getCellElem())
                    count ++

                    System.out.println("Cell duration " + (System.nanoTime() - startCell) / 1_000_000)
                }

            }
        }
        System.out.println("Cells duration " + (System.nanoTime() - startCells) / 1_000_000)

        TablaElemContent tablaElemContent = new TablaElemContent(table)

        return tablaElemContent
    }

    Rectangle calculateCellRectangle(){

        float rectangleWidth = this.width / this.numColumns
        float rectangleHeight = this.height / this.numRows


        Rectangle rectangle = new Rectangle(rectangleWidth, rectangleHeight)
        return rectangle
    }
}
