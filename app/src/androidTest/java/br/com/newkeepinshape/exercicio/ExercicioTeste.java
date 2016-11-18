package br.com.newkeepinshape.exercicio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import br.com.newkeepinshape.db.ConfigDBTestCase;
import br.com.newkeepinshape.domain.exercicio.Exercicio;
import br.com.newkeepinshape.domain.exercicio.ExercicioRepository;
import br.com.newkeepinshape.infrastructure.persist.exercicio.ExercicioDaoIml;

/**
 * Created by root on 11/11/16.
 */
public class ExercicioTeste extends ConfigDBTestCase {

    private ExercicioRepository exercicioRepository;
    private final List<Exercicio> exerciciosList = new ArrayList<>(Arrays.asList(
            Exercicio.valueOfExercico(null, "Supino A", 30, 3,100),
            Exercicio.valueOfExercico(null, "Supino B", 35, 3, 110),
            Exercicio.valueOfExercico(null, "Supino C", 40, 3, 1120)));


    @Before
    public void config() throws SQLException {
        exercicioRepository = new ExercicioDaoIml(getContext());
    }

    @Test
    public void salvarExercicio() throws SQLException {

        exercicioRepository.createExercicio(exerciciosList.get(0));

        final Exercicio exercicioActual = exercicioRepository.findExercicio(Integer.valueOf(1));

        assertExercicio(exerciciosList.get(0), exercicioActual);
    }

    @Test
    public void removerExercicio() throws SQLException {

        exercicioRepository.createExercicio(exerciciosList.get(0));
        assertExercicio(exerciciosList.get(0), exercicioRepository.findExercicio(Integer.valueOf(1)));
        Assert.assertEquals(0, 1, exercicioRepository.deleteExercicio(Integer.valueOf(1)));
        Assert.assertEquals(null, exercicioRepository.findExercicio(Integer.valueOf(1)));

    }

    @Test
    public void listaExercicio() throws SQLException {

        final List<Exercicio> exerciciosSave = new ArrayList<>();

        for (final Exercicio eachExercicio : exerciciosList) {
            exercicioRepository.createExercicio(eachExercicio);
        }

        exerciciosSave.addAll(exercicioRepository.listAllExercicio());

        assertExercicios(exerciciosList, exerciciosSave);

    }

    private void assertExercicio(final Exercicio expected, final Exercicio actual) {
        Assert.assertEquals(expected.getNome(), actual.getNome());
        Assert.assertEquals(0, expected.getPeso(), actual.getPeso());
        Assert.assertEquals(0, expected.getPontuacao(), actual.getPontuacao());
        Assert.assertEquals(0, expected.getPontuacao(), actual.getPontuacao());
    }

    private void assertExercicios(final List<Exercicio> expected, final List<Exercicio> actual) {
        Assert.assertEquals(0, expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertExercicio(expected.get(i), actual.get(i));
        }
    }


}
