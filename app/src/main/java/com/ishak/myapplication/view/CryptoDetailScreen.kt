package com.ishak.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.ishak.myapplication.ui.theme.SurfaceColor
import com.ishak.myapplication.viewmodel.CryptoDetailViewModel


@Composable
fun CryptoDetailScreen(
    id:String,
    navController: NavController,
    viewModel: CryptoDetailViewModel= hiltViewModel()
) {

    println("count::::::::::::: id:"+id)



    LaunchedEffect(key1 = true) {
        println("launchedEffect:::::::>>>>>>>>")
        viewModel.loadImageData("78")
       // viewModel.loadLatestPriceData()
    }




    Surface(color= SurfaceColor,modifier = Modifier
        .fillMaxSize()
    ) {

        Text(text = "Detail Screen")
        CreateImage()


        //CreateLatestPrice()
    }

}


@Composable
fun CreateImage(viewModel: CryptoDetailViewModel= hiltViewModel()) {

   // var image by remember { mutableStateOf("") }
    var image=remember {
        viewModel.image
    }

    var isImageError=remember {
        viewModel.isImageError
    }
    var isImageLoading=remember {
        viewModel.isImageLoading

    }


    //note rememberi içindeki mutable değeri değişirse otomatikmen rememberin atandığı değer yani "image" bulunduğu her yerde güncellenecek.
    //bu rememberin özelliğidir.rememberin asıl amacı değerleri akılında tutmaktır tabi değeri değişirse güncellenecektir.
    //burada ki asıl nokta viewmodelda bulunan Mutable sınıfından çağrılan mutable(Mutable sınıfını return eder) fonksiyonunun remember
    // bloğuunda saklanmasıdır.yani remember bloğunda referans bulunur.referans olduğu için o referansın işaret ettiği nokta değişirse
    //o referansı işaret eden her yer değişir.güncellenir.
    //remember ve launchefect çok önemlidri.launch effectile de yaptım çalışıyor.ikisini de çok güzel şekilde araştırmam gerek.

   /* LaunchedEffect(viewModel.image) {
        println("launnnnnn")
        image=viewModel.image
        println("imagelaunceffec (${image})")

    }*/


    println("image:::::(${image.value})")

    Image(painter = rememberImagePainter(data =image.value), contentDescription ="Crypto money image" ,
        modifier = Modifier
            .padding(16.dp)
            .size(200.dp, 200.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape)
    )

}

@Composable
fun CreateLatestPrice(viewModel: CryptoDetailViewModel= hiltViewModel()) {

    var latestPrice = remember {
        viewModel.latestPrice
    }

    var islatestPriceError=remember {
        viewModel.isLatestPriceError
    }
    var islatestPriceLoading=remember {
        viewModel.isLatestPriceLoading
    }

    println("CreateLatestPrice"+latestPrice)



}