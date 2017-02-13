package com.test.task;

import java.io.File;
import java.io.IOException;
import  java.util.logging.Level;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import static com.test.task.MyLogger.logger;

public class XsdValidator
{
    public static boolean validateXMLSchema(String xmlName,String xsdName)
    {
        try {
            File xml = new File(xmlName);
            File xsd = new File(xsdName);
            if (!xml.exists()) {
                logger.log(Level.SEVERE,"File "+xml + " not found");
            }
            if (!xsd.exists()) {
                logger.log(Level.SEVERE,"File "+xsd + " not found");
            }

            if (!xml.exists() || !xsd.exists()) {
                return false;
            }
            // Получить фабрику для схемы
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // Загрузить схему из XSD
            Schema schema = factory.newSchema(xsd);
            // Создать валидатор
            Validator validator = schema.newValidator();
            logger.log(Level.INFO,"Start " + xml+ " validation");
            validator.validate(new StreamSource(xml));
            logger.log(Level.INFO,"Validation "+xml+" complete");
            return true;

        } catch ( SAXException e) {
            logger.log(Level.SEVERE,"Sax error: " + e.getMessage());
            logger.log(Level.INFO,"Validation " + xmlName+" failed");
            return false;
        } catch ( IOException e) {
            logger.log(Level.SEVERE,"I/O error: " + e.getMessage());
            logger.log(Level.INFO,"Validation "+ xmlName+" failed");
            return false;
        }

    }
}