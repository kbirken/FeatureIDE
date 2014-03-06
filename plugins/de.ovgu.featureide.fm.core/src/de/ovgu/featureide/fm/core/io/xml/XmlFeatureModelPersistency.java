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
package de.ovgu.featureide.fm.core.io.xml;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.io.AbstractFeatureModelReader;
import de.ovgu.featureide.fm.core.io.AbstractFeatureModelWriter;
import de.ovgu.featureide.fm.core.io.IFeatureModelPersistency;

/**
 * The persistency layer for the original FeatureIDE file-format (XML).
 * 
 * @author Klaus Birken (itemis AG)
 */
public class XmlFeatureModelPersistency implements IFeatureModelPersistency {

	/* (non-Javadoc)
	 * @see de.ovgu.featureide.fm.core.io.IFeatureModelPersistency#createFeatureModelReader(de.ovgu.featureide.fm.core.FeatureModel)
	 */
	@Override
	public AbstractFeatureModelReader createFeatureModelReader(FeatureModel featureModel) {
		return new XmlFeatureModelReader(featureModel);
	}

	/* (non-Javadoc)
	 * @see de.ovgu.featureide.fm.core.io.IFeatureModelPersistency#createFeatureModelWriter(de.ovgu.featureide.fm.core.FeatureModel)
	 */
	@Override
	public AbstractFeatureModelWriter createFeatureModelWriter(FeatureModel featureModel) {
		return new XmlFeatureModelWriter(featureModel);
	}

}
