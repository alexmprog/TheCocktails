package com.alexmprog.thecocktails.core.testing.rules

import android.Manifest.permission.POST_NOTIFICATIONS
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import androidx.test.rule.GrantPermissionRule.grant
import org.junit.rules.TestRule

class GrantPostNotificationsPermissionRule :
    TestRule by if (SDK_INT >= TIRAMISU) grant(POST_NOTIFICATIONS) else grant()