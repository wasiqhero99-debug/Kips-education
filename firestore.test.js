const {
  initializeTestEnvironment,
  assertFails,
  assertSucceeds,
} = require("@firebase/rules-unit-testing");
const { test, before, after, beforeEach } = require("node:test");
const fs = require("node:fs");

let testEnv;
const PROJECT_ID = "demo-no-project";
const ALICE_UID = "alice_student_1";
const BOB_UID = "bob_student_2";

before(async () => {
  const rules = fs.readFileSync("./firestore.rules", "utf8");
  testEnv = await initializeTestEnvironment({
    projectId: PROJECT_ID,
    firestore: {
      rules,
      host: "127.0.0.1",
      port: 8085,
    },
  });
});

after(async () => {
  if (testEnv) {
    await testEnv.cleanup();
  }
});

beforeEach(async () => {
  if (testEnv) {
    await testEnv.clearFirestore();
  }
});

test("Unauthenticated user: cannot read community messages", async () => {
  const unauthDb = testEnv.unauthenticatedContext().firestore();
  await assertFails(unauthDb.collection("community_messages").get());
});

test("Authenticated student: can read community messages", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  await assertSucceeds(aliceDb.collection("community_messages").get());
});

test("Authenticated student: can post a valid question or reply", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  await assertSucceeds(
    aliceDb.collection("community_messages").doc("msg_1").set({
      id: "msg_1",
      userId: ALICE_UID,
      userName: "Alice KIPS",
      text: "How to memorize the 20 pairs of words easily?",
      likesCount: 0,
      createdAt: new Date(),
    })
  );
});

test("Authenticated student: cannot post with someone else's userId", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  await assertFails(
    aliceDb.collection("community_messages").doc("msg_fake").set({
      id: "msg_fake",
      userId: BOB_UID, // Spoofed user ID
      userName: "Impostor",
      text: "Fake post",
      likesCount: 0,
      createdAt: new Date(),
    })
  );
});

test("Authenticated student: can like/upvote a message", async () => {
  // Seed message by Alice
  await testEnv.withSecurityRulesDisabled(async (context) => {
    await context.firestore().collection("community_messages").doc("msg_1").set({
      id: "msg_1",
      userId: ALICE_UID,
      userName: "Alice KIPS",
      text: "Is Unit 4 translation coming in tomorrow's test?",
      likesCount: 0,
      createdAt: new Date(),
    });
  });

  // Bob upvotes Alice's message
  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  await assertSucceeds(
    bobDb.collection("community_messages").doc("msg_1").update({
      likesCount: 1,
    })
  );
});

test("Another student: cannot alter the original text or author of someone else's message", async () => {
  await testEnv.withSecurityRulesDisabled(async (context) => {
    await context.firestore().collection("community_messages").doc("msg_1").set({
      id: "msg_1",
      userId: ALICE_UID,
      userName: "Alice KIPS",
      text: "Original question",
      likesCount: 0,
      createdAt: new Date(),
    });
  });

  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  await assertFails(
    bobDb.collection("community_messages").doc("msg_1").update({
      text: "Hacked by Bob",
    })
  );
});
