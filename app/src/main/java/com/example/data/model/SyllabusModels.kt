package com.example.data.model

enum class StudyMode(val label: String, val urduLabel: String) {
  ORDINARY("Ordinary (Simple & Easy)", "سادہ اور آسان"),
  EXTRAORDINARY("Extraordinary (Topper Level)", "اعلیٰ اور غیر معمولی")
}

data class PairOfWordsItem(
  val id: Int,
  val wordA: String,
  val urduA: String,
  val defA: String,
  val simpleSentenceA: String,
  val sentenceA: String,
  val wordB: String,
  val urduB: String,
  val defB: String,
  val simpleSentenceB: String,
  val sentenceB: String,
  val examNote: String = ""
)

data class StoryItem(
  val number: Int,
  val title: String,
  val urduTitle: String,
  val subtitle: String,
  val moralQuotes: List<String>,
  val simpleParagraphs: List<String>,
  val paragraphs: List<String>,
  val vocabularyNotes: List<Pair<String, String>>,
  val examTips: String
)

data class ApplicationItem(
  val number: Int,
  val title: String,
  val urduTitle: String,
  val subject: String,
  val recipientDesignation: String = "The Principal,",
  val institution: String = "Govt. College,",
  val alternateInstitution: String = "KIPS College,",
  val city: String = "City A.B.C.",
  val toAddress: String,
  val salutation: String,
  val simpleBodyParagraphs: List<String>,
  val bodyParagraphs: List<String>,
  val subscription: String,
  val studentSignature: String,
  val rollNoLine: String = "Roll No. 12489",
  val classLine: String = "Class: 1st Year (Intermediate Part-I)",
  val date: String,
  val marksBreakdown: List<String> = emptyList(),
  val formatTips: List<String>
)

data class LetterItem(
  val number: Int,
  val title: String,
  val urduTitle: String,
  val hallLine: String = "Examination Hall,",
  val cityLine: String = "City A.B.C.",
  val addressLine: String,
  val date: String,
  val salutation: String,
  val simpleBodyParagraphs: List<String>,
  val bodyParagraphs: List<String>,
  val subscription: String,
  val senderName: String = "X.Y.Z.",
  val marksBreakdown: List<String> = emptyList(),
  val keyGuidelines: List<String>
)

data class VerbRule(
  val ruleTitle: String,
  val urduSummary: String,
  val triggerWords: String,
  val formula: String,
  val simpleFormula: String,
  val examples: List<Pair<String, String>>
)

data class VerbQuestion(
  val id: Int,
  val sentence: String,
  val options: List<String>,
  val correctIndex: Int,
  val explanation: String
)

data class ChapterParagraph(
  val number: Int,
  val title: String,
  val englishText: String,
  val urduTranslation: String,
  val simpleEnglishSummary: String = "",
  val simpleUrduSummary: String = ""
)

data class UnitQuestionAnswer(
  val questionNumber: Int,
  val question: String,
  val simpleAnswer: String,
  val answer: String,
  val urduMeaning: String = ""
)

data class UnitVocab(
  val word: String,
  val urduMeaning: String,
  val englishMeaning: String,
  val sampleSentence: String
)

data class UnitFourData(
  val title: String,
  val urduTitle: String,
  val author: String,
  val overview: String,
  val simpleOverview: String,
  val keyTheme: String,
  val paragraphs: List<ChapterParagraph>,
  val questionsAnswers: List<UnitQuestionAnswer>,
  val vocabulary: List<UnitVocab>,
  val grammarRules: List<String>,
  val sampleMcqs: List<VerbQuestion>
)
