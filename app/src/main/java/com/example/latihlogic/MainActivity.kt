package com.example.latihlogic

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.latihlogic.ui.theme.LatihLogicTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val getImg = intent.getIntExtra("IMAGE",0)
        val getName = intent.getStringExtra("NAMA")
        val getPrice = intent.getIntExtra("HARGA",0)
        super.onCreate(savedInstanceState)
        setContent {
            LatihLogicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TesApp(
                        getImg = getImg,
                        getNama = getName,
                        getHarga = getPrice
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PrevApp() {
        LatihLogicTheme {
            TesApp(getImg = R.drawable.corsx, getNama = "PlaceHolder" , getHarga = 90000 )
        }
    }
}

@Composable
fun TesApp(modifier:Modifier = Modifier,getImg:Int,getNama:String?,getHarga:Int) {
    var count by remember { mutableStateOf(0) }
    val price by remember { mutableStateOf(getHarga) }
    val total by remember { derivedStateOf { count * price }}

    Column(modifier.fillMaxSize()) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .height(intrinsicSize = IntrinsicSize.Max),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = getImg),
                contentDescription = "skincare",modifier.size(200.dp))
            if (getNama != null) {
                Text(text = getNama)
            }
            Text(text = "IDR $getHarga")
        }
            Row(
                modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add",
                    modifier.clickable {
                    count++
                })
                Text(text = count.toString(),modifier.padding(horizontal = 30.dp))
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Add",
                    modifier.clickable { if (count > 0){count--} })
            }

           Row(modifier.fillMaxSize(), verticalAlignment = Alignment.Bottom) {
               Box(
                   modifier
                       .width(250.dp)
                       .background(Color.Gray)
                       .padding(bottom = 70.dp, start = 20.dp, top = 20.dp)){
                   Column {
                       Text(text = "Totalnya", color = Color.White)
                       Spacer(modifier = Modifier.height(15.dp))
                       Text(text = total.toString(), color = Color.White, fontSize = 25.sp)
                   }
               }
               Column(
                   modifier
                       .fillMaxWidth()
                       .background(Color.Gray)
                       .height(160.dp),
                   horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.Center
                   ){
                    Icon(imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Tambah",
                        tint = Color.White,
                        modifier = Modifier.size(60.dp))
               }
           }

    }
}
