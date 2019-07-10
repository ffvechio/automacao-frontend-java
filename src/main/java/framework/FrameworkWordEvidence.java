
package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.Br;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Text;

public class FrameworkWordEvidence {
    public WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
        WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));
        return template;
    }

    public List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
        List<Object> result = new ArrayList<Object>();
        if (obj instanceof JAXBElement)
            obj = ((JAXBElement<?>) obj).getValue();

        if (obj.getClass().equals(toSearch))
            result.add(obj);
        else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child, toSearch));
            }

        }
        return result;
    }

    public void replacePlaceholder(WordprocessingMLPackage template, String name, String placeholder) {
        List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);

        for (Object text : texts) {
            Text textElement = (Text) text;
            if (textElement.getValue().equals(placeholder)) {
                textElement.setValue(name);
            }
        }
    }

    public void writeDocxToStream(WordprocessingMLPackage template, String target) throws IOException, Docx4JException {
        File f = new File(target);
        template.save(f);
    }

    public void addImage(WordprocessingMLPackage template, String evidence) throws Exception {
        File file = new File(evidence);
        InputStream is = new FileInputStream(file);
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            System.out.println("Could not completely read file " + file.getName());
        }
        is.close();

        String filenameHint = null;
        String altText = null;
        int id1 = 0;
        int id2 = 1;
        org.docx4j.wml.P p = newImage(template, bytes, filenameHint, altText, id1, id2, 9000);
        template.getMainDocumentPart().addObject(p);
    }

    public org.docx4j.wml.P newImage(WordprocessingMLPackage wordMLPackage, byte[] bytes, String filenameHint,
                                     String altText, int id1, int id2, long cx) throws Exception {

        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        Inline inline = imagePart.createImageInline(filenameHint, altText, id1, id2, cx, false);

        // Now add the inline in w:p/w:r/w:drawing
        org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
        org.docx4j.wml.P p = factory.createP();
        org.docx4j.wml.R run = factory.createR();
        p.getContent().add(run);
        org.docx4j.wml.Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);

        return p;

    }

    public void addText(WordprocessingMLPackage template, String text) throws InvalidFormatException, JAXBException {
        org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
        P spc = factory.createP();
        R rspc = factory.createR();
        Br br = factory.createBr();
        Text t2 = factory.createText();
        String[] txtParagraphArray = text.split("\n");
        for (String txt : txtParagraphArray) {
            template.getMainDocumentPart().addParagraphOfText(txt);
            //template.getMainDocumentPart().addObject(br);
        }
        //template.getMainDocumentPart().addParagraph(sb.toString());
    }

    public void createWordEvidence(WordprocessingMLPackage template, String evidencePath, String fileName)
            throws Exception {
        File folder = new File(evidencePath);
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles);
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].getName().contains("png")) {
                addImage(template, evidencePath + "/" + listOfFiles[i].getName());
                listOfFiles[i].delete();
            }
        }
        folder.delete();
        if (System.getProperty("os.name").contains("Window"))
            writeDocxToStream(template, evidencePath + "\\..\\" + fileName + ".docx");
        else
            writeDocxToStream(template, evidencePath + ".docx");

    }

}
