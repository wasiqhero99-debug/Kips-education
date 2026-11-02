package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.data.model.StudyMode
import com.example.ui.components.AppHeader
import com.example.ui.components.SyllabusSheetDialog
import com.example.ui.screens.AiTutorScreen
import com.example.ui.screens.ApplicationsScreen
import com.example.ui.screens.CommunityChatScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LettersScreen
import com.example.ui.screens.PairOfWordsScreen
import com.example.ui.screens.QuickQuizScreen
import com.example.ui.screens.ScreenRoute
import com.example.ui.screens.StoriesScreen
import com.example.ui.screens.UnitFourScreen
import com.example.ui.screens.VerbsScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.util.configureAppCheck

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // MANDATORY: Configure App Check with launch intent before any UI or Firebase operation
    configureAppCheck(this, intent)

    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        KipsAppRoot()
      }
    }
  }
}

@Composable
fun KipsAppRoot() {
  var currentRoute by remember { mutableStateOf(ScreenRoute.HOME) }
  var showSyllabusDialog by remember { mutableStateOf(false) }
  var globalStudyMode by remember { mutableStateOf(StudyMode.ORDINARY) }

  // Back handling: pop back to Home if in any subscreen
  BackHandler(enabled = currentRoute != ScreenRoute.HOME) {
    currentRoute = ScreenRoute.HOME
  }

  val pageTitle = when (currentRoute) {
    ScreenRoute.HOME -> "KIPS College Test Portal"
    ScreenRoute.PAIR_OF_WORDS -> "Pair of Words (41 - 60)"
    ScreenRoute.VERBS -> "Correct Forms of Verbs"
    ScreenRoute.STORIES -> "Moral Stories (5 & 6)"
    ScreenRoute.APPLICATIONS -> "Applications (5 & 6)"
    ScreenRoute.LETTERS -> "Letters (5 & 6)"
    ScreenRoute.UNIT_FOUR -> "Unit-4: Team Moon (Class 11)"
    ScreenRoute.QUICK_QUIZ -> "Self Quiz Warm-Up"
    ScreenRoute.AI_TUTOR -> "Gemini AI English Tutor"
    ScreenRoute.COMMUNITY_CHAT -> "Student Discussion Chat"
  }

  val pageSubtitle = when (currentRoute) {
    ScreenRoute.HOME -> "★ Since 1992 • English Test Portal"
    ScreenRoute.PAIR_OF_WORDS -> "20 Pairs • Simple vs Topper"
    ScreenRoute.VERBS -> "Tense Formulas & Solved MCQs"
    ScreenRoute.STORIES -> "A Foolish Stag & King and Spider"
    ScreenRoute.APPLICATIONS -> "Security Refund & Urgent Work"
    ScreenRoute.LETTERS -> "Bad Company & Borrowing Books"
    ScreenRoute.UNIT_FOUR -> "Team Moon Full Urdu Translation & Q&A"
    ScreenRoute.QUICK_QUIZ -> "Quick 8-Question Assessment"
    ScreenRoute.AI_TUTOR -> "Direct Questioning with Gemini 3.8 Flash"
    ScreenRoute.COMMUNITY_CHAT -> "Live Student Q&A & Peer Replies"
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .statusBarsPadding()
      .navigationBarsPadding()
      .testTag("app_scaffold"),
    topBar = {
      AppHeader(
        title = pageTitle,
        subtitle = pageSubtitle,
        showBack = currentRoute != ScreenRoute.HOME,
        onBackClick = { currentRoute = ScreenRoute.HOME },
        onViewSyllabusClick = { showSyllabusDialog = true },
        studyMode = globalStudyMode,
        onToggleStudyMode = { globalStudyMode = it },
        onAiClick = { currentRoute = ScreenRoute.AI_TUTOR },
        onChatClick = { currentRoute = ScreenRoute.COMMUNITY_CHAT }
      )
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      AnimatedContent(
        targetState = currentRoute,
        transitionSpec = {
          if (targetState == ScreenRoute.HOME) {
            (slideInHorizontally { -it } + fadeIn()).togetherWith(slideOutHorizontally { it } + fadeOut())
          } else {
            (slideInHorizontally { it } + fadeIn()).togetherWith(slideOutHorizontally { -it } + fadeOut())
          }
        },
        label = "ScreenTransition"
      ) { route ->
        when (route) {
          ScreenRoute.HOME -> HomeScreen(
            studyMode = globalStudyMode,
            onNavigate = { currentRoute = it },
            onOpenSyllabusSheet = { showSyllabusDialog = true }
          )
          ScreenRoute.PAIR_OF_WORDS -> PairOfWordsScreen(
            initialMode = globalStudyMode
          )
          ScreenRoute.VERBS -> VerbsScreen(
            initialMode = globalStudyMode
          )
          ScreenRoute.STORIES -> StoriesScreen(
            initialMode = globalStudyMode
          )
          ScreenRoute.APPLICATIONS -> ApplicationsScreen(
            initialMode = globalStudyMode
          )
          ScreenRoute.LETTERS -> LettersScreen(
            initialMode = globalStudyMode
          )
          ScreenRoute.UNIT_FOUR -> UnitFourScreen(
            initialMode = globalStudyMode
          )
          ScreenRoute.QUICK_QUIZ -> QuickQuizScreen(
            onBackToHome = { currentRoute = ScreenRoute.HOME }
          )
          ScreenRoute.AI_TUTOR -> AiTutorScreen(
            onNavigateBack = { currentRoute = ScreenRoute.HOME }
          )
          ScreenRoute.COMMUNITY_CHAT -> CommunityChatScreen(
            onNavigateBack = { currentRoute = ScreenRoute.HOME }
          )
        }
      }

      if (showSyllabusDialog) {
        SyllabusSheetDialog(onDismiss = { showSyllabusDialog = false })
      }
    }
  }
}
