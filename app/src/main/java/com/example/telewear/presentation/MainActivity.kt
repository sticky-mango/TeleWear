/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.telewear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import org.drinkless.td.libcore.telegram.Client
import org.drinkless.td.libcore.telegram.TdApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.example.telewear.R
import com.example.telewear.presentation.theme.TelewearTheme


class ResultHandler : Client.ResultHandler{
    override fun onResult(`object`: TdApi.Object?) {
        TODO("Not yet implemented")
    }
}

class ExceptionHandler : Client.ExceptionHandler{
    override fun onException(e: Throwable?) {
        TODO("Not yet implemented")
    }

}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client =  Client.create(ResultHandler(), ExceptionHandler(), ExceptionHandler())
        setContent {
            TelewearTheme {

                // Set three buttons -> contacts/+/settings
                // Set Contacts list
                //  for each contact in contact list, build a message card with avatar, name and preview of the last message
                    ChatCard(ChatData("Alessandro", "Lorem Ipsum"))
                }
            }
        }
    }

data class ChatData(val contactName : String, val lastMessage : String){
    internal val date : Int = 2022
}

data class IconProperties

@Composable
fun MainButton(){
    Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .padding()
            .background(MaterialTheme.colors.background)){
        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings_button),
            contentDescription = "Gear Icon",
            modifier = Modifier.size(ButtonDefaults.SmallButtonSize) )
    }
}

@Composable
fun ChatCard(message : ChatData){
    Row(modifier = Modifier.padding(all = 16.dp),
        horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(R.drawable.mango),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = message.contactName
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = message.lastMessage
            )
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun AppPreview()
{
    ChatCard(ChatData("Alessandro", "Lorem Ipsum"))
}

/*
@Composable
fun WearApp(greetingName: String) {
    TelewearTheme {
        */
/* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         *//*

        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background),
                verticalArrangement = Arrangement.Center
        ) {
            Greeting(greetingName = greetingName)
        }
    }
}

@Composable
fun Greeting(greetingName: String) {
    Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
            text = stringResource(R.string.hello_world, greetingName)
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}*/
