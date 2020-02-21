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
package org.polarsys.capella.core.commandline.core.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.polarsys.capella.core.commandline.core.AbstractCommandLine;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.platform.sirius.ui.app.CapellaWorkbenchAdvisor;

public abstract class AbstractWorkbenchCommandLine extends AbstractCommandLine {

  private boolean openWindows;

  private IStatus status = Status.OK_STATUS;

  public AbstractWorkbenchCommandLine() {
    this(false);
  }

  public AbstractWorkbenchCommandLine(boolean openWindows) {
    this.openWindows = openWindows;
  }

  public void setStatus(IStatus status) {
    if (this.status.isOK()) {
      this.status = status == null ? Status.OK_STATUS : status;
    }
  }

  @Override
  public boolean execute(IApplicationContext context) throws CommandLineException {
    startWorkbench(openWindows);

    if (!status.isOK()) {
      throw new CommandLineException(status.getMessage());
    }
    return true;
  }

  protected abstract IStatus executeWithinWorkbench();

  protected void registerCommandLine() {
    IEclipseContext context = PlatformUI.getWorkbench().getService(IEclipseContext.class);
    context.set(AbstractWorkbenchCommandLine.class, this);
  }

  protected void startWorkbench(boolean openWindows) {
    if (PlatformUI.isWorkbenchRunning()) {
      return;
    }
    Display display = PlatformUI.createDisplay();
    try {
      if (openWindows) {
        PlatformUI.createAndRunWorkbench(display, new CapellaWorkbenchAdvisor() {

          @Override
          public void postStartup() {
            super.postStartup();
            registerCommandLine();
          }

        });

      } else {
        PlatformUI.createAndRunWorkbench(display, new WorkbenchAdvisor() {

          @Override
          public boolean openWindows() {
            return false;
          }

          @Override
          public String getInitialWindowPerspectiveId() {
            return null;
          }

          @Override
          public void preStartup() {
            super.preStartup();
          }
          
        });
        executeWithinWorkbench();
      }

    } finally {
      try {
        display.update();
        // Consume all pending work for the UI Thread
        while (display.readAndDispatch()) {
          // do nothing
        }
      } catch (Exception e) {
        // do nothing
      }
      if (display != null) {
        display.dispose();
      }
    }
  }

}
