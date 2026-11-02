package com.example.data.repository

import com.example.data.model.ApplicationItem
import com.example.data.model.ChapterParagraph
import com.example.data.model.LetterItem
import com.example.data.model.PairOfWordsItem
import com.example.data.model.StoryItem
import com.example.data.model.UnitFourData
import com.example.data.model.UnitQuestionAnswer
import com.example.data.model.UnitVocab
import com.example.data.model.VerbQuestion
import com.example.data.model.VerbRule

object SyllabusRepository {

  // Pair of Words 41 to 60 with both Simple (Ordinary) and Advanced (Extraordinary) sentences
  val pairOfWords: List<PairOfWordsItem> = listOf(
    PairOfWordsItem(
      id = 41,
      wordA = "Devise",
      urduA = "تدبیر کرنا، منصوبہ بنانا",
      defA = "Verb: To invent or plan something",
      simpleSentenceA = "We must devise an easy plan to win the match.",
      sentenceA = "The engineers managed to devise a cost-effective solar generator for rural areas.",
      wordB = "Device",
      urduB = "آلہ، سامان",
      defB = "Noun: An instrument or gadget",
      simpleSentenceB = "A smartphone is a useful modern device.",
      sentenceB = "An advanced safety device was installed inside the nuclear reactor.",
      examNote = "Devise with 's' is an action verb; Device with 'c' is an object noun."
    ),
    PairOfWordsItem(
      id = 42,
      wordA = "Die",
      urduA = "مرنا، وفات پانا",
      defA = "Verb: To stop living",
      simpleSentenceA = "All living beings must die one day.",
      sentenceA = "True patriots are always ready to die for the defense of their homeland.",
      wordB = "Dye",
      urduB = "رنگنا (کپڑا یا بال)",
      defB = "Verb/Noun: To change the color of something",
      simpleSentenceB = "She decided to dye her white scarf red.",
      sentenceB = "The textile factory uses natural organic dye to colour pure silk garments.",
      examNote = "Die = Death; Dye = Coloring fabric with chemical stains."
    ),
    PairOfWordsItem(
      id = 43,
      wordA = "Economic",
      urduA = "معاشی، مالیاتی نظام",
      defA = "Adjective: Related to trade, money, and economy",
      simpleSentenceA = "The government is improving the economic condition of the country.",
      sentenceA = "Pakistan has initiated comprehensive economic reforms to curb inflation.",
      wordB = "Economical",
      urduB = "کفایت شعار، کم خرچ",
      defB = "Adjective: Spending money carefully, saving cost",
      simpleSentenceB = "My mother is very economical and saves money.",
      sentenceB = "Hybrid and electric vehicles are exceptionally economical on fuel consumption.",
      examNote = "Economic = Nation's financial system; Economical = Thrifty, avoiding waste."
    ),
    PairOfWordsItem(
      id = 44,
      wordA = "Elect",
      urduA = "منتخب کرنا (بذریعہ ووٹ)",
      defA = "Verb: To choose someone by voting",
      simpleSentenceA = "We will elect our class monitor tomorrow.",
      sentenceA = "The citizens gathered at polling stations to elect their parliamentary representatives.",
      wordB = "Select",
      urduB = "چننا، انتخاب کرنا",
      defB = "Verb: To pick out the best from many",
      simpleSentenceB = "Please select the best book from the shelf.",
      sentenceB = "The panel took hours to select the most outstanding research paper for publication.",
      examNote = "Elect involves voting; Select means carefully picking from choices."
    ),
    PairOfWordsItem(
      id = 45,
      wordA = "Elicit",
      urduA = "نکالنا، سچ اگلوانا",
      defA = "Verb: To draw out information or facts",
      simpleSentenceA = "The teacher tried to elicit the correct answer from Ali.",
      sentenceA = "The prosecutor’s astute cross-examination helped elicit the unvarnished truth from the witness.",
      wordB = "Illicit",
      urduB = "ناجائز، غیر قانونی",
      defB = "Adjective: Forbidden by law or morality",
      simpleSentenceB = "Smuggling goods is an illicit trade.",
      sentenceB = "Custom authorities confiscated a massive consignment of illicit narcotics at the port.",
      examNote = "Elicit starts with 'E' for extract; Illicit starts with 'Il' for illegal."
    ),
    PairOfWordsItem(
      id = 46,
      wordA = "Eminent",
      urduA = "مشہور، نمایاں شخصیت",
      defA = "Adjective: Famous, respected, and distinguished",
      simpleSentenceA = "Dr. Abdul Qadeer Khan was an eminent scientist.",
      sentenceA = "An eminent professor of literature delivered the convocation keynote address.",
      wordB = "Imminent",
      urduB = "قریب الوقوع، جو فوراً ہونے والا ہو",
      defB = "Adjective: About to happen immediately",
      simpleSentenceB = "Dark clouds show that heavy rain is imminent.",
      sentenceB = "Seeing the escalating tempest, the coast guards warned of an imminent maritime disaster.",
      examNote = "Eminent = Famous leader; Imminent = Immediate danger or event."
    ),
    PairOfWordsItem(
      id = 47,
      wordA = "Entomology",
      urduA = "کیڑے مکوڑوں کا علم",
      defA = "Noun: The scientific study of insects",
      simpleSentenceA = "He is studying entomology to learn about insects.",
      sentenceA = "Agricultural scientists specializing in entomology are investigating crop-destroying locust swarms.",
      wordB = "Etymology",
      urduB = "الفاظ کی اصل اور تاریخ کا علم",
      defB = "Noun: The study of word origins and history",
      simpleSentenceB = "Consult a dictionary to know the etymology of words.",
      sentenceB = "A deep grasp of Latin etymology enhances one's understanding of English vocabulary.",
      examNote = "Entomology = Insects; Etymology = Word origins and linguistic roots."
    ),
    PairOfWordsItem(
      id = 48,
      wordA = "Especially",
      urduA = "خاص طور پر",
      defA = "Adverb: Particularly, in an outstanding way",
      simpleSentenceA = "I like seasonal fruits, especially sweet mangoes.",
      sentenceA = "He excels in all academic subjects, especially theoretical physics and mathematics.",
      wordB = "Specially",
      urduB = "خاص مقصد کے لیے تیار کردہ",
      defB = "Adverb: For a specific customized purpose",
      simpleSentenceB = "This test paper was specially prepared for our class.",
      sentenceB = "This digital portal was specially crafted by Developer Wasiq for KIPS students.",
      examNote = "Especially = In particular; Specially = Tailored for a distinct purpose."
    ),
    PairOfWordsItem(
      id = 49,
      wordA = "Everyday",
      urduA = "روزمرہ کا، معمول کا",
      defA = "Adjective: Ordinary, routine (placed before a noun)",
      simpleSentenceA = "Checking WhatsApp has become our everyday routine.",
      sentenceA = "Patience and courtesy should be evident in your everyday conversational habits.",
      wordB = "Every day",
      urduB = "ہر روز، روزانہ",
      defB = "Adverbial phrase: Each day without missing (two words)",
      simpleSentenceB = "I go to college every day by bus.",
      sentenceB = "Disciplined intermediate scholars revise their lecture summaries diligently every day.",
      examNote = "Everyday (one word) is an adjective; Every day (two words) shows how often."
    ),
    PairOfWordsItem(
      id = 50,
      wordA = "Explicit",
      urduA = "صاف، واضح، دو ٹوک",
      defA = "Adjective: Clear and fully stated",
      simpleSentenceA = "The teacher gave explicit rules for the test.",
      sentenceA = "The board issued explicit guidelines prohibiting mobile devices inside examination centers.",
      wordB = "Implicit",
      urduB = "پوشیدہ، اندرونی، اشارتاً",
      defB = "Adjective: Implied without saying directly",
      simpleSentenceB = "We have implicit faith in our parents.",
      sentenceB = "Although he spoke with diplomatic courtesy, there was an implicit critique of negligence.",
      examNote = "Explicit = Openly expressed; Implicit = Implied beneath the surface."
    ),
    PairOfWordsItem(
      id = 51,
      wordA = "Farther",
      urduA = "زیادہ دور (جسمانی فاصلہ)",
      defA = "Adjective/Adverb: Greater physical measurable distance",
      simpleSentenceA = "My college is farther away than my school.",
      sentenceA = "The ancient fortress is situated much farther down the mountain ridge than we estimated.",
      wordB = "Further",
      urduB = "مزید، آگے کی تفصیل",
      defB = "Adjective/Adverb: Additional, furthermore, to a deeper degree",
      simpleSentenceB = "Do you have any further questions?",
      sentenceB = "The principal requested further investigation into the student council election discrepancies.",
      examNote = "Farther = Physical miles; Further = Additional details or degree."
    ),
    PairOfWordsItem(
      id = 52,
      wordA = "Fewer",
      urduA = "کم تعداد (گننے والی چیزوں کے لیے)",
      defA = "Determiner: Used with plural countable nouns",
      simpleSentenceA = "Fewer students were absent today.",
      sentenceA = "The candidate committed fewer grammatical mistakes in his English composition essay.",
      wordB = "Less",
      urduB = "کم مقدار (ناقابل شمار اشیاء کے لیے)",
      defB = "Determiner: Used with singular uncountable quantities",
      simpleSentenceB = "Please put less sugar in my tea.",
      sentenceB = "Conscientious students spend less time on recreational browsing during final exams.",
      examNote = "Fewer books, errors, days (countable); Less time, water, anxiety (uncountable)."
    ),
    PairOfWordsItem(
      id = 53,
      wordA = "Formerly",
      urduA = "پہلے زمانے میں، ماضی میں",
      defA = "Adverb: In the past, previously",
      simpleSentenceA = "He was formerly our English teacher.",
      sentenceA = "This historic colonial bungalow was formerly used as a provincial administrative office.",
      wordB = "Formally",
      urduB = "باضابطہ، رسمی طریقے سے",
      defB = "Adverb: Officially, in accordance with etiquette",
      simpleSentenceB = "He was formally welcomed by the principal.",
      sentenceB = "The college dean formally conferred honors upon the top position holders in the hall.",
      examNote = "Formerly relates to time past; Formally relates to formal ceremonial manners."
    ),
    PairOfWordsItem(
      id = 54,
      wordA = "Fourth",
      urduA = "چوتھا (ترتیب میں نمبر 4)",
      defA = "Ordinal number: Number four in series",
      simpleSentenceA = "He came fourth in the class race.",
      sentenceA = "Unit 4 is the fourth chapter prescribed in our terminal English test syllabus.",
      wordB = "Forth",
      urduB = "آگے، سامنے کی طرف",
      defB = "Adverb: Forward, out into sight",
      simpleSentenceB = "The soldier came forth bravely.",
      sentenceB = "The valiant commander stepped forth to lead his troops into the decisive battle.",
      examNote = "Fourth has 'u' like the number four; Forth means marching onward."
    ),
    PairOfWordsItem(
      id = 55,
      wordA = "Heard",
      urduA = "سنا (Hear کی ماضی حالت)",
      defA = "Verb: Past of hear (perceived sounds)",
      simpleSentenceA = "I heard the college bell ringing.",
      sentenceA = "We heard the thunderous cheers echoing across the campus following the victory.",
      wordB = "Herd",
      urduB = "ریوڑ، مویشیوں کا غول",
      defB = "Noun: A group of cattle or grazing animals",
      simpleSentenceB = "A herd of cows was grazing in the green field.",
      sentenceB = "The nomadic shepherd safely guided his extensive herd of sheep through the steep pass.",
      examNote = "Heard uses your ear (h-ear-d); Herd is a flock of farm animals."
    ),
    PairOfWordsItem(
      id = 56,
      wordA = "Healthful",
      urduA = "صحت بخش (غذا یا عادت)",
      defA = "Adjective: Good for health, wholesome",
      simpleSentenceA = "Fresh milk and clean water are healthful for boys.",
      sentenceA = "Eating green vegetables and exercising regularly promote a healthful lifestyle.",
      wordB = "Healthy",
      urduB = "تندرست، صحت مند انسان",
      defB = "Adjective: In sound physical condition, free from disease",
      simpleSentenceB = "A morning walk keeps him fit and healthy.",
      sentenceB = "The athlete maintains a sound and healthy physique through disciplined physical training.",
      examNote = "Healthful foods and climate make a person healthy."
    ),
    PairOfWordsItem(
      id = 57,
      wordA = "Heroine",
      urduA = "بہادر عورت، ہیروئن",
      defA = "Noun: A brave courageous woman",
      simpleSentenceA = "Fatima Jinnah was a great national heroine.",
      sentenceA = "Mohtarma Fatima Jinnah is revered as a selfless national heroine of the Pakistan movement.",
      wordB = "Heroin",
      urduB = "نشہ آور جان لیوا دوا",
      defB = "Noun: A dangerous illegal narcotic drug",
      simpleSentenceB = "Heroin destroys human life and health.",
      sentenceB = "Law enforcement agencies took strict measures to eradicate the illicit trafficking of heroin.",
      examNote = "Heroine with 'e' at end is a brave lady; Heroin without 'e' is a lethal narcotic."
    ),
    PairOfWordsItem(
      id = 58,
      wordA = "Horde",
      urduA = "لشکر، حملہ آور ہجوم",
      defA = "Noun: A large destructive crowd or swarm",
      simpleSentenceA = "A horde of locusts attacked the crops.",
      sentenceA = "A horde of savage barbarian invaders besieged the frontiers of the ancient Roman empire.",
      wordB = "Hoard",
      urduB = "ذخیرہ اندوزی کرنا، مال چھپانا",
      defB = "Verb: To store money or goods greedily in secret",
      simpleSentenceB = "Do not hoard sugar and wheat to make quick profit.",
      sentenceB = "Unscrupulous profiteers should not hoard essential grains to cause artificial market inflation.",
      examNote = "Horde = Big unruly crowd; Hoard = Stashing supplies greedily."
    ),
    PairOfWordsItem(
      id = 59,
      wordA = "Hour",
      urduA = "گھنٹہ (60 منٹ کا وقت)",
      defA = "Noun: Sixty minutes period",
      simpleSentenceA = "The English paper is of two hours duration.",
      sentenceA = "The final intermediate board paper has an allocated examination duration of two hours.",
      wordB = "Our",
      urduB = "ہمارا، ہماری",
      defB = "Pronoun: Belonging to us",
      simpleSentenceB = "We love our college and teachers.",
      sentenceB = "We take immense collective pride in our prestigious academic traditions at KIPS College.",
      examNote = "Hour has silent 'H' for clock time; Our is the pronoun meaning belonging to us."
    ),
    PairOfWordsItem(
      id = 60,
      wordA = "Illusion",
      urduA = "نظر کا دھوکہ، سراب",
      defA = "Noun: A false impression or deceptive appearance",
      simpleSentenceA = "A mirage in the desert is an optical illusion.",
      sentenceA = "The perceived pool of water on blistering desert highways is merely an optical illusion.",
      wordB = "Allusion",
      urduB = "اشارہ، ادبی یا تاریخی حوالہ",
      defB = "Noun: An indirect literary or historical reference",
      simpleSentenceB = "The poem contains an allusion to Hazrat Ali (R.A).",
      sentenceB = "The orator’s eloquent discourse was enriched with classical allusions to Islamic golden history.",
      examNote = "Illusion = Magic / visual mirage; Allusion = Literary quote / historical reference."
    )
  )

