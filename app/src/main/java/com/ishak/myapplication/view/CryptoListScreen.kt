package com.ishak.myapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ishak.myapplication.model.cryptomoneylist.Response
import com.ishak.myapplication.viewmodel.CryptoListViewModel
import com.ishak.myapplication.ui.theme.*


@Composable
fun CryptoListScreen(navController:NavController,viewModel: CryptoListViewModel= hiltViewModel()) {

    println("CryptoListScreeen")

    viewModel.loadData()

var result by remember {
    mutableStateOf("")
}

    Surface(color = SurfaceColor, modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Crypto Money",modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.height(10.dp))


            BasicTextField(value =result , onValueChange ={
                result=it
            } ,
                textStyle = TextStyle(color = Color.Red),
                modifier= Modifier
                    .fillMaxWidth()
                    .background(SearchBarColor, CircleShape)
            )
            Spacer(modifier = Modifier.height(20.dp))
            list(navController = navController)
        }

        
    }

}

@Composable
fun list(navController: NavController,viewModel:CryptoListViewModel= hiltViewModel()) {

    var cryptoList by remember {
        viewModel.cryptoList
    }
    var isError by remember {
        viewModel.isError
    }
    var isLoading by remember {
        viewModel.isLoading
    }


    LazyColumn {
        items(cryptoList){
            Column {
                CreateRow(it,navController)
            }
        }
    }


    Box(){
        if (isLoading){
            CircularProgressIndicator(color = Color.Blue)
        }
        if (isError){
            Text(text = "erooooooooor",Modifier.size(100.dp))

        }
    }
}

@Composable
fun CreateRow(crypto:Response,navController: NavController) {
    Text(text = crypto.symbol,
        maxLines = 1,
        fontSize =30.sp,
        textAlign = TextAlign.Center,
        modifier=Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                println("createRow")
                navController.navigate(
                    "crypto_detail_screen/${crypto.id.toInt()}"
                )

            }
            .background(color= TextViewColor),
        fontStyle = FontStyle.Italic,
        style = TextStyle(color= TextColor)
        )
}