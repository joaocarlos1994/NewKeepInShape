package br.com.newkeepinshape.services.exercicio;

import android.widget.EditText;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import br.com.newkeepinshape.domain.exercicio.Exercicio;
import br.com.newkeepinshape.domain.exercicio.ExercicioRepository;


/**
 * Created by root on 20/09/16.
 */
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    private ExercicioService(final ExercicioRepository exercicioDao){
        this.exercicioRepository = exercicioDao;
    }

    public static ExercicioService valueOfExercicioService(final ExercicioRepository exercicioDao) {
        return new ExercicioService(exercicioDao);
    }

    public void salvarOuAtualizarExercicio(final TextView idTxtView, final EditText nomeText, final EditText pesoText, final EditText quantidadeText, final EditText pontuacaoText) {

        final Integer id = Integer.parseInt(idTxtView.getText().toString());
        final String nome = nomeText.getText().toString();
        final double peso = Double.parseDouble(pesoText.getText().toString());
        final int quantidade = Integer.parseInt(quantidadeText.getText().toString());
        final double pontuacao = Double.parseDouble(pontuacaoText.getText().toString());

        final Exercicio exercicio = Exercicio.valueOfExercico(id, nome, peso, quantidade, pontuacao);


        if (id == null) {
            saveExercicio(exercicio);
        } else {
            atualizarExercicio(exercicio);
        }
    }

    private void atualizarExercicio(final Exercicio exercicio) {
        exercicioRepository.atualizarExercicio(exercicio);
    }

    public void deleteExercicio(final Integer id) {
        exercicioRepository.deleteExercicio(id);
    }

    public Collection<Exercicio> listAllExercicio() {
        return exercicioRepository.listAllExercicio();
    }

    public Exercicio returnExercicio(final Integer id) {
        return exercicioRepository.findExercicio(id);
    }

    public List<Map<String, String>> getExercicioCursor() {
        return  exercicioRepository.getExerciciosCursor((List)listAllExercicio());
    }

    private void saveExercicio(final Exercicio exercicio) {
        exercicioRepository.createExercicio(exercicio);
    }





}