  // Stories with both Ordinary (Simple & Easy) and Extraordinary (Topper) versions
  val stories: List<StoryItem> = listOf(
    StoryItem(
      number = 5,
      title = "A Foolish Stag",
      urduTitle = "احمق بارہ سنگھا",
      subtitle = "Vanity and true utility of nature's gifts",
      moralQuotes = listOf(
        "All that glitters is not gold.",
        "Pride hath a fall.",
        "Never despise the gifts of nature."
      ),
      // Ordinary (Simple & Easy to memorize in 5 mins)
      simpleParagraphs = listOf(
        "Once a beautiful stag was drinking water at a clear stream in a forest.",
        "He saw his reflection in the water. He was very proud of his fine, curving horns. He thought, 'How beautiful and royal my horns look! Every animal must envy me.'",
        "Then he looked at his thin, ugly legs. He felt sad and despised them. He said, 'Nature has been cruel to me. These spindly legs look so shameful beneath my lovely horns.'",
        "Suddenly, a pack of fierce hunting hounds attacked him. The stag ran for his life. His despised thin legs carried him like the wind and took him far away from danger.",
        "Soon, the stag ran into thick bushes. His large branch-like horns were caught in the branches. He tried hard to free himself, but in vain. The hounds reached him and tore him to pieces.",
        "While dying, the stag realized his folly: 'The ugly legs I despised saved my life, but the beautiful horns I was proud of became the cause of my death.'"
      ),
      // Extraordinary (Topper Level Distinction)
      paragraphs = listOf(
        "Once upon a time, in the heart of a verdant forest, there dwelt a majestic stag. On a scorching summer afternoon, driven by unbearable thirst, he trotted toward a secluded crystal stream to quench his parched throat.",
        "As the animal bent low over the tranquil surface of the water, he was dazzled by his own mirrored reflection. Beholding his grand, branching antlers soaring gracefully towards the sky, his heart swelled with immense vanity. He exclaimed to himself with boundless conceit, 'How regal and magnificent is my crown! Truly, I am the peerless monarch of these woods.'",
        "A fleeting moment later, his downward gaze caught sight of his legs. Observing how lean, sinewy, and spindly his limbs appeared, a wave of profound mortification overcame him. He lamented bitterly, 'Alas! What an unkind prank mother nature has played upon me! These pitiful, spindle-like legs look so contemptible beneath such glorious antlers!'",
        "While he was still brooding over his perceived imperfections, the echoing barks of a pack of hunting hounds shattered the forest tranquility. Turning in sudden panic, the stag saw huntsmen armed with spears charging towards him with bloodthirsty dogs.",
        "Without lingering a second, the stag sprang into a desperate gallop. The very same slender, despised legs carried him swiftly across the rugged terrain like a gust of wind, effortlessly leaving the pursuing pack miles behind in the dust.",
        "Tragically, as he dashed into a dense clump of overarching trees, his sprawling antlers became inextricably entangled in the low, twisting branches. He wrenched and jerked furiously to liberate his crown, but every frantic struggle merely tightened the woody noose. Within moments, the ferocious baying hounds converged upon him and ripped him mercilessly apart.",
        "Gasping his final dying breaths, the stag wept in bitter repentance: 'What a catastrophic fool I was! The nimble legs I despised with all my soul delivered me from imminent doom, while the ornate horns I worshipped in pride have become the sole architect of my destruction!'"
      ),
      vocabularyNotes = listOf(
        "Parched" to "شدید پیاسا (Extremely thirsty)",
        "Antlers" to "سینگ (Branching horns of a deer)",
        "Inextricably" to "ناقابلِ رہائی طور پر (Impossible to untangle)",
        "Conceit" to "تکبر، غرور (Excessive pride in oneself)"
      ),
      examTips = "Ordinary: Simple 6 lines, perfect for passing. Extraordinary: Use rich contrasting words (despised legs vs proud antlers) and quote two proverbs for full 10/10 marks."
    ),
    StoryItem(
      number = 6,
      title = "The King and the Spider",
      urduTitle = "بادشاہ اور مکڑی (رابرٹ بروس کی جدوجہد)",
      subtitle = "Patience and relentless determination",
      moralQuotes = listOf(
        "Try, try again until you succeed.",
        "Failure is the stepping stone to success.",
        "Patience and perseverance conquer all difficulties."
      ),
      // Ordinary (Simple & Easy)
      simpleParagraphs = listOf(
        "Robert Bruce was the king of Scotland. He loved his country and wanted to make it free.",
        "The King of England invaded Scotland with a large army. Bruce fought bravely six times, but he was defeated in every battle.",
        "He lost his army and was forced to hide in a dark cave to save his life. He felt completely hopeless and decided to give up the fight.",
        "While resting in the cave, he saw a tiny spider hanging from the ceiling. It was trying to reach a beam to weave its web.",
        "The spider tried six times and fell each time, just like Bruce had failed six times. But the spider did not lose heart. It made a brave seventh attempt and reached the beam successfully!",
        "This brave act gave new hope to King Bruce. He said, 'If a small spider can succeed on the seventh attempt, why should I give up?'",
        "He gathered his brave Scottish soldiers, fought one more fierce battle, defeated the English army, and liberated Scotland."
      ),
      // Extraordinary (Topper Level)
      paragraphs = listOf(
        "King Robert Bruce ruled over Scotland with exemplary valor, benevolence, and wisdom. His soul was fired with an intense passion for the sovereign independence and honor of his motherland.",
        "However, the King of England marched into Scotland with an overwhelming, heavily armed imperial host and conquered the territory. Refusing to bend the knee to foreign tyranny, Robert Bruce mobilized his loyal clansmen and engaged the enemy in six successive ferocious pitched battles, only to suffer agonizing defeat each time.",
        "His army was completely decimated, his closest companions were captured, and a huge bounty was set on his head. Utterly exhausted and despairing of his destiny, King Bruce was compelled to seek sanctuary inside a damp, lonely cavern amid the desolate Scottish crags.",
        "One desolate rainy evening, reclining upon a heap of straw in acute melancholia, Bruce contemplated abandoning the struggle forever. 'Fate has declared against me,' he muttered in anguish. 'It is utterly futile to battle against destiny. I shall renounce my crown and accept perpetual exile.'",
        "Just as despair was about to claim his spirit, his wandering eye was arrested by a tiny spider dangling by a gossamer thread from the cavern ceiling. The diminutive creature was straining with incredible exertion to swing itself toward a neighboring wooden rafter to anchor its web.",
        "Six times the brave arachnid swung with all its tiny strength, and six times it fell short and tumbled down, mirroring Bruce’s own six battlefield tragedies. Bruce murmured softly, 'Little friend, you too must concede defeat to the impossible.'",
        "Yet, to the monarch's sheer astonishment, the spider harbored not the faintest glimmer of defeatism. Rallying its remaining vigor, it launched into an audacious seventh attempt. With an extraordinary surge of momentum, it reached the beam and triumphantly secured its thread!",
        "The spectacle ignited an electrifying spark in Bruce's despondent heart. He leaped to his feet with blazing determination: 'Shall a humble insect exhibit greater fortitude than the King of Scotland? If a spider can conquer defeat on the seventh trial, I too shall fight again!'",
        "Revitalized with invincible resolve, Bruce marched forth, rallied his dispersed Scottish patriots, and inspired them with indomitable fire. In the historic battle of Bannockburn, the Scots shattered the English legions decisively and restored freedom to Scotland forever."
      ),
      vocabularyNotes = listOf(
        "Benevolence" to "مہربانی، سخاوت (Kindness and goodwill)",
        "Decimated" to "تباہ و برباد ہو گیا (Heavily destroyed)",
        "Fortitude" to "ثابت قدمی، حوصلہ (Courage in pain or adversity)",
        "Indomitable" to "ناقابل شکست (Impossible to defeat)"
      ),
      examTips = "Highlight the parallel between the spider's 6 attempts and Bruce's 6 battles. End with the Battle of Bannockburn."
    )
  )

