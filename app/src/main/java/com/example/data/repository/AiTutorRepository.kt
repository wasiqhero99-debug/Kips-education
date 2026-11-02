package com.example.data.repository

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.content
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout

private const val TAG = "AiTutorRepository"

sealed interface AiChatState {
  data object Idle : AiChatState
  data object Loading : AiChatState
  data class Streaming(val fullText: String) : AiChatState
  data class Success(val fullText: String) : AiChatState
  data class Error(val message: String) : AiChatState
}

data class ChatMessage(
  val isUser: Boolean,
  val text: String,
  val timestamp: Long = System.currentTimeMillis()
)

class AiTutorRepository {

  private val systemPrompt = """
    You are an expert, encouraging English Professor and Exam Tutor for KIPS College and Punjab Board (BISE Lahore, Gujranwala, Rawalpindi, Faisalabad, Multan) Intermediate Part-I (1st Year).
    You are specifically helping students prepare for tomorrow's English syllabus:
    1. Unit-4 "Team Moon" (Apollo 11 lunar landing by Catherine Thimmesh) - full explanations, summaries, Urdu translations, vocabulary, and textbook Q&A.
    2. Correct Forms of Verbs - Tense formulas (daily habits, now, already, yesterday/ago, since/for, conditional if-clauses, before/after).
    3. Pair of Words 41-60 (Devise:Device, Die:Dye, Economic:Economical, Elect:Select, Elicit:Illicit, Eminent:Imminent, Entomology:Etymology, Especially:Specially, Everyday:Every day, Explicit:Implicit, Farther:Further, Fewer:Less, Formerly:Formally, Fourth:Forth, Heard:Herd, Healthful:Healthy, Heroine:Heroin, Horde:Hoard, Hour:Our, Illusion:Allusion).
    4. Stories: 5. A Foolish Stag, 6. The King and the Spider (with proverbs and morals).
    5. Applications: 5. Refund of Library/Hostel Security, 6. Urgent Piece of Work (Strict Punjab Board pattern).
    6. Letters: 5. Younger Brother advising him to avoid bad company, 6. Friend to borrow books (Strict Punjab Board pattern: Examination Hall, City A.B.C., Yours affectionately / Yours sincerely, X.Y.Z.).

    Guidelines:
    - Answer questions accurately, clearly, and concisely.
    - Provide both English and Urdu explanations where helpful.
    - For letters and applications, emphasize the exact Punjab Board punctuation rules.
    - Be warm, encouraging, and highly educational for Pakistani FSC/ICS students.
  """.trimIndent()

  private val model by lazy {
    Firebase.ai.generativeModel(
      modelName = "gemini-3.8-flash",
      systemInstruction = content { text(systemPrompt) }
    )
  }

  fun askQuestionStream(question: String): Flow<AiChatState> = flow {
    emit(AiChatState.Loading)
    val accumulated = StringBuilder()
    try {
      withTimeout(60_000L) {
        model.generateContentStream(question).collect { chunk ->
          val chunkText = chunk.text.orEmpty()
          accumulated.append(chunkText)
          emit(AiChatState.Streaming(accumulated.toString()))
        }
      }
      if (accumulated.isNotBlank()) {
        emit(AiChatState.Success(accumulated.toString()))
      } else {
        emit(AiChatState.Error("Gemini AI returned an empty response. Please try again."))
      }
    } catch (e: TimeoutCancellationException) {
      Log.e(TAG, "AI question stream timed out", e)
      emit(AiChatState.Error("Response timed out after 60 seconds. Please check your internet connection."))
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Log.e(TAG, "AI question failed", e)
      emit(AiChatState.Error(e.localizedMessage ?: "Failed to reach Gemini AI."))
    }
  }
}
