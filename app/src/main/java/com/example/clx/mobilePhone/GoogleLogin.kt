package com.example.clx.mobilePhone

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clx.AuthviewModel
import com.example.clx.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable

fun googleLogin(modifier: Modifier, navController: NavController, authviewModel: AuthviewModel){

    var user by remember {
        mutableStateOf(Firebase.auth.currentUser)
    }

    val token = stringResource(id = R.string.clint_id)

    val context = LocalContext.current

    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(user == null){
            Spacer(modifier = Modifier.height(20.dp))

            ElevatedButton(onClick = {

            },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxSize()
                    .padding(5.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Blue
                )

                ) {
                
                Image(painter = painterResource(id = R.drawable.google_logo) ,
                    contentDescription = "Google Logo",
                    modifier = Modifier.size(40.dp),
                    contentScale = ContentScale.Fit)

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "Sign in via Google",
                fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    letterSpacing = 0.1.em
                    )
            }

        }else{
            Text("Hi${user!!.displayName}!",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
               color = Color.White
                )
            Spacer(modifier = Modifier.height(35.dp))

            Button(onClick = {
                Firebase.auth.signOut()
                user = null
            },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(5.dp)
                ) {
                Text(text = "Log Out",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    letterSpacing = 0.1.em
                    )
            }
        }

    }
}

