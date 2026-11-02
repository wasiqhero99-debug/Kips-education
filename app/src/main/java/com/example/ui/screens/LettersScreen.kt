package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FormatAlignLeft
import androidx.compose.material.icons.filled.FormatAlignRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.StudyMode
import com.example.data.repository.SyllabusRepository
import com.example.ui.components.DeveloperCreditFooter
import com.example.ui.theme.KipsBlueContainer
import com.example.ui.theme.KipsBlueDark
import com.example.ui.theme.KipsBluePrimary

enum class LetterLayoutType(val label: String) {
  MODERN_BLOCK("Modern Left-Aligned (2026/27)"),
  TRADITIONAL("Traditional (Right Aligned)")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LettersScreen(
  initialLetterIndex: Int = 0,
  initialMode: StudyMode = StudyMode.ORDINARY
) {
  val context = LocalContext.current
  var selectedTab by remember { mutableIntStateOf(initialLetterIndex) }
  var currentMode by remember { mutableStateOf(initialMode) }
  var layoutType by remember { mutableStateOf(LetterLayoutType.MODERN_BLOCK) }
  var showMarkingScheme by remember { mutableStateOf(true) }

  val letter = SyllabusRepository.letters[selectedTab.coerceIn(0, SyllabusRepository.letters.lastIndex)]
  val isOrd = currentMode == StudyMode.ORDINARY
  val activeBodyParagraphs = if (isOrd) letter.simpleBodyParagraphs else letter.bodyParagraphs

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    // Screen Top Control Panel
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
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "Punjab Board Informal Letters",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = KipsBluePrimary
              )
              Spacer(modifier = Modifier.width(6.dp))
              Surface(
                shape = RoundedCornerShape(4.dp),
                color = Color(0xFFDCFCE7)
              ) {
                Text(
                  text = "BISE 2026-27",
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF15803D),
                  modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                )
              }
            }
            Text(
              text = if (isOrd) "Ordinary: سادہ اور آسان 3 پیراگراف (100% مستند پیٹرن)" else "Extraordinary: ٹاپرز کے لیے ادبی محاورے اور 10/10 فل مارکس",
              fontSize = 11.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Letter selection
        SecondaryTabRow(
          selectedTabIndex = selectedTab,
          containerColor = Color.Transparent,
          contentColor = KipsBluePrimary,
          modifier = Modifier.testTag("letters_tab_row")
        ) {
          Tab(
            selected = selectedTab == 0,
            onClick = { selectedTab = 0 },
            text = { Text("5. Younger Brother", fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal) }
          )
          Tab(
            selected = selectedTab == 1,
            onClick = { selectedTab = 1 },
            text = { Text("6. Borrow Books", fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal) }
          )
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
      }
    }

    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .testTag("letter_detail_content"),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      // Punjab Board Verified Pattern Header Card
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
              verticalAlignment = Alignment.Top
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = if (isOrd) Color(0xFFE0F2FE) else Color(0xFFEDE9FE)
                ) {
                  Text(
                    text = "Letter #${letter.number} • BISE Official 10-Mark Rubric",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isOrd) Color(0xFF0369A1) else Color(0xFF6D28D9),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                  )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                  text = letter.title,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Black,
                  color = KipsBlueDark
                )
                Text(
                  text = letter.urduTitle,
                  fontSize = 12.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = Color(0xFF0F766E)
                )
              }

              Spacer(modifier = Modifier.width(8.dp))

              Button(
                onClick = {
                  val fullLetterText = if (layoutType == LetterLayoutType.MODERN_BLOCK) {
                    """
                      ${letter.hallLine}
                      ${letter.cityLine}
                      ${letter.date}

                      ${letter.salutation}

                      ${activeBodyParagraphs.joinToString("\n\n")}

                      ${letter.subscription}
                      ${letter.senderName}
                    """.trimIndent()
                  } else {
                    """
                                                    ${letter.hallLine}
                                                    ${letter.cityLine}
                                                    ${letter.date}

                      ${letter.salutation}

                      ${activeBodyParagraphs.joinToString("\n\n")}

                                                    ${letter.subscription}
                                                    ${letter.senderName}
                    """.trimIndent()
                  }
                  val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                  clipboard.setPrimaryClip(ClipData.newPlainText("Punjab Board Letter", fullLetterText))
                  Toast.makeText(context, "Copied letter with Punjab Board pattern!", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                  containerColor = if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED)
                ),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                modifier = Modifier.testTag("copy_letter_button")
              ) {
                Icon(
                  imageVector = Icons.Default.ContentCopy,
                  contentDescription = "Copy",
                  modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Copy", fontSize = 12.sp)
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Layout Selector: Modern Block vs Traditional
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "Exam Layout:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              FilterChip(
                selected = layoutType == LetterLayoutType.MODERN_BLOCK,
                onClick = { layoutType = LetterLayoutType.MODERN_BLOCK },
                label = { Text("Modern Block (Left-Aligned)", fontSize = 11.sp) },
                leadingIcon = {
                  Icon(imageVector = Icons.Default.FormatAlignLeft, contentDescription = null, modifier = Modifier.size(12.dp))
                },
                colors = FilterChipDefaults.filterChipColors(
                  selectedContainerColor = KipsBlueContainer,
                  selectedLabelColor = KipsBluePrimary
                )
              )
              FilterChip(
                selected = layoutType == LetterLayoutType.TRADITIONAL,
                onClick = { layoutType = LetterLayoutType.TRADITIONAL },
                label = { Text("Traditional (Right)", fontSize = 11.sp) },
                leadingIcon = {
                  Icon(imageVector = Icons.Default.FormatAlignRight, contentDescription = null, modifier = Modifier.size(12.dp))
                },
                colors = FilterChipDefaults.filterChipColors(
                  selectedContainerColor = KipsBlueContainer,
                  selectedLabelColor = KipsBluePrimary
                )
              )
            }
          }
        }
      }

      // Punjab Board 2026-27 Marking Scheme Breakdown
      item {
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF3C7).copy(alpha = 0.6f)),
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFDE68A)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(14.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                  imageVector = Icons.Default.CheckCircle,
                  contentDescription = "Marks",
                  tint = Color(0xFFB45309),
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "BISE 10-Marks Scheme (پنجاب بورڈ نمبر تقسیم):",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF92400E)
                )
              }
              OutlinedButton(
                onClick = { showMarkingScheme = !showMarkingScheme },
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier.height(28.dp)
              ) {
                Text(if (showMarkingScheme) "Hide" else "Show", fontSize = 10.sp)
              }
            }

            AnimatedVisibility(visible = showMarkingScheme) {
              Column(modifier = Modifier.padding(top = 8.dp)) {
                letter.marksBreakdown.forEach { item ->
                  Text(
                    text = "• $item",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF78350F),
                    modifier = Modifier.padding(vertical = 1.dp)
                  )
                }
              }
            }
          }
        }
      }

      // Letter Examination Paper Layout
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color.White),
          elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
          border = androidx.compose.foundation.BorderStroke(
            1.5.dp,
            if (isOrd) Color(0xFFBAE6FD) else Color(0xFFDDD6FE)
          ),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(20.dp)
          ) {
            // Header / Heading Box
            val headerAlignment = if (layoutType == LetterLayoutType.MODERN_BLOCK) Alignment.Start else Alignment.End
            val headerTextAlign = if (layoutType == LetterLayoutType.MODERN_BLOCK) TextAlign.Start else TextAlign.End

            Column(
              modifier = Modifier.fillMaxWidth(),
              horizontalAlignment = headerAlignment
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = letter.hallLine,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold,
                  fontFamily = FontFamily.Serif,
                  color = KipsBlueDark,
                  textAlign = headerTextAlign
                )
                Spacer(modifier = Modifier.width(4.dp))
                PunctuationTag(text = "Comma ,")
              }

              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = letter.cityLine,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold,
                  fontFamily = FontFamily.Serif,
                  color = KipsBlueDark,
                  textAlign = headerTextAlign
                )
                Spacer(modifier = Modifier.width(4.dp))
                PunctuationTag(text = "Dots A.B.C.")
              }

              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = letter.date,
                  fontSize = 13.sp,
                  fontWeight = FontWeight.SemiBold,
                  fontFamily = FontFamily.Serif,
                  color = KipsBlueDark,
                  textAlign = headerTextAlign
                )
                Spacer(modifier = Modifier.width(4.dp))
                PunctuationTag(text = "Period .")
              }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Salutation (Left margin)
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = letter.salutation,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Serif,
                color = if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED)
              )
              Spacer(modifier = Modifier.width(6.dp))
              PunctuationTag(text = "Comma , (No Real Name)")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Body 3 Paragraphs with Clear Paragraph Tags
            activeBodyParagraphs.forEachIndexed { idx, para ->
              Column(modifier = Modifier.padding(vertical = 4.dp)) {
                Surface(
                  shape = RoundedCornerShape(4.dp),
                  color = Color(0xFFF1F5F9),
                  modifier = Modifier.padding(bottom = 2.dp)
                ) {
                  Text(
                    text = when (idx) {
                      0 -> "Paragraph 1 (Introduction & Inquiry)"
                      1 -> "Paragraph 2 (Main Counsel / Book Request)"
                      else -> "Paragraph 3 (Conclusion & Family Regards)"
                    },
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF475569),
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                  )
                }
                Text(
                  text = para,
                  fontSize = 14.sp,
                  lineHeight = 22.sp,
                  fontFamily = FontFamily.Serif,
                  color = Color(0xFF1E293B)
                )
              }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Subscription & Signature
            val closingAlignment = if (layoutType == LetterLayoutType.MODERN_BLOCK) Alignment.Start else Alignment.End
            Column(
              modifier = Modifier.fillMaxWidth(),
              horizontalAlignment = closingAlignment
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = letter.subscription,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold,
                  fontFamily = FontFamily.Serif,
                  color = KipsBlueDark
                )
                Spacer(modifier = Modifier.width(4.dp))
                PunctuationTag(text = "No 's, comma ,")
              }
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = letter.senderName,
                  fontSize = 13.sp,
                  fontWeight = FontWeight.Bold,
                  fontFamily = FontFamily.Serif,
                  color = if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED)
                )
                Spacer(modifier = Modifier.width(4.dp))
                PunctuationTag(text = "Dots X.Y.Z.")
              }
            }
          }
        }
      }

      // Punjab Board Punctuation & Penalty Rules
      item {
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF1F2)),
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFECDD3)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(14.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Penalty",
                tint = Color(0xFFE11D48),
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Punjab Board Strict Punctuation & Error Checklist:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF9F1239)
              )
            }
            Spacer(modifier = Modifier.height(6.dp))
            letter.keyGuidelines.forEach { tip ->
              Text(
                text = "• $tip",
                fontSize = 11.sp,
                color = Color(0xFF881337),
                modifier = Modifier.padding(vertical = 2.dp)
              )
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

@Composable
fun PunctuationTag(text: String) {
  Surface(
    shape = RoundedCornerShape(3.dp),
    color = Color(0xFFEFF6FF),
    border = androidx.compose.foundation.BorderStroke(0.5.dp, Color(0xFF93C5FD))
  ) {
    Text(
      text = text,
      fontSize = 9.sp,
      fontWeight = FontWeight.SemiBold,
      color = Color(0xFF1D4ED8),
      modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
    )
  }
}
