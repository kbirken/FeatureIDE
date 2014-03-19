package de.ovgu.featureide.fm.core;

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
 * The registry for configurable feature factory providers.
 * 
 * If an alternate feature factory provider extension has been registered in the IDE,
 * subclassed feature objects may be created and used, which offer additional attributes.
 * 
 * Note that only none or exactly one extension can be registered. If more than one
 * extension is registered, all will be ignored and the default feature factory will be
 * used as a fallback.
 * 
 * @author Klaus Birken (itemis AG)
 */
public class FeatureFactoryRegistry {

	private static final String EXTENSION_POINT_ID =
			"de.ovgu.featureide.fm.core.FeatureFactory";

	private static IFeatureFactory actualFactory = null;

	private static List<IFeatureFactory> extensions = null;

	
	/**
	 * Get IDE-wide feature factory which should be used by FeatureIDE.
	 * 
	 * The feature factory is used for creating feature objects.
	 * 
	 * @return the actual feature factory
	 */
	public static IFeatureFactory getFactory() {
		if (actualFactory == null) {
			// register extensions on the fly 
			initExtensions();

			// if there is exactly one extension, use it
			if (extensions.size() == 1) {
				actualFactory = extensions.get(0);
			} else {
				if (extensions.isEmpty()) {
					// no extensions, silently ignore
				} else if (extensions.size() > 1) {
					// TODO: use logger for reporting error message
					System.err.println(
						"More than one extension has been registered " +
						"at FeatureIDE's feature factory extension point, " +
						"using built-in feature objects!");
				}
				
				// use standard behavior of FeatureIDE
				actualFactory = new DefaultFeatureFactory();
			}
		}

		return actualFactory;
	}
	
	/**
	 * Initialize extensions from Eclipse's extension registry.
	 */
	private static void initExtensions() {
		extensions = new ArrayList<IFeatureFactory>();

		IExtensionRegistry reg = Platform.getExtensionRegistry();
		if (reg==null) {
			// standalone mode, we cannot get extensions from extension point registry
			return;
		}

		IExtensionPoint ep = reg.getExtensionPoint(EXTENSION_POINT_ID);
		for (IExtension extension : ep.getExtensions()) {
			for (IConfigurationElement ce : extension.getConfigurationElements()) {
				if (ce.getName().equals("featureFactory")) {
					try {
						Object o = ce.createExecutableExtension("class");
						if (o instanceof IFeatureFactory) {
							IFeatureFactory factory = (IFeatureFactory) o;
							extensions.add(factory);
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
