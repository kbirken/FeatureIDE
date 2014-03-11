/* FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) 2005-2013  FeatureIDE team, University of Magdeburg, Germany
 *
 * This file is part of FeatureIDE.
 * 
 * FeatureIDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * FeatureIDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with FeatureIDE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * See http://www.fosd.de/featureide/ for further information.
 */
package de.ovgu.featureide.fm.core.io;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.configuration.Configuration;
import de.ovgu.featureide.fm.core.configuration.IConfigurationReader;
import de.ovgu.featureide.fm.core.configuration.IConfigurationWriter;

/**
 * A helper class which can be used to create various readers and writers for feature models.
 * 
 * It will take into account if an alternate persistency layer has been registered in the IDE. 
 * 
 * @author Klaus Birken (itemis AG)
 */
public class PersistencyFactory {

	/**
	 * Create a standard feature model reader.
	 * 
	 * @param featureModel the feature model which receives the loaded data.
	 * @return the reader
	 */
	public static AbstractFeatureModelReader createFeatureModelReader (FeatureModel featureModel) {
		IFeatureModelPersistency persistency = PersistencyRegistry.getPersistency();
		return persistency.createFeatureModelReader(featureModel);
	}

	/**
	 * Create a standard feature model writer.
	 * 
	 * @param featureModel the feature model which will be stored.
	 * @return the writer
	 */
	public static AbstractFeatureModelWriter createFeatureModelWriter (FeatureModel featureModel) {
		IFeatureModelPersistency persistency = PersistencyRegistry.getPersistency();
		return persistency.createFeatureModelWriter(featureModel);
	}

	/**
	 * Create a feature model reader which is also capable of reading from an IFile.
	 * 
	 * @param featureModel the feature model which receives the loaded data.
	 * @return the reader
	 */
	public static FeatureModelReaderIFileWrapper createIFileFeatureModelReader (FeatureModel featureModel) {
		IFeatureModelPersistency persistency = PersistencyRegistry.getPersistency();
		AbstractFeatureModelReader reader = persistency.createFeatureModelReader(featureModel);
		return new FeatureModelReaderIFileWrapper(reader);
	}

	/**
	 * Create a feature model writer which is also capable of writing to an IFile.
	 * 
	 * @param featureModel the feature model which will be stored.
	 * @return the writer
	 */
	public static FeatureModelWriterIFileWrapper createIFileFeatureModelWriter (FeatureModel featureModel) {
		IFeatureModelPersistency persistency = PersistencyRegistry.getPersistency();
		AbstractFeatureModelWriter writer = persistency.createFeatureModelWriter(featureModel);
		return new FeatureModelWriterIFileWrapper(writer);
	}
	
	/**
	 * Create a standard configuration reader.
	 * 
	 * @param config the configuration which receives the loaded data.
	 * @return the reader
	 */
	public static IConfigurationReader createConfigurationReader (Configuration config) {
		IFeatureModelPersistency persistency = PersistencyRegistry.getPersistency();
		return persistency.createConfigurationReader(config);
	}

	/**
	 * Create a standard configuration writer.
	 * 
	 * @param config the configuration which will be stored.
	 * @return the writer
	 */
	public static IConfigurationWriter createConfigurationWriter (Configuration config) {
		IFeatureModelPersistency persistency = PersistencyRegistry.getPersistency();
		return persistency.createConfigurationWriter(config);
	}

}
