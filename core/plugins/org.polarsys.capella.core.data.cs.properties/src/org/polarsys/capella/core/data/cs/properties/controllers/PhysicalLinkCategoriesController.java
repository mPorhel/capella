/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.cs.properties.controllers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;

public class PhysicalLinkCategoriesController extends AbstractMultipleSemanticFieldController {

  @Override
  protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
    return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), CsPackage.Literals.PHYSICAL_LINK__CATEGORIES);
  }
  
  @Override
  public List<EObject> writeOpenValues(EObject semanticElement, EStructuralFeature semanticFeature,
      List<EObject> values) {
    List<EObject> modelCurrentList = readOpenValues(semanticElement, semanticFeature, false);
    for (EObject category : modelCurrentList) {
      if (!values.contains(category)) {
        doRemoveOperationInWriteOpenValues(category, CsPackage.Literals.PHYSICAL_LINK_CATEGORY__LINKS, semanticElement);
      }
    }
    for (EObject category : values) {
      if (!modelCurrentList.contains(category)) {
        doAddOperationInWriteOpenValues(category, CsPackage.Literals.PHYSICAL_LINK_CATEGORY__LINKS, semanticElement);
      }
    }
    return values;
  }
  
}
