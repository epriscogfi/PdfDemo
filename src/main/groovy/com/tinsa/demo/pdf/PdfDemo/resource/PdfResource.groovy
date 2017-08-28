package com.tinsa.demo.pdf.PdfDemo.resource

import com.itextpdf.layout.Document
import com.tinsa.demo.pdf.PdfDemo.abst.DocumentAbst
import com.tinsa.demo.pdf.PdfDemo.elems.ElemDoc2PDFImpl
import com.tinsa.demo.pdf.PdfDemo.elems.ElemImg2PDFImpl
import com.tinsa.demo.pdf.PdfDemo.elems.Elems2PDFWrapper
import com.tinsa.demo.pdf.PdfDemo.impl.DocumentDefaultFactoryimpl
import com.tinsa.demo.pdf.PdfDemo.interfaces.IElem2PDF
import groovy.io.FileType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
class PdfResource{

    static final Map<String, String> CATEGORIES_ORDER = ['1':'CAT_IMG_001', '2':'CAT_IMG_002', '3':'CAT_IMG_003', '4':'CAT_IMG_004', '5':'CAT_IMG_005', '6':'CAT_IMG_006', '7':'CAT_DOC_001', '8':'CAT_DOC_002']

    static final List<String> DOC_CATEGORIES = ['CAT_DOC_001','CAT_DOC_002']
    static final List<String> IMG_CATEGORIES = ['CAT_IMG_001','CAT_IMG_002','CAT_IMG_003','CAT_IMG_004','CAT_IMG_005','CAT_IMG_006']

    static final String MIME_TYPE_IMAGE = 'Image'
    static final String MIME_TYPE_DOC =  'Document'
    static final Map MAP_MIME_TYPE = ['PNG': MIME_TYPE_IMAGE, 'JPG':MIME_TYPE_IMAGE, "PDF": MIME_TYPE_DOC]

    static final String TIPO_ELEMS_DOC = 'DOC'
    static final String TIPO_ELEMS_IMG = 'IMG'


    @RequestMapping(value="/generar")
    @ResponseStatus(HttpStatus.CREATED)
    void generatePdf(@RequestParam(value="dossier") String id){

        File dirBase = new File("/home/edu/dossiers/" + id)
        String destino = "/home/edu/dossiers/generated/tmp/prueba.pdf"

        File fileDestiny = new File(destino)
        fileDestiny.getParentFile().mkdirs()

        Map<String, Elems2PDFWrapper> mapCategoriesLists = generateMapCategoriesElems(dirBase)

        DocumentAbst doc = new DocumentDefaultFactoryimpl()
                .withDestino(destino)
                .withElems2Pdf(mapCategoriesLists)
                .withOrderCategories(CATEGORIES_ORDER)
                .build()

        Document document = new Document(doc.getPdfDocument())
        document.close()

    }

    Map<String, Elems2PDFWrapper> generateMapCategoriesElems (File dirBase){

        Map<String, Elems2PDFWrapper> mapCategoriesLists = new HashMap<String, Elems2PDFWrapper>()

        dirBase.eachFileRecurse (FileType.FILES){file ->

            IElem2PDF elem2PDF
            String category
            String tipoElems
            Random random = new Random()


            switch(MAP_MIME_TYPE.get(file.path.substring(file.path.lastIndexOf('.')+1).toUpperCase())){

                case MIME_TYPE_IMAGE:

                    category = IMG_CATEGORIES.get(random.nextInt(IMG_CATEGORIES.size()))
                    elem2PDF = new ElemImg2PDFImpl(pathElem: file.path)
                    tipoElems = TIPO_ELEMS_IMG

                    break

                case MIME_TYPE_DOC:

                    category = DOC_CATEGORIES.get(random.nextInt(DOC_CATEGORIES.size()))
                    elem2PDF = new ElemDoc2PDFImpl(pathElem: file.path)
                    tipoElems = TIPO_ELEMS_DOC

                    break

                default:

                    break
            }

            if (mapCategoriesLists.get(category) == null){

                Elems2PDFWrapper elems2PDFWrapper = new Elems2PDFWrapper(elem2PDFAbstList: new ArrayList<IElem2PDF>(), elemsPorPagina: elem2PDF.getNumElemsPerPage(), tipoElems: tipoElems)
                elems2PDFWrapper.addElemToElemList(elem2PDF)

                mapCategoriesLists.put(category, elems2PDFWrapper)

            }
            else{

                mapCategoriesLists.get(category).addElemToElemList(elem2PDF)

            }

        }

        return mapCategoriesLists

    }




}