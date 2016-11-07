package br.com.newkeepinshape.domain.exercicio;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.newkeepinshape.domain.treino.Treino;

/**
 * Created by root on 05/10/16.
 */
@DatabaseTable(tableName = "Exercicio")
public class Exercicio {
    @DatabaseField(generatedId = true)
    private Integer _id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private double peso;
    @DatabaseField
    private int quantidade;
    @DatabaseField
    private double pontuacao;
    @DatabaseField(foreign = true)
    private Treino treino;

    private Exercicio(final String nome, final double peso, final int quantidade, final double pontuacao){
        this.nome = nome;
        this.peso = peso;
        this.quantidade = quantidade;
        this.pontuacao = pontuacao;
    }

    public static Exercicio valueOfExercico(final String nome, final double peso, final int quantidade, final double pontuacao){
        return new Exercicio(nome, peso, quantidade, pontuacao);
    }

    public Exercicio() {
    }

    public Integer get_id() {
        return _id;
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void adicionarTreino(final Treino treino){
        this.treino = treino;
    }
}
