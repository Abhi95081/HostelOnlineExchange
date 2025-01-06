import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clx.AuthviewModel

@Composable
fun Account(modifier: Modifier, navController: NavController, authviewModel: AuthviewModel) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Centered content
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Account Page", fontSize = 45.sp, color = Color.Gray)

            // Logout Button
            Button(
                onClick = {
                    // Handle logout action here
                    authviewModel.signOut() // Assuming a logout method exists in the AuthviewModel
                    navController.navigate("login") // Navigate to the login screen after logout
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Logout")
            }
        }
    }
}
