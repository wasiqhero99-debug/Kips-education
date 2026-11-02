package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.AiChatState
import com.example.data.repository.AiTutorRepository
import com.example.data.repository.ChatMessage
import com.example.ui.components.DeveloperCreditFooter
import com.example.ui.theme.KipsBlueDark
import com.example.ui.theme.KipsBluePrimary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiTutorScreen(
  onNavigateBack: () -> Unit
) {
  val repository = remember { AiTutorRepository() }
  val scope = rememberCoroutineScope()
  val listState = rememberLazyListState()

  var inputText by remember { mutableStateOf("") }
  val messages = remember {
    mutableStateListOf(
      ChatMessage(
        isUser = false,
        text = "Assalam-o-Alaikum! I am your KIPS College English AI Tutor. Ask me any question about tomorrow's syllabus (Unit-4 Team Moon translation & Q&A, Verbs, Pair of Words, Stories, Letters, or Applications)."
      )
    )
  }
  var isGenerating by remember { mutableStateOf(false) }
  var currentStreamingText by remember { mutableStateOf<String?>(null) }
  var errorMessage by remember { mutableStateOf<String?>(null) }

  val samplePrompts = listOf(
    "Unit 4 Team Moon short summary",
    "How to use 'Since' vs 'For' in verbs?",
    "Difference between Eminent and Imminent?",
    "Punjab board letter punctuation rules",
    "Story 5 moral and quotes"
  )

  fun sendMessage(prompt: String) {
    val trimmed = prompt.trim()
    if (trimmed.isBlank() || isGenerating) return

    messages.add(ChatMessage(isUser = true, text = trimmed))
    inputText = ""
    isGenerating = true
    currentStreamingText = ""
    errorMessage = null

    scope.launch {
      listState.animateScrollToItem(messages.size)
      repository.askQuestionStream(trimmed).collect { state ->
        when (state) {
          is AiChatState.Loading -> {
            currentStreamingText = ""
          }
          is AiChatState.Streaming -> {
            currentStreamingText = state.fullText
          }
          is AiChatState.Success -> {
            messages.add(ChatMessage(isUser = false, text = state.fullText))
            currentStreamingText = null
            isGenerating = false
          }
          is AiChatState.Error -> {
            errorMessage = state.message
            isGenerating = false
            currentStreamingText = null
          }
          else -> {}
        }
      }
    }
  }

  LaunchedEffect(messages.size, currentStreamingText) {
    if (messages.isNotEmpty()) {
      listState.animateScrollToItem(messages.size)
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFFF8FAFC))
  ) {
    // Top Bar
    TopAppBar(
      title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(Brush.linearGradient(listOf(Color(0xFF8B5CF6), Color(0xFF6D28D9)))),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.AutoAwesome,
              contentDescription = null,
              tint = Color.White,
              modifier = Modifier.size(20.dp)
            )
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = "Gemini AI English Tutor",
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Text(
              text = "KIPS College Test Assistant • 24/7",
              fontSize = 11.sp,
              color = Color.White.copy(alpha = 0.85f)
            )
          }
        }
      },
      navigationIcon = {
        IconButton(
          onClick = onNavigateBack,
          modifier = Modifier.testTag("ai_tutor_back_button")
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White
          )
        }
      },
      colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6D28D9))
    )

    // Quick Prompt Chips
    Surface(
      color = Color.White,
      shadowElevation = 1.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
          text = "Quick Question Suggestions (فوری سوالات):",
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF6D28D9),
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
        )
        LazyRow(
          contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          items(samplePrompts) { prompt ->
            Surface(
              shape = RoundedCornerShape(16.dp),
              color = Color(0xFFF3E8FF),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDDD6FE)),
              modifier = Modifier
                .clickable(enabled = !isGenerating) { sendMessage(prompt) }
            ) {
              Text(
                text = prompt,
                fontSize = 11.5.sp,
                color = Color(0xFF6D28D9),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
              )
            }
          }
        }
      }
    }

    // Message List
    LazyColumn(
      state = listState,
      modifier = Modifier
        .weight(1f)
        .fillMaxWidth()
        .padding(horizontal = 14.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
      contentPadding = PaddingValues(vertical = 12.dp)
    ) {
      items(messages) { msg ->
        AiMessageBubble(message = msg)
      }

      if (isGenerating && !currentStreamingText.isNullOrEmpty()) {
        item {
          AiMessageBubble(
            message = ChatMessage(isUser = false, text = currentStreamingText!!),
            isStreaming = true
          )
        }
      } else if (isGenerating) {
        item {
          Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            CircularProgressIndicator(
              modifier = Modifier.size(18.dp),
              strokeWidth = 2.dp,
              color = Color(0xFF7C3AED)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Gemini AI is thinking...",
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }

      errorMessage?.let { err ->
        item {
          Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFEE2E2)),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(10.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "Error: $err",
                fontSize = 12.sp,
                color = Color(0xFFB91C1C)
              )
            }
          }
        }
      }

      item {
        Spacer(modifier = Modifier.height(6.dp))
        DeveloperCreditFooter()
      }
    }

    // Input Bar
    Surface(
      color = Color.White,
      shadowElevation = 4.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        OutlinedTextField(
          value = inputText,
          onValueChange = { inputText = it },
          placeholder = { Text("Ask Gemini AI any question...", fontSize = 13.sp) },
          shape = RoundedCornerShape(24.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF7C3AED),
            unfocusedBorderColor = Color(0xFFE2E8F0)
          ),
          modifier = Modifier
            .weight(1f)
            .testTag("ai_input_text_field"),
          maxLines = 3
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
          onClick = { sendMessage(inputText) },
          enabled = inputText.isNotBlank() && !isGenerating,
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C3AED)),
          shape = CircleShape,
          contentPadding = PaddingValues(10.dp),
          modifier = Modifier
            .size(44.dp)
            .testTag("ai_send_button")
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.Send,
            contentDescription = "Send",
            tint = Color.White,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }
  }
}

@Composable
fun AiMessageBubble(
  message: ChatMessage,
  isStreaming: Boolean = false
) {
  val isUser = message.isUser

  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
  ) {
    if (!isUser) {
      Box(
        modifier = Modifier
          .size(30.dp)
          .clip(CircleShape)
          .background(Color(0xFF7C3AED)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.AutoAwesome,
          contentDescription = null,
          tint = Color.White,
          modifier = Modifier.size(16.dp)
        )
      }
      Spacer(modifier = Modifier.width(8.dp))
    }

    Surface(
      shape = RoundedCornerShape(
        topStart = 14.dp,
        topEnd = 14.dp,
        bottomStart = if (isUser) 14.dp else 2.dp,
        bottomEnd = if (isUser) 2.dp else 14.dp
      ),
      color = if (isUser) KipsBluePrimary else Color.White,
      shadowElevation = 1.dp,
      border = if (isUser) null else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
      modifier = Modifier.widthIn(max = 300.dp)
    ) {
      Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
        Text(
          text = message.text,
          fontSize = 13.5.sp,
          lineHeight = 20.sp,
          color = if (isUser) Color.White else Color(0xFF1E293B)
        )
        if (isStreaming) {
          Text(
            text = "● typing...",
            fontSize = 10.sp,
            color = Color(0xFF7C3AED),
            modifier = Modifier.padding(top = 2.dp)
          )
        }
      }
    }
  }
}
