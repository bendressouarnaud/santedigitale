package com.maternite.gestion.controllers;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.maternite.gestion.beans.Fichiers;
import com.maternite.gestion.repositories.FichiersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TraiterController {

    @Autowired
    FichiersRepository fichiersRepository;

    @CrossOrigin("*")
    @PostMapping(value="/traiterfichier")
    public void traiterfichier(
            @RequestParam("fichiersajoute") MultipartFile fichiers,
            HttpServletResponse response){

            ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            if (fichiers != null) {
                if (!fichiers.isEmpty()) {

                    Fichiers fichiers1 = fichiersRepository.findByIdfic(23);
                    //byte[] bytes = fichiers1.getFichier();

                    // Process , try to open it :
                    byte[] bytes = fichiers.getBytes();
                    InputStream is = new ByteArrayInputStream(bytes);
                    PdfReader pdfReader = new PdfReader(is);
                    PdfDocument pdfDoc = new PdfDocument(pdfReader);
                    System.out.println("Page : "+pdfDoc.getNumberOfPages());

                    // New ONE :
                    ByteArrayOutputStream mBos = new ByteArrayOutputStream();
                    PdfDocument pdfDocX = new PdfDocument(new PdfWriter(mBos));
                    pdfDoc.initializeOutlines();
                    pdfDoc.copyPagesTo(1, pdfDoc.getNumberOfPages(), pdfDocX);
                    pdfDoc.close();
                    System.out.println("Nouvelles Pages : "+pdfDocX.getNumberOfPages());

                    // Try to add a new page :
                    pdfDocX.addNewPage();
                    Document doc = new Document(pdfDocX);
                    ImageData image = ImageDataFactory.create(fichiers1.getFichier());
                    Image imageModel = new Image(image);
                    //imageModel.setFixedPosition(100, 250);

                    // Adding image to the document
                    doc.add(new Paragraph("Ma signature"));
                    doc.add(imageModel);


                    // Now :
                    /*
                    AffineTransform at =
                            AffineTransform.getTranslateInstance(36, 300);
                    at.concatenate(AffineTransform.getScaleInstance(imageModel.getImageScaledWidth(),
                            imageModel.getImageScaledHeight()));
                    PdfCanvas canvas = new PdfCanvas(pdfDocX.getLastPage());//.getFirstPage());
                    float[] matrix = new float[6];
                    at.getMatrix(matrix);
                    canvas.addImage(image, matrix[0], matrix[1], matrix[2], matrix[3], matrix[4], matrix[5]);
                    */

                    //
                    pdfDoc.close();
                    pdfDocX.close();
                    doc.close();

                    // setting some response headers
                    response.setHeader("Expires", "0");
                    response.setHeader("Cache-Control",
                            "must-revalidate, post-check=0, pre-check=0");
                    response.setHeader("Pragma", "public");
                    // setting the content type
                    response.setContentType("application/pdf");
                    // the contentlength
                    response.setContentLength(mBos.size());
                    // write ByteArrayOutputStream to the ServletOutputStream
                    OutputStream os = response.getOutputStream();
                    mBos.writeTo(os);
                    os.flush();
                    os.close();



                }
            }
        }
        catch (Exception exc){
            System.out.println("Exception : "+exc.toString());
        }
    }


    //afficherhistpatient
    @GetMapping(value = "/gestmixer")
    public String gestmixer(Model model, HttpSession session)
    {
        return "mixerfichier";
    }


    @GetMapping(value = "/testfichiers")
    public void testfichiers(HttpServletResponse response)
    {
        try {
            response.setContentType("application/pdf");
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(response.getOutputStream()));
            Document doc = new Document(pdfDoc);

            // Add a paragraph :
            Paragraph premierParagraph = new Paragraph();
            premierParagraph.add("Introduction");
            doc.add(premierParagraph);
            // Add Table :
            float[] pointColumnWidths = {150F, 150F, 150F};
            Table mTable = new Table(pointColumnWidths);

            // Adding cells to the table
            PdfFont f = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
            Cell nCell = new Cell(1,3);
            nCell.add(new Paragraph("En-tete"));
            nCell.setFont(f);
            nCell.setFontSize(14)
            .setFontColor(DeviceGray.WHITE)
                    .setBackgroundColor(DeviceGray.BLACK)
                    .setTextAlignment(TextAlignment.CENTER);
            //
            mTable.addHeaderCell(nCell);

            //
            for (int i = 0; i < 2; i++) {
                Cell[] headerFooter = new Cell[]{
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("#")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Key")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Value"))
                };

                for (Cell hfCell : headerFooter) {
                    if (i == 0) {
                        mTable.addHeaderCell(hfCell);
                    } else {
                        mTable.addFooterCell(hfCell);
                    }
                }
            }

            for (int counter = 0; counter < 10; counter++) {
                mTable.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(counter + 1))));
                mTable.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("key " + (counter + 1))));
                mTable.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("value " + (counter + 1))));
            }

            doc.add(mTable);
            doc.close();
        }
        catch (Exception exc){

        }
    }
}
