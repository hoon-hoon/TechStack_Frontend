package kr.ac.kumoh.ce.s20171225.techstack

import android.content.Intent
import android.net.Uri
import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberImagePainter

@Composable
fun TechDetail(tech: Tech) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("${tech.official_website}")
                    )
                    startActivity(context, intent, null)
                },
                colors = ButtonDefaults.buttonColors(
                    Color(222, 239, 255),
                    contentColor = Color.DarkGray,
                ),
                border = BorderStroke(.5.dp, Color.Gray),
                shape = RoundedCornerShape(8.dp)

            ) {
                Text(text = "go to learn ${tech.name}")
            }

        }
        Image(
            painter = rememberImagePainter(data = tech.image_url),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = tech.name,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = tech.category,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )

        BoldText(text = "Description")
        DescriptionText(text = tech.description)

        BoldText(text = "Purpose")
        DescriptionText(text = tech.usage_purpose)

        Spacer(modifier = Modifier.height(16.dp))

        RatingSection("Difficulty", tech.difficulty)
        Spacer(modifier = Modifier.height(8.dp)) // Adjust spacing between two sections
        RatingSection("Trend Index", tech.trend_index)
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
            val color = if (it < value) Color( 240, 240, 50) else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = color
            )
        }
    }
}

@Composable
fun BoldText(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun DescriptionText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun RatingSection(title: String, rating: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(200, 200, 200), shape = MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .padding(8.dp)
    ) {
        Column {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))
            StarRating(rating)
        }
    }
}


