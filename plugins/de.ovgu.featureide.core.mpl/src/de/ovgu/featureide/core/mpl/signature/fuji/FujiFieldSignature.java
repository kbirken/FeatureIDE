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
package de.ovgu.featureide.core.mpl.signature.fuji;

import java.util.Arrays;

import AST.TypeDecl;
import de.ovgu.featureide.core.mpl.signature.abstr.AbstractClassSignature;
import de.ovgu.featureide.core.mpl.signature.abstr.AbstractFieldSignature;

/**
 * Holds the java signature of a field.
 * 
 * @author Sebastian Krieter
 */
public class FujiFieldSignature extends AbstractFieldSignature {

	protected TypeDecl returnType;

	public FujiFieldSignature(AbstractClassSignature parent, String name,
			String modifiers, TypeDecl returnType) {
		super(parent, name, modifiers, returnType.name());
		this.returnType = returnType;
	}

	// public FujiFieldSignature(FujiFieldSignature orgSig) {
	// this(orgSig, false);
	// }
	//
	// private FujiFieldSignature(FujiFieldSignature orgSig, boolean ext) {
	// super(orgSig, ext);
	// }

	@Override
	public String toString() {
		StringBuilder fieldString = new StringBuilder();

		fieldString.append(super.toString());
		if (fieldString.length() > 0) {
			fieldString.append(LINE_SEPARATOR);
		}

		if (modifiers.length > 0) {
			for (String modifier : modifiers) {
				fieldString.append(modifier);
				fieldString.append(' ');
			}
		}
		fieldString.append(type);
		fieldString.append(' ');
		fieldString.append(name);

		return fieldString.toString();
	}

	// @Override
	// public FujiFieldSignature createExtendedSignature() {
	// return new FujiFieldSignature(this, true);
	// }

	@Override
	protected void computeHashCode() {
		hashCode = 1;
		hashCode = hashCodePrime * hashCode + fullName.hashCode();
		hashCode = hashCodePrime * hashCode + Arrays.hashCode(modifiers);
		hashCode = hashCodePrime * hashCode + type.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		FujiFieldSignature otherSig = (FujiFieldSignature) obj;

		if (!super.sigEquals(otherSig)
		// || !returnType.sameStructure(otherSig.returnType)) {
				|| returnType != otherSig.returnType) {
			return false;
		}
		return true;
	}
}
