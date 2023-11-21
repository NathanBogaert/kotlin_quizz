import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun WelcomeScreen(navigator: Navigator, quiz: List<Quiz>) {
    var selectedQuiz by remember { mutableStateOf("") }
    MaterialTheme {
        Box(modifier = Modifier.background(Color.Gray).fillMaxSize()) {
            Card(modifier = Modifier.padding(horizontal = 10.dp).align(Alignment.Center), shape = RoundedCornerShape(7.dp), backgroundColor = Color.White) {
                Column(Modifier.fillMaxWidth().verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Quiz",
                        fontSize = 28.sp
                    )
                    Text(
                        text = "Select a quiz",
                        modifier = Modifier.padding(top = 13.dp, bottom = 5.dp).fillMaxWidth(.5f)
                    )
                    quiz.forEach { i ->
                        Row() {
                            DropdownMenuItem(onClick = {
                                selectedQuiz = i.name
                            }, modifier = Modifier.fillMaxWidth(.5f)) {
                                Text(i.name)
                                if (selectedQuiz == i.name) {
                                    Icon(Icons.Rounded.Check, contentDescription = null, modifier = Modifier.padding(start = 10.dp, bottom = 12.dp).align(Alignment.Bottom))
                                }
                            }
                        }
                    }
                    Button(onClick = { var list: List<Question> = listOf()
                        for (i in quiz) {
                            if (i.name == selectedQuiz) {
                                list = i.questions
                                println("LISTE DES QUESTIONS: $list")
                            }
                        }
                        navigator.navigate(route = "/quiz/$list")}) {
                        Text("Start Quiz")
                    }
                }
            }
        }
    }
}