package com.example.telewear.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.example.telewear.R

@Composable
fun MainButton(iconProperties: IconProperties, callback: () -> Unit) {
    Button(
        onClick = callback,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(MainButtonColor)),
        modifier = Modifier
            .padding()
            .background(Color.Transparent)
            .height(MainButtonHeight.dp)
            .width(MainButtonWidth.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = iconProperties.iconType),
            contentDescription = iconProperties.contentDesc,
            modifier = Modifier.size(iconProperties.size)
        )
    }
}

data class IconProperties(val iconType: Int, val contentDesc: String, val size: Dp)

@Composable
fun ChatCard(message: ChatData) {
    Row(
        modifier = Modifier.padding(all = 2.dp)
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(32.dp))
            .background(color = Color(0xFF202124))
            .clickable {/*TODO open chat with user*/},
        horizontalArrangement = Arrangement.Center,

    ) {
        Image(
            painter = painterResource(R.drawable.mango),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                //.size(ChatCardProfileImageSize.dp)
                .size(38.dp)
                .clip(CircleShape)
                .align(CenterVertically)
        )

        //Spacer(modifier = Modifier.width(ChatCardSpacerWidth.dp))
        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = message.contactName,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(0.dp))

            Text(
                text = message.lastMessage,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        //Spacer(modifier = Modifier.width(ChatCardSpacerWidth.dp))
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = message.lastMessageTime,
                //fontSize = ChatCardLMTFontSize.sp,
                fontSize = 8.sp,
                modifier = Modifier.align(CenterHorizontally)
                    .padding(top = 14.dp, start = 6.dp)
            )
        }
    }

}

data class ChatData(val contactName: String, val lastMessage: String, val lastMessageTime: String)
