package com.tinsa.demo.pdf.PdfDemo.resource

import groovy.io.FileType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
class PdfResource{

    static final Map CATEGORIES_ORDER = ['CAT_IMG_001':'1', 'CAT_IMG_002':'2', 'CAT_IMG_003':'3', 'CAT_DOC_001':'4', 'CAT_DOC_002':'5']
    static final List<String> DOC_CATEGORIES = ['CAT_DOC_001','CAT_DOC_002']
    static final List<String> IMG_CATEGORIES = ['CAT_IMG_001','CAT_IMG_002','CAT_IMG_003']
    static final String MIME_TYPE_IMAGE = 'Image'
    static final String MIME_TYPE_DOC =  'Document'
    static final Map MAP_MIME_TYPE = ["PNG": MIME_TYPE_IMAGE, "JPG":MIME_TYPE_IMAGE, "PDF": MIME_TYPE_DOC]


    @RequestMapping(value="/generar")
    @ResponseStatus(HttpStatus.CREATED)
    void generatePdf(@RequestParam(value="dossier") String id){

        def dirBase = new File("/home/edu/dossiers/" + id)

        def random = new Random()
        dirBase.eachFileRecurse (FileType.FILES){file ->
            println(file.path)
            file.metaPropertyValues.each {prop ->
                println(prop.name + ': ' + prop.value + ' - type:' + prop.type )
            }
        }



    }
}