package br.com.newkeepinshape.services;

import android.content.Context;

import com.j256.ormlite.support.ConnectionSource;

import br.com.newkeepinshape.infrastructure.persist.DatabaseHelper;


/**
 * Created by root on 03/10/16.
 */
public class DatabaseHelperFactory {

    private static ConnectionSource connectionSource = null;
    private static DatabaseHelper databaseHelper = null;

    public static ConnectionSource getIntanceConnection(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context);
            return databaseHelper.getConnectionSource();
        }
        return databaseHelper.getConnectionSource();
    }

}
