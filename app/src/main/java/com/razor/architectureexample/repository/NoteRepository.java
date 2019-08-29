package com.razor.architectureexample.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.razor.architectureexample.dao.NoteDao;
import com.razor.architectureexample.database.NoteDatabase;
import com.razor.architectureexample.entity.Note;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * This class is to call the Room settings.
 * <p>
 * To make use of this class we need to instantiate the DAO interface and instantiate the live data class,
 * then we will create the methods we need.
 * <p>
 */

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    /**
     * CREATE CONSTRUCTOR
     * <p>
     * add the data to the constructor
     * <p>
     */
    public NoteRepository(Application application) {
        /**
         * instanciamos nuestra clase abstracta y agregamos el application
         */
        NoteDatabase database = NoteDatabase.getInstance(application);
        /**
         * inicializamos nuestra interfaz Dao apartir de la instancia de nuestra clase abstracta
         */
        noteDao = database.noteDao();
        /**
         * Accedemos al metodo implementado apartir de nuestro DAO
         */
        allNotes = noteDao.getAllNotes();
    }

    /**
     * Replicamos los metodos que se encuentran en nuestra interfaz DAO
     */

    public void insert(Note note) {
        new InsertNoteAsyntask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyntask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyntask(noteDao).execute(note);
    }

    public void delleteAllNotes() {
        new DeleteAllNoteAsyntask(noteDao).execute();
    }

    public LiveData <List<Note>> getAllNotes(){
        return allNotes;
    }
    /**
     * Creamos asyntask para que los metodos agantareas en segundo plano.
     * */
    private static class InsertNoteAsyntask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private InsertNoteAsyntask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyntask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private UpdateNoteAsyntask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyntask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private DeleteNoteAsyntask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyntask extends AsyncTask<Void, Void, Void>{

        private NoteDao noteDao;

        private DeleteAllNoteAsyntask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
