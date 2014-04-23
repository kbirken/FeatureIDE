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
 * An abstract interface for features.
 * 
 * @author Klaus Birken (itemis AG)
 */
public interface IFeature {
	/**
	 * The unique name of this feature.
	 * 
	 * This name must be unique across the whole model.
	 * 
	 * @return the unique name of this feature
	 */
	public String getName();
	
	/**
	 * The feature name which is displayed in the UI.
	 * 
	 * The visible name might be a short form of the feature's actual name.
	 * It doesn't need to be unique.
	 *  
	 * @return the visible name of this feature
	 */
	public String getVisibleName();

	/**
	 * Transform the unique name from the visible name.
	 * 
	 * The name of the feature will not be changed, this method will just
	 * compute the unique name from some visible name. Use this function
	 * before setting the feature's name with setName().
	 * 
	 * This method should be used for uniqueness checks, e.g., when
	 * renaming a feature.
	 *  
	 * @param visible the visible name to be converted
	 * @return the unique name based on the visible name
	 */
	public String computeNameFromVisibleName(String visible);
	
	// further attribute getters
	public boolean isConcrete();
	public boolean isAbstract();
	public boolean isRoot();
	public boolean hasHiddenParent();
	public boolean isConstraintSelected();
	public boolean isHidden();
	public FeatureStatus getFeatureStatus();
	public String getDescription();
	public String getRelevantConstraintsString();

}
