/*
 * package com.everestengg.challenge.courier.shell;
 * 
 * import org.springframework.shell.component.StringInput; import
 * org.springframework.shell.component.StringInput.StringInputContext; import
 * org.springframework.shell.standard.AbstractShellComponent; import
 * org.springframework.shell.standard.ShellComponent; import
 * org.springframework.shell.standard.ShellMethod;
 * 
 * import com.everestengg.challenge.courier.model.PackageDetailsSummary;
 * 
 * @ShellComponent public class ComponentCommands extends AbstractShellComponent
 * {
 * 
 * @ShellMethod(key = "cost", value = "Delivery cost estimator", group =
 * "Courier service") public PackageDetailsSummary packageDetailsSummaryInput()
 * { StringInput component = new StringInput(getTerminal(),
 * "Enter base delivery cost", "baseDeliveryCost");
 * component.setResourceLoader(getResourceLoader());
 * component.setTemplateExecutor(getTemplateExecutor()); StringInputContext
 * context = component.run(StringInputContext.empty()); Integer baseDeliveryCost
 * = Integer.parseInt(context.getDefaultValue());
 * System.out.println("baseDeliveryCost " + baseDeliveryCost); component = new
 * StringInput(getTerminal(), "Enter number of packages", "noOfPackages");
 * component.setResourceLoader(getResourceLoader());
 * component.setTemplateExecutor(getTemplateExecutor()); context =
 * component.run(StringInputContext.empty()); Integer noOfPackages =
 * Integer.parseInt(context.getDefaultValue());
 * System.out.println("noOfPackages " + noOfPackages); return new
 * PackageDetailsSummary(baseDeliveryCost, noOfPackages); }
 * 
 * 
 * @ShellMethod(key = "cost", value = "Delivery cost estimator", group =
 * "Courier service") public PackageDetails
 * packageDetailsInput(PackageDetailsSummary packageDetailsSummary) {
 * StringInput component = new StringInput(getTerminal(),
 * "Enter base delivery cost", "baseDeliveryCost");
 * component.setResourceLoader(getResourceLoader());
 * component.setTemplateExecutor(getTemplateExecutor()); StringInputContext
 * context = component.run(StringInputContext.empty()); Integer baseDeliveryCost
 * = Integer.parseInt(context.getDefaultValue()); component = new
 * StringInput(getTerminal(), "Enter number of packages", "noOfPackages");
 * component.setResourceLoader(getResourceLoader());
 * component.setTemplateExecutor(getTemplateExecutor()); context =
 * component.run(StringInputContext.empty()); Integer noOfPackages =
 * Integer.parseInt(context.getDefaultValue()); return new PackageDetails(); }
 * 
 * 
 * }
 */