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
package de.ovgu.featureide.fm.core.configuration;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

/**
 * Reads a configuration from a file or string.
 * 
 * @author Klaus Birken (itemis AG)
 */
public interface IConfigurationReader {

	/**
	 * Loads a configuration from a file.
	 * 
	 * @param file the file which will receive the configuration contents
	 * @throws CoreException, IOException
	 */
	public boolean readFromFile(IFile file) throws CoreException, IOException;
	
	/**
	 * Creates a configuration from a textual representation.
	 * 
	 * @return the string containing the configuration representation
	 */
	public boolean readFromString(String text);

	/**
	 * Returns a list of warnings encountered during load of the configuration.
	 * 
	 * @return the list of warnings
	 */
	public List<String> getWarnings();
	
	/**
	 * Returns a list of the warnings' positions (line numbers).
	 * 
	 * @return the list of warning positions (line numbers)
	 */
	public List<Integer> getPositions();
}
