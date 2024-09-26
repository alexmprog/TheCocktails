package com.alexmprog.thecocktails.ui

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.text.AnnotatedString

internal fun tabMatcher(text: String): SemanticsMatcher = textMatcher(text)
    .and(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab))

internal fun textMatcher(text: String): SemanticsMatcher = SemanticsMatcher.expectValue(
    SemanticsProperties.Text, listOf(AnnotatedString(text))
)