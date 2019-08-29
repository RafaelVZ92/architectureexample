package com.razor.architectureexample.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.razor.architectureexample.MainActivity;
import com.razor.architectureexample.R;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.razor.architectureexample.view.activity.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.razor.architectureexample.view.activity.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.razor.architectureexample.view.activity.EXTRA_PRIORITY";
    private TextView editTextTitle;
    private TextView editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Uinizializer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(AddNoteActivity.this, "Please insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        setResult(RESULT_OK, data);
        finish();
    }

    private void Uinizializer() {
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);
        /**
         * Establece el icono cerrar en el ationbar
         * */
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add note");
    }
}
