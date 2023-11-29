package com.example.warehousemanagerapp.view.createWarehouse.colorPicker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.ui.theme.*
import com.example.warehousemanagerapp.view.loginWarehouse.LogScreenViewModel
import com.github.skydoves.colorpicker.compose.*
import java.lang.String

//import dev.shreyaspatil.easyupipayment.EasyUpiPayment
//import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener
//import dev.shreyaspatil.easyupipayment.model.PaymentApp
//import dev.shreyaspatil.easyupipayment.model.TransactionDetails
//import java.text.SimpleDateFormat
//import java.util.*

/* Designed and developed by 2022 skydoves (Jaewoong Eum)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. */

@Composable
fun ColorPicker(viewModel: LogScreenViewModel, navController: NavHostController) {
    Surface(
// on below line we are specifying modifier and color for our app
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {

        // on the below line we are specifying the theme as the scaffold.
        Scaffold(

            // in scaffold we are specifying the top bar.
            topBar = {

                // inside top bar we are specifying background color.
                TopAppBar(backgroundColor = greenColor,

                    // along with that we are specifying title for our top bar.
                    title = {

                        // in the top bar we are specifying tile as a text
                        Text(

                            // on below line we are specifying
                            // text to display in top app bar.
                            text = "GFG",

                            // on below line we are specifying
                            // modifier to fill max width.
                            modifier = Modifier.fillMaxWidth(),

                            // on below line we are specifying
                            // text alignment.
                            textAlign = TextAlign.Center,

                            // on below line we are specifying
                            // color for our text.
                            color = Color.White
                        )
                    })
            }) {
            // on below line we are
            // calling color picker method
            colorPic(viewModel, navController)
        }
    }
}

@Composable
fun colorPic(viewModel: LogScreenViewModel, navController: NavHostController) {
    // on below line we are creating a variable for controller
    val controller = rememberColorPickerController()

    // on below line we are creating a column,
    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp)
    ) {
        // on below line we are adding a row.
        Row(
            // on below line we are adding a modifier
            modifier = Modifier.fillMaxWidth(),
            // on below line we are adding horizontal
            // and vertical alignment.
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // on below line we are adding a alpha tile.
            AlphaTile(
                // on below line we are
                // adding modifier to it
                modifier = Modifier
                    .fillMaxWidth()
                    // on below line
                    // we are adding a height.
                    .height(60.dp)
                    // on below line we are adding clip.
                    .clip(RoundedCornerShape(6.dp)),
                // on below line we are adding controller.
                controller = controller
            )
        }
        // on below line we are
        // adding horizontal color picker.
        HsvColorPicker(
            // on below line we are
            // adding a modifier to it
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(10.dp),
            // on below line we are
            // adding a controller
            controller = controller,
            // on below line we are
            // adding on color changed.
            onColorChanged = {  }
        )
        // on below line we are adding a alpha slider.
        AlphaSlider(
            // on below line we
            // are adding a modifier to it.
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            // on below line we are
            // adding a controller.
            controller = controller,
            // on below line we are
            // adding odd and even color.
            tileOddColor = Color.White,
            tileEvenColor = Color.Black
        )
        // on below line we are
        // adding a brightness slider.
        BrightnessSlider(
            // on below line we
            // are adding a modifier to it.
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            // on below line we are
            // adding a controller.
            controller = controller,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val intColor = controller.selectedColor.value.toArgb()
            val hexColor = String.format("#%06X", 0xFFFFFF and intColor)
            viewModel.color = hexColor
            navController.popBackStack()
        },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}

object ColorPickerGraph {
    const val ADD_COLOR_PICKER = "add_color_picker_graph"
}