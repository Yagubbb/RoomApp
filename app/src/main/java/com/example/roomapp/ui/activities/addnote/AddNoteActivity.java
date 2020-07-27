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
import com.example.roomapp.model.entity.Note;

import java.util.UUID;

public class AddNoteActivity extends AppCompatActivity {

    private Button addNoteButton;
    private EditText editTextTitle;
    private EditText editTextDescription;

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initializeUI();
        setListeners();
    }

    private void initializeUI(){
        addNoteButton = findViewById(R.id.button_add_note);
        editTextTitle= findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        appDatabase = AppDatabase.getDatabase(getApplicationContext());
    }

    private void setListeners(){
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                if (title.isEmpty() && description.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Write title and description", Toast.LENGTH_SHORT).show();
                }
                else if (title.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Write title", Toast.LENGTH_SHORT).show();
                }
                else if (description.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Write description", Toast.LENGTH_SHORT).show();
                }
                else{
                    Note newNote = new Note(UUID.randomUUID().toString(),title,description);
                    finish();
                }

            }
        });
    }
}