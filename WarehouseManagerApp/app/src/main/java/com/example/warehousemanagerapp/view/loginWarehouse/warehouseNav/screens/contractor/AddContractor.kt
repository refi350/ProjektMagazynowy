package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.contractor


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.data.Address
import com.example.warehousemanagerapp.data.Commodity
import com.example.warehousemanagerapp.data.Contractor


@Composable
fun AddContractor(contractorViewModel: ContractorViewModel, navController: NavController) {
    var contractorName by rememberSaveable { mutableStateOf("") }
    var streetName by rememberSaveable { mutableStateOf("") }
    var houseNumber by rememberSaveable { mutableStateOf("") }
    var localNumber by rememberSaveable { mutableStateOf("") }
    var place by rememberSaveable { mutableStateOf("") }
    var code by rememberSaveable { mutableStateOf("") }
    var receipt by rememberSaveable { mutableStateOf(true) }
    var supplier by rememberSaveable { mutableStateOf(false) }
    var nip by rememberSaveable { mutableStateOf("") }
    var isEmptyNameError by rememberSaveable { mutableStateOf(false) }
    val errorEmptyNameMessage = "Nazwa nie może być pusta"
//    var isEmptyNameError by rememberSaveable { mutableStateOf(false) }
//    val errorEmptyNameMessage = "Nazwa nie może być pusta"
    
    // var passwordHidden by rememberSaveable { mutableStateOf(true) }
    Text(
        text = stringResource(id = R.string.contractor_data_label),
        fontSize = 24.sp
    )
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            //.fillMaxWidth()
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        TextField(
            value = contractorName,
            onValueChange = { contractorName = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.name_label)) },
            supportingText = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Red,
                    text =
                    if (isEmptyNameError) errorEmptyNameMessage else "",
                    textAlign = TextAlign.End
                )
            },
        )

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = streetName,
            onValueChange = { streetName = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.street_name_label)) }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = houseNumber,
            onValueChange = { houseNumber = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.house_number_label)) }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = localNumber,
            onValueChange = { value -> localNumber = value.filter { it.isDigit() } },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.local_number_label)) }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = place,
            onValueChange = { place = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.place_label)) }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = code,
            onValueChange = { code = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.code_label)) }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = nip,
            onValueChange = { value -> nip = value.filter { it.isDigit() } },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.nip_label)) }
        )

        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = receipt,
                onCheckedChange = { checked_ ->
                    receipt = checked_
                }
            )

            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "odbiorca"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = supplier,
                onCheckedChange = { checked_ ->
                    supplier = checked_
                }
            )

            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "dostawca"
            )
        }


        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                isEmptyNameError = contractorName.isBlank()
                if (!isEmptyNameError) {
                    val contractor = Contractor(
                        contractorName = contractorName,
                        recipient = receipt,
                        supplier = supplier,
                        nip = nip
                    ).apply {
                        contractorAddress =
                            Address(
                                streetName = streetName, houseNumber = houseNumber,
                                localNumber = if (localNumber.isNotBlank())
                                    localNumber.toInt() else 0,
                                place = place,
                                code = code
                            )
                    }
                    contractorViewModel.postContractor(contractor)
                    navController.popBackStack()
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)// Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.add_contractor_label))
        }

        //Text(text = "hello, $name")
    }
    
}
