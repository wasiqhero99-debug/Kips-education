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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.data.model.StudyMode
import com.example.data.model.VerbQuestion
import com.example.data.model.VerbRule
import com.example.data.repository.SyllabusRepository
import com.example.ui.components.DeveloperCreditFooter
import com.example.ui.theme.KipsBlueContainer
import com.example.ui.theme.KipsBlueDark
import com.example.ui.theme.KipsBluePrimary
import com.example.ui.theme.KipsBlueSecondary
import com.example.ui.theme.KipsGreenLight
import com.example.ui.theme.KipsGreenSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbsScreen(
  initialMode: StudyMode = StudyMode.ORDINARY
) {
  var selectedTab by remember { mutableIntStateOf(0) }
  var currentMode by remember { mutableStateOf(initialMode) }

  val isOrd = currentMode == StudyMode.ORDINARY

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    Surface(
      color = Color.White,
      shadowElevation = 2.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Correct Forms of Verbs",
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = KipsBluePrimary
            )
            Text(
              text = if (isOrd) "Ordinary: آسان اصول اور تیز چیٹ شیٹ" else "Extraordinary: تفصیلی بورڈ ٹرکس اور 15 MCQs",
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }

          Surface(
            shape = RoundedCornerShape(10.dp),
            color = if (isOrd) Color(0xFFE0F2FE) else Color(0xFFEDE9FE)
          ) {
            Text(
              text = if (isOrd) "Simple Mode" else "Topper Mode",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED),
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Dual Mode tabs
        SecondaryTabRow(
          selectedTabIndex = if (isOrd) 0 else 1,
          containerColor = Color.Transparent,
          contentColor = if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED)
        ) {
          Tab(
            selected = isOrd,
            onClick = { currentMode = StudyMode.ORDINARY },
            text = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.MenuBook, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Ordinary (Simple)")
              }
            }
          )
          Tab(
            selected = !isOrd,
            onClick = { currentMode = StudyMode.EXTRAORDINARY },
            text = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Star, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Extraordinary (Topper)")
              }
            }
          )
        }

        Spacer(modifier = Modifier.height(6.dp))

        SecondaryTabRow(
          selectedTabIndex = selectedTab,
          containerColor = Color.Transparent,
          contentColor = KipsBluePrimary,
          modifier = Modifier.testTag("verbs_tab_row")
        ) {
          Tab(
            selected = selectedTab == 0,
            onClick = { selectedTab = 0 },
            text = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Rule, contentDescription = null, modifier = Modifier.size(15.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Tense Rules (7)")
              }
            }
          )
          Tab(
            selected = selectedTab == 1,
            onClick = { selectedTab = 1 },
            text = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.School, contentDescription = null, modifier = Modifier.size(15.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Solved MCQs (15)")
              }
            }
          )
        }
      }
    }

    if (selectedTab == 0) {
      VerbRulesList(rules = SyllabusRepository.verbRules, mode = currentMode)
    } else {
      VerbQuestionsList(questions = SyllabusRepository.verbQuestions, mode = currentMode)
    }
  }
}

