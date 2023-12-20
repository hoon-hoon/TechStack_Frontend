package kr.ac.kumoh.ce.s20171225.techstack

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import coil.compose.AsyncImage

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


enum class TechScreen {
    List,
    Detail
}

@Composable
fun TechApp(techList: List<Tech>) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TechScreen.List.name,
    ) {
        composable(route = TechScreen.List.name) {
            TechList(navController, techList)
        }
        composable(
            route = TechScreen.Detail.name + "/{index}",
            arguments = listOf(navArgument("index") {
                type = NavType.IntType
            })
        ) {
            val index = it.arguments?.getInt("index") ?: -1
            if (index >= 0)
                TechDetail(techList[index])
        }
    }
}

@Composable
fun TechList(navController: NavController, techList: List<Tech>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        itemsIndexed(techList) { index, tech ->
            TechItem(navController, techList, index)
        }
    }
}

@Composable
fun TechItem(navController: NavController,
             techList: List<Tech>,
             index: Int) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .background(Color(232, 244, 255))
                .padding(8.dp)
        ) {
            AsyncImage(
                model = "${techList[index].image_url}",
                contentDescription = "기술 스택 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(percent = 10)),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(242, 249, 255)),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextContent(title = techList[index].name, category = techList[index].category)
                    Spacer(modifier = Modifier.weight(1f))
                    DetailBtn { navController.navigate(TechScreen.Detail.name + "/$index") }
                }

            }
        }
        AnimatedVisibility(visible = expanded) {
            TextDescription(techList[index].description)
        }
    }
}

@Composable
fun TextContent(title: String, category: String) {
    Column {
        TextTitle(title = title)
        TextCategory(category = category)
    }
}

@Composable
fun TextTitle(title: String) {
    Text(
        text = title,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 1,  // 최대 1줄만 표시
        overflow = TextOverflow.Ellipsis,  // 넘칠 경우 ... 으로
        modifier = Modifier.width(150.dp)
    )
}

@Composable
fun TextCategory(category: String) {
    Text(category, fontSize = 15.sp)
}

@Composable
fun TextDescription(description: String) {
    Text(description, fontSize = 15.sp)
}

@Composable
fun DetailBtn(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            Color(222, 239, 255),
            contentColor = Color.DarkGray
        )
    ) {
        Text(text = "More", fontSize = 15.sp)
    }
}

