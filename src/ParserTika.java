import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.parser.odf.OpenDocumentParser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.txt.TXTParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ParserTika {


    public String parseHTML(File fichero) throws IOException, TikaException, SAXException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(fichero);
        ParseContext pcontext=new ParseContext();


        HtmlParser htmlParser = new HtmlParser();
        htmlParser.parse(inputstream, handler, metadata,pcontext);

        return handler.toString();
    }


    public String parseTXT(File fichero) throws IOException, TikaException, SAXException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(fichero);
        ParseContext pcontext=new ParseContext();


        TXTParser TexTParser = new TXTParser();
        TexTParser.parse(inputstream, handler, metadata,pcontext);

        return handler.toString();
    }

    public String parsePDF(File fichero) throws TikaException, IOException, SAXException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(fichero);
        ParseContext pcontext = new ParseContext();


        PDFParser pdfparser = new PDFParser();
        pdfparser.parse(inputstream, handler, metadata, pcontext);


        return handler.toString();
    }

    public String parseOpenDoc(File fichero) throws IOException, TikaException, SAXException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(fichero);
        ParseContext pcontext=new ParseContext();

        OpenDocumentParser docParser = new OpenDocumentParser();
        docParser.parse(inputstream, handler, metadata,pcontext);

        return handler.toString();
    }

    public String parseMSDoc(File fichero) throws IOException, TikaException, SAXException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(fichero);
        ParseContext pcontext=new ParseContext();

        OOXMLParser docParser = new OOXMLParser  ();
        docParser.parse(inputstream, handler, metadata,pcontext);

        return handler.toString();
    }

    public String getExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

}
