package br.com.newkeepinshape.db;


import android.test.InstrumentationTestCase;
import android.test.RenamingDelegatingContext;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;

import br.com.newkeepinshape.services.DatabaseHelperFactory;

/**
 * Created by root on 11/11/16.
 */

public class ConfigDBTestCase extends InstrumentationTestCase {

    private static final String BD_TEST = "DATABASE_TEST";
    private RenamingDelegatingContext context = null;

    @Before
    public void setUp() throws Exception {

        context = new RenamingDelegatingContext(getInstrumentation().getTargetContext(), BD_TEST);

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

    public RenamingDelegatingContext getContext() {
        return context;
    }

}
