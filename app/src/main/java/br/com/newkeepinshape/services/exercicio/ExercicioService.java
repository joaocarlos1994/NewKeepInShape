package br.com.newkeepinshape.services.exercicio;

import android.widget.EditText;

import java.sql.SQLException;
import java.util.Collection;

import br.com.newkeepinshape.domain.exercicio.Exercicio;
import br.com.newkeepinshape.domain.exercicio.ExercicioRepository;


/**
 * Created by root on 20/09/16.
 */
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;
    private Exercicio exercicio;

    private ExercicioService(final ExercicioRepository exercicioDao){
        this.exercicioRepository = exercicioDao;
    }

    public static ExercicioService valueOfExercicioService(final ExercicioRepository exercicioDao) {
        return new ExercicioService(exercicioDao);
    }

    public void saveExercicio(final EditText nomeText, final EditText pesoText, final EditText quantidadeText, final EditText pontuacaoText) throws SQLException {

        String nome = nomeText.getText().toString();

        System.out.println(pesoText.getText().toString());

        double peso = Double.parseDouble(pesoText.getText().toString());
        int quantidade = Integer.parseInt(quantidadeText.getText().toString());
        double pontuacao = Double.parseDouble(pontuacaoText.getText().toString());

        exercicio = Exercicio.valueOfExercico(nome, peso, quantidade, pontuacao);
        saveExercicio(exercicio);
    }

    public void deleteExercicio(final Integer id) throws SQLException {
        exercicioRepository.deleteExercicio(id);
    }

    public Collection<Exercicio> listAllExercicio() throws SQLException {
        return exercicioRepository.listAllExercicio();
    }

    public Exercicio returnExercicio(final Integer id) throws SQLException {
        return exercicioRepository.findExercicio(id);
    }

    private void saveExercicio(final Exercicio exercicio) throws SQLException {
        exercicioRepository.createExercicio(exercicio);
    }





}
