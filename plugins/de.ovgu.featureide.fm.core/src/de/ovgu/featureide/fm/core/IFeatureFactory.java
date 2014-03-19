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
package de.ovgu.featureide.fm.core;

/**
 * The factory interface for providing new feature objects.
 * 
 * This is defining an Eclipse extension point, which is used to register a
 * factory which will be used to create new feature objects. The factory might
 * create subclassed objects, which allows to add attributes to the existing
 * Feature class.
 * 
 * @author Klaus Birken (itemis AG)
 */
public interface IFeatureFactory {

	/**
	 * Create a new feature for a feature model with some default name.
	 *  
	 * @param model the feature model
	 * @return the newly created feature
	 */
	public Feature createFeature(FeatureModel model);
	
	/**
	 * Create a new feature for a feature model with a given name.
	 * 
	 * @param model the feature model
	 * @param name the name for the new feature
	 * @return the newly created feature
	 */
	public Feature createFeature(FeatureModel model, String name);
	
	/**
	 * Create a new feature by cloning an existing feature.
	 * 
	 * @param original the existing feature
	 * @return the newly created feature
	 */
	public Feature clone(Feature original);
}
