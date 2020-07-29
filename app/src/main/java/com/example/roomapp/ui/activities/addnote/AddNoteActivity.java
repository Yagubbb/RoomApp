package com.example.roomapp.ui.activities.addnote;

import androidx.appcompat.app.AppCompatActivity;

import android.media.effect.Effect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomapp.R;
import com.example.roomapp.local.AppDatabase;
import com.example.roomapp.local.dao.NotesDao;
import com.example.roomapp.model.entity.Note;

import java.util.UUID;

public class AddNoteActivity extends AppCompatActivity {

    private Button addNoteButton;
    private EditText editTextTitle;
    private EditText editTextDescription;

    private NotesDao notesDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initializeUI();
        setListeners();
        configureDatabase();
    }

    private void initializeUI(){
        addNoteButton = findViewById(R.id.button_add_note);
        editTextTitle= findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
    }
    private void configureDatabase(){
        AppDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext());
        this.notesDao = appDatabase.getNotesDao();
    }

    private void setListeners(){
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();

                if (validationOfInputs(title,description)){
                    Note newNote = new Note(UUID.randomUUID().toString(),title,description);
                    notesDao.insert(newNote);
                    Toast.makeText(getApplicationContext(), "Note added", Toast.LENGTH_SHORT).show();

                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Write title and description", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    private boolean validationOfInputs(String title,String description){
        boolean flag;
        if (title.isEmpty() || description.isEmpty()){
            flag = false;
        }
        else {
            flag = true;
        }
        return flag;

    }
}