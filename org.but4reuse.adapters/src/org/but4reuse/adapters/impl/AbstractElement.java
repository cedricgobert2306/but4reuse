package org.but4reuse.adapters.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.but4reuse.adapters.IDependencyObject;
import org.but4reuse.adapters.IElement;
import org.but4reuse.adapters.preferences.PreferencesHelper;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

/**
 * Abstract Element
 * 
 * @author jabier.martinez
 */
public abstract class AbstractElement implements IElement, IDependencyObject {

	public static final String MAIN_DEPENDENCY_ID = "depends on";
	/**
	 * Abstract IElement
	 * 
	 * @author jabier.martinez
	 */
	private Map<String, List<IDependencyObject>> dependencies = new LinkedHashMap<String, List<IDependencyObject>>();
	private Map<String, List<IDependencyObject>> dependants = new LinkedHashMap<String, List<IDependencyObject>>();
	private Map<String, Integer> minDependencies = new HashMap<String, Integer>();
	private Map<String, Integer> maxDependencies = new HashMap<String, Integer>();

	@Override
	/**
	 * HashCode default implementation returns always 1 so it will be handled as a list.
	 * This method is intended to be overridden if a hash method could be provided to improve performance.
	 */
	public int hashCode() {
		return 1;
	}

	@Override
	public String toString() {
		return getText();
	}

	@Override
	public Map<String, List<IDependencyObject>> getDependencies() {
		return dependencies;
	}

	@Override
	public Map<String, List<IDependencyObject>> getDependants() {
		return dependants;
	}

	@Override
	public int getMaxDependencies(String dependencyID) {
		if (maxDependencies.get(dependencyID) == null) {
			return Integer.MAX_VALUE;
		}
		return maxDependencies.get(dependencyID);
	}

	@Override
	public int getMinDependencies(String dependencyID) {
		if (minDependencies.get(dependencyID) == null) {
			return Integer.MIN_VALUE;
		}
		return minDependencies.get(dependencyID);
	}

	public void setMaximumDependencies(String dependencyID, int number) {
		maxDependencies.put(dependencyID, number);
	}

	public void setMinimumDependencies(String dependencyID, int number) {
		minDependencies.put(dependencyID, number);
	}

	/**
	 * Add a dependency. We do not check if it was already added because it is
	 * expensive, try to avoid duplicates while adding dependencies
	 * 
	 * @param dependencyID
	 * @param dependency
	 */
	public void addDependency(String dependencyID, IDependencyObject dependency) {
		// Add dependencies
		List<IDependencyObject> o = dependencies.get(dependencyID);
		if (o == null) {
			o = new ArrayList<IDependencyObject>();
		}
		o.add(dependency);
		dependencies.put(dependencyID, o);
		// Add also dependants
		if (dependency instanceof AbstractElement) {
			AbstractElement ae = (AbstractElement) dependency;
			List<IDependencyObject> oo = ae.getDependants().get(dependencyID);
			if (oo == null) {
				oo = new ArrayList<IDependencyObject>();
			}
			oo.add(this);
			ae.getDependants().put(dependencyID, oo);
		}
	}

	public void addDependency(IDependencyObject dependency) {
		addDependency(MAIN_DEPENDENCY_ID, dependency);
	}

	public void addDependencies(List<IDependencyObject> dependencies) {
		for (IDependencyObject dependency : dependencies) {
			addDependency(MAIN_DEPENDENCY_ID, dependency);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IElement) {
			// get threshold
			double automaticThreshold = PreferencesHelper.getAutomaticEqualThreshold();
			double similarity = similarity((IElement) obj);
			if (similarity >= automaticThreshold) {
				// The similarity was greater than the equal threshold
				return true;
			}
			// check if we should ask the user
			if (!PreferencesHelper.isManualEqualActivated()
					|| PreferencesHelper.isDeactivateManualEqualOnlyForThisTime()) {
				// no? ok, so it is not equal
				return false;
			}

			// check if we should really ask
			double manualThreshold = PreferencesHelper.getManualEqualThreshold();
			if (similarity < manualThreshold) {
				return false;
			}

			// ok, let's ask the user
			boolean userDecision = manualEqual(similarity, (IElement)obj);
			return userDecision;
		}
		return super.equals(obj);
	}

	/**
	 * Manual equal. Override to provide tailored comparison user interfaces for
	 * your elements
	 * 
	 * @return whether the element is equal to another
	 */
	public boolean manualEqual(double calculatedSimilarity, IElement element) {
		Boolean cache = ManualEqualCache.check(this, element);
		if (cache != null) {
			return cache;
		}
		ElementTextManualComparison manualComparison = new ElementTextManualComparison(calculatedSimilarity,
				this.getText(), element.getText());
		Display.getDefault().syncExec(manualComparison);
		int buttonIndex = manualComparison.getResult();
		if (buttonIndex == 0) {
			ManualEqualCache.add(this, element, true);
			return true;
		} else if (buttonIndex == 1) {
			ManualEqualCache.add(this, element, false);
			return false;
		} else {
			PreferencesHelper.setDeactivateManualEqualOnlyForThisTime(true);
			return false;
		}
	}

	public interface IManualComparison extends Runnable {
		public int getResult();
	};

	public class ElementTextManualComparison implements IManualComparison {
		String elementText1;
		String elementText2;
		double calculatedSimilarity;
		int result;

		public ElementTextManualComparison(double calculatedSimilarity, String elementText1, String elementText2) {
			this.elementText1 = elementText1;
			this.elementText2 = elementText2;
			this.calculatedSimilarity = calculatedSimilarity;
		}

		@Override
		public void run() {
			// TODO implement "Always" and "Never" buttons
			// Default is No
			MessageDialog dialog = new MessageDialog(null, "Manual decision for equal. Automatic said "
					+ new DecimalFormat("#").format(calculatedSimilarity * 100) + "%", null, elementText1
					+ "\n\n is equal to \n\n" + elementText2, MessageDialog.QUESTION, new String[] { "Yes", "No",
					"Deactivate manual equal" }, 1);
			result = dialog.open();
		}

		@Override
		public int getResult() {
			return result;
		}

	}

	@Override
	public String getDependencyObjectText() {
		return getText();
	}

}
