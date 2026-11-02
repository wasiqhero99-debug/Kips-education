package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.StudyMode
import com.example.ui.theme.KipsBlueContainer
import com.example.ui.theme.KipsBlueDark
import com.example.ui.theme.KipsBluePrimary
import com.example.ui.theme.KipsBlueSecondary
import com.example.ui.theme.KipsGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader(
  title: String = "KIPS College Test Prep",
  subtitle: String? = "English Syllabus Solutions",
  showBack: Boolean = false,
  onBackClick: () -> Unit = {},
  onViewSyllabusClick: (() -> Unit)? = null,
  studyMode: StudyMode = StudyMode.ORDINARY,
  onToggleStudyMode: ((StudyMode) -> Unit)? = null,
  onAiClick: (() -> Unit)? = null,
  onChatClick: (() -> Unit)? = null
) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .shadow(elevation = 4.dp),
    color = Color.White
  ) {
    Column(modifier = Modifier.fillMaxWidth()) {

      // TOP BAR: Made by Developer Wasiq (Always visible at the very top of every page)
      Surface(
        modifier = Modifier.fillMaxWidth(),
        color = KipsBlueDark
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 7.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(KipsBlueSecondary),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Code,
                contentDescription = "Code",
                tint = Color.White,
                modifier = Modifier.size(13.dp)
              )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Made by Developer Wasiq",
              fontSize = 12.5.sp,
              fontWeight = FontWeight.Black,
              letterSpacing = 0.3.sp,
              color = Color.White
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
              imageVector = Icons.Default.Verified,
              contentDescription = "Verified Creator",
              tint = Color(0xFF38BDF8),
              modifier = Modifier.size(15.dp)
            )
          }

          Text(
            text = "KIPS Student Portal",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFBAE6FD)
          )
        }
      }

      // Main College Header Row
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f)
        ) {
          if (showBack) {
            IconButton(
              onClick = onBackClick,
              modifier = Modifier
                .padding(end = 4.dp)
                .testTag("header_back_button")
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = KipsBluePrimary
              )
            }
          }

          // College Emblem Logo
          Surface(
            modifier = Modifier
              .size(46.dp)
              .testTag("college_logo_container"),
            shape = RoundedCornerShape(10.dp),
            color = Color.White,
            shadowElevation = 2.dp,
            border = androidx.compose.foundation.BorderStroke(1.dp, KipsBlueContainer)
          ) {
            Image(
              painter = painterResource(id = R.drawable.kips_logo),
              contentDescription = "KIPS Education System Logo",
              modifier = Modifier.padding(2.dp),
              contentScale = ContentScale.Fit
            )
          }

          Spacer(modifier = Modifier.width(10.dp))

          Column {
            Text(
              text = "KIPS EDUCATION SYSTEM",
              fontSize = 13.5.sp,
              fontWeight = FontWeight.Black,
              letterSpacing = 0.5.sp,
              color = KipsBluePrimary
            )
            Text(
              text = subtitle ?: "★ Since 1992 • College Campus",
              fontSize = 11.sp,
              fontWeight = FontWeight.Medium,
              color = KipsBlueDark.copy(alpha = 0.75f)
            )
          }
        }

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          if (onAiClick != null) {
            Surface(
              onClick = onAiClick,
              shape = RoundedCornerShape(16.dp),
              color = Color(0xFFF3E8FF),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDDD6FE)),
              modifier = Modifier.testTag("header_ai_button")
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.AutoAwesome,
                  contentDescription = "Ask AI",
                  tint = Color(0xFF7C3AED),
                  modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                  text = "AI",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF7C3AED)
                )
              }
            }
          }

          if (onChatClick != null) {
            Surface(
              onClick = onChatClick,
              shape = RoundedCornerShape(16.dp),
              color = KipsBlueContainer,
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBAE6FD)),
              modifier = Modifier.testTag("header_chat_button")
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.Forum,
                  contentDescription = "Student Chat",
                  tint = KipsBluePrimary,
                  modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                  text = "Chat",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = KipsBluePrimary
                )
              }
            }
          }

          if (onViewSyllabusClick != null) {
            Surface(
              onClick = onViewSyllabusClick,
              shape = RoundedCornerShape(16.dp),
              color = Color(0xFFF1F5F9),
              modifier = Modifier.testTag("view_syllabus_sheet_button")
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.Description,
                  contentDescription = "Syllabus Sheet",
                  tint = KipsBlueDark,
                  modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                  text = "Sheet",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = KipsBlueDark
                )
              }
            }
          }
        }
      }

      // Dual Mode Switcher Bar (Ordinary Simple & Easy ↔ Extraordinary Topper)
      if (onToggleStudyMode != null) {
        Surface(
          color = Color(0xFFF1F5F9),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            // Option 1: Ordinary (Simple & Easy)
            val isOrd = studyMode == StudyMode.ORDINARY
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = if (isOrd) Color(0xFF0284C7) else Color.White,
              border = androidx.compose.foundation.BorderStroke(
                1.dp,
                if (isOrd) Color(0xFF0284C7) else Color(0xFFCBD5E1)
              ),
              modifier = Modifier
                .weight(1f)
                .clickable { onToggleStudyMode(StudyMode.ORDINARY) }
                .testTag("mode_toggle_ordinary")
            ) {
              Row(
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.MenuBook,
                  contentDescription = null,
                  tint = if (isOrd) Color.White else Color(0xFF475569),
                  modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                  Text(
                    text = "Simple & Easy",
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isOrd) Color.White else Color(0xFF1E293B)
                  )
                  Text(
                    text = "آسان الفاظ (Ordinary)",
                    fontSize = 9.sp,
                    color = if (isOrd) Color.White.copy(alpha = 0.9f) else Color(0xFF64748B)
                  )
                }
              }
            }

            // Option 2: Extraordinary (Topper Level)
            val isExt = studyMode == StudyMode.EXTRAORDINARY
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = if (isExt) Color(0xFF7C3AED) else Color.White,
              border = androidx.compose.foundation.BorderStroke(
                1.dp,
                if (isExt) Color(0xFF7C3AED) else Color(0xFFCBD5E1)
              ),
              modifier = Modifier
                .weight(1f)
                .clickable { onToggleStudyMode(StudyMode.EXTRAORDINARY) }
                .testTag("mode_toggle_extraordinary")
            ) {
              Row(
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.Star,
                  contentDescription = null,
                  tint = if (isExt) Color(0xFFFDE047) else Color(0xFF9333EA),
                  modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                  Text(
                    text = "Extraordinary",
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isExt) Color.White else Color(0xFF1E293B)
                  )
                  Text(
                    text = "ٹاپرز کے لیے (Topper)",
                    fontSize = 9.sp,
                    color = if (isExt) Color.White.copy(alpha = 0.9f) else Color(0xFF64748B)
                  )
                }
              }
            }
          }
        }
      }

      // Title bar for the active screen
      if (showBack) {
        Surface(
          color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = title,
              fontSize = 13.5.sp,
              fontWeight = FontWeight.Bold,
              color = KipsBluePrimary
            )
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = if (studyMode == StudyMode.ORDINARY) Color(0xFFE0F2FE) else Color(0xFFEDE9FE)
            ) {
              Text(
                text = if (studyMode == StudyMode.ORDINARY) "Mode: Simple" else "Mode: Topper",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = if (studyMode == StudyMode.ORDINARY) Color(0xFF0369A1) else Color(0xFF6D28D9),
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
          }
        }
      }
    }
  }
}
