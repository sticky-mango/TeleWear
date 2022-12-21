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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.telewear.R
import com.example.telewear.presentation.theme.TelewearTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client = Client.create(Client.ResultHandler(function = {}),
            Client.ExceptionHandler(function = {}), Client.ExceptionHandler(function = {})
        )
        setContent { WearApp() }
    }
}

@Composable
fun WearApp() {
    TelewearTheme {

        // Set three buttons -> contacts/+/settings
        // Set Contacts list
        SetContactsList()
        SetMainButtons()

        //  for each contact in contact list, build a message card with avatar, name and preview of the last message
    }
}

@Composable
fun SetMainButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = MainCardRowTopPadding.dp),
        horizontalArrangement = Arrangement.Center
    )
    {
        MainButton(
            iconProperties = IconProperties(
                R.drawable.ic_new_chat_button,
                contentDesc = "Person Icon",
                size = MainButtonIconSize.dp,
            ), callback = {})

        Spacer(modifier = Modifier.width(MainButtonSpacerWidth.dp))

        MainButton(
            iconProperties = IconProperties(
                R.drawable.ic_add_button,
                contentDesc = "Plus Icon",
                size = MainButtonIconSize.dp,
            ), callback = {})

        Spacer(modifier = Modifier.width(MainButtonSpacerWidth.dp))

        MainButton(
            iconProperties = IconProperties(
                R.drawable.ic_settings_button,
                contentDesc = "Gear Icon",
                size = MainButtonIconSize.dp,
            ), callback = {})
    }
}


@Composable
fun SetContactsList() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO: Remove item; for beginning only.
        item { ChatCard(message = ChatData("Sticky Mango", "test", "Wed")) }
        item { ChatCard(message = ChatData("Sticky Mango", "test", "Thu")) }
        item { ChatCard(message = ChatData("Sticky Mango", "test", "Mon")) }
        item { ChatCard(message = ChatData("Sticky Mango", "test", "12:40")) }
        item { ChatCard(message = ChatData("Sticky Mango", "test", "Fri")) }
    }

}


@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun AppPreview() {
    ChatCard(ChatData("Alessandro", "Lorem Ipsum", "12:40"))
}
