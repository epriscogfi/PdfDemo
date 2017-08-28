package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.io.font.FontConstants
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.layout.border.Border
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.property.HorizontalAlignment
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.VerticalAlignment
import com.tinsa.demo.pdf.PdfDemo.elems.CeldaElemImg
import com.tinsa.demo.pdf.PdfDemo.elems.TablaElemEncabezado
import com.tinsa.demo.pdf.PdfDemo.interfaces.IElem2PDF
import com.tinsa.demo.pdf.PdfDemo.abst.TablaElemAbst
import com.tinsa.demo.pdf.PdfDemo.interfaces.ITablaElemFactory
import org.springframework.context.annotation.PropertySource


/**
 * Created by edu on 14/07/17.
 */
@PropertySource('classpath:application.properties')
class TablaElemEncabezadoFactoryImpl implements ITablaElemFactory{

    static final String NOMBRE_EMPRESA="TASACIONES INMOBILIARIAS S.A."
    static final String DIRECCION_CALLE="José Echegaray"
    static final String DIRECCION_NUMERO="9"
    static final String DIRECCION_CP="28232"
    static final String DIRECCION_POBLACION="Las Rozas"
    static final String DIRECCION_PROVINCIA="Madrid"
    static final String TELEFONO="91 372 75 00"
    static final String FAX="91 372 75 10"
    static final String REGISTRO="Inscrita en el Registro del Banco de España con el nº 4313"
    static final String CABECERA_EXPEDIENTE="Nº Expediente: "
    static final String CABECERA_EXPEDIENTE_DUMMY="S - 00255/17-"
    static final String CABECERA_RENT_ENT_FIN="Nº Expediente: "
    static final String CABECERA_RENT_ENT_FIN_DUMMY="INGD (0110 LAS ROZAS)"
    static final String CABECERA_REF_TASADOR="Ref. Tasador"
    static final String CABECERA_REF_TASADOR_DUMMY="#OC"
    static final String CABECERA_DOSSIER="D:"
    static final String CABECERA_DOSSIER_DUMMY="5/17"



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
        table.setWidth((this.width - 15f).floatValue())
        table.setMarginLeft(this.marginLeft)
        table.setMarginTop(this.marginTop)

        int cellWidth = (this.width / this.numColumns).intValue()
        int cellHeight = this.height.intValue()

        Rectangle rectangle = new  Rectangle(cellWidth, cellHeight)

        // Celda con logo
        CeldaElemImg celdaElemLogoTinsa = new CeldaElemImgFactoryImpl()
                .withImage(new Image(ImageDataFactory.create(getClass().getResource('/img/LogoTinsa.png'))))
                .withPadding(9)
                .withRectangle(rectangle)
                .build()

        table.addCell(celdaElemLogoTinsa.getCellElem())

        // Datos de la empresa y dirección
        Cell cell = new Cell()
        cell.setBorder(Border.NO_BORDER)
        cell.setHeight(rectangle.getHeight())
        cell.setWidth((rectangle.getWidth() + 15f).floatValue())
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER)
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE)


        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA)


        Paragraph paragNomEmpresa = new Paragraph(new Text(NOMBRE_EMPRESA).setFont(font).setFontSize(8f).setBold()).setTextAlignment(TextAlignment.CENTER)
        Paragraph paragDireccion = new Paragraph(new Text(DIRECCION_CALLE + ', ' + DIRECCION_NUMERO + ', ' + DIRECCION_CP + ' ' + DIRECCION_POBLACION + ' ' + DIRECCION_PROVINCIA ).setFont(font).setFontSize(8f)).setTextAlignment(TextAlignment.CENTER)


        Paragraph paragTelefonos = new Paragraph(new Text('Tel. ' + TELEFONO+ ' Fax.' + FAX).setFont(font).setFontSize(8f)).setTextAlignment(TextAlignment.CENTER)

        Paragraph paragRegistroMercanti = new Paragraph(new Text(this.REGISTRO).setFont(font).setFontSize(6f)).setTextAlignment(TextAlignment.CENTER)

        cell.add(paragNomEmpresa).add(paragDireccion).add(paragTelefonos).add(paragRegistroMercanti)

        table.addCell(cell)

        // Datos del Expediente
        cell = new Cell()
        cell.setBorder(Border.NO_BORDER)
        cell.setHeight(rectangle.getHeight())
        cell.setWidth(rectangle.getWidth())
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE)

        Paragraph paragExpediente  =  new Paragraph(new Text(CABECERA_EXPEDIENTE + CABECERA_EXPEDIENTE_DUMMY).setFont(font).setFontSize(8f)).setHorizontalAlignment(HorizontalAlignment.LEFT)
        Paragraph paragRef = new Paragraph(new Text(CABECERA_RENT_ENT_FIN + CABECERA_RENT_ENT_FIN_DUMMY).setFont(font).setFontSize(8f)).setHorizontalAlignment(HorizontalAlignment.LEFT)
        Paragraph paragRefTasadpr  =  new Paragraph(new Text(CABECERA_REF_TASADOR + CABECERA_REF_TASADOR_DUMMY).setFont(font).setFontSize(8f)).setHorizontalAlignment(HorizontalAlignment.LEFT)
        Paragraph paragNumDossier  =  new Paragraph(new Text(CABECERA_DOSSIER + CABECERA_DOSSIER_DUMMY).setFont(font).setFontSize(8f)).setHorizontalAlignment(HorizontalAlignment.LEFT)

        cell.add(paragExpediente).add(paragRef).add(paragRefTasadpr).add(paragNumDossier)

        table.addCell(cell)

        return new TablaElemEncabezado(table)
    }
}
