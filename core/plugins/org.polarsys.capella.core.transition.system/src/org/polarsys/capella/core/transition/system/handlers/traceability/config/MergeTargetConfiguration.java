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

package org.polarsys.capella.core.transition.system.handlers.traceability.config;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ExtendedTraceabilityConfiguration;
import org.polarsys.capella.core.transition.system.handlers.traceability.RealizationLinkTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.ReconciliationTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.SIDTraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class MergeTargetConfiguration extends ExtendedTraceabilityConfiguration {

  protected class TargetReconciliationTraceabilityHandler extends ReconciliationTraceabilityHandler {

    public TargetReconciliationTraceabilityHandler(String identifier) {
      super(identifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeBlockArchitecture(BlockArchitecture source, BlockArchitecture target, IContext context, LevelMappingTraceability map) {
      super.initializeBlockArchitecture(source, target, context, map);

      Component sourceComponent = BlockArchitectureExt.getFirstComponent(source);
      Component targetComponent = BlockArchitectureExt.getFirstComponent(target);
      if ((sourceComponent != null) && (targetComponent != null)) {
        if ((!map.contains(sourceComponent)) && (!map.contains(targetComponent))) {
          addMapping(map, sourceComponent, targetComponent, context);
        }
      }

      if ((sourceComponent != null) && (sourceComponent.getRepresentingPartitions().size() == 1)) {
        if ((targetComponent != null) && (targetComponent.getRepresentingPartitions().size() == 1)) {
          Partition sourcePartition = sourceComponent.getRepresentingPartitions().get(0);
          Partition targetPartition = targetComponent.getRepresentingPartitions().get(0);
          addMapping(map, sourcePartition, targetPartition, context);
        }
      }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeRootMappings(IContext context) {
      super.initializeRootMappings(context);
      EObject source = (EObject) context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
      EObject target = (EObject) context.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
      addMappings(source, target, context);
    }
  }

  protected class TargetSIDTraceabilityHandler extends SIDTraceabilityHandler {

    /**
     * @param identifier
     */
    public TargetSIDTraceabilityHandler(String identifier) {
      super(identifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeRootMappings(IContext context) {
      super.initializeRootMappings(context);
      EObject source = (EObject) context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
      EObject target = (EObject) context.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
      initializeMappings(source, target, context);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExtensionIdentifier(IContext context) {
    return ISchemaConstants.TARGET_TRACEABILITY_CONFIGURATION;
  }

  @Override
  protected void initHandlers(IContext fContext) {
    addHandler(fContext, new TargetReconciliationTraceabilityHandler(getIdentifier(fContext)));
    addHandler(fContext, new TargetSIDTraceabilityHandler(getIdentifier(fContext)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForAttachment(EObject source, EObject target, ITraceabilityHandler handler, IContext context) {

    //We disable Reconciliation for attachment
    if (handler instanceof ReconciliationTraceabilityHandler) {
      return false;
    }

    return super.useHandlerForAttachment(source, target, handler, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForTracedElements(EObject source, ITraceabilityHandler handler, IContext context) {

    boolean result = super.useHandlerForTracedElements(source, handler, context);
    if (result) {
      //We disable RealizationLinks for SystemEngineering and BlockArchitecture
      if (handler instanceof SIDTraceabilityHandler) {

        if (source instanceof SystemEngineering) {
          result = false;
        }
        if (source instanceof BlockArchitecture) {
          result = false;
        }

      }

    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForSourceElements(EObject source, ITraceabilityHandler handler, IContext context) {
    boolean result = super.useHandlerForSourceElements(source, handler, context);
    if (result) {
      //We disable RealizationLinks for SystemEngineering and BlockArchitecture
      if (handler instanceof RealizationLinkTraceabilityHandler) {

        if (source instanceof SystemEngineering) {
          result = false;
        }
        if (source instanceof BlockArchitecture) {
          result = false;
        }
      }
    }

    return result;
  }

}