package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Verified
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.KipsBlueContainer
import com.example.ui.theme.KipsBlueDark
import com.example.ui.theme.KipsBluePrimary
import com.example.ui.theme.KipsBlueSecondary
import com.example.ui.theme.KipsGold

@Composable
fun DeveloperCreditFooter(
  modifier: Modifier = Modifier,
  compact: Boolean = false
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .testTag("developer_credit_footer"),
    shape = RoundedCornerShape(16.dp),
    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
    border = androidx.compose.foundation.BorderStroke(
      1.dp,
      Brush.horizontalGradient(
        listOf(
          KipsBluePrimary.copy(alpha = 0.4f),
          KipsBlueSecondary.copy(alpha = 0.2f),
          KipsBluePrimary.copy(alpha = 0.4f)
        )
      )
    )
  ) {
    if (compact) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 14.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.Code,
          contentDescription = "Code",
          tint = KipsBluePrimary,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Made by Developer Wasiq",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          color = KipsBlueDark
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
          imageVector = Icons.Default.Verified,
          contentDescription = "Verified",
          tint = KipsBlueSecondary,
          modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "• For KIPS College Students",
          fontSize = 11.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    } else {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(CircleShape)
              .background(Brush.linearGradient(listOf(KipsBluePrimary, KipsBlueSecondary))),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Code,
              contentDescription = "Developer",
              tint = Color.White,
              modifier = Modifier.size(18.dp)
            )
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "Made by Developer Wasiq",
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                color = KipsBlueDark
              )
              Spacer(modifier = Modifier.width(4.dp))
              Icon(
                imageVector = Icons.Default.Verified,
                contentDescription = "Verified Creator",
                tint = KipsBlueSecondary,
                modifier = Modifier.size(16.dp)
              )
            }
            Text(
              text = "Dedicated to KIPS Students • English Test Prep Portal",
              fontSize = 11.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = "“Best of luck to all college fellows for tomorrow's test! Revise thoroughly.”",
          fontSize = 11.sp,
          fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
          color = KipsBluePrimary,
          textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
      }
    }
  }
}
