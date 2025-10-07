import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GerenciadorDeNoticiasSWT {

    private static List<Noticia> todasAsNoticias = new ArrayList<>();
    private static Table newsTable;
    private static Text searchField;
    private static Combo categoryFilter;
    private static Button editButton, deleteButton;
    private static Label totalNewsLabel, statusLabel;
    private static Display display;

    public static void main(String[] args) {
        display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Gerenciador de Notícias (SWT)");
        shell.setSize(850, 600);

        GridLayout mainLayout = new GridLayout(1, false);
        shell.setLayout(mainLayout);

        Group searchGroup = new Group(shell, SWT.NONE);
        searchGroup.setText("Buscar e Filtrar");
        searchGroup.setLayout(new GridLayout(6, false));
        searchGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        new Label(searchGroup, SWT.NONE).setText("Título:");
        searchField = new Text(searchGroup, SWT.BORDER);
        searchField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        new Label(searchGroup, SWT.NONE).setText("Categoria:");
        String[] categories = {"Todas", "Tecnologia", "Esportes", "Política", "Economia", "Saúde"};
        categoryFilter = new Combo(searchGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
        categoryFilter.setItems(categories);
        categoryFilter.select(0);

        Button searchButton = new Button(searchGroup, SWT.PUSH);
        searchButton.setText("Buscar");
        searchButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

        newsTable = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL);
        newsTable.setHeaderVisible(true);
        newsTable.setLinesVisible(true);
        newsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        String[] titles = {"ID", "Título", "Autor", "Categoria", "Data de Publicação"};
        int[] widths = {40, 250, 150, 100, 150};
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(newsTable, SWT.NONE);
            column.setText(titles[i]);
            column.setWidth(widths[i]);
        }

        Composite bottomComposite = new Composite(shell, SWT.NONE);
        bottomComposite.setLayout(new GridLayout(2, false));
        bottomComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Group actionGroup = new Group(bottomComposite, SWT.NONE);
        actionGroup.setLayout(new GridLayout(4, false));
        actionGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Button addButton = new Button(actionGroup, SWT.PUSH);
        addButton.setText("[Adicionar]");
        editButton = new Button(actionGroup, SWT.PUSH);
        editButton.setText("[Editar]");
        deleteButton = new Button(actionGroup, SWT.PUSH);
        deleteButton.setText("[Excluir]");
        Button clearButton = new Button(actionGroup, SWT.PUSH);
        clearButton.setText("[Limpar Filtros]");

        Group statusGroup = new Group(bottomComposite, SWT.NONE);
        statusGroup.setLayout(new GridLayout(1, false));
        statusGroup.setLayoutData(new GridData(SWT.END, SWT.CENTER, false, false));
        totalNewsLabel = new Label(statusGroup, SWT.NONE);
        statusLabel = new Label(statusGroup, SWT.NONE);

        carregarNoticiasIniciais();
        atualizarTabela("");
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        atualizarStatus("Sistema pronto", false);

        searchButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                atualizarTabela(searchField.getText());
            }
        });

        clearButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                searchField.setText("");
                categoryFilter.select(0);
                atualizarTabela("");
            }
        });

        categoryFilter.addSelectionListener(new SelectionAdapter() {
             @Override
            public void widgetSelected(SelectionEvent e) {
                atualizarTabela(searchField.getText());
            }
        });

        newsTable.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean isSelected = newsTable.getSelectionCount() > 0;
                editButton.setEnabled(isSelected);
                deleteButton.setEnabled(isSelected);
            }
        });

        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                mostrarDialogoNoticia(shell, null);
            }
        });

        editButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (newsTable.getSelectionCount() > 0) {
                    TableItem selectedItem = newsTable.getSelection()[0];
                    Noticia noticia = (Noticia) selectedItem.getData();
                    mostrarDialogoNoticia(shell, noticia);
                }
            }
        });

        deleteButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                excluirNoticia(shell);
            }
        });

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    private static void carregarNoticiasIniciais() {
        todasAsNoticias.add(new Noticia(5, "‘Supernatural’ pode voltar em 2025?", "Igor Alencar", "A palavra-chave para um possível retorno de Supernatural é propósito...", "Economia", LocalDate.of(2025, 10, 5)));
        todasAsNoticias.add(new Noticia(1, "IA avança na criação de código", "Maximus Bragança", "Nova IA consegue gerar aplicações complexas...", "Tecnologia", LocalDate.of(2025, 10, 1)));
        todasAsNoticias.add(new Noticia(2, "Brasil vence Copa do Mundo", "Erivan Junior", "Seleção brasileira conquista o título...", "Esportes", LocalDate.of(2025, 9, 28)));
        todasAsNoticias.add(new Noticia(3, "Nova lei ambiental é aprovada", "Maria Vitória", "Congresso aprova nova legislação...", "Política", LocalDate.of(2025, 9, 25)));
        todasAsNoticias.add(new Noticia(4, "Descoberta cura para doença rara", "Eduardo Martiniano", "Cientistas anunciam avanço significativo...", "Saúde", LocalDate.of(2025, 9, 22)));
        todasAsNoticias.add(new Noticia(5, "Economia cresce 5% no trimestre", "Daniela Sales", "PIB surpreende e mostra forte recuperação...", "Economia", LocalDate.of(2025, 9, 20)));
        todasAsNoticias.add(new Noticia(5, "Recife ganha novo espaço cultural com propósito social", "Saulo Rodrigues", "O Centro Cultural Instituto Marcos Hacker de Melo...", "Economia", LocalDate.of(2025, 9, 18)));
    }

    private static void atualizarTabela(String filtroTitulo) {
        newsTable.removeAll();
        String categoriaSelecionada = categoryFilter.getText();

        List<Noticia> noticiasFiltradas = todasAsNoticias.stream()
            .filter(n -> n.getTitulo().toLowerCase().contains(filtroTitulo.toLowerCase()))
            .filter(n -> "Todas".equals(categoriaSelecionada) || n.getCategoria().equals(categoriaSelecionada))
            .collect(Collectors.toList());

        for (Noticia noticia : noticiasFiltradas) {
            TableItem item = new TableItem(newsTable, SWT.NONE);
            item.setText(new String[]{
                String.valueOf(noticia.getId()),
                noticia.getTitulo(),
                noticia.getAutor(),
                noticia.getCategoria(),
                noticia.getDataPublicacaoFormatada()
            });
            item.setData(noticia);
        }

        totalNewsLabel.setText("Total de notícias: " + todasAsNoticias.size());
        totalNewsLabel.getParent().layout();
    }

    private static void mostrarDialogoNoticia(Shell parent, Noticia noticia) {
        Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setText(noticia == null ? "Adicionar Notícia" : "Editar Notícia");
        dialog.setLayout(new GridLayout(2, false));

        new Label(dialog, SWT.NONE).setText("Título:");
        Text tituloText = new Text(dialog, SWT.BORDER);
        tituloText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        if (noticia != null) tituloText.setText(noticia.getTitulo());

        new Label(dialog, SWT.NONE).setText("Autor:");
        Text autorText = new Text(dialog, SWT.BORDER);
        autorText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        if (noticia != null) autorText.setText(noticia.getAutor());

        new Label(dialog, SWT.NONE).setText("Categoria:");
        Combo categoriaCombo = new Combo(dialog, SWT.DROP_DOWN | SWT.READ_ONLY);
        categoriaCombo.setItems(new String[]{"Tecnologia", "Esportes", "Política", "Economia", "Saúde"});
        if (noticia != null) {
            categoriaCombo.setText(noticia.getCategoria());
        } else {
            categoriaCombo.select(0);
        }

        new Label(dialog, SWT.NONE).setText("Conteúdo:");
        Text conteudoText = new Text(dialog, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.heightHint = 100;
        conteudoText.setLayoutData(gd);
        if (noticia != null) conteudoText.setText(noticia.getConteudo());

        Button okButton = new Button(dialog, SWT.PUSH);
        okButton.setText("Salvar");

        Button cancelButton = new Button(dialog, SWT.PUSH);
        cancelButton.setText("Cancelar");

        okButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (noticia == null) {
                    int newId = todasAsNoticias.stream().mapToInt(Noticia::getId).max().orElse(0) + 1;
                    Noticia novaNoticia = new Noticia(newId, tituloText.getText(), autorText.getText(), conteudoText.getText(), categoriaCombo.getText(), LocalDate.now());
                    todasAsNoticias.add(novaNoticia);
                    atualizarStatus("Notícia adicionada com sucesso!", true);
                } else {
                    noticia.setTitulo(tituloText.getText());
                    noticia.setAutor(autorText.getText());
                    noticia.setCategoria(categoriaCombo.getText());
                    noticia.setConteudo(conteudoText.getText());
                    atualizarStatus("Notícia atualizada com sucesso!", true);
                }
                atualizarTabela(searchField.getText());
                dialog.close();
            }
        });

        cancelButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                dialog.close();
            }
        });

        dialog.pack();
        dialog.open();
    }

    private static void excluirNoticia(Shell parent) {
        if (newsTable.getSelectionCount() > 0) {
            MessageBox messageBox = new MessageBox(parent, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
            messageBox.setText("Confirmar Exclusão");
            messageBox.setMessage("Tem certeza que deseja excluir esta notícia?");
            int response = messageBox.open();

            if (response == SWT.YES) {
                TableItem selectedItem = newsTable.getSelection()[0];
                Noticia noticiaParaExcluir = (Noticia) selectedItem.getData();
                todasAsNoticias.remove(noticiaParaExcluir);
                atualizarTabela(searchField.getText());
                atualizarStatus("Notícia excluída com sucesso!", true);
            }
        }
    }

    private static void atualizarStatus(String message, boolean temporario) {
        statusLabel.setText("Status: " + message);
        statusLabel.getParent().layout();

        if (temporario) {
            display.timerExec(3000, () -> {
                if (!statusLabel.isDisposed()) {
                    statusLabel.setText("Status: Sistema pronto");
                    statusLabel.getParent().layout();
                }
            });
        }
    }
}
