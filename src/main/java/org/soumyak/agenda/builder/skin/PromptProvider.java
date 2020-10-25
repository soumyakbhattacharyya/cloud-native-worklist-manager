package org.soumyak.agenda.builder.skin;

import org.jline.utils.AttributedString;

public interface PromptProvider {
  AttributedString getPrompt();
}