  // Applications with both Ordinary (Simple & Easy) and Extraordinary (Topper) versions (Punjab Board BISE 2026/27 Pattern)
  val applications: List<ApplicationItem> = listOf(
    ApplicationItem(
      number = 5,
      title = "Refund of Library / Hostel Security",
      urduTitle = "سکیورٹی فیس کی واپسی کے لیے درخواست (پنجاب بورڈ پیٹرن)",
      subject = "Application for Refund of Library and Hostel Security.",
      recipientDesignation = "The Principal,",
      institution = "Govt. College,",
      alternateInstitution = "KIPS College,",
      city = "City A.B.C.",
      toAddress = "The Principal,\nGovt. College,\nCity A.B.C.",
      salutation = "Respected Sir,",
      // Ordinary (Simple & Easy - 100% Error-Free for Board Exam)
      simpleBodyParagraphs = listOf(
        "Most respectfully, it is stated that I was a bonafide student of Intermediate Part-I (1st Year) in your college under Roll No. 12489.",
        "At the time of admission, I deposited the required security amount for the college library and hostel. As I have now completed my academic session, I have returned all the issued library books in good condition and cleared all my outstanding hostel dues. I have also obtained the official clearance certificates (NOC) from both the college librarian and the hostel warden.",
        "Therefore, I humbly request you to kindly refund my library and hostel security deposit. I shall be very grateful to you for this act of kindness."
      ),
      // Extraordinary (Topper Level Distinction)
      bodyParagraphs = listOf(
        "With the most profound respect, I beg to submit that I have successfully completed my intermediate academic tenure at your esteemed institution with distinguished merit.",
        "In strict compliance with college admission regulations, I had deposited the mandatory library and hostel security funds at the commencement of my academic session. Having now successfully finalized my curriculum, I have formally vacated the campus hostel residence and surrendered all borrowed academic literature in pristine condition. Furthermore, I have secured official clearance verification slips and No Objection Certificates (NOC) from both the Chief Librarian and the Hostel Superintendent.",
        "In light of the aforementioned facts, I humbly petition your good self to issue prompt directives to the college accounts department for the reimbursement of my deposited security funds at your earliest convenience.",
        "I shall remain immensely indebted to you for this administrative benevolence and favor."
      ),
      subscription = "Yours obediently,",
      studentSignature = "X.Y.Z.",
      rollNoLine = "Roll No. 12489",
      classLine = "Class: 1st Year (Intermediate Part-I)",
      date = "November 3, 2026.",
      marksBreakdown = listOf(
        "Addressing & Heading (The Principal, Govt. College, City A.B.C.) = 2 Marks",
        "Subject Line & Salutation (Respected Sir,) = 1 Mark",
        "Body of Application (3 Clear Paragraphs) = 5 Marks",
        "Subscription, Signature & Date (Dated: ... / Yours obediently, X.Y.Z.) = 2 Marks",
        "Total Marks = 10 Marks"
      ),
      formatTips = listOf(
        "Punctuation Rule 1: Always put comma after 'The Principal,' and after 'Govt. College,'",
        "Punctuation Rule 2: Put dots in 'City A.B.C.' without any trailing comma.",
        "Punctuation Rule 3: End Subject with a full stop: 'Subject: Application for Refund of Library and Hostel Security.'",
        "Punctuation Rule 4: NEVER write apostrophe in 'Yours' (Writing 'Your's' results in 1 whole mark deduction).",
        "Punctuation Rule 5: 'obediently' starts with a lowercase 'o', followed by comma: 'Yours obediently,'",
        "Anonymity: Always write 'X.Y.Z.' - never write your real name, college name, or city name in board papers."
      )
    ),
    ApplicationItem(
      number = 6,
      title = "An Urgent Piece of Work",
      urduTitle = "ضروری کام کی رخصت کے لیے درخواست (پنجاب بورڈ پیٹرن)",
      subject = "Application for Leave Due to an Urgent Piece of Work.",
      recipientDesignation = "The Principal,",
      institution = "Govt. College,",
      alternateInstitution = "KIPS College,",
      city = "City A.B.C.",
      toAddress = "The Principal,\nGovt. College,\nCity A.B.C.",
      salutation = "Respected Sir,",
      // Ordinary (Simple & Easy - 100% Error-Free for Board Exam)
      simpleBodyParagraphs = listOf(
        "Most respectfully, it is stated that I am a bonafide student of Intermediate Part-I (1st Year) in your college.",
        "I have an urgent piece of work at home today which requires my immediate presence. For this reason, I am unable to attend my scheduled classes and lectures today.",
        "Therefore, I humbly request you to kindly grant me leave for one day only, dated November 3, 2026. I assure you that I will cover my missed lessons tomorrow. I shall be very grateful to you for this favor."
      ),
      // Extraordinary (Topper Level Distinction)
      bodyParagraphs = listOf(
        "Most respectfully, it is submitted that I am a bonafide student of Intermediate Part-I (F.Sc.) in your reputable college, maintaining an exemplary academic and punctual attendance record.",
        "I have encountered an unavoidable and urgent domestic contingency at home which necessitates my indispensable personal presence today. On account of this unforeseen family emergency, I find myself utterly unable to attend my scheduled lectures and laboratory sessions for the day.",
        "Under these pressing circumstances, I humbly request your benevolent authority to kindly grant me leave of absence for today, November 3, 2026. I assure your good self that I shall diligently catch up on all missed academic lectures and practical assignments from my classmates immediately upon my resumption.",
        "I shall remain deeply grateful to you for your sympathetic consideration and kindness."
      ),
      subscription = "Yours obediently,",
      studentSignature = "X.Y.Z.",
      rollNoLine = "Roll No. 12489",
      classLine = "Class: 1st Year (Intermediate Part-I)",
      date = "November 3, 2026.",
      marksBreakdown = listOf(
        "Addressing & Heading (The Principal, Govt. College, City A.B.C.) = 2 Marks",
        "Subject Line & Salutation (Respected Sir,) = 1 Mark",
        "Body of Application (3 Clear Paragraphs) = 5 Marks",
        "Subscription, Signature & Date (Dated: ... / Yours obediently, X.Y.Z.) = 2 Marks",
        "Total Marks = 10 Marks"
      ),
      formatTips = listOf(
        "Punctuation Rule 1: 'The Principal,' -> Capital 'T', Capital 'P', comma at end.",
        "Punctuation Rule 2: 'Govt. College,' -> Dot after Govt., capital 'C', comma at end.",
        "Punctuation Rule 3: 'City A.B.C.' -> Dots after A, B, C; no trailing comma.",
        "Punctuation Rule 4: 'Yours obediently,' -> Capital 'Y', lowercase 'o', comma at end.",
        "Alignment: In Punjab Board exams, date is placed on the Left ('Dated: November 3, 2026.') and subscription on the Right."
      )
    )
  )

