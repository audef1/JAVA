package Marshaller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class XMLHelper {

		public static void saveInstance(OutputStream outputStream, Object instance) throws JAXBException , IOException
		{
			Marshaller marshaller = JAXBContext.newInstance(instance.getClass()).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT , Boolean.TRUE);
			marshaller.marshal(instance , outputStream);
			outputStream.flush();
		}
		
		public static void saveInstance(Writer writer, Object instance) throws JAXBException , IOException
		{
			Marshaller marshaller = JAXBContext.newInstance(instance.getClass()).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT , Boolean.TRUE);
			marshaller.marshal(instance , writer);
			writer.flush();
		}
		
		@SuppressWarnings("rawtypes")
		public static Object loadInstance(InputStream inputStream, Class instanceClass) throws JAXBException {
			Unmarshaller unmarshaller = JAXBContext.newInstance(instanceClass).createUnmarshaller();
			Object instance = unmarshaller.unmarshal(inputStream);
			return instance;
		}
		
		@SuppressWarnings("rawtypes")
		public static Object loadInstance(Reader reader, Class instanceClass) throws JAXBException {
			Unmarshaller unmarshaller = JAXBContext.newInstance(instanceClass).createUnmarshaller();
			Object instance = unmarshaller.unmarshal(reader);
			return instance;
		}
		
		//XSD erstellen
		//lokale klasse
		static class LocalFileSchemaResolver extends SchemaOutputResolver {
			
			private File baseDir;

			public LocalFileSchemaResolver(File baseDir){
				this.baseDir = baseDir;
			}
			
			public Result createOutput(String namespaceUri, String suggestedFileName)throws IOException{
					return new StreamResult(new File(baseDir, suggestedFileName));
			}
		}
		
		public static void saveSchema(File baseDir, Class ... classes) throws JAXBException, IOException{
			JAXBContext context = JAXBContext.newInstance(classes);
			context.generateSchema(new LocalFileSchemaResolver(baseDir));
		}
		
		//xml mit verknüpftem schema erstellen
		public static void saveInstance(OutputStream outputStream, URL schemaURL, String schemaName, Object instance) throws JAXBException, IOException {
			Marshaller marshaller = JAXBContext.newInstance(instance.getClass()).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaURL + " " + schemaName);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(instance, outputStream);
			outputStream.flush();
		}
		
		public static void saveInstance(Writer writer, URL schemaURL, String schemaName, Object instance) throws JAXBException, IOException {
			Marshaller marshaller = JAXBContext.newInstance(instance.getClass()).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaURL + " " + schemaName);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(instance, writer);
			writer.flush();
		}
		
		//load schema from Schema-File
		public static Schema loadSchema(URL schemaURL) throws SAXException{
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			return sf.newSchema(schemaURL);
		}
		
		//load instance from xml using schema
		public static Object loadInstance(InputStream inputStream , Schema schema, Class instanceClass) throws JAXBException {
			Unmarshaller unmarshaller = JAXBContext.newInstance(instanceClass).createUnmarshaller();
			unmarshaller.setSchema(schema);
			Object instance = unmarshaller.unmarshal(inputStream);
			return instance;
		}
		
		public static Object loadInstance(Reader reader , Schema schema, Class instanceClass) throws JAXBException {
			Unmarshaller unmarshaller = JAXBContext.newInstance(instanceClass).createUnmarshaller();
			unmarshaller.setSchema(schema);
			Object instance = unmarshaller.unmarshal(reader);
			return instance;
		}
		
}