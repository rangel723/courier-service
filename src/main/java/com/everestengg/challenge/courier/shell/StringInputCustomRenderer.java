
package com.everestengg.challenge.courier.shell;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.springframework.shell.component.StringInput.StringInputContext;
import org.springframework.util.StringUtils;

class StringInputCustomRenderer implements Function<StringInputContext, List<AttributedString>> {

	@Override
	public List<AttributedString> apply(StringInputContext context) {
		AttributedStringBuilder builder = new AttributedStringBuilder();
		builder.append(context.getName());
		builder.append(" ");
		if (context.getResultValue() != null) {
			builder.append(context.getResultValue());
		} else {
			String input = context.getInput();
			if (StringUtils.hasText(input)) {
				builder.append(input);
			} else {
				builder.append("[Default " + context.getDefaultValue() + "]");
			}
		}
		return Arrays.asList(builder.toAttributedString());
	}
}
