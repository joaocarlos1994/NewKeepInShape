package br.com.newkeepinshape.treino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import br.com.newkeepinshape.db.ConfigDBTestCase;
import br.com.newkeepinshape.domain.exercicio.Exercicio;
import br.com.newkeepinshape.domain.treino.Treino;
import br.com.newkeepinshape.domain.treino.TreinoRepository;
import br.com.newkeepinshape.infrastructure.persist.treino.TreinoDaoIml;
import br.com.newkeepinshape.util.DiaSemana;

/**
 * Created by root on 16/11/16.
 */
@RunWith(JUnit4.class)
public class TreinoTeste extends ConfigDBTestCase {

    private TreinoRepository treinoRepository;
    private final List<Treino> treinosData = new ArrayList<>();


    @Before
    public void config() throws SQLException {
        treinoRepository = new TreinoDaoIml(getContext());

        final Treino treinoPeito = Treino.valeOfTreino("Costas", DiaSemana.SEGUNDA);
        treinoPeito.adicionarExercicio(Exercicio.valueOfExercico(null, "Peito 1", 30, 3,100));
        treinoPeito.adicionarExercicio(Exercicio.valueOfExercico(null, "Peito 2", 30, 3,100));

        final Treino treinoCosta = Treino.valeOfTreino("Costas", DiaSemana.SEGUNDA);
        treinoPeito.adicionarExercicio(Exercicio.valueOfExercico(null, "Costas 1", 30, 3,100));
        treinoPeito.adicionarExercicio(Exercicio.valueOfExercico(null, "Costas 2", 30, 3,100));

        treinosData.add(treinoPeito);
        treinosData.add(treinoCosta);

    }

    @Test
    public void testSalvarTreino() {

        final Treino treino = treinosData.get(0);
        treinoRepository.createTreino(treino);

        assertTreino(treino, treinoRepository.findTreino(Integer.valueOf(1)));
    }

/*    @Test
    public void testDeletarTreino() {
    }

    @Test
    public void testAtualizarTreino() {
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
        Assert.assertEquals(expected.getExercicios().size(), actual.getExercicios().size());

    }

}
