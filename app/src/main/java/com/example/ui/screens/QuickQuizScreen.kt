package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.VerbQuestion
import com.example.ui.components.DeveloperCreditFooter
import com.example.ui.theme.KipsBlueContainer
import com.example.ui.theme.KipsBlueDark
import com.example.ui.theme.KipsBluePrimary
import com.example.ui.theme.KipsGold
import com.example.ui.theme.KipsGreenLight
import com.example.ui.theme.KipsGreenSuccess

val quizBank = listOf(
  VerbQuestion(
    id = 1,
    sentence = "The team will _______ a new mechanism to solve this problem.",
    options = listOf("device", "devise", "devising", "devised"),
    correctIndex = 1,
    explanation = "We need the action verb 'devise' (to plan/invent), not the noun 'device'."
  ),
  VerbQuestion(
    id = 2,
    sentence = "Look! Black clouds are gathering, a thunderstorm is _______ .",
    options = listOf("eminent", "imminent", "illicit", "elicit"),
    correctIndex = 1,
    explanation = "'Imminent' means about to happen immediately. 'Eminent' means famous."
  ),
  VerbQuestion(
    id = 3,
    sentence = "He has been living in Lahore _______ 2018.",
    options = listOf("for", "since", "from", "at"),
    correctIndex = 1,
    explanation = "Exact point in time requires 'since'."
  ),
  VerbQuestion(
    id = 4,
    sentence = "She wore a dress of bright silk that was dyed with blue _______ .",
    options = listOf("die", "dye", "died", "dying"),
    correctIndex = 1,
    explanation = "'Dye' is the coloring substance; 'die' means to expire."
  ),
  VerbQuestion(
    id = 5,
    sentence = "There were _______ errors in his paper than expected.",
    options = listOf("less", "fewer", "little", "least"),
    correctIndex = 1,
    explanation = "'Errors' are countable plural nouns, which take 'fewer', not 'less'."
  ),
  VerbQuestion(
    id = 6,
    sentence = "The train had left before he _______ at the platform.",
    options = listOf("arrive", "arrived", "had arrived", "arrives"),
    correctIndex = 1,
    explanation = "Past Perfect 'had left' before Past Indefinite 'arrived'."
  ),
  VerbQuestion(
    id = 7,
    sentence = "The shepherd drove his _______ of cattle toward the valley.",
    options = listOf("heard", "herd", "hoard", "horde"),
    correctIndex = 1,
    explanation = "'Herd' is a group of animals. 'Heard' is listening."
  ),
  VerbQuestion(
    id = 8,
    sentence = "If you work hard with dedication, you _______ in the test.",
    options = listOf("succeed", "will succeed", "would succeed", "succeeded"),
    correctIndex = 1,
    explanation = "First conditional: If + present tense requires will + base verb."
  )
)

