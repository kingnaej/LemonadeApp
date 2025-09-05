package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonApp()
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun LemonApp() {
    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFF99))
                .padding(30.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (currentStep) {
                1 -> LemonStep(
                    text = stringResource(R.string.leomon_tree),
                    image = R.drawable.lemon_tree,
                    contentDesc = stringResource(R.string.image1_descr),
                    onClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )

                2 -> LemonStep(
                    text = stringResource(R.string.leomon),
                    image = R.drawable.lemon_squeeze,
                    contentDesc = stringResource(R.string.image2_descr),
                    onClick = {
                        squeezeCount--
                        if (squeezeCount == 0) currentStep = 3
                    }
                )

                3 -> LemonStep(
                    text = stringResource(R.string.glass_leomon),
                    image = R.drawable.lemon_drink,
                    contentDesc = stringResource(R.string.image3_descr),
                    onClick = { currentStep = 4 }
                )

                4 -> LemonStep(
                    text = stringResource(R.string.empty_glass),
                    image = R.drawable.lemon_restart,
                    contentDesc = stringResource(R.string.image4_descr),
                    onClick = { currentStep = 1 }
                )
            }
        }
    }
}

@Composable
fun LemonStep(
    text: String,
    image: Int,
    contentDesc: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(40.dp),
            modifier = Modifier.padding(10.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF90EE90))
                    .padding(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = contentDesc,
                    modifier = Modifier.size(210.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = text,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
    }
}