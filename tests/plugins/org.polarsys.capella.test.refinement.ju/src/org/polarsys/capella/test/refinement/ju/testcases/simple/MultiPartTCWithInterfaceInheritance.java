/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.refinement.ju.testcases.simple;

import java.util.Arrays;
import java.util.Collections;

public class MultiPartTCWithInterfaceInheritance extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public MultiPartTCWithInterfaceInheritance() {
    super(Messages.MultiPartTCWithInterfaceInheritance_Name,
        Messages.MultiPartTCWithInterfaceInheritance_Desc,
        Collections.singletonList(Messages.MultiPartTC_LogicalArchitecture),
        Collections.singletonList(Messages.MultiPartTC_LogicalArchitecture),
        Messages.MultiPartTCWithInterfaceInheritance_Src,
        Messages.MultiPartTCWithInterfaceInheritance_Ref,
        Collections.singletonList(Messages.MultiPartTC_LogicalSystem_LC3a_Part),
        Collections.singletonList(Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC1a_Part, Messages.MultiPartTC_LogicalSystem_LC1b_Part, Messages.MultiPartTC_LogicalSystem_LC3a_Part, Messages.MultiPartTC_LogicalSystem_LC3b_Part, Messages.MultiPartTC_LogicalSystem_LC4a_Part, Messages.MultiPartTC_LogicalSystem_LC4b_Part)),
    Collections.EMPTY_LIST);
  }
}