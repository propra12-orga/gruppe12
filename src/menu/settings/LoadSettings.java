package menu.settings;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LoadSettings {
	public static Settings load() throws JAXBException {
		Settings sets;
		File settingsFile = new File("settings.set");
		JAXBContext context = JAXBContext.newInstance(Settings.class);
		Unmarshaller unmarsh = context.createUnmarshaller();
		sets = (Settings) unmarsh.unmarshal(settingsFile);
		return sets;
	}
}
