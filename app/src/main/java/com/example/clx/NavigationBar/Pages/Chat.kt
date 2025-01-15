package com.example.clx.NavigationBar.Pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clx.AuthviewModel

data class Message(val text: String, val isSentByCurrentUser: Boolean)

@Composable
fun Chat(
    modifier: Modifier = Modifier,
    navController: NavController,
    authviewModel: AuthviewModel,
) {
    var messageText by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            Message("Hello! How are you?", isSentByCurrentUser = false),
            Message("I'm good, thanks! How about you?", isSentByCurrentUser = true),
            Message("I'm doing great. What's new?", isSentByCurrentUser = false),
            Message("Just working on a project. You?", isSentByCurrentUser = true),
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Chat messages
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            messages.forEach { message ->
                MessageBubble(
                    text = message.text,
                    isSentByCurrentUser = message.isSentByCurrentUser
                )
            }
        }

        // Message input area
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = messageText,
                onValueChange = { messageText = it },
                placeholder = { Text("Type a message...") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (messageText.isNotBlank()) {
                    messages.add(Message(messageText, isSentByCurrentUser = true))
                    messageText = ""
                }
            }) {
                Text("Send")
            }
        }
    }
}

@Composable
fun MessageBubble(text: String, isSentByCurrentUser: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = if (isSentByCurrentUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (isSentByCurrentUser) Color(0xFFDCF8C6) else Color(0xFFEAEAEA),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(8.dp)
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
        }
    }
}
