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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.StudyMode
import com.example.data.repository.SyllabusRepository
import com.example.ui.components.DeveloperCreditFooter
import com.example.ui.theme.KipsBlueContainer
import com.example.ui.theme.KipsBlueDark
import com.example.ui.theme.KipsBluePrimary
import com.example.ui.theme.KipsBlueSecondary
import com.example.ui.theme.KipsGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoriesScreen(
  initialStoryIndex: Int = 0,
  initialMode: StudyMode = StudyMode.ORDINARY
) {
  val context = LocalContext.current
  var selectedTab by remember { mutableIntStateOf(initialStoryIndex) }
  var currentMode by remember { mutableStateOf(initialMode) }
  var fontSizeSp by remember { mutableFloatStateOf(14.5f) }

  val story = SyllabusRepository.stories[selectedTab.coerceIn(0, SyllabusRepository.stories.lastIndex)]
  val isOrd = currentMode == StudyMode.ORDINARY
  val activeParagraphs = if (isOrd) story.simpleParagraphs else story.paragraphs

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    // Header
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
              text = "Moral Stories (Test Syllabus)",
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = KipsBluePrimary
            )
            Text(
              text = if (isOrd) "Ordinary: سادہ اور آسان انگریزی (یاد کرنے میں آسان)" else "Extraordinary: ٹاپرز کے لیے اعلیٰ ادبی فصاحت",
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }

          // Font size controls
          Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
              onClick = { if (fontSizeSp > 12f) fontSizeSp -= 1.5f },
              modifier = Modifier.size(32.dp)
            ) {
              Text("A-", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = KipsBluePrimary)
            }
            IconButton(
              onClick = { if (fontSizeSp < 22f) fontSizeSp += 1.5f },
              modifier = Modifier.size(32.dp)
            ) {
              Text("A+", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = KipsBluePrimary)
            }
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Story Selection Tabs
        SecondaryTabRow(
          selectedTabIndex = selectedTab,
          containerColor = Color.Transparent,
          contentColor = KipsBluePrimary,
          modifier = Modifier.testTag("stories_tab_row")
        ) {
          Tab(
            selected = selectedTab == 0,
            onClick = { selectedTab = 0 },
            text = { Text("5. A Foolish Stag") }
          )
          Tab(
            selected = selectedTab == 1,
            onClick = { selectedTab = 1 },
            text = { Text("6. The King & Spider") }
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Dual Mode Tabs (Ordinary vs Extraordinary)
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
        .testTag("story_detail_content"),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      // Story Header Card
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color.White),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isOrd) Color(0xFFBAE6FD) else Color(0xFFDDD6FE)
          ),
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
                    text = "Story #${story.number} • ${if (isOrd) "Simple & Easy Version" else "Topper Full Marks Version"}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isOrd) Color(0xFF0369A1) else Color(0xFF6D28D9),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                  )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                  text = story.title,
                  fontSize = 20.sp,
                  fontWeight = FontWeight.Black,
                  color = KipsBlueDark
                )
                Text(
                  text = story.urduTitle,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = Color(0xFF0F766E)
                )
              }

              Button(
                onClick = {
                  val fullStoryText = """
                    ${story.title} (${story.urduTitle}) - ${if (isOrd) "Ordinary (Simple)" else "Extraordinary (Topper)"}
                    
                    ${activeParagraphs.joinToString("\n\n")}
                    
                    Moral:
                    ${story.moralQuotes.map { "• $it" }.joinToString("\n")}
                  """.trimIndent()
                  val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                  clipboard.setPrimaryClip(ClipData.newPlainText("Story Text", fullStoryText))
                  Toast.makeText(context, "Copied story to clipboard!", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                  containerColor = if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED)
                ),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                modifier = Modifier.testTag("copy_story_button")
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

            // Moral Banner
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = Color(0xFFFEF3C7),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFDE68A)),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Moral",
                    tint = KipsGold,
                    modifier = Modifier.size(16.dp)
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(
                    text = "Key Examination Morals:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF92400E)
                  )
                }
                Spacer(modifier = Modifier.height(4.dp))
                story.moralQuotes.forEach { moral ->
                  Text(
                    text = "★ \"$moral\"",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF78350F)
                  )
                }
              }
            }
          }
        }
      }

      // Paragraphs
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
              Text(
                text = if (isOrd) "Simple & Easy Narrative (آسان متن):" else "Full Literary Narrative (اعلیٰ معیاری متن):",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = KipsBlueDark
              )
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = if (isOrd) Color(0xFFE0F2FE) else Color(0xFFEDE9FE)
              ) {
                Text(
                  text = "${activeParagraphs.size} Paragraphs",
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  color = if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED),
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            activeParagraphs.forEachIndexed { index, para ->
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 6.dp),
                verticalAlignment = Alignment.Top
              ) {
                Surface(
                  shape = RoundedCornerShape(4.dp),
                  color = if (isOrd) Color(0xFFE0F2FE) else Color(0xFFEDE9FE),
                  modifier = Modifier.padding(top = 2.dp)
                ) {
                  Text(
                    text = "P${index + 1}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED),
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                  )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = para,
                  fontSize = fontSizeSp.sp,
                  lineHeight = (fontSizeSp * 1.55f).sp,
                  color = KipsBlueDark
                )
              }
            }
          }
        }
      }

      // Vocabulary Notes (in Extraordinary mode)
      if (!isOrd) {
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
                text = "Key Vocabulary & Word Meanings:",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = KipsBlueDark
              )
              Spacer(modifier = Modifier.height(8.dp))

              story.vocabularyNotes.forEach { (word, meaning) ->
                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = "• $word",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7C3AED)
                  )
                  Text(
                    text = meaning,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }
              }
            }
          }
        }
      }

      // Exam Tips
      item {
        Surface(
          shape = RoundedCornerShape(12.dp),
          color = Color(0xFFEFF6FF),
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBFDBFE)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
          ) {
            Icon(
              imageVector = Icons.Default.Lightbulb,
              contentDescription = "Tips",
              tint = KipsBluePrimary,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = story.examTips,
              fontSize = 11.sp,
              color = Color(0xFF1E40AF)
            )
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
