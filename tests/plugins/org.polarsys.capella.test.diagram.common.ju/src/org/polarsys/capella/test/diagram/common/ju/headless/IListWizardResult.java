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
package org.polarsys.capella.test.diagram.common.ju.headless;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

/**
 * Interface which have to be implemented by the classes with are used in order to short-cut GUI call to
 * {@link SelectElementsFromListWizard}
 */
@Deprecated
public interface IListWizardResult extends IHeadlessResult {

  /**
   * the simulated result.
   * 
   * @param selections
   *          @see {@link SelectElementsFromListWizard}
   * @param parameters
   *          @see {@link SelectElementsFromListWizard}
   * @return the "as" selected element list.
   * @see {@link SelectElementsFromListWizard}
   */
  public Object getResult(Collection<? extends EObject> selections, Map<String, Object> parameters);
}
