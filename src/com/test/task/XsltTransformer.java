package com.test.task;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.logging.Level;

import static com.test.task.MyLogger.logger;

public class XsltTransformer {


    public static boolean transform(String xml,String xlst,String resultXml) {

        try {
            File xmlFile = new File(xml);
            File xlstFile = new File(xlst);

            if (!xmlFile.exists()) {
                logger.log(Level.SEVERE,"File "+xml + " not found");
            }

            if (!xlstFile.exists()) {
                logger.log(Level.SEVERE,"File "+xlst + " not found");
            }

            if (!xmlFile.exists() || !xlstFile.exists()) {
                return false;
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            //установка используемого XSL-преобразования
            Transformer transformer = tf.newTransformer(new StreamSource(xlst));
            //установка исходного XML-документа и конечного XML-файла
            logger.log(Level.INFO,"Start transformation " + xml);
            transformer.transform(new StreamSource(xml), new StreamResult(resultXml));
            logger.log(Level.INFO,"Transformation "+xml+" complete");
            logger.log(Level.INFO,"Create "+resultXml);
            return true;

        } catch(TransformerException e) {

            logger.log(Level.SEVERE,"Impossible transform file " + xml+ " : " + e.getMessage());
            logger.log(Level.INFO,"Transformation "+xml+" failed");
            return false;
        }

    }

}

