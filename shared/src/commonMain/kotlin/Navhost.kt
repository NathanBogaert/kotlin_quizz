import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition


@Composable
internal fun navigation() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = "/welcome",
    ) {
        scene(
            route = "/welcome",
            navTransition = NavTransition()
        ) {
            WelcomeScreen(navigator, quizList)
        }
        scene(
            route = "/quiz/{quizname}",
            navTransition = NavTransition()
        ) { backStackEntry ->
            val quizname: String? = backStackEntry.path<String>("quizname")
            if (quizname != null) {
                quizList.forEach {
                    if (it.name == quizname) {
                        QuestionScreen(navigator, it)
                    }
                }

            }
        }
        scene(
            route = "/score/{score}/{quizSize}",
            navTransition = NavTransition()
        ) { backStackEntry ->
            val score: Int? = backStackEntry.path<Int>("score")
            val quizSize: Int? = backStackEntry.path<Int>("quizSize")
            if (score != null && quizSize != null) {
                ScoreScreen(navigator, score, quizSize)
            }
        }
        scene(
            route = "/create",
            navTransition = NavTransition()
        ) {
            CreateScreen(navigator)
        }
        scene(
            route = "/import",
            navTransition = NavTransition()
        ) {
            ImportScreen(navigator)
        }
        scene(
            route = "/import/{importerror}",
            navTransition = NavTransition()
        ) {
            val importError: String? = it.path<String>("importerror")
            println(importError)
            ImportScreen(navigator,importError)
        }
        scene(
            route = "/import_process/{url}",
            navTransition = NavTransition()
        ) {
            val url: String? = it.path<String>("url")
            if(url!=null && url!=""){
                ImportProcess(navigator,url)
            }

        }
    }
}