package menu.settings;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Kriegt Settings übergeben und speichert sie in die XML-Datei "settings.set".
 * 
 * @author garg
 * 
 */

public class SaveSettings {

	Settings sets;
	File settings;

	public SaveSettings(Settings sets) throws JAXBException {
		this.sets = sets;
		save();
	}

	public void save() throws JAXBException {

		settings = new File("settings.set");

		JAXBContext context = JAXBContext.newInstance(Settings.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(sets, settings);
	}
}
