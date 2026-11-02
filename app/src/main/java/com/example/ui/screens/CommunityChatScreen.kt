package com.example.ui.screens

import android.widget.Toast
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Reply
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.CommunityMessage
import com.example.data.repository.CommunityChatRepository
import com.example.ui.components.DeveloperCreditFooter
import com.example.ui.theme.KipsBlueContainer
import com.example.ui.theme.KipsBlueDark
import com.example.ui.theme.KipsBluePrimary
import com.example.ui.theme.KipsBlueSecondary
import com.example.util.onGoogleSignInClicked
import com.example.util.signOutGoogle
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityChatScreen(
  onNavigateBack: () -> Unit
) {
  val context = LocalContext.current
  val scope = rememberCoroutineScope()
  val credentialManager = remember { CredentialManager.create(context) }
  val repository = remember { CommunityChatRepository(context) }
  val listState = rememberLazyListState()

  var currentUser by remember { mutableStateOf(Firebase.auth.currentUser) }
  var isSigningIn by remember { mutableStateOf(false) }
  var signInError by remember { mutableStateOf<String?>(null) }

  // Listen to Auth State
  DisposableEffect(Unit) {
    val authListener = FirebaseAuth.AuthStateListener { auth ->
      currentUser = auth.currentUser
    }
    Firebase.auth.addAuthStateListener(authListener)
    onDispose {
      Firebase.auth.removeAuthStateListener(authListener)
    }
  }

  // Real-time messages from Firestore
  var messages by remember { mutableStateOf<List<CommunityMessage>>(emptyList()) }
  var isObserving by remember { mutableStateOf(true) }

  LaunchedEffect(currentUser) {
    if (currentUser != null) {
      try {
        repository.observeMessages().collect { list ->
          messages = list
          isObserving = false
          if (list.isNotEmpty()) {
            listState.animateScrollToItem(list.lastIndex)
          }
        }
      } catch (e: Exception) {
        isObserving = false
      }
    } else {
      isObserving = false
      messages = emptyList()
    }
  }

  // Active Reply Target
  var replyingToMessage by remember { mutableStateOf<CommunityMessage?>(null) }
  var inputText by remember { mutableStateOf("") }
  var isSending by remember { mutableStateOf(false) }

  fun handleSend() {
    val text = inputText.trim()
    if (text.isBlank() || isSending) return

    if (currentUser == null) {
      Toast.makeText(context, "Please sign in with Google to post!", Toast.LENGTH_SHORT).show()
      return
    }

    isSending = true
    scope.launch {
      try {
        repository.sendMessage(
          text = text,
          replyToMessageId = replyingToMessage?.id,
          replyToAuthor = replyingToMessage?.userName,
          replyToText = replyingToMessage?.text
        )
        inputText = ""
        replyingToMessage = null
      } catch (e: Exception) {
        Toast.makeText(context, "Failed to send: ${e.message}", Toast.LENGTH_SHORT).show()
      } finally {
        isSending = false
      }
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFFF1F5F9))
  ) {
    // Top Bar
    TopAppBar(
      title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(KipsBluePrimary),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Forum,
              contentDescription = null,
              tint = Color.White,
              modifier = Modifier.size(20.dp)
            )
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = "Student Community Chat",
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Text(
              text = "KIPS Live Discussion & Peer Replies",
              fontSize = 11.sp,
              color = Color.White.copy(alpha = 0.85f)
            )
          }
        }
      },
      navigationIcon = {
        IconButton(
          onClick = onNavigateBack,
          modifier = Modifier.testTag("chat_back_button")
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White
          )
        }
      },
      actions = {
        if (currentUser != null) {
          IconButton(
            onClick = {
              signOutGoogle(
                context = context,
                credentialManager = credentialManager,
                onSignOutComplete = {
                  currentUser = null
                  Toast.makeText(context, "Signed out successfully", Toast.LENGTH_SHORT).show()
                },
                scope = scope
              )
            },
            modifier = Modifier.testTag("chat_sign_out_button")
          ) {
            Icon(
              imageVector = Icons.Default.Logout,
              contentDescription = "Sign Out",
              tint = Color.White
            )
          }
        }
      },
      colors = TopAppBarDefaults.topAppBarColors(containerColor = KipsBluePrimary)
    )

    // User Session Banner
    Surface(
      color = Color.White,
      shadowElevation = 1.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        if (currentUser != null) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(Color(0xFF0284C7)),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = (currentUser?.displayName?.firstOrNull() ?: 'K').toString().uppercase(),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
              Text(
                text = currentUser?.displayName ?: "KIPS Student",
                fontSize = 12.5.sp,
                fontWeight = FontWeight.Bold,
                color = KipsBlueDark
              )
              Text(
                text = "Online • Ready to reply",
                fontSize = 10.sp,
                color = Color(0xFF16A34A)
              )
            }
          }
        } else {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Lock,
              contentDescription = null,
              tint = Color(0xFFE11D48),
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Sign in to post questions or reply to students",
              fontSize = 12.sp,
              color = Color(0xFF64748B)
            )
          }
        }
      }
    }

    // Main Content
    if (currentUser == null) {
      // Unauthenticated Welcome Card
      Column(
        modifier = Modifier
          .weight(1f)
          .fillMaxWidth()
          .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        Box(
          modifier = Modifier
            .size(72.dp)
            .clip(CircleShape)
            .background(KipsBlueContainer),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Forum,
            contentDescription = null,
            tint = KipsBluePrimary,
            modifier = Modifier.size(40.dp)
          )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = "KIPS College Student Discussion",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = KipsBlueDark
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
          text = "Ask questions, share test tips, and reply to your fellow classmates in real time.",
          fontSize = 13.sp,
          color = Color(0xFF64748B),
          textAlign = androidx.compose.ui.text.style.TextAlign.Center,
          modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
          onClick = {
            isSigningIn = true
            signInError = null
            onGoogleSignInClicked(
              context = context,
              credentialManager = credentialManager,
              onAuthSuccess = {
                isSigningIn = false
                currentUser = Firebase.auth.currentUser
                Toast.makeText(context, "Signed in as ${currentUser?.displayName}!", Toast.LENGTH_SHORT).show()
              },
              onAuthError = { err ->
                isSigningIn = false
                signInError = err
                Toast.makeText(context, err, Toast.LENGTH_LONG).show()
              },
              scope = scope,
              onAuthCancelled = { isSigningIn = false }
            )
          },
          enabled = !isSigningIn,
          colors = ButtonDefaults.buttonColors(containerColor = KipsBluePrimary),
          shape = RoundedCornerShape(12.dp),
          contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
          modifier = Modifier.testTag("google_sign_in_button")
        ) {
          if (isSigningIn) {
            CircularProgressIndicator(
              modifier = Modifier.size(18.dp),
              color = Color.White,
              strokeWidth = 2.dp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Connecting...", fontSize = 14.sp)
          } else {
            Text("Sign in with Google", fontSize = 14.sp, fontWeight = FontWeight.Bold)
          }
        }

        signInError?.let { err ->
          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = err,
            fontSize = 12.sp,
            color = Color(0xFFE11D48),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
          )
        }

        Spacer(modifier = Modifier.height(24.dp))
        DeveloperCreditFooter()
      }
    } else {
      // Authenticated Live Message List
      LazyColumn(
        state = listState,
        modifier = Modifier
          .weight(1f)
          .fillMaxWidth()
          .padding(horizontal = 14.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        if (messages.isEmpty()) {
          item {
            Card(
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(containerColor = Color.White),
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Text(
                  text = "No questions yet!",
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Bold,
                  color = KipsBlueDark
                )
                Text(
                  text = "Be the first student to ask a question about tomorrow's test below.",
                  fontSize = 12.sp,
                  color = Color(0xFF64748B),
                  textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
              }
            }
          }
        }

        items(messages) { msg ->
          val isMyMessage = msg.userId == currentUser?.uid
          CommunityMessageCard(
            message = msg,
            isMyMessage = isMyMessage,
            onReplyClick = {
              replyingToMessage = msg
            },
            onLikeClick = {
              scope.launch {
                try {
                  repository.likeMessage(msg.id)
                } catch (e: Exception) {
                  Toast.makeText(context, "Could not like", Toast.LENGTH_SHORT).show()
                }
              }
            },
            onDeleteClick = {
              scope.launch {
                try {
                  repository.deleteMessage(msg.id)
                  Toast.makeText(context, "Message deleted", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                  Toast.makeText(context, "Could not delete", Toast.LENGTH_SHORT).show()
                }
              }
            }
          )
        }

        item {
          Spacer(modifier = Modifier.height(8.dp))
          DeveloperCreditFooter()
        }
      }
    }

    // Active Replying Banner
    AnimatedVisibility(visible = replyingToMessage != null) {
      replyingToMessage?.let { parentMsg ->
        Surface(
          color = Color(0xFFE0F2FE),
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBAE6FD)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "Replying to ${parentMsg.userName}:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0369A1)
              )
              Text(
                text = parentMsg.text,
                fontSize = 11.5.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color(0xFF0F172A)
              )
            }
            IconButton(
              onClick = { replyingToMessage = null },
              modifier = Modifier.size(24.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Cancel reply",
                tint = Color(0xFF0369A1),
                modifier = Modifier.size(16.dp)
              )
            }
          }
        }
      }
    }

    // Input Bar (if user is authenticated)
    if (currentUser != null) {
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
            placeholder = {
              Text(
                text = if (replyingToMessage != null) "Write your reply..." else "Ask a question or share a test tip...",
                fontSize = 13.sp
              )
            },
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = KipsBluePrimary,
              unfocusedBorderColor = Color(0xFFCBD5E1)
            ),
            modifier = Modifier
              .weight(1f)
              .testTag("chat_input_text_field"),
            maxLines = 3
          )

          Spacer(modifier = Modifier.width(8.dp))

          Button(
            onClick = { handleSend() },
            enabled = inputText.isNotBlank() && !isSending,
            colors = ButtonDefaults.buttonColors(containerColor = KipsBluePrimary),
            shape = CircleShape,
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
              .size(44.dp)
              .testTag("chat_send_button")
          ) {
            if (isSending) {
              CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                color = Color.White,
                strokeWidth = 2.dp
              )
            } else {
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
  }
}

