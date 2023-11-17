package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun LoginContent(
    onClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit
) {
    ConstraintLayout {
        val (button, button2, button3) = createRefs()
        Button(
            onClick = onClick,
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text(text = "LOGIN")
        }
        Button(onClick = onSignUpClick, modifier = Modifier.constrainAs(button2) {
            top.linkTo(button.bottom)
        }) {
            Text(text = "onSignUp")
        }
        Button(onClick = onForgotClick, modifier = Modifier.constrainAs(button3) {
            top.linkTo(button2.bottom)
        }) {
            Text(text = "onSignUp")
        }
    }
}