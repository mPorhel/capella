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

package org.polarsys.capella.core.model.links.helpers;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.helpers.refmap.VPair;
import org.polarsys.capella.core.model.links.helpers.commands.AbstractCreateLinksCommand;
import org.polarsys.capella.core.model.links.helpers.commands.AbstractQueryBasedCommand;
import org.polarsys.capella.core.model.links.helpers.commands.AddCompExchangeToCompExchangeCat;
import org.polarsys.capella.core.model.links.helpers.commands.AddComponentExchangeToPhysicalLinkCommand;
import org.polarsys.capella.core.model.links.helpers.commands.AddExchangeCategoryToFunctionalExchangeCommand;
import org.polarsys.capella.core.model.links.helpers.commands.AddExchangeCategoryToPhysicalLinkCommand;
import org.polarsys.capella.core.model.links.helpers.commands.AddExchangeItemToComponentExchangeCommand;
import org.polarsys.capella.core.model.links.helpers.commands.AddExchangeItemToFunctionExchange;
import org.polarsys.capella.core.model.links.helpers.commands.AddExchangeItemToFunctionPort;
import org.polarsys.capella.core.model.links.helpers.commands.AddFunctionalExchangeToComponentExchangeCommand;
import org.polarsys.capella.core.model.links.helpers.commands.AddModeStateToCapabilityCommand;
import org.polarsys.capella.core.model.links.helpers.commands.AddModeStateToFunctionCommand;
import org.polarsys.capella.core.model.links.helpers.commands.CreateAssociationCommand;
import org.polarsys.capella.core.model.links.helpers.commands.CreateComponentExchangeAllocation;
import org.polarsys.capella.core.model.links.helpers.commands.CreateExchangeItemAllocationCommand;
import org.polarsys.capella.core.model.links.helpers.commands.CreateFunctionalAllocationCommand;
import org.polarsys.capella.core.model.links.helpers.commands.CreateFunctionalExchangeAllocation;
import org.polarsys.capella.core.model.links.helpers.commands.CreateGeneralizationsCommand;
import org.polarsys.capella.core.model.links.helpers.commands.CreateRealizationLinksCommand;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;


/**
 */
public class LinksCommandRegistry {
	public static class CommandScope {
		public EReference _linkReferenceInSource;

		public EClass _linkSuperType;

		/**
		 * @param linkSuperType
		 * @param linkReferenceInSource
		 */
		public CommandScope(EClass linkSuperType,
				EReference linkReferenceInSource) {
			_linkSuperType = linkSuperType;
			_linkReferenceInSource = linkReferenceInSource;
		}