@Composable
fun QuickQuizScreen(onBackToHome: () -> Unit) {
  val userAnswers = remember { mutableStateMapOf<Int, Int>() }
  var isSubmitted by remember { mutableStateOf(false) }

  val answeredCount = userAnswers.size
  val totalQuestions = quizBank.size
  val correctCount = userAnswers.count { (qId, optionIdx) ->
    val question = quizBank.firstOrNull { it.id == qId }
    question?.correctIndex == optionIdx
  }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("quiz_screen_content"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // Header
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(36.dp)
                  .clip(CircleShape)
                  .background(KipsBlueContainer),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.Quiz,
                  contentDescription = null,
                  tint = KipsBluePrimary,
                  modifier = Modifier.size(20.dp)
                )
              }
              Spacer(modifier = Modifier.width(10.dp))
              Column {
                Text(
                  text = "Exam Readiness Warm-Up",
                  fontSize = 17.sp,
                  fontWeight = FontWeight.Bold,
                  color = KipsBlueDark
                )
                Text(
                  text = "Test yourself on Verbs and Pair of Words",
                  fontSize = 12.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }

            Surface(
              shape = RoundedCornerShape(12.dp),
              color = KipsBlueContainer
            ) {
              Text(
                text = "$answeredCount / $totalQuestions",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = KipsBluePrimary,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          LinearProgressIndicator(
            progress = { (answeredCount.toFloat() / totalQuestions).coerceIn(0f, 1f) },
            modifier = Modifier
              .fillMaxWidth()
              .height(6.dp)
              .clip(CircleShape),
            color = KipsBluePrimary,
            trackColor = KipsBlueContainer
          )
        }
      }
    }

    // Result card if submitted
    if (isSubmitted) {
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(
            containerColor = if (correctCount >= 6) Color(0xFFF0FDF4) else Color(0xFFFFFBEB)
          ),
          border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (correctCount >= 6) Color(0xFF86EFAC) else Color(0xFFFDE68A)
          ),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Icon(
              imageVector = Icons.Default.EmojiEvents,
              contentDescription = "Score",
              tint = if (correctCount >= 6) KipsGreenSuccess else KipsGold,
              modifier = Modifier.size(42.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "Score: $correctCount / $totalQuestions",
              fontSize = 22.sp,
              fontWeight = FontWeight.Black,
              color = KipsBlueDark
            )
            Text(
              text = if (correctCount >= 6) {
                "Outstanding preparation! You are ready to ace tomorrow's English test."
              } else {
                "Good practice! Revise the Pair of Words and Verb rules once more."
              },
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
              Button(
                onClick = {
                  userAnswers.clear()
                  isSubmitted = false
                },
                colors = ButtonDefaults.buttonColors(containerColor = KipsBluePrimary),
                shape = RoundedCornerShape(10.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Refresh,
                  contentDescription = null,
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Retry Quiz")
              }
              OutlinedButton(
                onClick = onBackToHome,
                shape = RoundedCornerShape(10.dp)
              ) {
                Text("Back to Syllabus")
              }
            }
          }
        }
      }
    }

    // Questions list
    quizBank.forEachIndexed { index, question ->
      item {
        val selected = userAnswers[question.id]
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color.White),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = KipsBlueContainer
              ) {
                Text(
                  text = "Q${index + 1}",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = KipsBluePrimary,
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = question.sentence,
                fontSize = 14.5.sp,
                fontWeight = FontWeight.Bold,
                color = KipsBlueDark
              )
            }

            Spacer(modifier = Modifier.height(10.dp))

            val labels = listOf("A", "B", "C", "D")
            question.options.forEachIndexed { optIndex, optionText ->
              val isChosen = selected == optIndex
              val isCorrect = optIndex == question.correctIndex

              val (bg, border, textCol) = when {
                isSubmitted && isCorrect -> Triple(KipsGreenLight, KipsGreenSuccess, KipsGreenSuccess)
                isSubmitted && isChosen && !isCorrect -> Triple(Color(0xFFFEE2E2), Color(0xFFEF4444), Color(0xFFB91C1C))
                isChosen -> Triple(KipsBlueContainer, KipsBluePrimary, KipsBluePrimary)
                else -> Triple(Color(0xFFF8FAFC), MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), KipsBlueDark)
              }

              Surface(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 3.dp)
                  .clickable(enabled = !isSubmitted) {
                    userAnswers[question.id] = optIndex
                  },
                shape = RoundedCornerShape(8.dp),
                color = bg,
                border = androidx.compose.foundation.BorderStroke(1.dp, border)
              ) {
                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.SpaceBetween
                ) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                      text = "(${labels[optIndex]})",
                      fontSize = 12.sp,
                      fontWeight = FontWeight.Bold,
                      color = textCol
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                      text = optionText,
                      fontSize = 13.sp,
                      fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Normal,
                      color = textCol
                    )
                  }

                  if (isSubmitted && isCorrect) {
                    Icon(
                      imageVector = Icons.Default.Check,
                      contentDescription = null,
                      tint = KipsGreenSuccess,
                      modifier = Modifier.size(16.dp)
                    )
                  }
                }
              }
            }

            if (isSubmitted) {
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = "Reason: ${question.explanation}",
                fontSize = 11.sp,
                color = KipsGreenSuccess,
                fontWeight = FontWeight.Medium
              )
            }
          }
        }
      }
    }

    if (!isSubmitted) {
      item {
        Button(
          onClick = { isSubmitted = true },
          enabled = userAnswers.isNotEmpty(),
          colors = ButtonDefaults.buttonColors(containerColor = KipsBluePrimary),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .testTag("submit_quiz_button")
        ) {
          Text("Submit Quiz & Check Score", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(8.dp))
      DeveloperCreditFooter()
      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}
