package com.tumme.scrudstudents.ui.subscribe

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tumme.scrudstudents.data.local.model.SubscriptionWithDetails

@Composable
fun SubscriptionRow(subscription: SubscriptionWithDetails) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "${subscription.userFirstName} ${subscription.userLastName}", modifier = Modifier.weight(0.4f))
        Text(text = subscription.courseName, modifier = Modifier.weight(0.4f))
        Text(text = subscription.score.toString(), modifier = Modifier.weight(0.2f))
    }
    HorizontalDivider()
}
