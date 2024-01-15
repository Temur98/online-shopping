package service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Product;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Slf4j
public class PdfExportUtil {
    public static byte[] exportProductsToPDF(List<Product> products) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);

            document.open();
            for (Product product : products) {
                document.add(new Paragraph("Product ID: " + product.getId()));
                document.add(new Paragraph("Product Name: " + product.getProductName()));
                document.add(new Paragraph("Brand: " + product.getBrand()));
                document.add(new Paragraph("Colour: " + product.getColor()));
                document.add(new Paragraph("Price: " + product.getPrice()));
                document.add(new Paragraph("Quantity: " + product.getQuantity()));
                document.add(new Paragraph("\n"));
            }
            document.close();

            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error("Error occurred while exporting to PDF");
            return new byte[0];
        }
    }

}
