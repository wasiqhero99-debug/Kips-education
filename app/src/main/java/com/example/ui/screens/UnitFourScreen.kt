package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Spellcheck
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ChapterParagraph
import com.example.data.model.StudyMode
import com.example.data.model.UnitQuestionAnswer
import com.example.data.repository.SyllabusRepository
import com.example.ui.components.DeveloperCreditFooter
import com.example.ui.theme.KipsBlueContainer
import com.example.ui.theme.KipsBlueDark
import com.example.ui.theme.KipsBluePrimary
import com.example.ui.theme.KipsBlueSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitFourScreen(
  initialMode: StudyMode = StudyMode.ORDINARY
) {
  val context = LocalContext.current
  var selectedTab by remember { mutableIntStateOf(0) }
  var currentMode by remember { mutableStateOf(initialMode) }
  val unitData = SyllabusRepository.unitFourData

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
          .padding(horizontal = 16.dp, vertical = 10.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Unit-4: Team Moon (Class 11)",
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = KipsBluePrimary
            )
            Text(
              text = if (isOrd) "Ordinary: سادہ اور آسان خلاصہ و مختصر سوال جواب" else "Extraordinary: مکمل 6 پیراگراف ترجمہ و تفصیلی ماڈل جوابات",
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }

          Surface(
            shape = RoundedCornerShape(8.dp),
            color = if (isOrd) Color(0xFFE0F2FE) else Color(0xFFEDE9FE)
          ) {
            Text(
              text = if (isOrd) "Simple" else "Topper",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED),
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Mode switch tabs
        SecondaryTabRow(
          selectedTabIndex = if (isOrd) 0 else 1,
          containerColor = Color.Transparent,
          contentColor = if (isOrd) Color(0xFF059669) else Color(0xFF7C3AED)
        ) {
          Tab(
            selected = isOrd,
            onClick = { currentMode = StudyMode.ORDINARY },
            text = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.MenuBook, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Ordinary (Simple & Easy)")
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

        // Content Tabs
        SecondaryTabRow(
          selectedTabIndex = selectedTab,
          containerColor = Color.Transparent,
          contentColor = KipsBluePrimary,
          modifier = Modifier.testTag("unit4_tab_row")
        ) {
          Tab(
            selected = selectedTab == 0,
            onClick = { selectedTab = 0 },
            text = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Translate, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(if (isOrd) "Easy Summary (6)" else "Full Translation (6)")
              }
            }
          )
          Tab(
            selected = selectedTab == 1,
            onClick = { selectedTab = 1 },
            text = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Help, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Questions (6)")
              }
            }
          )
          Tab(
            selected = selectedTab == 2,
            onClick = { selectedTab = 2 },
            text = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Spellcheck, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Vocabulary")
              }
            }
          )
          Tab(
            selected = selectedTab == 3,
            onClick = { selectedTab = 3 },
            text = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.MenuBook, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Grammar & MCQs")
              }
            }
          )
        }
      }
    }

    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .testTag("unit4_content_list"),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      // Overview Card
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color.White),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isOrd) Color(0xFFA7F3D0) else Color(0xFFDDD6FE)
          ),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(32.dp)
                  .clip(CircleShape)
                  .background(if (isOrd) Color(0xFFD1FAE5) else Color(0xFFEDE9FE)),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.RocketLaunch,
                  contentDescription = null,
                  tint = if (isOrd) Color(0xFF059669) else Color(0xFF7C3AED),
                  modifier = Modifier.size(18.dp)
                )
              }
              Spacer(modifier = Modifier.width(10.dp))
              Column {
                Text(
                  text = "${unitData.title} • ${if (isOrd) "Simple & Easy Version" else "Extraordinary In-Depth Version"}",
                  fontSize = 17.sp,
                  fontWeight = FontWeight.Bold,
                  color = KipsBlueDark
                )
                Text(
                  text = "Author: ${unitData.author} • Class 11 New Syllabus",
                  fontSize = 12.sp,
                  color = KipsBluePrimary
                )
              }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = unitData.urduTitle,
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold,
              color = Color(0xFF0F766E)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = if (isOrd) unitData.simpleOverview else unitData.overview,
              fontSize = 13.sp,
              color = KipsBlueDark,
              lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = Color(0xFFF0FDF4),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBBF7D0)),
              modifier = Modifier.fillMaxWidth()
            ) {
              Text(
                text = "★ Central Theme: ${unitData.keyTheme}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF166534),
                modifier = Modifier.padding(8.dp)
              )
            }
          }
        }
      }

      when (selectedTab) {
        0 -> {
          // Paragraphs / Summaries
          items(unitData.paragraphs, key = { it.number }) { para ->
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
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isOrd) Color(0xFFE0F2FE) else Color(0xFFEDE9FE)
                  ) {
                    Text(
                      text = "Paragraph ${para.number} • ${if (isOrd) "Easy Summary" else "Full Text"}",
                      fontSize = 11.sp,
                      fontWeight = FontWeight.Bold,
                      color = if (isOrd) Color(0xFF0369A1) else Color(0xFF6D28D9),
                      modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                  }

                  IconButton(
                    onClick = {
                      val text = if (isOrd) {
                        "${para.title}\nEnglish: ${para.simpleEnglishSummary}\nاردو: ${para.simpleUrduSummary}"
                      } else {
                        "${para.title}\nEnglish:\n${para.englishText}\n\nاردو ترجمہ:\n${para.urduTranslation}"
                      }
                      val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                      clipboard.setPrimaryClip(ClipData.newPlainText("Paragraph", text))
                      Toast.makeText(context, "Copied paragraph ${para.number}!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.size(32.dp)
                  ) {
                    Icon(
                      imageVector = Icons.Default.ContentCopy,
                      contentDescription = "Copy",
                      tint = KipsBlueSecondary,
                      modifier = Modifier.size(16.dp)
                    )
                  }
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                  text = para.title,
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Bold,
                  color = KipsBlueDark
                )

                Spacer(modifier = Modifier.height(8.dp))

                // English Text
                Surface(
                  shape = RoundedCornerShape(10.dp),
                  color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                      text = if (isOrd) "Simple English (آسان انگریزی):" else "English Text:",
                      fontSize = 11.sp,
                      fontWeight = FontWeight.Bold,
                      color = KipsBluePrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                      text = if (isOrd) para.simpleEnglishSummary else para.englishText,
                      fontSize = 13.5.sp,
                      lineHeight = 20.sp,
                      color = KipsBlueDark
                    )
                  }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Urdu Translation
                Surface(
                  shape = RoundedCornerShape(10.dp),
                  color = Color(0xFFF0FDF4),
                  border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBBF7D0)),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                      text = if (isOrd) "آسان اردو مفہوم (Easy Urdu):" else "بامحاورہ اردو ترجمہ (Urdu Translation):",
                      fontSize = 12.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFF166534)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                      text = if (isOrd) para.simpleUrduSummary else para.urduTranslation,
                      fontSize = 14.sp,
                      lineHeight = 22.sp,
                      color = Color(0xFF14532D)
                    )
                  }
                }
              }
            }
          }
        }
        1 -> {
          // Questions and Answers
          items(unitData.questionsAnswers, key = { it.questionNumber }) { qa ->
            val activeAns = if (isOrd) qa.simpleAnswer else qa.answer

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
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.Top
                ) {
                  Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.Top
                  ) {
                    Box(
                      modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        .background(if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED)),
                      contentAlignment = Alignment.Center
                    ) {
                      Text(
                        text = qa.questionNumber.toString(),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                      )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                      text = qa.question,
                      fontSize = 14.sp,
                      fontWeight = FontWeight.Bold,
                      color = KipsBlueDark
                    )
                  }

                  IconButton(
                    onClick = {
                      val text = "Q${qa.questionNumber}: ${qa.question}\nAns: $activeAns\n${qa.urduMeaning}"
                      val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                      clipboard.setPrimaryClip(ClipData.newPlainText("Unit 4 QA", text))
                      Toast.makeText(context, "Q&A copied to clipboard!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.size(32.dp)
                  ) {
                    Icon(
                      imageVector = Icons.Default.ContentCopy,
                      contentDescription = "Copy QA",
                      tint = KipsBlueSecondary,
                      modifier = Modifier.size(16.dp)
                    )
                  }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                  shape = RoundedCornerShape(10.dp),
                  color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                      text = if (isOrd) "Simple & Easy Answer (مختصر جواب):" else "Model Board Answer (اعلیٰ معیاری جواب):",
                      fontSize = 11.sp,
                      fontWeight = FontWeight.Bold,
                      color = if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                      text = activeAns,
                      fontSize = 13.5.sp,
                      lineHeight = 20.sp,
                      color = KipsBlueDark
                    )
                  }
                }

                if (qa.urduMeaning.isNotBlank()) {
                  Spacer(modifier = Modifier.height(8.dp))
                  Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFF0FDF4),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBBF7D0)),
                    modifier = Modifier.fillMaxWidth()
                  ) {
                    Text(
                      text = qa.urduMeaning,
                      fontSize = 12.5.sp,
                      lineHeight = 20.sp,
                      color = Color(0xFF14532D),
                      modifier = Modifier.padding(10.dp)
                    )
                  }
                }
              }
            }
          }
        }
        2 -> {
          // Chapter Vocabulary & Meanings
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
                Text(
                  text = "Team Moon: Key Glossary & Synonyms",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  color = KipsBlueDark
                )
                Spacer(modifier = Modifier.height(10.dp))

                unitData.vocabulary.forEach { vocab ->
                  Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                    modifier = Modifier
                      .fillMaxWidth()
                      .padding(vertical = 4.dp)
                  ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                      Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                      ) {
                        Text(
                          text = vocab.word,
                          fontSize = 15.sp,
                          fontWeight = FontWeight.Bold,
                          color = KipsBluePrimary
                        )
                        Text(
                          text = vocab.urduMeaning,
                          fontSize = 13.sp,
                          fontWeight = FontWeight.SemiBold,
                          color = Color(0xFF0F766E)
                        )
                      }
                      Text(
                        text = "Meaning: ${vocab.englishMeaning}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                      )
                      Spacer(modifier = Modifier.height(4.dp))
                      Text(
                        text = "Sentence: ${vocab.sampleSentence}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = KipsBlueDark
                      )
                    }
                  }
                }
              }
            }
          }
        }
        3 -> {
          // Grammar & Objective MCQs
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
                Text(
                  text = "Chapter Grammar Concepts",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  color = KipsBlueDark
                )
                Spacer(modifier = Modifier.height(10.dp))

                unitData.grammarRules.forEach { rule ->
                  Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFEFF6FF),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBFDBFE)),
                    modifier = Modifier
                      .fillMaxWidth()
                      .padding(vertical = 4.dp)
                  ) {
                    Text(
                      text = "• $rule",
                      fontSize = 12.sp,
                      color = Color(0xFF1E40AF),
                      modifier = Modifier.padding(10.dp)
                    )
                  }
                }
              }
            }
          }

          items(unitData.sampleMcqs) { mcq ->
            Card(
              shape = RoundedCornerShape(14.dp),
              colors = CardDefaults.cardColors(containerColor = Color.White),
              elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(14.dp)
              ) {
                Text(
                  text = mcq.sentence,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold,
                  color = KipsBlueDark
                )
                Spacer(modifier = Modifier.height(8.dp))
                mcq.options.forEachIndexed { idx, opt ->
                  val isCorrect = idx == mcq.correctIndex
                  Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isCorrect) Color(0xFFD1FAE5) else Color(0xFFF8FAFC),
                    border = androidx.compose.foundation.BorderStroke(
                      1.dp,
                      if (isCorrect) Color(0xFF10B981) else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier
                      .fillMaxWidth()
                      .padding(vertical = 2.dp)
                  ) {
                    Text(
                      text = "${if (isCorrect) "✓ " else ""}$opt",
                      fontSize = 12.sp,
                      fontWeight = if (isCorrect) FontWeight.Bold else FontWeight.Normal,
                      color = if (isCorrect) Color(0xFF065F46) else KipsBlueDark,
                      modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                  }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = "Explanation: ${mcq.explanation}",
                  fontSize = 11.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
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
}
