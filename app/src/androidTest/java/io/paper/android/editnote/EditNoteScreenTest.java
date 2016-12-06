package io.paper.android.editnote;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.paper.android.PaperApp;
import io.paper.android.R;
import io.paper.android.notes.Note;
import io.paper.android.notes.NotesRepository;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EditNoteScreenTest {
    private static final Long NOTE_ID = 1L;
    private static final String NOTE_TITLE = "Fancy note title";
    private static final String NOTE_DESCRIPTION = "Fancy note description";

    @Rule
    public ActivityTestRule<EditNoteActivity> editNoteActivityRule =
            new ActivityTestRule<>(EditNoteActivity.class, true, false);

    @Before
    public void setup() {
        // add note to fake repository synchronously
        NotesRepository fakeNotesRepository = PaperApp.getAppComponent(
                InstrumentationRegistry.getTargetContext()).notesRepository();
        fakeNotesRepository.add(Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .description(NOTE_DESCRIPTION)
                .build())
                .toBlocking()
                .subscribe();

        // start activity
        Intent intent = new Intent();
        intent.putExtra(EditNoteActivity.ARG_NOTE_ID, NOTE_ID);
        editNoteActivityRule.launchActivity(intent);
    }

    @Test
    public void noteDetails_displayedInUi() {
        // Check that the note title are description are displayed
        onView(withId(R.id.edittext_note_title)).check(matches(withText(NOTE_TITLE)));
        onView(withId(R.id.edittext_note_description)).check(matches(withText(NOTE_DESCRIPTION)));
    }
}