  // Letters with both Ordinary (Simple & Easy) and Extraordinary (Topper) versions (Punjab Board BISE 2026/27 Pattern)
  val letters: List<LetterItem> = listOf(
    LetterItem(
      number = 5,
      title = "Letter to Younger Brother Advising Him to Avoid Bad Company & Pay Attention to Studies",
      urduTitle = "چھوٹے بھائی کے نام خط: بری صحبت سے بچنے اور پڑھائی پر توجہ دینے کی تلقین",
      hallLine = "Examination Hall,",
      cityLine = "City A.B.C.",
      addressLine = "Examination Hall,\nCity A.B.C.",
      date = "November 3, 2026.",
      salutation = "My dear Brother,",
      // Ordinary (Simple & Easy - 100% Board Compliant)
      simpleBodyParagraphs = listOf(
        "I hope this letter finds you in good health and high spirits. I received Father's letter yesterday, and I was deeply grieved to learn about your poor performance in the recent college house tests.",
        "Father has written that you have started keeping bad company with careless boys who skip classes and waste their precious time. As your elder brother, it is my moral duty to warn you that bad company ruins a student's life and career. Always remember the wise golden saying: 'A man is known by the company he keeps.' You have been sent to the college hostel to study hard and build an honorable future. If you waste your precious time now, you will face lifelong remorse.",
        "Therefore, I strongly advise you to leave such careless companions immediately. Formulate a balanced daily study timetable and pay undivided attention to your textbooks. I am confident that you will heed my advice and score top marks in the annual board examinations. Convey my respectful regards to Mother and Father."
      ),
      // Extraordinary (Topper Level Distinction)
      bodyParagraphs = listOf(
        "I hope this letter finds you sailing serenely in the tranquil waters of radiant health and cheerful spirits. I received a letter from revered Father yesterday, and my heart sank with acute grief and anxiety upon learning of your dismal performance in the recent college assessments.",
        "Father mentioned that you have unfortunately fallen into the undesirable company of frivolous students who habitually play truant and squander their prime hours in idle pursuits. As an elder brother, it is my sacred moral obligation to counsel you that evil company is like burning coal; when blazing it burns the flesh, and when cold it soils the hands. A man is invariably judged by the company he keeps.",
        "Do not lose sight of the sublime mission for which our hardworking parents sent you to the hostel. Youth is the golden foundational cornerstone of human destiny. If you dissipate these irreplaceable formative hours in worthless amusements, agonizing remorse will be your perpetual companion. Therefore, I earnestly exhort you to sever all relations with indolent companions without hesitation. Devote your undivided intellect and energies to your studies.",
        "I cherish unwavering faith in your innate potential and believe you will bring glory to our family in the forthcoming Board examinations. Please convey my profound respects to Mother and Father."
      ),
      subscription = "Yours affectionately,",
      senderName = "X.Y.Z.",
      marksBreakdown = listOf(
        "Heading (Examination Hall, City A.B.C.) = 1 Mark",
        "Date Format (November 3, 2026.) = 1 Mark",
        "Salutation (My dear Brother,) = 1 Mark",
        "Body of Letter (3 Paragraphs) = 5 Marks",
        "Subscription & Signature (Yours affectionately, X.Y.Z.) = 2 Marks",
        "Total Marks = 10 Marks"
      ),
      keyGuidelines = listOf(
        "Punctuation Rule 1: 'Examination Hall,' must have a comma.",
        "Punctuation Rule 2: 'City A.B.C.' has dots after A, B, and C.",
        "Punctuation Rule 3: Date must end with a full stop: 'November 3, 2026.'",
        "Punctuation Rule 4: Salutation: 'My dear Brother,' -> 'My' is capitalized, 'dear' is lowercase, 'Brother' is capitalized, followed by a comma. (NEVER write a real name like 'Ali'!).",
        "Punctuation Rule 5: Subscription for brother is ALWAYS 'Yours affectionately,' with lowercase 'a' and a comma.",
        "Rule 6: Never write your real name, roll number, or city name; always write 'X.Y.Z.'"
      )
    ),
    LetterItem(
      number = 6,
      title = "Letter to Friend to Borrow Books From Him",
      urduTitle = "دوست کے نام خط: امتحانی تیاری کے لیے کتابیں ادھار مانگنے کے لیے",
      hallLine = "Examination Hall,",
      cityLine = "City A.B.C.",
      addressLine = "Examination Hall,\nCity A.B.C.",
      date = "November 3, 2026.",
      salutation = "Dear Friend,",
      // Ordinary (Simple & Easy - 100% Board Compliant)
      simpleBodyParagraphs = listOf(
        "I hope this letter finds you in good health and high spirits. As you know, our intermediate English board test is approaching very rapidly, and I am preparing for it day and night.",
        "While revising my syllabus, I noticed that I do not possess the standard guide book for 'Pair of Words, Correct Forms of Verbs, and Unit Translations'. I visited several prominent bookshops in the city, but the book is out of print everywhere. You mentioned last week that you have already thoroughly mastered this syllabus and finished your revision. If you are not utilizing the book at present, could you kindly lend it to me for one week?",
        "I assure you that I will handle your book with extreme care, keep it spotless, and return it safely to you well before the scheduled test. Please send it through your younger brother or let me know when I may collect it from your residence. Thank you very much in anticipation of your kind cooperation. Convey my heartfelt regards to your respected parents."
      ),
      // Extraordinary (Topper Level Distinction)
      bodyParagraphs = listOf(
        "I hope this letter finds you basking in the warm sunshine of radiant health, boundless serenity, and joyful spirits. As you are acutely aware, our decisive intermediate board examinations are drawing nigh, demanding unwavering perseverance and scholarly devotion from all ambitious students.",
        "While conducting an exhaustive review of my English syllabus yesterday, I discovered to my utter dismay that I am lacking the authoritative compilation 'Intermediate English Grammar & Composition' covering Pair of Words, Rules of Verbs, and Unit Solutions. I promptly scoured the local book repositories, but the distributors informed me that the current edition has run completely out of stock.",
        "I distinctly recall your mention during our previous encounter that you had already accomplished a comprehensive revision of these very modules. If those academic volumes are presently resting unconsulted upon your study desk, would you be so gracious as to lend them to me for a brief period of a week? I pledge my solemn word of honor that I shall handle your treasured books with scrupulous care, avoiding the slightest crease or pencil marking, and return them promptly with deepest gratitude.",
        "Kindly dispatch them through your younger brother, or notify me so that I may personally call at your residence to receive them. I shall remain profoundly obliged to you for this generous friendly gesture. Please convey my warmest regards and respects to your venerable parents."
      ),
      subscription = "Yours sincerely,",
      senderName = "X.Y.Z.",
      marksBreakdown = listOf(
        "Heading (Examination Hall, City A.B.C.) = 1 Mark",
        "Date Format (November 3, 2026.) = 1 Mark",
        "Salutation (Dear Friend,) = 1 Mark",
        "Body of Letter (3 Paragraphs) = 5 Marks",
        "Subscription & Signature (Yours sincerely, X.Y.Z.) = 2 Marks",
        "Total Marks = 10 Marks"
      ),
      keyGuidelines = listOf(
        "Punctuation Rule 1: 'Examination Hall,' must have a comma.",
        "Punctuation Rule 2: 'City A.B.C.' has dots after A, B, and C.",
        "Punctuation Rule 3: Date format: 'November 3, 2026.' with comma after 3 and full stop after year.",
        "Punctuation Rule 4: Salutation for friend is 'Dear Friend,' -> Capital 'D', Capital 'F', comma at end. (NEVER write a real name like 'Asad'!).",
        "Punctuation Rule 5: Subscription for friend is ALWAYS 'Yours sincerely,' with lowercase 's' and a comma.",
        "Rule 6: Never reveal identity; always sign with 'X.Y.Z.'"
      )
    )
  )

