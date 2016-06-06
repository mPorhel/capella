/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class ItemQuery_Flow_sourceOwner implements IQuery {

	/**
	 * 
	 */
	public ItemQuery_Flow_sourceOwner() {
    // do nothing
	}

	/**
	 * 
	 * source.owner
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof AbstractInformationFlow) {
			AbstractInformationFlow f = (AbstractInformationFlow) object;
			
		// connection 
      if (f instanceof ComponentExchange) {
        Part sourcePart = ComponentExchangeExt.getSourcePart((ComponentExchange) f);
        if (null != sourcePart) {
          result.add(sourcePart); 
        } else{
          InformationsExchanger source = f.getSource();
          if (null != source && (source instanceof ComponentPort || source instanceof PhysicalPort)) {
            EObject eContainer = source.eContainer();
            if (null != eContainer && eContainer instanceof SystemComponent) {
              result.add(eContainer);    
            }
          }
        }
        
        return result;
      }
      
			if (null != f.getSource()) result.add(f.getSource().eContainer());
		}
        return result;
	}
}
