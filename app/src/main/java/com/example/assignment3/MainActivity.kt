package com.example.assignment3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment3.ui.theme.Assignment3Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment3Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MyApp()

                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MainScreen(onNavigate: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { onNavigate(1) }) {
            Text("Animation 1 Demo")
        }
        Button(onClick = { onNavigate(2) }) {
            Text("Animation 2 Demo")
        }
        Button(onClick = { onNavigate(3) }) {
            Text("Animation 3 Demo")
        }
        Button(onClick = { onNavigate(4) }) {
            Text("Animation 4 Demo")
        }
        Spacer(modifier = Modifier.height(60.dp))
        Text("Abhishek Rijal", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
        Text("301370583", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
    }
}

@ExperimentalAnimationApi
@Composable
fun Screen1(onBackPressed: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Black,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(3000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onBackPressed) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
        )
        }

}

@ExperimentalAnimationApi
@Composable
fun Screen2(onBackPressed: () -> Unit) {
    var visible by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize()) {
        IconButton(onClick = onBackPressed) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = visible) {
                Text("Animation taking values", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { visible = !visible }) {
                Text(if (visible) "Hide Text" else "Show Text")
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Screen3(onBackPressed: () -> Unit) {
    var count by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Greeting(name = "Hello !!!", modifier = Modifier.padding(16.dp))
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Row {
        var count by remember { mutableStateOf(0) }
        var score by remember { mutableStateOf(0) }
        Button(onClick = { count++; score += 100 }) {
            Text("Add")
        }

        AnimatedContent(targetState = count, label = "") { targetCount ->
            Text(text = "Count: $targetCount")
        }

        AnimatedContent(targetState = count, label = "") { targetCount ->
            Text(text = "Count: $targetCount")
        }

        AnimatedContent(targetState = score, label = "") { targetScore ->
            Text(text = "Game Score: $targetScore")
        }

        AnimatedContent(
            targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    SizeTransform(clip = false)
                )
            }, label = ""
        ) { targetCount ->
            Text(text = "$targetCount")
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Screen4(onBackPressed: () -> Unit) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    Column(modifier = Modifier.fillMaxSize()) {
        IconButton(onClick = onBackPressed) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offsetX.toInt(), offsetY.toInt()) }
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Green)
                .clickable { },
        ) {
            Text(
                text = "Gesture Based Animation",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun MyApp() {
    var currentScreen by remember { mutableStateOf(0) }

    val onNavigate: (Int) -> Unit = { screen ->
        currentScreen = screen
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Playing with animations") },
                navigationIcon = {
                    if (currentScreen != 0) {
                        IconButton(onClick = { currentScreen = 0 }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        }
    ) {

        when (currentScreen) {
            0 -> MainScreen(onNavigate = onNavigate)
            1 -> Screen1(onBackPressed = { currentScreen = 0 })
            2 -> Screen2(onBackPressed = { currentScreen = 0 })
            3 -> Screen3(onBackPressed = { currentScreen = 0 })
            4 -> Screen4(onBackPressed = { currentScreen = 0 })
        }


    }
}


@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Assignment3Theme {
        MyApp()

    }
}
