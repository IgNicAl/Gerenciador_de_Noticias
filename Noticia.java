import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Noticia {

    private int id;
    private String titulo;
    private String autor;
    private String conteudo;
    private String categoria;
    private LocalDate dataPublicacao;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Noticia(int id, String titulo, String autor, String conteudo, String categoria, LocalDate dataPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.conteudo = conteudo;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public String getDataPublicacaoFormatada() {
        return dataPublicacao.format(DATE_FORMATTER);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public String toString() {
        return "• " + this.titulo;
    }
}

