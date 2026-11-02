package com.example.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp

@IgnoreExtraProperties
data class CommunityMessage(
  @DocumentId val id: String = "",
  val userId: String = "",
  val userName: String = "",
  val text: String = "",
  val replyToMessageId: String? = null,
  val replyToAuthor: String? = null,
  val replyToText: String? = null,
  val likesCount: Long = 0L,
  @ServerTimestamp val createdAt: Timestamp? = null,
  @ServerTimestamp val updatedAt: Timestamp? = null
)
