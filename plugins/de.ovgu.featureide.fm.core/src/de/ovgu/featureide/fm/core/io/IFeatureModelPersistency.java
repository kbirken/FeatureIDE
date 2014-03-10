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
import de.ovgu.featureide.fm.core.configuration.IConfigurationWriter;

/**
 * The interface for defining a persistency layer for feature models.
 * 
 * This is defining an Eclipse extension point, which is used to register a
 * persistency layer which reads/writes a specific file format (or uses something
 * completely different than files, e.g., an online connection or a database).
 * 
 * @author Klaus Birken (itemisAG)
 */
public interface IFeatureModelPersistency {

	/**
	 * Factory function which provides a specific reader for feature models.
	 * 
	 * @param featureModel the feature model which will contain the data after reading 
	 * @return the actual reader  
	 */
	public AbstractFeatureModelReader createFeatureModelReader(FeatureModel featureModel); 

	/**
	 * Factory function which provides a specific writer for feature models.
	 * 
	 * @param featureModel the feature model which should be stored persistently
	 * @return the actual writer
	 */
	public AbstractFeatureModelWriter createFeatureModelWriter(FeatureModel featureModel); 

	/**
	 * Factory function which provides a specific writer for configurations.
	 * 
	 * @param config the configuration which should be stored persistently
	 * @return the actual writer
	 */
	public IConfigurationWriter createConfigurationWriter(Configuration config); 

	
}
