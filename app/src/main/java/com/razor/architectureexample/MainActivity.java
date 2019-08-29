package com.razor.architectureexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.razor.architectureexample.ViewModel.NoteViewModel;
import com.razor.architectureexample.entity.Note;
import com.razor.architectureexample.view.activity.AddNoteActivity;
import com.razor.architectureexample.view.adapter.NoteAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton actionButtonAddnote = findViewById(R.id.fab_addnote);
        actionButtonAddnote.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
        });

        RecyclerView recyclerView = findViewById(R.id.notes_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        NoteAdapter noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteAdapter.setNotes(notes);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            String priority = String.valueOf(data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1));

            Note note = new Note(title, description, priority);
            noteViewModel.insert(note);
            Toast.makeText(MainActivity.this, "Note saved", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
