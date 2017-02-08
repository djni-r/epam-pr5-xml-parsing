/**
 * Epam External Training
 * Task 5
 * XML XSD Parsing
 */
package by.malinouski.xmlparse.validator;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

/**
 * @author makarymalinouski
 * Validates XML document to match its XSD rules
 */
public class XsdValidator {

    static final Logger LOGGER = LogManager.getLogger(XsdValidator.class);
    private SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    
    public boolean validate(String fileName, String schemaName) {
        File schemaLoc = new File(schemaName);
        
        try {
            Schema schema = factory.newSchema(schemaLoc);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(fileName));
            return true;
        } catch (SAXException e) {
            LOGGER.error("Not valid: " + e.getMessage());
            return false;
        } catch (IOException e) {
            LOGGER.error("Not valid: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            LOGGER.error("Cannot validate: " + e.getMessage());
            return false;
        }
    }
}
