package br.com.newkeepinshape.infrastructure.persist;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.com.newkeepinshape.domain.exercicio.Exercicio;
import br.com.newkeepinshape.domain.treino.Treino;


/**
 * Created by root on 21/09/16.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String NAME_BD = "keep-in-shape.db";
    private static final int  VERSION_BD = 2;


    public DatabaseHelper(final Context context) {
        super(context, NAME_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase dataBase, ConnectionSource connSource) {
        try {
            TableUtils.createTable(connSource, Exercicio.class);
            TableUtils.createTable(connSource, Treino.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase dataBase, ConnectionSource connSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connSource, Exercicio.class, true);
            onCreate(dataBase, connSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