  // Verb Rules with both Simple (Ordinary) and Advanced (Extraordinary) formulas
  val verbRules: List<VerbRule> = listOf(
    VerbRule(
      ruleTitle = "Universal Truths & Daily Habits",
      urduSummary = "عالمگیر سچائی، روزمرہ معمولات کے لیے Present Indefinite (1st form / -s, -es) استعمال ہوتا ہے۔",
      triggerWords = "always, daily, every day, usually, often, seldom, never",
      simpleFormula = "He/She/It -> 1st Form + s/es | I/We/They -> Simple 1st Form",
      formula = "Subject + 1st Form of Verb (+ s/es with 3rd Person Singular Subject)",
      examples = listOf(
        "The sun (rise) in the east." to "The sun rises in the east.",
        "He always (speak) the truth." to "He always speaks the truth."
      )
    ),
    VerbRule(
      ruleTitle = "Actions in Progress Right Now",
      urduSummary = "اگر جملے میں now, at present, look آئے تو Present Continuous (is/am/are + 1st form-ing) آتا ہے۔",
      triggerWords = "now, at present, look!, listen!, currently",
      simpleFormula = "is / am / are + Verb-ing",
      formula = "Subject + is/am/are + 1st Form-ing (Present Continuous Tense)",
      examples = listOf(
        "They (play) football now." to "They are playing football now.",
        "Look! The bird (fly) away." to "Look! The bird is flying away."
      )
    ),
    VerbRule(
      ruleTitle = "Actions Just Completed",
      urduSummary = "اگر جملے میں already, just, yet آئے تو Present Perfect (has/have + 3rd form) آتا ہے۔",
      triggerWords = "already, just, recently, yet, so far",
      simpleFormula = "has / have + 3rd Form of Verb",
      formula = "Subject + has/have + 3rd Form of Verb (Past Participle)",
      examples = listOf(
        "He has already (take) the test." to "He has already taken the test.",
        "I have not (receive) the letter yet." to "I have not received the letter yet."
      )
    ),
    VerbRule(
      ruleTitle = "Past Definite Time Markers",
      urduSummary = "اگر جملے میں yesterday, ago, last night آئے تو ہمیشہ 2nd Form of Verb لگتی ہے۔",
      triggerWords = "yesterday, ago, last night, last week, in 1992",
      simpleFormula = "Subject + 2nd Form of Verb (Did + 1st form for negative)",
      formula = "Subject + 2nd Form of Verb (Past Indefinite Tense)",
      examples = listOf(
        "He (go) to Lahore yesterday." to "He went to Lahore yesterday.",
        "They (build) this house ten years ago." to "They built this house ten years ago."
      )
    ),
    VerbRule(
      ruleTitle = "Since / For with Time Duration",
      urduSummary = "اگر وقت کے ساتھ Since یا For آئے اور کام جاری ہو تو has/have been + ing آتا ہے۔",
      triggerWords = "since morning, for two hours, since 2015",
      simpleFormula = "has been / have been + Verb-ing",
      formula = "Subject + has/have been + Verb-ing + since/for + Time Phrase",
      examples = listOf(
        "It (rain) since morning." to "It has been raining since morning.",
        "He (study) for three hours." to "He has been studying for three hours."
      )
    ),
    VerbRule(
      ruleTitle = "Conditional Sentences (Type 1 & 2)",
      urduSummary = "اگر If میں 1st form ہو تو اگلے حصے میں will + 1st form۔ اگر If میں 2nd form ہو تو would + 1st form۔",
      triggerWords = "If + Present -> will; If + Past -> would",
      simpleFormula = "If + 1st form -> will + 1st form | If + 2nd form -> would + 1st form",
      formula = "If + Subject + Present Indefinite, Subject + will/shall + 1st Form",
      examples = listOf(
        "If he works hard, he (pass)." to "If he works hard, he will pass.",
        "If you invited me, I (come)." to "If you invited me, I would come."
      )
    ),
    VerbRule(
      ruleTitle = "Past Perfect with 'Before' & 'After'",
      urduSummary = "پہلے ہونے والے کام کے لیے had + 3rd form اور بعد والے کام کے لیے 2nd form لگتی ہے۔",
      triggerWords = "before, after, when the doctor arrived",
      simpleFormula = "had + 3rd form ... before ... 2nd form",
      formula = "Past Perfect (had + 3rd form) + before + Past Indefinite (2nd form)",
      examples = listOf(
        "The patient had died before the doctor (arrive)." to "The patient had died before the doctor arrived."
      )
    )
  )

  // 15 Golden Solved Board MCQs
  val verbQuestions: List<VerbQuestion> = listOf(
    VerbQuestion(
      id = 1,
      sentence = "The sun _______ in the east every morning.",
      options = listOf("rise", "rises", "rose", "is rising"),
      correctIndex = 1,
      explanation = "Universal truth and habitual fact; singular subject 'The sun' takes 'rises' (1st form + s)."
    ),
    VerbQuestion(
      id = 2,
      sentence = "They _______ football now in the college ground.",
      options = listOf("play", "played", "are playing", "have played"),
      correctIndex = 2,
      explanation = "'Now' indicates an action occurring right at this present moment, demanding Present Continuous (are playing)."
    ),
    VerbQuestion(
      id = 3,
      sentence = "He has already _______ his English assignment.",
      options = listOf("finish", "finished", "finishing", "finishes"),
      correctIndex = 1,
      explanation = "With auxiliary 'has' in Present Perfect, the 3rd form (past participle) 'finished' is compulsory."
    ),
    VerbQuestion(
      id = 4,
      sentence = "Quaid-e-Azam _______ hard for the creation of Pakistan.",
      options = listOf("work", "worked", "has worked", "is working"),
      correctIndex = 1,
      explanation = "Completed historical fact in past time requires Past Indefinite (2nd form 'worked')."
    ),
    VerbQuestion(
      id = 5,
      sentence = "It _______ since early this morning.",
      options = listOf("rains", "is raining", "has been raining", "rained"),
      correctIndex = 2,
      explanation = "'Since early this morning' denotes continuous duration starting in past up to now, taking Present Perfect Continuous."
    ),
    VerbQuestion(
      id = 6,
      sentence = "I _______ him in the college library yesterday.",
      options = listOf("meet", "met", "have met", "am meeting"),
      correctIndex = 1,
      explanation = "'Yesterday' is an unmistakable past definite time marker, requiring 2nd form of verb 'met'."
    ),
    VerbQuestion(
      id = 7,
      sentence = "If he works hard, he _______ the examination with flying colors.",
      options = listOf("passed", "passes", "will pass", "would pass"),
      correctIndex = 2,
      explanation = "Type 1 Conditional: If-clause in Present Indefinite (works) calls for Future Indefinite (will pass) in the main clause."
    ),
    VerbQuestion(
      id = 8,
      sentence = "The train had departed before we _______ at the station.",
      options = listOf("reach", "reached", "had reached", "reaching"),
      correctIndex = 1,
      explanation = "When two past actions occur, the prior one takes 'had + 3rd' while the later action takes Past Indefinite 2nd form 'reached'."
    ),
    VerbQuestion(
      id = 9,
      sentence = "Water _______ at 100 degrees Celsius under normal pressure.",
      options = listOf("boil", "boils", "boiled", "is boiling"),
      correctIndex = 1,
      explanation = "Scientific fact requires Present Indefinite tense ('boils')."
    ),
    VerbQuestion(
      id = 10,
      sentence = "She _______ a letter to her friend tomorrow.",
      options = listOf("wrote", "will write", "has written", "writes"),
      correctIndex = 1,
      explanation = "'Tomorrow' specifies future time, requiring Future Indefinite 'will write'."
    ),
    VerbQuestion(
      id = 11,
      sentence = "He talks as if he _______ a king.",
      options = listOf("is", "was", "were", "are"),
      correctIndex = 2,
      explanation = "In subjunctive clauses with 'as if' or 'wish', the hypothetical past 'were' is used for all persons."
    ),
    VerbQuestion(
      id = 12,
      sentence = "The bell had not _______ when the teacher entered the classroom.",
      options = listOf("ring", "rang", "rung", "ringing"),
      correctIndex = 2,
      explanation = "Past Perfect with auxiliary 'had' always requires the 3rd form (past participle) 'rung' (ring - rang - rung)."
    ),
    VerbQuestion(
      id = 13,
      sentence = "They _______ in this city since 2015.",
      options = listOf("live", "are living", "have been living", "lived"),
      correctIndex = 2,
      explanation = "'Since 2015' indicates point of origin in past extending into present; requires 'have been living'."
    ),
    VerbQuestion(
      id = 14,
      sentence = "Did you _______ your English textbook yesterday?",
      options = listOf("brought", "bring", "brings", "bringing"),
      correctIndex = 1,
      explanation = "After the auxiliary 'did' in past interrogative/negative, the verb is always in its 1st base form 'bring'."
    ),
    VerbQuestion(
      id = 15,
      sentence = "Good students never _______ their precious time in idle gossip.",
      options = listOf("waste", "wastes", "wasted", "wasting"),
      correctIndex = 0,
      explanation = "Plural subject 'Good students' with habitual adverb 'never' takes base 1st form 'waste'."
    )
  )

