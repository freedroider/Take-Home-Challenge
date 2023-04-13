package com.example.presentation.composables.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AudioFile
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.entity.RadioElement
import com.example.domain.entity.Type
import com.example.presentation.screens.DashboardEvent

@Composable
fun RadioElementItem(
    item: RadioElement,
    onEvent: (DashboardEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onEvent(DashboardEvent.OnRadioElementClick(item)) }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item.image?.let { image ->
            GlideImage(
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp),
                model = image,
                contentDescription = null,
            )
        }
        Text(
            modifier = Modifier
                .weight(1.0f)
                .padding(end = 8.dp),
            text = item.text,
            style = MaterialTheme.typography.caption,
            color = Color.DarkGray
        )
        Icon(
            imageVector = when (item.type) {
                is Type.Undefined -> Icons.Filled.QuestionMark
                is Type.Audio -> Icons.Filled.AudioFile
                is Type.Link -> Icons.Filled.Link
                else -> Icons.Filled.QuestionMark
            },
            contentDescription = null,
            tint = Color.DarkGray
        )
    }
}