@Composable
fun CommunityMessageCard(
  message: CommunityMessage,
  isMyMessage: Boolean,
  onReplyClick: () -> Unit,
  onLikeClick: () -> Unit,
  onDeleteClick: () -> Unit
) {
  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (isMyMessage) Color(0xFFEFF6FF) else Color.White
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
    border = androidx.compose.foundation.BorderStroke(
      1.dp,
      if (isMyMessage) Color(0xFFBFDBFE) else Color(0xFFE2E8F0)
    ),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
    ) {
      // Header: Author Name & Badges
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(24.dp)
              .clip(CircleShape)
              .background(if (isMyMessage) KipsBluePrimary else Color(0xFF64748B)),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = message.userName.firstOrNull()?.toString()?.uppercase() ?: "K",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          }
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = message.userName,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = KipsBlueDark
          )
          if (isMyMessage) {
            Spacer(modifier = Modifier.width(6.dp))
            Surface(
              shape = RoundedCornerShape(4.dp),
              color = Color(0xFFDBEAFE)
            ) {
              Text(
                text = "You",
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1D4ED8),
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
              )
            }
          }
        }

        if (isMyMessage) {
          IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.size(24.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Delete,
              contentDescription = "Delete",
              tint = Color(0xFF94A3B8),
              modifier = Modifier.size(15.dp)
            )
          }
        }
      }

      // If this message is a reply to another message, show quotation snippet
      if (!message.replyToMessageId.isNullOrBlank() && !message.replyToText.isNullOrBlank()) {
        Spacer(modifier = Modifier.height(6.dp))
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = Color(0xFFF8FAFC),
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .width(3.dp)
                .height(24.dp)
                .background(KipsBluePrimary)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
              Text(
                text = "Replying to ${message.replyToAuthor ?: "Student"}:",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = KipsBluePrimary
              )
              Text(
                text = message.replyToText,
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color(0xFF475569)
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Main Message Text
      Text(
        text = message.text,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        color = Color(0xFF0F172A)
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Footer: Actions (Reply & Like)
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Like Button
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable { onLikeClick() }
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Icon(
            imageVector = Icons.Default.ThumbUp,
            contentDescription = "Like",
            tint = if (message.likesCount > 0) Color(0xFF2563EB) else Color(0xFF64748B),
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = if (message.likesCount > 0) "${message.likesCount}" else "Helpful",
            fontSize = 11.sp,
            color = if (message.likesCount > 0) Color(0xFF2563EB) else Color(0xFF64748B),
            fontWeight = FontWeight.SemiBold
          )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Reply Button
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable { onReplyClick() }
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.Reply,
            contentDescription = "Reply",
            tint = KipsBluePrimary,
            modifier = Modifier.size(15.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "Reply",
            fontSize = 11.sp,
            color = KipsBluePrimary,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }
  }
}
