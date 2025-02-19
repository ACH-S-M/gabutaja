package com.example.latihlogic

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.latihlogic.ui.theme.LatihLogicTheme

class ListItem : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LatihLogicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Myapp()
                }
            }
        }
    }
}
@Composable
fun Myapp(modifier: Modifier = Modifier) {
    val list = getList()
    val context = LocalContext.current
    LazyColumn(modifier.fillMaxSize()){
        items(list){items ->
            LayoutCard(image = painterResource(id = items.imgRes),
                harga = items.harganya, nama = items.nama,
                onClick = {
                  val intent = Intent(context,MainActivity::class.java).apply {
                      putExtra("IMAGE",items.imgRes)
                      putExtra("NAMA",items.nama)
                      putExtra("HARGA",items.harganya)
                  }
                    context.startActivity(intent)
                })

            
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MyappPrev() {
    LatihLogicTheme {
        Myapp()
    }
}

@Composable
fun LayoutCard(
    modifier: Modifier = Modifier,
    image:Painter,
    harga:Int,
    nama:String?,
    onClick:() -> Unit
    ) {
    val context = LocalContext.current
    Row(
        modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clickable {
                onClick()
            }) {
      Image(painter = image, contentDescription = "Null",Modifier.size(150.dp).padding(32.dp))
        Column(
            modifier
                .fillMaxSize()
                .padding(32.dp), verticalArrangement = Arrangement.SpaceAround) {
            if (nama != null) {
                Text(text = nama, fontSize = 24.sp)
            }
            Text(text = " IDR $harga")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LayoutCardPrev() {
    LatihLogicTheme {

    }
}
@Composable
fun getList():List<product>{
    val context = LocalContext.current
    val namabarang = context.resources.getStringArray(R.array.produk)
    val fotoProduk = context.resources.obtainTypedArray(R.array.skincare)
    val harga = context.resources.getIntArray(R.array.Harga)
    var listProduk = mutableListOf<product>()
    for (i in namabarang.indices){
        listProduk.add(
            product(
                nama = namabarang[i],
                imgRes = fotoProduk.getResourceId(i,0),
                harganya = harga[i]
            )
        )
    }
    fotoProduk.recycle()
 return listProduk
}