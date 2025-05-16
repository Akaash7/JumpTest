package com.example.jumptest.components

import android.graphics.Color.red
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorSlider(
    modifier: Modifier = Modifier,
    sliderValue: Float,
    onValueChange: (Float) -> Unit,
    color: Color,
) {

    Column(
        modifier = Modifier.padding(12.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = sliderValue.toInt().toString())
        Slider(
            modifier = Modifier,
            value = sliderValue,
            onValueChange = onValueChange,
            valueRange = 0f..255f,
        )
        Spacer(
            modifier = Modifier.size(24.dp).fillMaxWidth().background(color = color)
        )
    }
}

// @Preview(name = "ColorSlider")
// @Composable
// private fun PreviewColorSlider() {
//    ColorSlider()
// }
