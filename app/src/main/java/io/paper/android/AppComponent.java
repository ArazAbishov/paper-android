package io.paper.android;

import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Singleton;

import dagger.Component;
import io.paper.android.editnote.EditNoteComponent;
import io.paper.android.editnote.EditNoteModule;
import io.paper.android.notes.NotesFragment;
import io.paper.android.notes.NotesRepository;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(NotesFragment notesFragment);

    EditNoteComponent plus(EditNoteModule editNoteModule);

    // dependencies exposed for testing purposes
    SqlBrite sqlBrite();

    SQLiteOpenHelper sqliteOpenHelper();

    NotesRepository notesRepository();
}
