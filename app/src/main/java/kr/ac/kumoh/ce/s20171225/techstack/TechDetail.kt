package kr.ac.kumoh.ce.s20171225.techstack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TechDetail(tech: Tech) {
    var keyboardController by remember { mutableStateOf<SoftwareKeyboardController?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = tech.image_url),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = tech.category,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = tech.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = tech.usage_purpose,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Difficulty")
        StarRating(tech.difficulty)

        Text("Trend Index")
        StarRating(tech.trend_index)
    }
}

@Composable
fun StarRating(value: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        repeat(10) {
            val color = if (it < value) Color.Cyan else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = color
            )
        }
    }
}
