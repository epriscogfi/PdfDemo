package com.tinsa.demo.pdf.PdfDemo.impl

import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.layout.element.Image
import com.tinsa.demo.pdf.PdfDemo.interfaces.IContent
import com.tinsa.demo.pdf.PdfDemo.interfaces.IContentFactory

/**
 * Created by edu on 12/07/17.
 */
class ContentImgFactoryImpl implements IContentFactory{

    String path

    @Override
    IContentFactory withPath(String path) {
        this.path = path
        return this
    }

    @Override
    IContent build() {

        Image image = new Image(ImageDataFactory.create(this.path))

        ContentImgImpl contentImg = new ContentImgImpl(image)

        return contentImg
    }

}
