/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.ui.commandline;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.polarsys.capella.core.commandline.core.ui.AbstractWorkbenchCommandLine;
import org.polarsys.capella.core.commandline.core.ui.CloseWorkbenchJob;
import org.polarsys.capella.core.sirius.ui.handlers.RefreshDiagramAndSaveJob;

public class RefreshAirdCommandLine extends AbstractWorkbenchCommandLine {

  public RefreshAirdCommandLine() {
    super(true);
  }
  
  protected IStatus executeWithinWorkbench() {
    IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(argHelper.getFilePath()));
    Job job = new RefreshDiagramAndSaveJob(file);
    job.addJobChangeListener(new JobChangeAdapter() {

      @Override
      public void done(IJobChangeEvent event) {
        logStatus(job.getResult());
        new CloseWorkbenchJob().schedule();
      }

    });
    job.schedule();
    return Status.OK_STATUS;
  }

}
