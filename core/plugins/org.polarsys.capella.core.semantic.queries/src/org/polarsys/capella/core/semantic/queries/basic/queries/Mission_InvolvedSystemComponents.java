/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class Mission_InvolvedSystemComponents implements IQuery {

	/**
	 * 
	 */
	public Mission_InvolvedSystemComponents() {
    // do nothing
	}

	/** 
	 *  
	 * current.participatingActors
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof Mission) {
			Mission m = (Mission) object;
			result.addAll(m.getInvolvedSystemComponents());
		}
		return result;
	}
}
