/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   which accompanies this distribution, and is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.la.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__involvedActors;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__involvedSystemComponents;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__participatingActors;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__participatingSystemComponents;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__realizedCapabilities;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__realizedCapabilityRealizations;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__realizingCapabilityRealizations;

/**
 * A pattern group formed of all public patterns defined in CapabilityRealization.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CapabilityRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.la.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CapabilityRealization__participatingActors</li>
 * <li>CapabilityRealization__participatingSystemComponents</li>
 * <li>CapabilityRealization__involvedActors</li>
 * <li>CapabilityRealization__involvedSystemComponents</li>
 * <li>CapabilityRealization__realizedCapabilities</li>
 * <li>CapabilityRealization__realizedCapabilityRealizations</li>
 * <li>CapabilityRealization__realizingCapabilityRealizations</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class CapabilityRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CapabilityRealization instance() {
    if (INSTANCE == null) {
        INSTANCE = new CapabilityRealization();
    }
    return INSTANCE;
  }
  
  private static CapabilityRealization INSTANCE;
  
  private CapabilityRealization() {
    querySpecifications.add(CapabilityRealization__participatingActors.instance());
    querySpecifications.add(CapabilityRealization__participatingSystemComponents.instance());
    querySpecifications.add(CapabilityRealization__involvedActors.instance());
    querySpecifications.add(CapabilityRealization__involvedSystemComponents.instance());
    querySpecifications.add(CapabilityRealization__realizedCapabilities.instance());
    querySpecifications.add(CapabilityRealization__realizedCapabilityRealizations.instance());
    querySpecifications.add(CapabilityRealization__realizingCapabilityRealizations.instance());
  }
  
  public CapabilityRealization__participatingActors getCapabilityRealization__participatingActors() {
    return CapabilityRealization__participatingActors.instance();
  }
  
  public CapabilityRealization__participatingActors.Matcher getCapabilityRealization__participatingActors(final ViatraQueryEngine engine) {
    return CapabilityRealization__participatingActors.Matcher.on(engine);
  }
  
  public CapabilityRealization__participatingSystemComponents getCapabilityRealization__participatingSystemComponents() {
    return CapabilityRealization__participatingSystemComponents.instance();
  }
  
  public CapabilityRealization__participatingSystemComponents.Matcher getCapabilityRealization__participatingSystemComponents(final ViatraQueryEngine engine) {
    return CapabilityRealization__participatingSystemComponents.Matcher.on(engine);
  }
  
  public CapabilityRealization__involvedActors getCapabilityRealization__involvedActors() {
    return CapabilityRealization__involvedActors.instance();
  }
  
  public CapabilityRealization__involvedActors.Matcher getCapabilityRealization__involvedActors(final ViatraQueryEngine engine) {
    return CapabilityRealization__involvedActors.Matcher.on(engine);
  }
  
  public CapabilityRealization__involvedSystemComponents getCapabilityRealization__involvedSystemComponents() {
    return CapabilityRealization__involvedSystemComponents.instance();
  }
  
  public CapabilityRealization__involvedSystemComponents.Matcher getCapabilityRealization__involvedSystemComponents(final ViatraQueryEngine engine) {
    return CapabilityRealization__involvedSystemComponents.Matcher.on(engine);
  }
  
  public CapabilityRealization__realizedCapabilities getCapabilityRealization__realizedCapabilities() {
    return CapabilityRealization__realizedCapabilities.instance();
  }
  
  public CapabilityRealization__realizedCapabilities.Matcher getCapabilityRealization__realizedCapabilities(final ViatraQueryEngine engine) {
    return CapabilityRealization__realizedCapabilities.Matcher.on(engine);
  }
  
  public CapabilityRealization__realizedCapabilityRealizations getCapabilityRealization__realizedCapabilityRealizations() {
    return CapabilityRealization__realizedCapabilityRealizations.instance();
  }
  
  public CapabilityRealization__realizedCapabilityRealizations.Matcher getCapabilityRealization__realizedCapabilityRealizations(final ViatraQueryEngine engine) {
    return CapabilityRealization__realizedCapabilityRealizations.Matcher.on(engine);
  }
  
  public CapabilityRealization__realizingCapabilityRealizations getCapabilityRealization__realizingCapabilityRealizations() {
    return CapabilityRealization__realizingCapabilityRealizations.instance();
  }
  
  public CapabilityRealization__realizingCapabilityRealizations.Matcher getCapabilityRealization__realizingCapabilityRealizations(final ViatraQueryEngine engine) {
    return CapabilityRealization__realizingCapabilityRealizations.Matcher.on(engine);
  }
}