@Composable
private fun VerbRulesList(rules: List<VerbRule>, mode: StudyMode) {
  val isOrd = mode == StudyMode.ORDINARY

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .testTag("verb_rules_list"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    items(rules) { rule ->
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = androidx.compose.foundation.BorderStroke(
          1.dp,
          if (isOrd) Color(0xFFBAE6FD) else Color(0xFFDDD6FE)
        )
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
          ) {
            Box(
              modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Rule,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
              text = rule.ruleTitle,
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              color = KipsBlueDark
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          Surface(
            shape = RoundedCornerShape(8.dp),
            color = if (isOrd) Color(0xFFF0F9FF) else Color(0xFFEFF6FF),
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = rule.urduSummary,
              fontSize = 13.sp,
              fontWeight = FontWeight.Medium,
              color = if (isOrd) Color(0xFF0369A1) else Color(0xFF1E3A8A),
              modifier = Modifier.padding(10.dp)
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = "Key Trigger Words (جملے میں آنے والے نشانی الفاظ):",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
          Text(
            text = rule.triggerWords,
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            color = KipsBluePrimary
          )

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = if (isOrd) "Simple Easy Formula:" else "Complete Formula:",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = if (isOrd) rule.simpleFormula else rule.formula,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              color = KipsBlueDark,
              modifier = Modifier.padding(8.dp)
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = "Exam Examples:",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
          rule.examples.forEach { (prompt, solved) ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
              verticalAlignment = Alignment.Top
            ) {
              Text(text = "• ", fontWeight = FontWeight.Bold, color = KipsBlueSecondary)
              Column {
                Text(text = prompt, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = "✓ $solved", fontSize = 12.5.sp, fontWeight = FontWeight.Bold, color = KipsGreenSuccess)
              }
            }
          }
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

@Composable
private fun VerbQuestionsList(questions: List<VerbQuestion>, mode: StudyMode) {
  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .testTag("verb_questions_list"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    items(questions) { question ->
      VerbQuestionCard(question = question)
    }

    item {
      Spacer(modifier = Modifier.height(8.dp))
      DeveloperCreditFooter()
      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}

@Composable
private fun VerbQuestionCard(question: VerbQuestion) {
  var selectedOption by remember { mutableStateOf<Int?>(null) }
  var showExplanation by remember { mutableStateOf(false) }

  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.6f))
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
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = KipsBlueContainer
        ) {
          Text(
            text = "Q${question.id} • Board Solved",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = KipsBluePrimary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }

        Text(
          text = if (selectedOption != null) "Tap to change" else "Select answer",
          fontSize = 11.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = question.sentence,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = KipsBlueDark
      )

      Spacer(modifier = Modifier.height(12.dp))

      val labels = listOf("A", "B", "C", "D")
      question.options.forEachIndexed { index, option ->
        val isChosen = selectedOption == index
        val isCorrect = index == question.correctIndex
        val isRevealed = selectedOption != null

        val (bgColor, borderColor, textColor) = when {
          isRevealed && isCorrect -> Triple(KipsGreenLight, KipsGreenSuccess, KipsGreenSuccess)
          isRevealed && isChosen && !isCorrect -> Triple(Color(0xFFFEE2E2), Color(0xFFEF4444), Color(0xFFB91C1C))
          isChosen -> Triple(KipsBlueContainer, KipsBluePrimary, KipsBluePrimary)
          else -> Triple(Color(0xFFF8FAFC), MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), KipsBlueDark)
        }

        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
              selectedOption = index
              showExplanation = true
            },
          shape = RoundedCornerShape(10.dp),
          color = bgColor,
          border = androidx.compose.foundation.BorderStroke(1.dp, borderColor)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "(${labels.getOrElse(index) { "?" }})",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = option,
                fontSize = 14.sp,
                fontWeight = if (isChosen || (isRevealed && isCorrect)) FontWeight.Bold else FontWeight.Normal,
                color = textColor
              )
            }

            if (isRevealed && isCorrect) {
              Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Correct",
                tint = KipsGreenSuccess,
                modifier = Modifier.size(18.dp)
              )
            }
          }
        }
      }

      AnimatedVisibility(visible = showExplanation || selectedOption != null) {
        Column(modifier = Modifier.padding(top = 10.dp)) {
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color(0xFFF0FDF4),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBBF7D0)),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(10.dp),
              verticalAlignment = Alignment.Top
            ) {
              Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Explanation",
                tint = KipsGreenSuccess,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Column {
                Text(
                  text = "Correct Answer: (${labels[question.correctIndex]}) ${question.options[question.correctIndex]}",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF14532D)
                )
                Text(
                  text = question.explanation,
                  fontSize = 11.sp,
                  color = Color(0xFF166534)
                )
              }
            }
          }
        }
      }
    }
  }
}
