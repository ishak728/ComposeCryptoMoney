package com.ishak.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

println("CryptoDetailScreen")

    LaunchedEffect(key1 = true) {
        println("launchedEffect:::::::>>>>>>>>")
        viewModel.loadImageData("78")
        viewModel.loadLatestPriceData("78")
    }

    Surface(color= Color.White,modifier = Modifier
        .fillMaxSize()
    )
    {
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

    /*
       //note rememberi içindeki mutable değeri değişirse otomatikmen rememberin atandığı değer yani "image" bulunduğu her yerde güncellenecek.
       //bu rememberin özelliğidir.rememberin asıl amacı değerleri akılında tutmaktır tabi değeri değişirse güncellenecektir.
       //burada ki asıl nokta viewmodelda bulunan Mutable sınıfından çağrılan mutable(Mutable sınıfını return eder) fonksiyonunun remember
       // bloğuunda saklanmasıdır.yani remember bloğunda referans bulunur.referans olduğu için o referansın işaret ettiği nokta değişirse
       //o referansı işaret eden her yer değişir.güncellenir.
       //remember ve launchefect çok önemlidri.launch effectile de yaptım çalışıyor.ikisini de çok güzel şekilde araştırmam gerek.

      LaunchedEffect(viewModel.image) {
           println("launnnnnn")
           image=viewModel.image
           println("imagelaunceffec (${image})")

       }*/


    Card(modifier = Modifier
        .wrapContentSize(Alignment.TopCenter)
        .padding(30.dp)
    )
    {

        Column (modifier= Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            println("asdasdf")
            Image(painter = rememberImagePainter(data=image.value), contentDescription ="crypto Money",
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .padding(10.dp)
                    .size(100.dp)
                    .border(width = 3.dp, color = Color.White, RoundedCornerShape(50))
                )
           CreateLatestPrice()
        }
    }
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


    Column(modifier= Modifier)

    {


        Text(text =if(latestPrice.value.isNullOrEmpty()) "..." else latestPrice.value.get(0).c  ,
            modifier= Modifier
                .background(
                    color = Color.White,
                    RoundedCornerShape(topStartPercent = 30, topEndPercent = 50, 10, 20)
                )
                .padding(30.dp, 10.dp, 30.dp, 10.dp)

        )
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        Text(text =if(latestPrice.value.isNullOrEmpty()) "..." else latestPrice.value.get(0).c  ,
            modifier= Modifier
                .background(
                    color = Color.White,
                    RoundedCornerShape(topStartPercent = 30, topEndPercent = 50, 10, 20)
                )
                .padding(30.dp, 10.dp, 30.dp, 10.dp)

        )
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        Text(text =if(latestPrice.value.isNullOrEmpty()) "..." else latestPrice.value.get(0).c  ,
            modifier= Modifier
                .background(
                    color = Color.White,
                    RoundedCornerShape(topStartPercent = 30, topEndPercent = 50, 10, 20)
                )
                .padding(30.dp, 10.dp, 30.dp, 10.dp)

        )
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        Text(text =if(latestPrice.value.isNullOrEmpty()) "..." else latestPrice.value.get(0).c  ,
            modifier= Modifier
                .background(
                    color = Color.White,
                    RoundedCornerShape(topStartPercent = 30, topEndPercent = 50, 10, 20)
                )
                .padding(30.dp, 10.dp, 30.dp, 10.dp)

        )

    }

}