package com.billiardb.composeexample

import android.app.Activity
import android.os.Bundle
import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.Center
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.WidthSpacer
import androidx.ui.material.Button
import androidx.ui.material.ContainedButtonStyle
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface

class MainActivity : Activity() {

    val myNames = listOf("GONG" , "SUL" , "MIN")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                Center {
                    Counter(CounterState())
                }
            }
        }
    }

    @Composable
    fun Counter(state: CounterState) {
        Button(text = " 클릭 횟수   ${state.count}  ",
            onClick = {
                        state.count++
            }
        )
    }

    @Composable
    fun DataFlow(items: List<User> , onClick: (User) -> Unit ) {
//        DataFlow(
//            listOf(
//                User("gong" , "gg") ,
//                User("sul" , "ss") ,
//                User("min" , "mm")
//            ) ,
//            {
//                Toast.makeText(this@MainActivity , "${it.name}" , Toast.LENGTH_LONG).show()
//
//            }
//        )

        Column {
            items.forEach {
                RenderItem(it) {onClick.invoke(it)}
            }
        }
    }

    fun RenderItem(user: User , onClick: () -> Unit) {
        Clickable(onClick = onClick) {
            Row {
                Text(text = user.name)
                WidthSpacer(4.dp)
                Text(text = user.nick)
            }
        }
    }

    @Composable
    fun Names(names: List<String>) {
        Column {
            for(name in names) {
                Text(text = name)
            }
        }
    }

    @Composable
    fun EnabledButton(text: String , enabled: Boolean) {
        Button(
            text = text ,
            style = ContainedButtonStyle(
                color = if (enabled) Color.White else Color.Gray
            )
        )
    }

    @Composable
    fun Greeting(name: String) {
        Center { Text(text = "Hello $name") }
    }

    @Composable
    fun YelloGreeting(name: String) {
        Surface(color = Color.Yellow) {
            Text(text = "hello $name" )
        }
    }

    @Composable
    fun MyApp() {
        MaterialTheme {
            Surface(color = Color.Yellow) {
                Greeting(name = "Gong Sul Min")
            }
        }
    }


    @Composable
    fun MyScreendContent() {
        Column {
            Greeting("Android")
            Divider(color = Color.Black)
            Greeting("There")
        }
    }


    @Composable
    fun MyApp(child: @Composable() () -> Unit) {
        MaterialTheme {
                child()
        }
    }

}

@Model
class CounterState(var count: Int = 0)

data class User(
    val name: String ,
    val nick: String
)
