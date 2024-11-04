package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.window.layout.WindowMetricsCalculator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
        val windowWidthDp = windowMetrics.bounds.width() / resources.displayMetrics.density
        val windowClass = calculateWindowSizeClass(windowWidthDp)

        setContent {
            ProfileScreen(windowClass)
        }
    }

    private fun calculateWindowSizeClass(windowWidthDp: Float): WindowWidthSizeClass {
        return when {
            windowWidthDp < 600 -> WindowWidthSizeClass.COMPACT
            windowWidthDp < 840 -> WindowWidthSizeClass.MEDIUM
            else -> WindowWidthSizeClass.EXPANDED
        }
    }
}

@Composable
fun Imgnam() {
    Image(
        painter = painterResource(id = R.drawable.img), // Remplacez par l'image de profil
        contentDescription = "Photo de profil",
        modifier = Modifier
            .size(200.dp)
            .padding(8.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Théo Mutel",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

}

@Composable
fun ProfileDescription() {
    Text(
        text = "Élève en 3ème année de MMI\nDiplômé d'un BTS SIO",
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )

}

@Composable
fun Cordonate() {
    Spacer(modifier = Modifier.height(25.dp))
    ContactInfo("✉️", "theo.mutel.empro@gmail.com")
    ContactInfo("🔗", "www.linkedin.com/in/theo.mutel")
    Spacer(modifier = Modifier.height(25.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(windowClass: WindowWidthSizeClass) {
    when (windowClass) {
        WindowWidthSizeClass.COMPACT -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileContent()
            }
        }
        WindowWidthSizeClass.EXPANDED -> {
            Column {


            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Imgnam()
                    ProfileDescription()
                }

                Spacer(modifier = Modifier.width(24.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Cordonate()
                    StartButton()

                }


            }

            }
        }
        else -> {
            ProfileContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
            Column {
                TopAppBar(
                    title = { Text("Titre de la page") },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    modifier = Modifier.background(Color.Yellow)
                )
            }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Imgnam()
            ProfileDescription()
            Cordonate()
            StartButton()
        }
    }
}

@Composable
fun StartButton() {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
    ) {
        Text(text = "Démarrer", color = Color.White)
    }
}

@Composable
fun ContactInfo(icon: String, text: String) {
    Row(
        modifier = Modifier.padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.width(8.dp))
        ClickableText(
            text = AnnotatedString(text),
            onClick = { }
        )
    }
}
