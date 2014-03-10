package de.ovgu.featureide.fm.core.io;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import de.ovgu.featureide.fm.core.io.IFeatureModelPersistency;
import de.ovgu.featureide.fm.core.io.xml.XmlFeatureModelPersistency;


/**
 * The registry for persistency layer extensions.
 * 
 * If an alternate persistency layer extension has been registered in the IDE,
 * it will be used instead of the built-in XML file format.
 * 
 * Note that only none or exactly one extension can be registered. If more than one
 * extensions are registered, all will be ignored and the built-in XML format will be
 * used as a fallback.
 * 
 * @author Klaus Birken (itemis AG)
 */
public class PersistencyRegistry {

	private static final String EXTENSION_POINT_ID = "de.ovgu.featureide.fm.core.Persistency";

	private static IFeatureModelPersistency actualPersistency = null;

	private static List<IFeatureModelPersistency> extensions = null;
	
	/**
	 * Get IDE-wide persistency layer which should be used by FeatureIDE.
	 * 
	 * The persistency layer defines which storage is used for storing and
	 * retrieving feature models. The default format is the XML-format built
	 * into FeatureIDE.
	 * 
	 * @return the actual persistency layer
	 */
	public static IFeatureModelPersistency getPersistency() {
		if (actualPersistency == null) {
			// register extensions on the fly 
			initExtensions();

			// if there is exactly one extension, use it
			if (extensions.size() == 1) {
				actualPersistency = extensions.get(0);
			} else {
				if (extensions.isEmpty()) {
					// no extensions, silently ignore
				} else if (extensions.size() > 1) {
					// TODO: use logger for reporting error message
					System.err.println(
						"More than one extension has been registered " +
						"at FeatureIDE's persistency extension point, " +
						"using built-in XML format!");
				}
				
				// use standard (XML) persistency of FeatureIDE
				actualPersistency = new XmlFeatureModelPersistency();
			}
		}

		return actualPersistency;
	}
	
	/**
	 * Initialize extensions from Eclipse's extension registry.
	 */
	private static void initExtensions() {
		extensions = new ArrayList<IFeatureModelPersistency>();

		IExtensionRegistry reg = Platform.getExtensionRegistry();
		if (reg==null) {
			// standalone mode, we cannot get extensions from extension point registry
			return;
		}

		IExtensionPoint ep = reg.getExtensionPoint(EXTENSION_POINT_ID);
		for (IExtension extension : ep.getExtensions()) {
			for (IConfigurationElement ce : extension.getConfigurationElements()) {
				if (ce.getName().equals("persistency")) {
					try {
						Object o = ce.createExecutableExtension("class");
						if (o instanceof IFeatureModelPersistency) {
							IFeatureModelPersistency persistency = (IFeatureModelPersistency) o;
							extensions.add(persistency);
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
