package br.com.newkeepinshape.treino;

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
import br.com.newkeepinshape.domain.treino.Treino;
import br.com.newkeepinshape.domain.treino.TreinoRepository;
import br.com.newkeepinshape.exercicio.ExercicioTeste;
import br.com.newkeepinshape.infrastructure.persist.exercicio.ExercicioDaoIml;
import br.com.newkeepinshape.infrastructure.persist.treino.TreinoDaoIml;
import br.com.newkeepinshape.util.DiaSemana;

/**
 * Created by root on 16/11/16.
 */
@RunWith(JUnit4.class)
public class TreinoTeste extends ConfigDBTestCase {

    private TreinoRepository treinoRepository;
    private ExercicioRepository exercicioRepository;
    private final List<Exercicio> exerciciosData = new ArrayList<>(
                                                    Arrays.asList(Exercicio.valueOfExercico(null, "Peito 1", 30, 3,100),
                                                    Exercicio.valueOfExercico(null, "Peito 2", 30, 3,100),
                                                    Exercicio.valueOfExercico(null, "Costas 1", 30, 3,100),
                                                    Exercicio.valueOfExercico(null, "Costas 2", 30, 3,100)));
    private final List<Treino> treinosData = new ArrayList<>();


    @Before
    public void config() throws SQLException {

        treinoRepository = new TreinoDaoIml(getContext());
        exercicioRepository = new ExercicioDaoIml(getContext());


        for (final Exercicio eachExercicio : exerciciosData) {
            exercicioRepository.createExercicio(eachExercicio);
        }


        final Treino treinoPeito = Treino.valeOfTreino(null, "Peito", DiaSemana.SEGUNDA);

        treinoPeito.adicionarExercicio(exercicioRepository.findExercicio(Integer.valueOf(1)));
        treinoPeito.adicionarExercicio(exercicioRepository.findExercicio(Integer.valueOf(2)));

        final Treino treinoCosta = Treino.valeOfTreino(null, "Costas", DiaSemana.TERÇA);

        treinoCosta.adicionarExercicio(exercicioRepository.findExercicio(Integer.valueOf(3)));
        treinoCosta.adicionarExercicio(exercicioRepository.findExercicio(Integer.valueOf(4)));

        treinosData.add(treinoPeito);
        treinosData.add(treinoCosta);

    }

    @Test
    public void salvarTreino() {

        final Treino treino = treinosData.get(0);
        final Treino treino2 = treinosData.get(1);

        treinoRepository.createTreino(treino);
        treinoRepository.createTreino(treino2);


        assertTreino(treino, treinoRepository.findTreino(Integer.valueOf(1)));
        assertTreino(treino2, treinoRepository.findTreino(Integer.valueOf(2)));
    }

    @Test
    public void atualizarTreino() {

        final Treino treino = treinoRepository.findTreino(Integer.valueOf(1));
        final Treino treinoExpected = Treino.valeOfTreino(treino.get_id(), "Treino Atualizado", DiaSemana.DOMINGO);

        for (final Exercicio eachExercicio: treino.getExercicios()) {
            treinoExpected.adicionarExercicio(eachExercicio);
        }

        treinoExpected.adicionarExercicio(exercicioRepository.findExercicio(Integer.valueOf(3)));
        treinoExpected.adicionarExercicio(exercicioRepository.findExercicio(Integer.valueOf(4)));

        treinoRepository.atualizarTreino(treinoExpected);

        final Treino treinoActual = treinoRepository.findTreino(Integer.valueOf(1));

        assertTreino(treinoExpected, treinoActual);
    }

    @Test
    public void listaTodosTreinos() {

        final List<Treino> listActual = treinoRepository.listAllTreino();
        assertTreinos(treinosData, listActual);

    }

    /*

    @Test
    public void deletarTreino() {

        final int i = treinoRepository.deleteTreino(Integer.valueOf(1));
        Assert.assertEquals(1, i, 0);
        Assert.assertNull(treinoRepository.findTreino(Integer.valueOf(1)));

    }


    @Test
    public void testProcurarTreino() {
    }

    @Test
    public void testTodosTreinos() {

    }*/

    private void assertTreino(final Treino expected, final Treino actual) {
        Assert.assertEquals(expected.getNome(), actual.getNome());
        Assert.assertEquals(expected.getDia().toString(), actual.getDia().toString());
        Assert.assertEquals(expected.getPontuacaoMaxima(), actual.getPontuacaoMaxima(), 0);
        Assert.assertEquals(expected.getExercicios().size(), actual.getExercicios().size(), 0);

        final List<Exercicio> exerciciosExpected = new ArrayList<>(expected.getExercicios());
        final List<Exercicio> exerciciosActual = new ArrayList<>(actual.getExercicios());

        ExercicioTeste.assertExercicios(exerciciosExpected, exerciciosActual);
    }

    private void assertTreinos(final List<Treino> expected, final List<Treino> actual) {
        Assert.assertEquals(expected.size(), actual.size(), 0);
        for (int i = 0; i < actual.size(); i++) {
            assertTreino(expected.get(i), actual.get(i));
        }
    }

}
