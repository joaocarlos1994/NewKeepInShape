package br.com.newkeepinshape.db;




import android.content.Context;
import android.test.RenamingDelegatingContext;

import org.junit.After;
import org.junit.Before;

import android.support.test.InstrumentationRegistry;


import java.io.IOException;

import br.com.newkeepinshape.services.DatabaseHelperFactory;

/**
 * Created by root on 11/11/16.
 */
public class ConfigDBTestCase {

    private static final String BD_TEST = "DATABASE_TEST";

    private RenamingDelegatingContext context = null;

    @Before
    public void setUp() throws Exception {

        context = new RenamingDelegatingContext(getContext(), BD_TEST);
        DatabaseHelperFactory.getIntanceConnection(context);

    }

    @After
    public void setDown() {
        try {
            DatabaseHelperFactory.getIntanceConnection(context).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Context getContext() {
        return InstrumentationRegistry.getTargetContext();
    }

}
