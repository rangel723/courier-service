/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.shell;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.component.StringInput;
import org.springframework.shell.component.StringInput.StringInputContext;
import org.springframework.shell.component.flow.ComponentFlow;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.everestengg.challenge.courier.model.PackageDetails;

/**
 * @author Rangel
 * 
 */
@ShellComponent
public class DeliveryCostEstimatorShell extends AbstractShellComponent {

	@Autowired
	private ComponentFlow.Builder componentFlowBuilder;

	private int baseDeliveryCost;
	private int expectedNoOfPackages;
	private final List<PackageDetails> items = new ArrayList<>();

	@ShellMethod(key = "summary", value = "Courier summary details", group = "Delivery cost estimator")
	public void summary() {
		baseDeliveryCost = Integer.valueOf(readFromTerminal("Enter base delivery cost"));
		expectedNoOfPackages = Integer.valueOf(readFromTerminal("Number of packages"));
	}
	
	private String readFromTerminal(String prompt) {
		StringInput component = new StringInput(getTerminal(), prompt, "",
				new StringInputCustomRenderer());
		component.setResourceLoader(getResourceLoader());
		component.setTemplateExecutor(getTemplateExecutor());
		StringInputContext context = component.run(StringInputContext.empty());
		return context.getResultValue();
	}

	@ShellMethod(key = "add", value = "Add an item to the list.", group = "Delivery cost estimator")
	public String addPackageItem() {
		if (items.size() < expectedNoOfPackages) {
			String pkgId = readFromTerminal("Package Id");
			int pkgWeightInKg = Integer.parseInt(readFromTerminal("Package weight in kg"));
			int distanceInKm = Integer.parseInt(readFromTerminal("Delivery distance in kms"));
			String offerCode = readFromTerminal("Offer code");
			items.add(PackageDetails.builder().pkgId(pkgId).pkgWeightInKg(pkgWeightInKg)
					.distanceInKm(distanceInKm).offerCode(offerCode).build());
			return "Item added. Please enter " + (expectedNoOfPackages - items.size()) + " more items.";
		}
		return String.format("All items already collected. - %s", items);
	}
}
