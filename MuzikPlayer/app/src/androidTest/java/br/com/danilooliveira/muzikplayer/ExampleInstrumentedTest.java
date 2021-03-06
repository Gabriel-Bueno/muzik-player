package br.com.danilooliveira.muzikplayer;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FilenameFilter;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("br.com.danilooliveira.muzikplayer", appContext.getPackageName());
    }

    @Test
    public void getAudioFiles() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] cursor_columns = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC + "=1";

        System.out.println("Iniciando os testes...");
        Cursor cursor = appContext.getContentResolver().query(uri, cursor_columns, selection, null, null);
        while (cursor.moveToNext()) {
            System.out.println(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
        }
        cursor.close();

        assertTrue(cursor.isClosed());
    }
}
