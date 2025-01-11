import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.clx.AuthState
import com.example.clx.AuthviewModel
import com.example.clx.R

@Composable
fun signupPage(
    modifier:  Modifier,
    navController: NavController,
    authviewModel: AuthviewModel
) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val authState = authviewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("bottom_nav_home")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottieanimationsignup))
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState), // Enables scrolling
        contentAlignment = Alignment.TopCenter // Ensures content alignment
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Adds padding for better UX
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                modifier = Modifier.size(300.dp), // Reduced size for better scrolling experience
                composition = composition
            )

            Text(
                text = "Create Your Account",
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text(text = "Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { authviewModel.signup(email, pass) },
                enabled = authState.value != AuthState.Loading
            ) {
                Text(text = "Create Account")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { navController.navigate("user") }) {
                Text(text = "Already have Account")
            }
        }
    }
}
