package com.example.jumptest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jumptest.components.ColorSlider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var red by rememberSaveable { mutableFloatStateOf(0f) }
    var green by rememberSaveable { mutableFloatStateOf(0f) }
    var blue by rememberSaveable { mutableFloatStateOf(0f) }
    var hexCode by rememberSaveable { mutableStateOf("") }
    val clipboardManager = LocalClipboardManager.current

    val redColor = Color(red = red / 255f, green = 0f / 255f, blue = 0f / 255f)
    val greenColor = Color(red = 0f, green = green / 255f, blue = 0f)
    val blueColor = Color(red = 0f, green = 0f, blue = blue / 255f)

    LaunchedEffect(red, green, blue) { hexCode = toHexCode(redColor, greenColor, blueColor) }

    Scaffold(
        modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Color Picker") },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Column(
                    modifier = Modifier.padding(12.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    ColorSlider(
                        sliderValue = red,
                        onValueChange = { value -> red = value },
                        color = redColor,
                    )
                    ColorSlider(
                        sliderValue = green,
                        onValueChange = { value -> green = value },
                        color = greenColor,
                    )
                    ColorSlider(
                        sliderValue = blue,
                        onValueChange = { value -> blue = value },
                        color = blueColor,
                    )
                }

                Text(text = hexCode, style = MaterialTheme.typography.headlineLarge)
                Spacer(
                    modifier =
                        Modifier.size(36.dp)
                            .fillMaxWidth()
                            .background(
                                color =
                                    Color(
                                        red = redColor.red.toFloat(),
                                        green = greenColor.green.toFloat(),
                                        blue = blueColor.blue.toFloat(),
                                    )
                            )
                )
                FilledTonalButton(
                    onClick = { clipboardManager.setText(AnnotatedString(hexCode)) }
                ) {
                    Text(text = "Copy Hex Code")
                }
            }
        }
    }
}

fun toHexCode(color1: Color, color2: Color, color3: Color): String {
    val red = color1.red * 255
    val green = color2.green * 255
    val blue = color3.blue * 255
    return String.format("#%02x%02x%02x", red.toInt(), green.toInt(), blue.toInt())
}

@Preview
@Composable
private fun PreviewMainScreen() {
    MainScreen()
}