  // Unit 4: Team Moon (Class 11) with both Ordinary (Simple & Easy) and Extraordinary (Topper) modes
  val unitFourData = UnitFourData(
    title = "Unit 4: Team Moon",
    urduTitle = "ٹیم مون: اپالو 11 کا تاریخی مشن (کلاس 11)",
    author = "Catherine Thimmesh",
    simpleOverview = "This chapter is about Apollo 11 landing on the Moon in 1969. While Neil Armstrong and Buzz Aldrin walked on the Moon, 400,000 scientists, seamstresses, and engineers worked as a team to make it possible.",
    overview = "This remarkable chapter honors the 400,000 unsung heroes behind the Apollo 11 lunar landing in July 1969. While Neil Armstrong and Buzz Aldrin stepped onto the lunar surface, an invisible army of engineers, seamstresses, flight controllers, programmers like Margaret Hamilton, and technicians worked selflessly to make the impossible possible through collective teamwork.",
    keyTheme = "Teamwork, collective effort, behind-the-scenes heroes, and dedication to achieve impossible goals.",
    paragraphs = listOf(
      ChapterParagraph(
        number = 1,
        title = "Paragraph 1: The Invisible Army (غیر مرئی فوج)",
        simpleEnglishSummary = "In July 1969, Apollo 11 landed on the Moon. Millions saw Neil Armstrong walk on the Moon. But behind him, 400,000 workers, scientists, and engineers worked hard as a team.",
        simpleUrduSummary = "جولائی 1969 میں نیل آرمسٹرانگ نے چاند پر قدم رکھا۔ لیکن اس تاریخی کامیابی کے پیچھے 4 لاکھ سائنسدانوں اور ورکرز کی غیر مرئی فوج تھی۔",
        englishText = "When Apollo 11 landed on the Moon in July 1969, the world watched in awe as Neil Armstrong and Buzz Aldrin took humanity's first steps on another celestial body. Millions remember the famous words, 'That's one small step for man, one giant leap for mankind.' Yet, behind those two astronauts stood an invisible army. Landing a man on the Moon was not merely the achievement of a couple of space travelers; it was the monumental triumph of a vast, coordinated network of minds. Over 400,000 scientists, engineers, seamstresses, machinists, and technicians poured their expertise, precision, and passion into making the impossible touchable. Team Moon is the story of these unsung heroes—the people whose dedication turned a national dream into a stellar reality.",
        urduTranslation = "جب جولائی 1969ء میں اپالو 11 (Apollo 11) چاند پر اترا، تو پوری دنیا نے حیرت و استعجاب سے دیکھا جب نیل آرمسٹرانگ (Neil Armstrong) اور بَز ایلڈرن (Buzz Aldrin) نے کسی دوسرے فلکیاتی جسم پر انسانیت کے پہلے قدم رکھے۔ کروڑوں انسانوں کو وہ تاریخی الفاظ یاد ہیں: \"یہ انسان کا ایک چھوٹا سا قدم ہے، لیکن نوعِ انسانی کے لیے ایک عظیم جست (چھلانگ) ہے۔\" لیکن ان دو خلا بازوں کے پیچھے ایک غیر مرئی فوج کھڑی تھی۔ چاند پر انسان کو اتارنا محض دو خلاء نوردوں کا ذاتی کارنامہ نہیں تھا؛ یہ انسانوں کے ایک وسیع، باہمی مربوط نیٹ ورک کی شاندار فتح تھی۔ 4 لاکھ (400,000) سے زائد سائنسدانوں، انجینئرز، خیاطوں (درزیوں)، کاریگروں اور تکنیکی ماہرین نے ناممکن کو حقیقت میں بدلنے کے لیے اپنی پوری مہارت، نفاست اور جذبہ نچھاور کر دیا۔ 'ٹیم مون' انہی گمنام ہیروز کی داستان ہے—وہ لوگ جن کے خلوص اور لگن نے ایک قومی خواب کو درخشاں حقیقت کا روپ بخشا۔"
      ),
      ChapterParagraph(
        number = 2,
        title = "Paragraph 2: The Seamstresses of Playtex (پلے ٹیکس کی درزی خواتین)",
        simpleEnglishSummary = "Women seamstresses at Playtex stitched the spacesuits for astronauts. These suits had to protect astronauts from deadly cold and vacuum in space. One wrong stitch could cause death.",
        simpleUrduSummary = "پلے ٹیکس کی درزی خواتین نے خلابازوں کے سوٹ سیے۔ ان کا کام زندگی اور موت کا معاملہ تھا کیونکہ ایک غلط ٹانکا جان لے سکتا تھا۔",
        englishText = "In a quiet workshop far from the launch pads of Cape Canaveral, a team of seamstresses at Playtex were busy with a task unlike any they had faced before. They were responsible for crafting the spacesuits that would protect the astronauts in the harsh environment of space. These suits had to be perfect: flexible enough for movement, yet strong enough to withstand the vacuum of space. One misplaced stitch could spell disaster. Working with a blend of meticulous care and quiet pride, these women stitched layer upon layer of specialised fabric, knowing that their work was literally a matter of life and death. Each seam, each stitch, was a thread in the tapestry of the Apollo 11 mission.",
        urduTranslation = "کیپ کینیورل (Cape Canaveral) کے راکٹ لانچ پیڈز سے دور ایک خاموش کارخانے میں، پلے ٹیکس (Playtex) کی ماہر درزی خواتین کی ایک ٹیم ایسے انوکھے کام میں مصروف تھی جو انہوں نے پہلے کبھی نہیں کیا تھا۔ وہ ان پیچیدہ خلائی ملبوسات (spacesuits) کی تیاری کی ذمہ دار تھیں جنہوں نے خلا کے جان لیوا ماحول میں خلا بازوں کی حفاظت کرنی تھی۔ ان سوٹس کا بے عیب ہونا لازمی تھا: حرکت کے لیے اتنے لچکدار کہ خلانورد آسانی سے جھک سکیں، اور خلا کے شدید دباؤ اور خلاء (vacuum) کا مقابلہ کرنے کے لیے اتنے مضبوط کہ ذرہ برابر نہ پھٹیں۔ ایک غلط ٹانکا تباہی اور موت کا سبب بن سکتا تھا۔ گہری احتیاط اور پُروقار فخر کے ساتھ، ان خواتین نے خصوصی کپڑوں کی تہوں پر تہیں سلائی کیں، یہ جانتے ہوئے کہ ان کا کام لفظی طور پر زندگی اور موت کا معاملہ تھا۔ ہر سلائی اور ہر ٹانکا اپالو 11 کے عظیم مشن کے تانے بانے میں ایک لائف لائن تھا۔"
      ),
      ChapterParagraph(
        number = 3,
        title = "Paragraph 3: NASA Engineers & Saturn V (ناسا کے انجینئرز اور راکٹ)",
        simpleEnglishSummary = "NASA engineers built the giant Saturn V rocket. They solved difficult problems of balance, direction, and safety to send astronauts to the Moon and bring them back safely.",
        simpleUrduSummary = "انجینئرز نے دیو ہیکل سیٹرن فائیو راکٹ بنایا اور چیلنجز حل کر کے خلانوردوں کی بحفاظت واپسی کو یقینی بنایا۔",
        englishText = "Across the country, engineers at NASA were tackling problems that no one had ever solved before. The Saturn V rocket, a towering behemoth of engineering, had to function flawlessly to carry its precious cargo to the Moon. The engineers ran countless tests, simulations, and calculations, refining their designs again and again. They dealt with challenges that seemed insurmountable: how to keep the rocket stable, how to navigate through space with precision, and how to bring the astronauts home safely. These engineers knew that the success of the mission depended on their collective efforts, and they worked tirelessly, often late into the night, driven by the belief that they were part of something bigger than themselves.",
        urduTranslation = "پورے ملک میں ناسا کے باصلاحیت انجینئرز ایسے پیچیدہ سائنسی مسائل کو سلجھانے میں مگن تھے جو اس سے قبل تاریخ میں کسی نے حل نہیں کیے تھے۔ 'سیٹرن فائیو' (Saturn V) راکٹ—جو انجینئرنگ کا ایک فلک بوس، دیو ہیکل شاہکار تھا—اسے اپنے قیمتی ترین اثاثے (خلابازوں) کو چاند تک لے جانے کے لیے بغیر کسی خامی کے چلنا تھا۔ انجینئرز نے ان گنت ٹیسٹ، فرضی آزمائشیں (simulations) اور حساب کتاب کیے، اور اپنے نقشوں کو بار بار نکھارا۔ انہوں نے ایسے چیلنجز کا ڈٹ کر مقابلہ کیا جو ناقابل تسخیر لگتے تھے: راکٹ کو خلا میں کیسے سیدھا اور متوازن رکھا جائے، خلا کے گھپ اندھیروں میں کیسے درست سمت میں سفر کیا جائے، اور خلا بازوں کو صحیح سلامت زمین پر واپس کیسے لایا جائے۔ ان سب کو معلوم تھا کہ مشن کی کامیابی ان کی اجتماعی جدوجہد پر منحصر ہے، اور انہوں نے رات دن انتھک محنت کی، اس پختہ یقین کے ساتھ کہ وہ اپنی ذات سے کہیں بلند ایک تاریخی مقصد کا حصہ ہیں۔"
      ),
      ChapterParagraph(
        number = 4,
        title = "Paragraph 4: Mission Control (مشن کنٹرول روم اور ہنگامی الارم)",
        simpleEnglishSummary = "During landing, alarms sounded in the spaceship. Mission Control controllers stayed calm, checked screens, and found the computer was just busy. They allowed the landing to continue.",
        simpleUrduSummary = "لینڈنگ کے وقت کمپیوٹر الارم بجے۔ کنٹرولرز نے پرسکون رہ کر چند سیکنڈ میں فیصلہ کیا اور نیل آرمسٹرانگ کو اترنے کی اجازت دی۔",
        englishText = "At NASA's Mission Control in Houston, a team of young flight controllers was preparing for the mission with military precision. Each member of the team, from the Flight Director to the communications officer, had a critical role to play. They practised every possible scenario, from equipment failures to emergency aborts, preparing for every potential problem. When the day of the lunar landing finally arrived, tension filled the room. As the lunar module, 'Eagle,' descended towards the Moon, alarms suddenly blared - error codes no one had expected. Mission Control quickly sprang into action. Flight controllers scanned their screens, consulted their manuals, and conferred with each other. In mere seconds, they realised the alarms were not mission-critical; the guidance computer was simply overloaded but still functioning properly. Their swift decision-making and calm under pressure skill allowed Armstrong and Aldrin to continue their descent. When Armstrong's voice finally crackled through the radio with the words, 'Houston, Tranquility Base here. The Eagle has landed,' the room erupted in applause. But there was no time for rest - the mission was far from over.",
        urduTranslation = "ہیوسٹن (Houston) میں ناسا کے مشن کنٹرول میں، نوجوان فلائٹ کنٹرولرز کا ایک دستہ عسکری نظم و ضبط کے ساتھ مشن کی نگرانی کر رہا تھا۔ فلائٹ ڈائریکٹر سے لے کر وائرلیس آفیسر تک، ہر رکن کا کردار بے حد نازک تھا۔ انہوں نے مشینوں کی خرابی سے لے کر ایمرجنسی میں مشن منسوخ کرنے تک ہر فرضی حادثے کی سینکڑوں بار مشق کی تھی۔ جب چاند پر اترنے کا حتمی دن آیا تو پورے کنٹرول روم میں سانسیں تھم گئیں۔ جیسے ہی چاند پر اترنے والا لینڈر 'ایگل' (Eagle) چاند کی سطح کی طرف نیچے جھکا، اچانک خطرناک الارم بج اٹھے—کمپیوٹر پر ایسے ایرر کوڈز آ گئے جن کی کسی کو توقع نہ تھی۔ مشن کنٹرول فوراً متحرک ہو گیا۔ کنٹرولرز نے کمپیوٹر سکرینوں کا تجزیہ کیا، اپنی ہنگامی کتابچوں کی ورق گردانی کی اور آپس میں مشورہ کیا۔ پلک جھپکتے ہی انہوں نے جان لیا کہ یہ الارم مشن کے لیے جان لیوا نہیں؛ گائیڈنس کمپیوٹر صرف ڈیٹا سے اوورلوڈ ہو گیا تھا لیکن وہ محفوظ طور پر کام کر رہا تھا۔ دباؤ کے وقت ان کے پرسکون رہنے اور سیکنڈز میں کیے گئے فیصلے نے نیل آرمسٹرانگ کو اترائی جاری رکھنے کا گرین سگنل دیا۔ جب ریڈیو سپیکر پر آرمسٹرانگ کی آواز گونجی: \"ہیوسٹن، ٹرانکوئلٹی بیس بول رہا ہے۔ ایگل چاند پر بحفاظت اتر چکا ہے\"، تو پورا کمرہ جوش بھری تالیوں سے گونج اٹھا۔ لیکن آرام کا کوئی موقع نہ تھا—کیونکہ مشن ابھی آدھا باقی تھا۔"
      ),
      ChapterParagraph(
        number = 5,
        title = "Paragraph 5: Margaret Hamilton (مارگریٹ ہیملٹن کا کوڈ)",
        simpleEnglishSummary = "Margaret Hamilton led the software team. She wrote code that could ignore less important tasks and focus on landing. Her smart software saved Apollo 11 from crashing.",
        simpleUrduSummary = "مارگریٹ ہیملٹن نے فلائٹ سافٹ ویئر لکھا۔ ان کے ذہین کوڈ نے کمپیوٹر اوورلوڈ کے وقت اہم کاموں کو ترجیح دے کر مشن کو بچایا۔",
        englishText = "One of the unsung heroes was Margaret Hamilton, a software engineer who led the team that developed the onboard flight software for the lunar module. In a time when coding was done by hand, with punch cards and endless lines of code, Hamilton and her team created software that could prioritise the most critical tasks. This foresight proved invaluable during the lunar landing, when the computer was overloaded with unnecessary data. Thanks to Hamilton’s code, the computer discarded less important tasks and focused on landing the spacecraft—an action that played a crucial role in the success of the mission.",
        urduTranslation = "ان گمنام ہیروز میں ایک غیر معمولی ہیروئن مارگریٹ ہیملٹن (Margaret Hamilton) تھیں، جو ایک لائق سافٹ ویئر انجینئر تھیں جنہوں نے اس ٹیم کی سربراہی کی جس نے چاند گاڑی کے لیے فلائٹ سافٹ ویئر تیار کیا تھا۔ اس دور میں جب کمپیوٹر کوڈنگ ہاتھ سے لکھی جاتی تھی اور کاغذ کے پنچ کارڈز پر مشتمل ہوتی تھی، ہیملٹن اور ان کی ٹیم نے ایسا ہوشیار سافٹ ویئر لکھا جو ہنگامی حالت میں سب سے اہم کاموں کو ترجیح (priority) دے سکتا تھا۔ ان کی یہ پیش بینی چاند پر اترتے وقت آبِ حیات ثابت ہوئی، جب کمپیوٹر پر راڈار سے غیر ضروری ڈیٹا کا شدید بوجھ پڑ گیا۔ ہیملٹن کے لکھے گئے ذہین کوڈ کی بدولت کمپیوٹر نے کم اہم کاموں کو ایک طرف پھینک دیا اور اپنی پوری توجہ جہاز کو چاند پر لینڈ کرانے پر مرکوز رکھی—ایک ایسا تاریخی فیصلہ جس نے پورے اپالو مشن کو تباہ ہونے سے بچا لیا۔"
      ),
      ChapterParagraph(
        number = 6,
        title = "Paragraph 6: Splashdown & Conclusion (محفوظ واپسی اور ٹیم ورک)",
        simpleEnglishSummary = "Apollo 11 was successful because everyone worked together, from cleaners to scientists. The astronauts stayed 21 hours on the Moon and returned safely to Earth by landing in the ocean.",
        simpleUrduSummary = "اپالو 11 کی کامیابی مشترکہ ٹیم ورک کی مرہونِ منت تھی۔ خلا بازوں نے چاند پر 21 گھنٹے گزارے اور بحر الکاہل میں بحفاظت اترے۔",
        englishText = "The success of Apollo 11 was a symphony conducted by countless hands. From the janitors who kept the labs clean, to the technicians who fuelled the rockets, to the scientists who plotted the trajectories, every person had a part to play. When the astronauts planted the American flag on the Moon and collected samples of lunar rocks, they were not just fulfilling their own dreams, but the dreams of all those who had worked tirelessly behind the scenes. Armstrong and Aldrin spent just over 21 hours on the lunar surface before returning to the command module. The astronauts splashed down safely in the Pacific Ocean, completing the mission. The central theme is that great achievements, like the Moon landing, are made possible through teamwork and collaboration.",
        urduTranslation = "اپالو 11 کی کامیابی حقیقت میں ایک ایسی روح پرور سمفنی (شاہکار موسیقی) تھی جسے لاکھوں ہاتھوں نے مل کر بجایا تھا۔ ان دربانوں اور خاکروبوں سے لے کر جنہوں نے لیبز کو گرد سے پاک رکھا، ان ٹیکنیشنز تک جنہوں نے راکٹ میں ایندھن بھرا، اور ان ریاضی دانوں اور سائنسدانوں تک جنہوں نے خلا کے راستوں کا تعین کیا—ہر فرد کا کردار سنگِ میل تھا۔ جب خلابازوں نے چاند کی سطح پر پرچم لہرایا اور چاند کی مٹی اور پتھروں کے نمونے لیے، تو وہ صرف اپنا ذاتی خواب پورا نہیں کر رہے تھے، بلکہ ان تمام چار لاکھ لوگوں کے خوابوں کی تعبیر دے رہے تھے جنہوں نے پردے کے پیچھے خاموشی سے خون پسینہ بہایا تھا۔ دونوں خلابازوں نے چاند پر تقریباً 21 گھنٹے گزارے اور بحفاظت بحر الکاہل میں لینڈ کر کے مشن مکمل کیا۔ اس سبق کا مرکزی سبق یہی ہے کہ انسان کی تاریخ کی سب سے عظیم کامیابیاں صرف اور صرف باہمی اتحاد، ٹیم ورک اور اخلاص سے ہی ممکن ہوتی ہیں۔"
      )
    ),
    questionsAnswers = listOf(
      UnitQuestionAnswer(
        questionNumber = 1,
        question = "What was significant about Neil Armstrong's first steps on the Moon?",
        simpleAnswer = "Neil Armstrong's first steps were historic because he was the first human to walk on the Moon. His words showed that this success was not an individual act, but 'one giant leap for mankind' made possible by 400,000 team members.",
        answer = "Neil Armstrong's first steps on the Moon in July 1969 were profoundly historic because he became the very first human being in history to walk on an extraterrestrial celestial surface. His immortal proclamation—'That's one small step for man, one giant leap for mankind'—signified that this monumental triumph was not an individual glory, but a collective evolutionary leap achieved through the coordinated labor of 400,000 scientists, engineers, and workers.",
        urduMeaning = "نیل آرمسٹرانگ چاند پر چلنے والے پہلے انسان بنے۔ ان کا قدم پوری انسانیت کی اجتماعی فتح تھی۔"
      ),
      UnitQuestionAnswer(
        questionNumber = 2,
        question = "Why was the job of the seamstresses at Playtex so important for the Apollo 11 mission?",
        simpleAnswer = "The Playtex seamstresses carefully stitched the spacesuits. These suits protected the astronauts from extreme cold and airless space. One single wrong stitch could cause death, so their job was a matter of life and death.",
        answer = "The seamstresses at Playtex played a critical, life-or-death role in the mission. They were entrusted with hand-crafting and stitching the multi-layered spacesuits worn by the astronauts. These suits had to simultaneously provide extreme flexibility for movement and absolute structural durability to withstand the deadly vacuum, radiation, and extreme temperatures of space. A single loose stitch or misaligned seam would cause decompression and lead to instant fatality, making their precise work paramount to mission safety.",
        urduMeaning = "درزی خواتین نے خلانوردوں کے حفاظتی سوٹ سیے۔ ایک غلط ٹانکا خلانوردوں کی جان لے سکتا تھا، اس لیے ان کی سلائی زندگی اور موت کا فیصلہ تھی۔"
      ),
      UnitQuestionAnswer(
        questionNumber = 3,
        question = "What kind of problems were the NASA engineers trying to solve with the Saturn V rocket?",
        simpleAnswer = "Engineers had to solve problems of rocket stability, direction in space, and enormous engine power. They had to ensure the 363-foot rocket could carry astronauts safely to the Moon and return them back to Earth.",
        answer = "NASA engineers were confronted with unprecedented, seemingly insurmountable engineering hurdles. The Saturn V was a mammoth rocket standing 363 feet tall. Engineers had to design massive multi-stage propulsion engines to generate 7.5 million pounds of thrust, solve dangerous pogo vibrations to keep the rocket structurally stable during ascent, plot ultra-precise navigational orbital trajectories, and build reliable re-entry heat shields to ensure the astronauts returned safely to Earth.",
        urduMeaning = "انجینئرز کو 363 فٹ بلند راکٹ کو متوازن رکھنے، خلا میں سمت کی درستگی، اور زمین پر محفوظ واپسی کے مسائل حل کرنے تھے۔"
      ),
      UnitQuestionAnswer(
        questionNumber = 4,
        question = "Why was it important for each member of the Mission Control team to have a specific role during the mission?",
        simpleAnswer = "Specific roles ensured that every detail was watched carefully. In emergency moments, controllers could check data in seconds, make quick calm decisions, and keep the astronauts completely safe.",
        answer = "Assigning clearly delineated, specialised roles to each flight controller ensured comprehensive 360-degree monitoring of every mechanical, electrical, biological, and navigational parameter of the spacecraft without any room for human oversight. During high-stress crisis moments—such as sudden radar computer alarms during lunar descent—this division of labor allowed controllers to cross-verify data within seconds, make calm split-second decisions, and communicate vital instructions to the crew.",
        urduMeaning = "مخصوص ذمہ داریوں کی وجہ سے ہر لمحے کی نگرانی ممکن ہوئی۔ ہنگامی سگنلز کے وقت ہر آفیسر نے فوراً درست فیصلہ لیا۔"
      ),
      UnitQuestionAnswer(
        questionNumber = 5,
        question = "How did the team at Mission Control prepare for unexpected problems during the Apollo 11 mission?",
        simpleAnswer = "They practiced hundreds of crisis simulations and drills for months. They practiced equipment failures and computer alarms so they could stay calm and solve problems under heavy pressure.",
        answer = "The Mission Control team engaged in relentless, highly intensive simulations and stress drills for months. Lead instructors simulated catastrophic equipment breakdowns, loss of radio communication, trajectory deviations, and computer overloads. This continuous exposure to high-pressure crisis scenarios trained the controllers to maintain icy composure, troubleshoot rationally under panic, and trust their team colleagues implicitly.",
        urduMeaning = "انہوں نے مہینوں تک نقلی ہنگامی مشقیں کیں تاکہ حقیقی بحران میں بھی پرسکون رہ کر فوری حل نکال سکیں۔"
      ),
      UnitQuestionAnswer(
        questionNumber = 6,
        question = "Who was Margaret Hamilton, and what was her role in the Apollo 11 mission?",
        simpleAnswer = "Margaret Hamilton was a brilliant software engineer who wrote the Apollo 11 flight code. When the computer was overloaded with data during landing, her code ignored minor tasks and focused on landing safely.",
        answer = "Margaret Hamilton was a pioneering computer scientist who served as the Director of the Software Engineering Division at the MIT Instrumentation Laboratory. She led the development of the onboard flight software for Apollo 11. Her genius architectural concept of 'asynchronous executive' allowed the computer to prioritise life-critical landing radar tasks while discarding low-priority peripheral tasks when the CPU was overwhelmed during lunar descent, thereby preventing a fatal mission abort.",
        urduMeaning = "مارگریٹ ہیملٹن نے فلائٹ سافٹ ویئر بنایا۔ جب لینڈنگ کے وقت کمپیوٹر اوورلوڈ ہوا تو ان کے کوڈ نے لینڈنگ کو ترجیح دے کر مشن بچا لیا۔"
      )
    ),
    vocabulary = listOf(
      UnitVocab("Celestial", "آسمانی، فلکیاتی", "Belonging to outer space", "The Moon is a celestial body."),
      UnitVocab("Behemoth", "دیو ہیکل چیز", "A colossal structure or machine", "Saturn V was a roaring behemoth of engineering."),
      UnitVocab("Seamstress", "خیاطہ، درزی عورت", "A woman who sews cloth", "Playtex seamstresses stitched the multi-layer suits."),
      UnitVocab("Insurmountable", "ناقابل تسخیر", "Too great to overcome", "They overcame insurmountable obstacles together."),
      UnitVocab("Foresight", "دور اندیشی", "Ability to prepare for future", "Margaret Hamilton's foresight saved the Apollo mission."),
      UnitVocab("Splashdown", "سمندر میں اترنا", "Landing of spacecraft on water", "The Apollo capsule made a safe splashdown in the ocean.")
    ),
    grammarRules = listOf(
      "Passive Voice in Scientific Narration: 'The spacesuits were meticulously crafted by seamstresses.'",
      "Relative Clauses: 'Margaret Hamilton, who directed the software division, created the operating code.'",
      "Modal Auxiliaries for Obligation: 'These suits had to withstand the vacuum of space.'"
    ),
    sampleMcqs = listOf(
      VerbQuestion(
        id = 101,
        sentence = "How many people worked behind the scenes to land Apollo 11 on the Moon?",
        options = listOf("40,000", "100,000", "400,000", "1,000,000"),
        correctIndex = 2,
        explanation = "According to Catherine Thimmesh, over 400,000 scientists, engineers, and workers contributed."
      ),
      VerbQuestion(
        id = 102,
        sentence = "The flight software that saved Apollo 11 during computer overload was developed by _______ .",
        options = listOf("Neil Armstrong", "Buzz Aldrin", "Margaret Hamilton", "Robert Bruce"),
        correctIndex = 2,
        explanation = "Margaret Hamilton led the software team and designed the priority-based operating software."
      ),
      VerbQuestion(
        id = 103,
        sentence = "The spacesuits for Apollo 11 astronauts were sewn by seamstresses at _______ .",
        options = listOf("NASA HQ", "Playtex", "Cape Canaveral", "Houston"),
        correctIndex = 1,
        explanation = "The Playtex company's skilled seamstresses stitched the multi-layer suits."
      )
    )
  )
}
