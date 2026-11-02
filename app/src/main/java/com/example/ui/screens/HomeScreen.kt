package com.example.ui.screens

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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.StudyMode
import com.example.ui.components.DeveloperCreditFooter
import com.example.ui.theme.KipsBlueContainer
import com.example.ui.theme.KipsBlueDark
import com.example.ui.theme.KipsBluePrimary
import com.example.ui.theme.KipsBlueSecondary
import com.example.ui.theme.KipsGold

import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Forum

enum class ScreenRoute {
  HOME,
  PAIR_OF_WORDS,
  VERBS,
  STORIES,
  APPLICATIONS,
  LETTERS,
  UNIT_FOUR,
  QUICK_QUIZ,
  AI_TUTOR,
  COMMUNITY_CHAT
}

@Composable
fun HomeScreen(
  studyMode: StudyMode,
  onNavigate: (ScreenRoute) -> Unit,
  onOpenSyllabusSheet: () -> Unit
) {
  val isOrdinary = studyMode == StudyMode.ORDINARY

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("home_screen_content"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // Hero Banner Card with College branding
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("hero_banner_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        border = androidx.compose.foundation.BorderStroke(
          1.dp,
          Brush.horizontalGradient(listOf(KipsBluePrimary.copy(alpha = 0.5f), KipsBlueSecondary.copy(alpha = 0.2f)))
        )
      ) {
        Box(modifier = Modifier.fillMaxWidth()) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(6.dp)
              .background(Brush.horizontalGradient(listOf(KipsBluePrimary, KipsBlueSecondary)))
          )

          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 18.dp, start = 18.dp, end = 18.dp, bottom = 18.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFFFEF2F2),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFECACA))
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Box(
                    modifier = Modifier
                      .size(8.dp)
                      .clip(CircleShape)
                      .background(Color(0xFFDC2626))
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(
                    text = "English Test Tomorrow!",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF991B1B)
                  )
                }
              }

              Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (isOrdinary) Color(0xFFE0F2FE) else Color(0xFFEDE9FE)
              ) {
                Text(
                  text = if (isOrdinary) "Simple & Easy Mode" else "Extraordinary Mode",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = if (isOrdinary) Color(0xFF0284C7) else Color(0xFF7C3AED),
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
              text = "KIPS College Test Portal",
              fontSize = 21.sp,
              fontWeight = FontWeight.Black,
              color = KipsBlueDark
            )
            Text(
              text = if (isOrdinary) {
                "سادہ اور آسان تیاری: مختصر اور فہم جملے جو کم وقت میں آسانی سے یاد ہو جائیں۔"
              } else {
                "ٹاپرز کی تیاری: غیر معمولی ادبی فصاحت، کوٹیشنز اور اعلیٰ امتحانی الفاظ مع مکمل حل۔"
              },
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              lineHeight = 19.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Button(
                onClick = onOpenSyllabusSheet,
                colors = ButtonDefaults.buttonColors(containerColor = KipsBluePrimary),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                  .weight(1f)
                  .testTag("home_view_sheet_button")
              ) {
                Icon(
                  imageVector = Icons.Default.Description,
                  contentDescription = null,
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Original Sheet", fontSize = 12.sp, fontWeight = FontWeight.Bold)
              }

              Button(
                onClick = { onNavigate(ScreenRoute.QUICK_QUIZ) },
                colors = ButtonDefaults.buttonColors(containerColor = KipsBlueContainer, contentColor = KipsBluePrimary),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                  .weight(0.9f)
                  .testTag("home_quick_quiz_button")
              ) {
                Icon(
                  imageVector = Icons.Default.Quiz,
                  contentDescription = null,
                  tint = KipsBluePrimary,
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Self Quiz", fontSize = 12.sp, fontWeight = FontWeight.Bold)
              }
            }
          }
        }
      }
    }

    // TWO PROMINENT FRONT ICONS: Gemini AI Questioning & Student Community Chat
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 2.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Direct Interactive Tools (سامنے دو فیچرز):",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = KipsBlueDark
          )
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color(0xFFDCFCE7)
          ) {
            Text(
              text = "Live Online",
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF15803D),
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          // ICON 1: Gemini AI Questioning (Front Icon)
          Surface(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(16.dp))
              .clickable { onNavigate(ScreenRoute.AI_TUTOR) }
              .testTag("front_icon_ai_tutor"),
            color = Color.White,
            shadowElevation = 3.dp,
            border = androidx.compose.foundation.BorderStroke(
              1.5.dp,
              Brush.linearGradient(listOf(Color(0xFF8B5CF6), Color(0xFFC084FC)))
            )
          ) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Box(
                modifier = Modifier
                  .size(52.dp)
                  .clip(CircleShape)
                  .background(
                    Brush.radialGradient(
                      listOf(Color(0xFF8B5CF6), Color(0xFF6D28D9))
                    )
                  ),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.AutoAwesome,
                  contentDescription = "Gemini AI Questioning",
                  tint = Color.White,
                  modifier = Modifier.size(28.dp)
                )
              }

              Spacer(modifier = Modifier.height(10.dp))

              Text(
                text = "Ask Gemini AI",
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF5B21B6)
              )

              Text(
                text = "AI سے سوال پوچھیں",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7C3AED)
              )

              Spacer(modifier = Modifier.height(4.dp))

              Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color(0xFFF3E8FF)
              ) {
                Text(
                  text = "24/7 English Tutor",
                  fontSize = 9.5.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = Color(0xFF6D28D9),
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }
          }

          // ICON 2: Student Discussion Chat (Front Icon)
          Surface(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(16.dp))
              .clickable { onNavigate(ScreenRoute.COMMUNITY_CHAT) }
              .testTag("front_icon_student_chat"),
            color = Color.White,
            shadowElevation = 3.dp,
            border = androidx.compose.foundation.BorderStroke(
              1.5.dp,
              Brush.linearGradient(listOf(Color(0xFF0284C7), Color(0xFF38BDF8)))
            )
          ) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Box(
                modifier = Modifier
                  .size(52.dp)
                  .clip(CircleShape)
                  .background(
                    Brush.radialGradient(
                      listOf(Color(0xFF0284C7), Color(0xFF003865))
                    )
                  ),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.Forum,
                  contentDescription = "Student Community Chat",
                  tint = Color.White,
                  modifier = Modifier.size(28.dp)
                )
              }

              Spacer(modifier = Modifier.height(10.dp))

              Text(
                text = "Student Chat",
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                color = KipsBluePrimary
              )

              Text(
                text = "طلباء مباحثہ و جوابات",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0369A1)
              )

              Spacer(modifier = Modifier.height(4.dp))

              Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color(0xFFE0F2FE)
              ) {
                Text(
                  text = "Free Live Replies",
                  fontSize = 9.5.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = Color(0xFF0284C7),
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }
          }
        }
      }
    }

    // Two-Level Learning Guide Card
    item {
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = if (isOrdinary) Icons.Default.MenuBook else Icons.Default.Star,
            contentDescription = null,
            tint = if (isOrdinary) Color(0xFF0284C7) else Color(0xFF7C3AED),
            modifier = Modifier.size(24.dp)
          )
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = if (isOrdinary) "Showing: Ordinary (Simple & Easy)" else "Showing: Extraordinary (Topper Level)",
              fontSize = 12.5.sp,
              fontWeight = FontWeight.Bold,
              color = KipsBlueDark
            )
            Text(
              text = "اوپر والے بٹن سے کسی بھی وقت موڈ تبدیل کر سکتے ہیں (سادہ / غیر معمولی)",
              fontSize = 11.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }
    }

    // Category Grid Header
    item {
      Text(
        text = "Syllabus Sections with Solutions",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = KipsBlueDark,
        modifier = Modifier.padding(start = 4.dp, top = 2.dp)
      )
    }

    // Card 1: Letters (Letters ka alag card)
    item {
      SyllabusCategoryCard(
        title = "Letters (5 & 6)",
        urduTitle = if (isOrdinary) "خطوط (پنجاب بورڈ مستند پیٹرن - آسان انداز)" else "خطوط (پنجاب بورڈ ٹاپرز فارمیٹ - 10/10)",
        badge = "BISE 2026-27 Pattern",
        description = "5. To Brother: Avoid bad company • 6. To Friend: Borrow books",
        icon = Icons.Default.Mail,
        accentColor = Color(0xFF0284C7),
        onClick = { onNavigate(ScreenRoute.LETTERS) }
      )
    }

    // Card 2: Applications (Applications ka alag card)
    item {
      SyllabusCategoryCard(
        title = "Applications (5 & 6)",
        urduTitle = if (isOrdinary) "درخواستیں (پنجاب بورڈ مستند 3 پیراگراف فارمیٹ)" else "درخواستیں (پنجاب بورڈ باضابطہ قانونی فارمیٹ)",
        badge = "BISE 2026-27 Pattern",
        description = "5. Security Fee Refund • 6. Urgent Piece of Work",
        icon = Icons.Default.Assignment,
        accentColor = Color(0xFF0D9488),
        onClick = { onNavigate(ScreenRoute.APPLICATIONS) }
      )
    }

    // Card 3: Moral Stories (Stories ka alag card)
    item {
      SyllabusCategoryCard(
        title = "Stories (5 & 6)",
        urduTitle = if (isOrdinary) "کہانیاں (سادہ الفاظ اور مختصر اسباق)" else "کہانیاں (شاہکار ادبی پیراگراف اور اقوال)",
        badge = if (isOrdinary) "Easy English" else "Literary",
        description = "5. A Foolish Stag • 6. The King and the Spider",
        icon = Icons.Default.AutoStories,
        accentColor = Color(0xFF7C3AED),
        onClick = { onNavigate(ScreenRoute.STORIES) }
      )
    }

    // Card 4: Pair of Words (41 - 60)
    item {
      SyllabusCategoryCard(
        title = "Pair of Words (41 - 60)",
        urduTitle = if (isOrdinary) "20 جوڑے (انتہائی آسان اور مختصر جملے)" else "20 جوڑے (اعلیٰ امتحانی جملے اور ٹپس)",
        badge = "20 Pairs",
        description = "Devise:Device, Die:Dye, Eminent:Imminent, Fourth:Forth, Hour:Our...",
        icon = Icons.Default.FormatListNumbered,
        accentColor = Color(0xFFEA580C),
        onClick = { onNavigate(ScreenRoute.PAIR_OF_WORDS) }
      )
    }

    // Card 5: Correct Forms of Verbs
    item {
      SyllabusCategoryCard(
        title = "Correct Forms of Verbs",
        urduTitle = if (isOrdinary) "فارمولے (سادہ چیٹ شیٹ + مشق)" else "افعال کے اصول (مکمل بورڈ ٹرپس + 15 MCQs)",
        badge = "7 Rules + 15 MCQs",
        description = "Tense triggers (yesterday, since/for, already, if-clause) & practice",
        icon = Icons.Default.Rule,
        accentColor = Color(0xFF2563EB),
        onClick = { onNavigate(ScreenRoute.VERBS) }
      )
    }

    // Card 6: Unit-4 Team Moon (Class 11)
    item {
      SyllabusCategoryCard(
        title = "UNIT-4: TEAM MOON",
        urduTitle = if (isOrdinary) "ٹیم مون: کلاس 11 (مختصر خلاصہ اور آسان سوال جواب)" else "ٹیم مون: کلاس 11 (مکمل 6 پیراگراف ترجمہ اور تفصیلی حل)",
        badge = "Class 11 Full",
        description = "All 6 Paragraphs with Urdu translation, 6 Q&A, Glossary & MCQs",
        icon = Icons.Default.School,
        accentColor = Color(0xFF059669),
        onClick = { onNavigate(ScreenRoute.UNIT_FOUR) }
      )
    }

    // Developer Credit Footer (prominently placed on home)
    item {
      Spacer(modifier = Modifier.height(6.dp))
      DeveloperCreditFooter()
      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}

@Composable
private fun SyllabusCategoryCard(
  title: String,
  urduTitle: String,
  badge: String,
  description: String,
  icon: ImageVector,
  accentColor: Color,
  onClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(onClick = onClick)
      .testTag("category_card_${title.take(6).trim()}"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.6f))
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(48.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(accentColor.copy(alpha = 0.12f)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = title,
          tint = accentColor,
          modifier = Modifier.size(26.dp)
        )
      }

      Spacer(modifier = Modifier.width(14.dp))

      Column(modifier = Modifier.weight(1f)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = KipsBlueDark
          )
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = accentColor.copy(alpha = 0.12f)
          ) {
            Text(
              text = badge,
              fontSize = 10.5.sp,
              fontWeight = FontWeight.Bold,
              color = accentColor,
              modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(2.dp))
        Text(
          text = urduTitle,
          fontSize = 12.sp,
          fontWeight = FontWeight.Medium,
          color = Color(0xFF0F766E)
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = description,
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          maxLines = 2
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
        contentDescription = "Open",
        tint = KipsBluePrimary.copy(alpha = 0.7f),
        modifier = Modifier.size(20.dp)
      )
    }
  }
}