		/**
		 * {@inheritDoc}<br>
		 * <br>
		 * This method has been generated by Eclipse.
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			CommandScope other = (CommandScope) obj;
			if (_linkReferenceInSource == null) {
				if (other._linkReferenceInSource != null) {
					return false;
				}
			} else if (!_linkReferenceInSource
					.equals(other._linkReferenceInSource)) {
				return false;
			}
			if (_linkSuperType == null) {
				if (other._linkSuperType != null) {
					return false;
				}
			} else if (!_linkSuperType.equals(other._linkSuperType)) {
				return false;
			}
			return true;
		}

		/**
		 * {@inheritDoc}<br>
		 * <br>
		 * This method has been generated by Eclipse.
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((_linkReferenceInSource == null) ? 0
							: _linkReferenceInSource.hashCode());
			result = prime
					* result
					+ ((_linkSuperType == null) ? 0 : _linkSuperType.hashCode());
			return result;
		}

		/**
		 * Is this scope valid for given link type and link reference in source.
		 * 
		 * @param linkType
		 * @param linkReferenceInSource
		 * @return
		 */
		public boolean isValidFor(EClass linkType,
				EReference linkReferenceInSource) {
			if (null != _linkSuperType) {
				if ((null == linkType)
						|| !_linkSuperType.isSuperTypeOf(linkType)) {
					return false;
				}
			}
			if (null != _linkReferenceInSource) {
				if (_linkReferenceInSource != linkReferenceInSource) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * Singleton instance.
	 */
	private static LinksCommandRegistry __instance;
	/**
	 * CommandScope to command map.
	 */
	private final Map<CommandScope, Class<?>> _commandScopeToCommand;

	/**
	 * Singleton private constructor.
	 */
	private LinksCommandRegistry() {
		_commandScopeToCommand = new HashMap<CommandScope, Class<?>>();
	}

	/**
	 * 
	 * @param sourceType
	 * @param targetType
	 * @return
	 */
	public boolean containsCommandForSourceTargetTypes(EClass sourceType,
			EClass targetType) {
		VPair vPair = CapellaLinksMap.getInstance().getMappingFor(sourceType,
				targetType);
		if (null == vPair) {
			return false;
		}
		return containsCommandForVPairs(Collections.singletonList(vPair));
	}

	/**
	 * 
	 * @param sourceType
	 * @return
	 */
	public boolean containsCommandForSourceType(EClass sourceType) {
		List<VPair> vPairs = CapellaLinksMap.getInstance()
				.findMappingsForSourceType(sourceType);
		return containsCommandForVPairs(vPairs);
	}

	/**
	 * 
	 * @param targetType
	 * @return
	 */
	public boolean containsCommandForTargetType(EClass targetType) {
		List<VPair> vPairs = CapellaLinksMap.getInstance()
				.findMappingsForTargetType(targetType);
		return containsCommandForVPairs(vPairs);
	}

	/**
	 * 
	 * @param vPairs
	 * @return
	 */
	protected boolean containsCommandForVPairs(List<VPair> vPairs) {
		for (VPair vPair : vPairs) {
			EClass[] linkTypes = vPair.getFirstValue();
			EReference[] linkReferencesInSource = vPair.getSecondValue();
			for (int i = 0; (i < linkTypes.length)
					&& (i < linkReferencesInSource.length); ++i) {
				for (CommandScope commandScope : _commandScopeToCommand
						.keySet()) {
					if (commandScope.isValidFor(linkTypes[i],
							linkReferencesInSource[i])) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param commandToExecute
	 */
	public void executeCommand(EObject context, final AbstractCreateLinksCommand commandToExecute) {
		AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
			@Override
			public void run() {
				commandToExecute.execute();
			}
		};
		TransactionHelper.getExecutionManager(context).execute(command);
	}

	/**
	 * 
	 * @param linkType
	 * @param linkRef
	 * @return
	 */
	private List<Class<?>> findCommandsFor(EClass linkType,
			EReference linkRef) {
		List<Class<?>> commands = new ArrayList<Class<?>>();

		for (Map.Entry<CommandScope, Class<?>> entry : _commandScopeToCommand
				.entrySet()) {
			if (entry.getKey().isValidFor(linkType, linkRef)) {
				commands.add(entry.getValue());
			}
		}

		return commands;
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public List<AbstractCreateLinksCommand> getExecutableCommands(
			Collection<EObject> source, Collection<EObject> target) {
		// Precondition.
		if ((null == source) || (null == target)) {
			return Collections.emptyList();
		}
		List<AbstractCreateLinksCommand> executableCommands = new ArrayList<AbstractCreateLinksCommand>();
		VPair vPair = CapellaLinksMap.getInstance().getMappingFor(
				source.iterator().next().eClass(),
				target.iterator().next().eClass());
		if (null == vPair) {
			return Collections.emptyList();
		}
		for (int i = 0; (i < vPair.getFirstValue().length)
				&& (i < vPair.getSecondValue().length); ++i) {
			EClass linkType = vPair.getFirstValue()[i];
			EReference linkReference = vPair.getSecondValue()[i];
			List<Class<?>> commandClasses = findCommandsFor(linkType,
					linkReference);
			for (Class<?> commandClass : commandClasses) {
				try {
					AbstractCreateLinksCommand commandInstance = null;
					if (AbstractQueryBasedCommand.class
							.isAssignableFrom(commandClass)) {
						Constructor<?> constructor = commandClass
								.getConstructor(EClass.class, EReference.class);
						commandInstance = (AbstractCreateLinksCommand) constructor
								.newInstance(linkType, linkReference);
					} else {
						commandInstance = (AbstractCreateLinksCommand) commandClass
								.newInstance();
					}

					commandInstance.setSources((ArrayList) source);
					commandInstance.setTargets((ArrayList) target);
					if (commandInstance.canExecute()) {
						executableCommands.add(commandInstance);
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}

			}
		}
		return executableCommands;
	}

	/**
	 * Singleton.
	 * 
	 * @return
	 */
	public static LinksCommandRegistry getInstance() {
		if (null == __instance) {
			__instance = new LinksCommandRegistry();
			__instance._commandScopeToCommand.put(new CommandScope(
					CsPackage.Literals.COMPONENT_ALLOCATION, null),
					CreateRealizationLinksCommand.class);
			__instance._commandScopeToCommand.put(new CommandScope(
					FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION, null),
					CreateFunctionalAllocationCommand.class);
			__instance._commandScopeToCommand.put(new CommandScope(
					FaPackage.Literals.FUNCTION_REALIZATION, null),
					CreateRealizationLinksCommand.class);
			__instance._commandScopeToCommand.put(new CommandScope(
					CapellacorePackage.Literals.GENERALIZATION, null),
					CreateGeneralizationsCommand.class);
			__instance._commandScopeToCommand.put(new CommandScope(
					CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION, null),
					CreateExchangeItemAllocationCommand.class);
			__instance._commandScopeToCommand.put(new CommandScope(
					InformationPackage.Literals.ASSOCIATION, null),
					CreateAssociationCommand.class);
			__instance._commandScopeToCommand.put(new CommandScope(
					FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATION, null),
					CreateComponentExchangeAllocation.class);// TODO only for
																// MA3
			__instance._commandScopeToCommand
					.put(new CommandScope(
							FaPackage.Literals.COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION,
							null), CreateFunctionalExchangeAllocation.class);

			__instance._commandScopeToCommand.put(new CommandScope(null,
					FaPackage.Literals.COMPONENT_EXCHANGE_CATEGORY__EXCHANGES),
					AddCompExchangeToCompExchangeCat.class);
			__instance._commandScopeToCommand
					.put(new CommandScope(
							null,
							FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS),
							AddExchangeItemToFunctionPort.class);
			__instance._commandScopeToCommand
					.put(new CommandScope(
							null,
							FaPackage.Literals.FUNCTION_OUTPUT_PORT__OUTGOING_EXCHANGE_ITEMS),
							AddExchangeItemToFunctionPort.class);

			__instance._commandScopeToCommand.put(new CommandScope(null,
					FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS),
					AddExchangeItemToFunctionExchange.class);

			__instance._commandScopeToCommand
					.put(new CommandScope(
							FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATION,
							FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS), // TODO
																																// only
																																// for
																																// MA3
							AddFunctionalExchangeToComponentExchangeCommand.class);// TODO
																					// only
																					// for
																					// MA3

			// component Exchnage to Exchenge Item command
			__instance._commandScopeToCommand
					.put(new CommandScope(
							null,
							ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS),
							AddExchangeItemToComponentExchangeCommand.class);

			// component Exchnage to Physical Link command
			__instance._commandScopeToCommand
					.put(new CommandScope(
							null,
							CsPackage.Literals.PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS),
							AddComponentExchangeToPhysicalLinkCommand.class);// TODO
																				// only
																				// for
																				// MA3

			// Functional Exchange category to Functional Exchnage
			__instance._commandScopeToCommand.put(new CommandScope(null,
					FaPackage.Literals.EXCHANGE_CATEGORY__EXCHANGES),
					AddExchangeCategoryToFunctionalExchangeCommand.class);

			// Functional Exchange category to Functional Exchnage
			__instance._commandScopeToCommand.put(new CommandScope(null,
					CsPackage.Literals.PHYSICAL_LINK_CATEGORY__LINKS),
					AddExchangeCategoryToPhysicalLinkCommand.class); 

			// Mode to
			// Function(ig:LogicalFunction,SystemFunction,OperationalActivity,...)
			__instance._commandScopeToCommand.put(new CommandScope(null,
					FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES),
					AddModeStateToFunctionCommand.class);

			// add State Or Mode to an Abstract Capability like
			// OperationalCapability, CapabilityRealization ...
			__instance._commandScopeToCommand
					.put(new CommandScope(
							null,
							InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES),
							AddModeStateToCapabilityCommand.class);

		}
		return __instance;
	}

	public Map<CommandScope, Class<?>> getCommandregistry() {

		return _commandScopeToCommand;
	}
}
