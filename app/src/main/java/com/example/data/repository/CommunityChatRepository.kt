package com.example.data.repository

import android.content.Context
import com.example.R
import com.example.data.model.CommunityMessage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class CommunityChatRepository(private val db: FirebaseFirestore) {

  constructor(context: Context) : this(
    FirebaseFirestore.getInstance(
      context.applicationContext.getString(R.string.firestore_database_id)
    )
  )

  private val auth = Firebase.auth
  private val collectionPath = "community_messages"

  private fun requireUserId(): String {
    return auth.currentUser?.uid
      ?: throw IllegalStateException("User must be signed in with Google before accessing chat.")
  }

  fun observeMessages(): Flow<List<CommunityMessage>> = flow {
    val query = db.collection(collectionPath)
      .orderBy("createdAt", Query.Direction.ASCENDING)
      .limitToLast(100)

    emitAll(
      query.snapshots()
        .map { snapshot -> snapshot.toObjects(CommunityMessage::class.java) }
        .catch { error ->
          if (error is Exception) {
            handleFirestoreError(error, OperationType.LIST, collectionPath)
          }
          throw error
        }
    )
  }

  suspend fun sendMessage(
    text: String,
    replyToMessageId: String? = null,
    replyToAuthor: String? = null,
    replyToText: String? = null
  ) {
    val uid = requireUserId()
    val user = auth.currentUser
    val userName = user?.displayName?.ifBlank { null }
      ?: user?.email?.substringBefore("@")
      ?: "KIPS Student"

    val docRef = db.collection(collectionPath).document()
    val messageId = docRef.id

    val payload = mutableMapOf<String, Any>(
      "id" to messageId,
      "userId" to uid,
      "userName" to userName,
      "text" to text.trim(),
      "likesCount" to 0L,
      "createdAt" to FieldValue.serverTimestamp(),
      "updatedAt" to FieldValue.serverTimestamp()
    )

    if (!replyToMessageId.isNullOrBlank()) {
      payload["replyToMessageId"] = replyToMessageId
    }
    if (!replyToAuthor.isNullOrBlank()) {
      payload["replyToAuthor"] = replyToAuthor
    }
    if (!replyToText.isNullOrBlank()) {
      payload["replyToText"] = replyToText.take(150)
    }

    try {
      docRef.set(payload).await()
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.CREATE, docRef.path)
      throw e
    }
  }

  suspend fun likeMessage(messageId: String) {
    requireUserId()
    val docRef = db.collection(collectionPath).document(messageId)
    try {
      docRef.update("likesCount", FieldValue.increment(1)).await()
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.UPDATE, docRef.path)
      throw e
    }
  }

  suspend fun deleteMessage(messageId: String) {
    requireUserId()
    val docRef = db.collection(collectionPath).document(messageId)
    try {
      docRef.delete().await()
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.DELETE, docRef.path)
      throw e
    }
  }
}
