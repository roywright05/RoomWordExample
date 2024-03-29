package com.example.roomwordsample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static volatile WordRoomDatabase INSTANCE;

    public abstract WordDao wordDao();

    static WordRoomDatabase getDatabase(final Context context){

        if(INSTANCE == null){

            synchronized (WordRoomDatabase.class){

                if(INSTANCE == null){

                    //create database here

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;

    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase database){

            super.onOpen(database);
            new PopulateDbAsync(INSTANCE).execute();

        }

            };

    private static class PopulateDbAsync
            extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabase database){

            mDao = database.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("Silicon");
            mDao.insert(word);
            word = new Word("Valley!");
            mDao.insert(word);
            return null;
        }
    }


}
