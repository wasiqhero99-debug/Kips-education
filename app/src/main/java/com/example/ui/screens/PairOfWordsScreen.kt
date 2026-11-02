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
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.data.model.PairOfWordsItem
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
fun PairOfWordsScreen(
  initialMode: StudyMode = StudyMode.ORDINARY
) {
  val context = LocalContext.current
  var currentMode by remember { mutableStateOf(initialMode) }
  var searchQuery by remember { mutableStateOf("") }

  val filteredList = remember(searchQuery) {
    if (searchQuery.isBlank()) {
      SyllabusRepository.pairOfWords
    } else {
      SyllabusRepository.pairOfWords.filter {
        it.wordA.contains(searchQuery, ignoreCase = true) ||
          it.wordB.contains(searchQuery, ignoreCase = true) ||
          it.urduA.contains(searchQuery, ignoreCase = true) ||
          it.urduB.contains(searchQuery, ignoreCase = true) ||
          it.id.toString().contains(searchQuery)
      }
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    // Top Bar with mode switcher & search
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
              text = "Pair of Words (41 - 60)",
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = KipsBluePrimary
            )
            Text(
              text = if (currentMode == StudyMode.ORDINARY) {
                "سادہ اور آسان جملے جو فوراً یاد ہو جائیں"
              } else {
                "اعلیٰ امتحانی جملے اور گولڈن نوٹس"
              },
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }

          Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (currentMode == StudyMode.ORDINARY) Color(0xFFE0F2FE) else Color(0xFFEDE9FE)
          ) {
            Text(
              text = "20 Pairs",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = if (currentMode == StudyMode.ORDINARY) Color(0xFF0284C7) else Color(0xFF7C3AED),
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Dual Mode Tabs
        SecondaryTabRow(
          selectedTabIndex = if (currentMode == StudyMode.ORDINARY) 0 else 1,
          containerColor = Color.Transparent,
          contentColor = KipsBluePrimary
        ) {
          Tab(
            selected = currentMode == StudyMode.ORDINARY,
            onClick = { currentMode = StudyMode.ORDINARY },
            text = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.MenuBook, contentDescription = null, modifier = Modifier.size(15.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Ordinary (Simple & Easy)")
              }
            }
          )
          Tab(
            selected = currentMode == StudyMode.EXTRAORDINARY,
            onClick = { currentMode = StudyMode.EXTRAORDINARY },
            text = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Star, contentDescription = null, modifier = Modifier.size(15.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Extraordinary (Topper)")
              }
            }
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
          value = searchQuery,
          onValueChange = { searchQuery = it },
          placeholder = { Text("Search word (e.g. Devise, Die, Eminent)...", fontSize = 13.sp) },
          leadingIcon = {
            Icon(
              imageVector = Icons.Default.Search,
              contentDescription = "Search",
              tint = KipsBluePrimary
            )
          },
          singleLine = true,
          modifier = Modifier
            .fillMaxWidth()
            .testTag("pair_search_input"),
          shape = RoundedCornerShape(12.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = KipsBluePrimary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline
          )
        )
      }
    }

    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .testTag("pair_of_words_list"),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      items(filteredList, key = { it.id }) { item ->
        val activeSentenceA = if (currentMode == StudyMode.ORDINARY) item.simpleSentenceA else item.sentenceA
        val activeSentenceB = if (currentMode == StudyMode.ORDINARY) item.simpleSentenceB else item.sentenceB

        PairWordCard(
          item = item,
          mode = currentMode,
          sentenceA = activeSentenceA,
          sentenceB = activeSentenceB,
          onCopy = {
            val textToCopy = """
              ${item.id}. ${item.wordA} vs ${item.wordB} (${if (currentMode == StudyMode.ORDINARY) "Simple" else "Extraordinary"})
              - ${item.wordA} (${item.urduA}): $activeSentenceA
              - ${item.wordB} (${item.urduB}): $activeSentenceB
              ${if (currentMode == StudyMode.EXTRAORDINARY) "Note: ${item.examNote}" else ""}
            """.trimIndent()
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("Pair of Words", textToCopy))
            Toast.makeText(context, "Copied pair to clipboard!", Toast.LENGTH_SHORT).show()
          }
        )
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
private fun PairWordCard(
  item: PairOfWordsItem,
  mode: StudyMode,
  sentenceA: String,
  sentenceB: String,
  onCopy: () -> Unit
) {
  val isOrd = mode == StudyMode.ORDINARY

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("pair_card_${item.id}"),
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
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(30.dp)
              .clip(CircleShape)
              .background(if (isOrd) Color(0xFF0284C7) else Color(0xFF7C3AED)),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = item.id.toString(),
              color = Color.White,
              fontWeight = FontWeight.Bold,
              fontSize = 13.sp
            )
          }
          Spacer(modifier = Modifier.width(10.dp))
          Text(
            text = "${item.wordA} : ${item.wordB}",
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold,
            color = KipsBlueDark
          )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = if (isOrd) Color(0xFFF0F9FF) else Color(0xFFF5F3FF)
          ) {
            Text(
              text = if (isOrd) "Simple" else "Topper",
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = if (isOrd) Color(0xFF0369A1) else Color(0xFF6D28D9),
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }

          IconButton(
            onClick = onCopy,
            modifier = Modifier
              .size(36.dp)
              .testTag("copy_pair_${item.id}")
          ) {
            Icon(
              imageVector = Icons.Default.ContentCopy,
              contentDescription = "Copy",
              tint = KipsBlueSecondary,
              modifier = Modifier.size(18.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Word A Block
      WordDetailBlock(
        word = item.wordA,
        urdu = item.urduA,
        definition = item.defA,
        sentence = sentenceA,
        accentColor = if (isOrd) Color(0xFF0284C7) else Color(0xFF6D28D9)
      )

      Spacer(modifier = Modifier.height(8.dp))

      // Word B Block
      WordDetailBlock(
        word = item.wordB,
        urdu = item.urduB,
        definition = item.defB,
        sentence = sentenceB,
        accentColor = if (isOrd) KipsBluePrimary else Color(0xFF7C3AED)
      )

      if (mode == StudyMode.EXTRAORDINARY && item.examNote.isNotBlank()) {
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = Color(0xFFFFFBEB),
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFDE68A)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.Top
          ) {
            Icon(
              imageVector = Icons.Default.Lightbulb,
              contentDescription = "Exam Tip",
              tint = KipsGold,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Exam Distinction Tip: ${item.examNote}",
              fontSize = 11.sp,
              fontWeight = FontWeight.Medium,
              color = Color(0xFF92400E)
            )
          }
        }
      }
    }
  }
}

@Composable
private fun WordDetailBlock(
  word: String,
  urdu: String,
  definition: String,
  sentence: String,
  accentColor: Color
) {
  Surface(
    shape = RoundedCornerShape(10.dp),
    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = word,
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          color = accentColor
        )
        Text(
          text = urdu,
          fontSize = 13.sp,
          fontWeight = FontWeight.SemiBold,
          color = Color(0xFF0F766E)
        )
      }

      Text(
        text = definition,
        fontSize = 11.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

      Spacer(modifier = Modifier.height(6.dp))

      Row(verticalAlignment = Alignment.Top) {
        Text(
          text = "• Ex: ",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = KipsBlueDark
        )
        Text(
          text = sentence,
          fontSize = 13.sp,
          fontWeight = FontWeight.Medium,
          color = KipsBlueDark
        )
      }
    }
  }
}
